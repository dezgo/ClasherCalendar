package com.derekgillett.clashercalendar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.util.LongSparseArray;
import android.util.Log;

public class Player {
	//region class vars
	private SQLiteDatabase moDB;
	private static final String TAG = "ClasherPlayer";
	
	private boolean mbRepopulateA = true;	// flag indicating whether to repopulate aggregate array
	private long mnPlayerID;
	private String msVillageName;
	private int mnTHLevel = 1;
	private LongSparseArray<PlayerElement> moPlayerElements = new LongSparseArray<PlayerElement>();

	// aggregated player elements where items with same element id and level are grouped together
	//private LongSparseArray<ElementA> moElementsA1 = new LongSparseArray<ElementA>();
	// and a matching treeset for sortingO
	private TreeMap<Long, ElementA> moElementsA = new TreeMap<Long, ElementA>();
	//private Iterator<Entry<Long, ElementA>> moElementsAIterator;
	private ArrayList<ElementA> moElementsASorted;
	
	private int mnIndex = 0;
	private int mnIndexA = 0;
	
	//region sorting local vars
	private int mnSortQty = 0;
	private int mnSortBuilding = 0;
	private int mnSortLevel = 0;
	private int mnSortMaxLevel = 0;
	private int mnSortCost = 0;
	private int mnSortBuildTime = 0;

	public int getSortQty() {
		return mnSortQty;
	}

	public int getSortBuilding() {
		return mnSortBuilding;
	}

	public int getSortLevel() {
		return mnSortLevel;
	}

	public int getSortMaxLevel() {
		return mnSortMaxLevel;
	}

	public int getSortCost() {
		return mnSortCost;
	}

	public int getSortBuildTime() {
		return mnSortBuildTime;
	}

	public int getSortArrowRes(int pnSort) {
		if (pnSort > 0)
			return R.drawable.down_arrow;
		else if (pnSort < 0)
			return R.drawable.up_arrow;
		else
			return 0;//R.drawable.unsorted;
	}
	
	//endregion
	//endregion
	
	//region Constructors
	public Player(SQLiteDatabase poDB, long pnPlayerID) {
		moDB = poDB;
		mnPlayerID = pnPlayerID;
		this.select();
		this.LoadExisting();
		Globals.INSTANCE.setPlayer(this);
	}
	
	public Player(SQLiteDatabase poDB, int pnTHLevel, String psVillageName) {
		moDB = poDB;
		mnTHLevel = pnTHLevel;
		msVillageName = psVillageName;
		this.insert();
		LoadDefaults();
		Globals.INSTANCE.setPlayer(this);
	}
	//endregion
	
	//region Getters
	public String getVillageName() {
		return this.msVillageName;
	}
	
	public int getTHLevel() {
		return mnTHLevel;
	}
	
	public long getid() {
		return this.mnPlayerID;
	}
	
	public PlayerElement getPlayerElement() {
		PlayerElement playerElement = null;
		if (mnIndex <= moPlayerElements.size() ) {
			playerElement = moPlayerElements.get(moPlayerElements.keyAt(mnIndex));
		}
		return playerElement;
	}
	
	// as per http://stackoverflow.com/questions/1066589/java-iterate-through-hashmap
	public ElementA getElementA() {
		ElementA oElementA = null;

		if (this.mnIndexA < this.moElementsASorted.size()) {
			oElementA = this.moElementsASorted.get(mnIndexA);
		}
		
		return oElementA;
	}
	//endregion
	
	public void incTHLevel() {
		mnTHLevel++;
		this.update();
		
		// now add in the new items
		THElements oTHElements = new THElements(moDB, mnTHLevel);
		THElements oTHElementsPrev = new THElements(moDB, mnTHLevel-1);
		oTHElements.moveToFirst();
		while (!oTHElements.isAfterLast()) {
			// get all th elements for this new th level
			THElement oTHElement = oTHElements.getTHElement();
			
			// start qty off at number of this item
			int qty = oTHElement.getQuantity();
			
			// now reduce it by the number of items there were at the previous th level
			THElement oTHElementPrev = oTHElementsPrev.getTHElement(oTHElement.getElement().getId());
			if (oTHElementPrev != null)
				qty = qty - oTHElementPrev.getQuantity();
			
			// now add that many of this new element
			for (int i=0; i<qty; i++)
				this.addPlayerElement(oTHElement.getElement(), 0);
			
			// NEXT!
			oTHElements.moveToNext();
		}
		this.mbRepopulateA = true;
	}
	
	public void forceRepopulate() {
		this.mbRepopulateA = true;
	}
	
	private void populateElementsA() {
		moElementsA.clear();
		for (int i=0; i<this.moPlayerElements.size(); i++) {
			PlayerElement oPlayerElement = moPlayerElements.get(moPlayerElements.keyAt(i));
			if (!oPlayerElement.getExclude()) {
				Element oElement = oPlayerElement.getElement();
				int nLevel = oPlayerElement.getLevel();
				
				long key;
				boolean bIsPlayerElement;
				if (oPlayerElement.getSecondsRemaining() > 0) {
					bIsPlayerElement = true;
					key = ElementA.getKey(oPlayerElement.getID(),null,0);
				} else {
					bIsPlayerElement = false;
					key = ElementA.getKey(0,oElement,nLevel);
				}
				ElementA oElementA = moElementsA.get(key);
				
				if (oElementA == null) {
					oElementA = new ElementA(oElement, nLevel, bIsPlayerElement, key);
					moElementsA.put(key, oElementA);
				} else
					oElementA.add(1);
			}
		}
		mbRepopulateA = false;
		moElementsASorted = new ArrayList<ElementA>(this.moElementsA.values());
		this.sortByBuilding();
	}
	
	public void incPlayerElementLevel(PlayerElement poPlayerElement, int pnIncrement) {
		junit.framework.Assert.assertTrue("increment can only be -1 or 1, not " + pnIncrement, pnIncrement == -1 || pnIncrement == 1);
/*		
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		junit.framework.Assert.assertTrue(
				"Player.incPlayerElementLevel should only be called from MainActivity, not " + stackTraceElements[0].getClassName(),
				stackTraceElements[0].getClassName() == "MainActivity");
	*/	
		// only increment if we're not already at max level
		// only decrement if we're at level > 0
		if (((pnIncrement ==  1) && (poPlayerElement.getLevel() < poPlayerElement.getElement().getMaxLevel(this.mnTHLevel)) ||
			((pnIncrement == -1) && (poPlayerElement.getLevel() > 0)))) {

			// for the standard array of player elements, just increment/decrement the level
			if (pnIncrement == 1)
				this.moPlayerElements.get(poPlayerElement.getID()).incLevel();
			else
				this.moPlayerElements.get(poPlayerElement.getID()).decLevel();
			mbRepopulateA = true;
		}
	}
	
	public void includeAll() {
		for (int i=0; i<moPlayerElements.size(); i++) {
			PlayerElement oPlayerElement = moPlayerElements.valueAt(i);
			oPlayerElement.setExclude(false);
		}
	}
	
	public void setExclude(ElementA poElementA, boolean pbExclude) {
		for (int i=0; i<moPlayerElements.size(); i++) {
			PlayerElement oPlayerElement = moPlayerElements.valueAt(i);
			if (poElementA.isPlayerElement() && oPlayerElement.getID() == poElementA.getKey() ||
				!poElementA.isPlayerElement() && oPlayerElement.getElement().getId() == poElementA.getElement().getId() && 
					oPlayerElement.getLevel() == poElementA.getLevel())
				oPlayerElement.setExclude(pbExclude);
		}
		
	}
	
	private void addPlayerElement(long pnPlayerElementID) {
		PlayerElement oPlayerElement = new PlayerElement(moDB, pnPlayerElementID, this);
		addPlayerElement(oPlayerElement);
	}
	
	private void addPlayerElement(Element poElement, int pnLevel) {
		PlayerElement oPlayerElement = new PlayerElement(moDB, this, poElement, pnLevel);
		addPlayerElement(oPlayerElement);
	}
	
	private void addPlayerElement(PlayerElement poPlayerElement) {
		// add to standard list of player elements
		moPlayerElements.put(poPlayerElement.getID(), poPlayerElement);
	}
	
	/*
	private long getKeyA(Element poElement, int pnLevel) {
		return poElement.getId() * 100 + pnLevel;
	}
	*/
	public PlayerElement getPlayerElement(long pnPlayerElementID) {
		return moPlayerElements.get(pnPlayerElementID);
	}
	
	public PlayerElement getPlayerElement(long pnElementID, int pnLevel) {
		PlayerElement oPlayerElement = null;
		boolean bFound = false;
		int i = 0;
		while (!bFound && i<moPlayerElements.size()) {
			oPlayerElement = moPlayerElements.get(moPlayerElements.keyAt(i));			
			bFound = (oPlayerElement.getElement().getId() == pnElementID && oPlayerElement.getLevel() == pnLevel);
			i++;
		}
		if (bFound)
			return oPlayerElement;
		else
			return null;
	}
	
	/*getElementA
	public ElementA getElementA(Element poElement, int pnLevel) {
		if (mbRepopulateA) this.populateElementsA();
		
		long key = getKeyA(poElement, pnLevel);
		return moElementsA.get(key);
	}
	*/
	
	//region Array Navigation
	public void moveToFirst() {
		this.mnIndex = 0;
	}
	
	public void moveToFirstA() {
		if (mbRepopulateA) this.populateElementsA();
		this.mnIndexA = 0;
	}
	
	public void moveToNext() {
		this.mnIndex++;
	}
	
	public void moveToNextA() {
		this.mnIndexA++;
	}
	
	public boolean isAfterLast() {
		return mnIndex >= this.moPlayerElements.size();
	}
	
	public boolean isAfterLastA() {
		return mnIndexA >= this.moElementsA.size();
	}
	//endregion
	
	public void setExclude(long key, boolean pbExclude) {
		PlayerElement playerElement = moPlayerElements.get(key);
		playerElement.setExclude(pbExclude);
	}
	
	private enum SortColumn {
		Qty, Building, Level, MaxLevel, Cost, BuildTime
	}
	// only sorting by one column, so reset all others when changing sort order
	private void applySort(SortColumn peSortColumn, int pnSort) {
		mnSortQty = 0;
		mnSortBuilding = 0;
		mnSortLevel = 0;
		mnSortMaxLevel = 0;
		mnSortCost = 0;
		mnSortBuildTime = 0;
		if (peSortColumn == SortColumn.Qty)
			mnSortQty = pnSort;
		else if (peSortColumn == SortColumn.Building)
			mnSortBuilding = pnSort;
		else if (peSortColumn == SortColumn.BuildTime)
			this.mnSortBuildTime = pnSort;
		else if (peSortColumn == SortColumn.Cost)
			this.mnSortCost = pnSort;
		else if (peSortColumn == SortColumn.Level)
			this.mnSortLevel = pnSort;
		else if (peSortColumn == SortColumn.MaxLevel)
			this.mnSortMaxLevel = pnSort;
	}
	
	// thanks to http://stackoverflow.com/questions/780541/how-to-sort-a-hashmap-in-java
	public void sortByBuilding() {
		if (this.mnSortBuilding == 0)
			applySort(SortColumn.Building, 1);
		else
			applySort(SortColumn.Building, -1 * mnSortBuilding);
		
		moElementsASorted = new ArrayList<ElementA>(this.moElementsA.values());
		Collections.sort(moElementsASorted, new Comparator<ElementA>() {
			@Override
			public int compare(ElementA arg0, ElementA arg1) {
				String name0 = arg0.getElement().getName();
				String name1 = arg1.getElement().getName();
				if (name0 != null && name1 != null)
					if (mnSortBuilding > 0)
						return name0.compareTo(name1);
					else
						return name1.compareTo(name0);
				else
					return 0;
			}
		});
	}
	
	public void sortByCost() {
		if (this.mnSortCost == 0)
			applySort(SortColumn.Cost, 1);
		else
			applySort(SortColumn.Cost, -1 * this.mnSortCost);

		moElementsASorted = new ArrayList<ElementA>(this.moElementsA.values());
		Collections.sort(moElementsASorted, new Comparator<ElementA>() {
			@Override
			public int compare(ElementA arg0, ElementA arg1) {
				Element oElement0 = arg0.getElement();
				if (oElement0 == null) { Log.d(TAG, "oElement0 is null!"); return 1; }
				Element oElement1 = arg1.getElement();
				if (oElement1 == null) { Log.d(TAG, "oElement1 is null!"); return 1; }
				
				ElementData oElementData0 = oElement0.getElementData(arg0.getLevel()+1);
				if (oElementData0 == null) { 
					Log.d(TAG, oElement0.getName() + ": oElementData0 is null for level " + arg0.getLevel()+1); 
					return 1; 
				}
				ElementData oElementData1 = oElement1.getElementData(arg1.getLevel()+1);
				if (oElementData1 == null) { 
					Log.d(TAG, oElement1.getName() + ": oElementData1 is null for level " + arg1.getLevel()+1);
					return 1; 
				}
				return mnSortCost * (oElementData0.getBuildCost() - oElementData1.getBuildCost());
			}
		});
	}
	
	public void sortByBuildTime() {
		if (this.mnSortBuildTime == 0)
			applySort(SortColumn.BuildTime, 1);
		else
			applySort(SortColumn.BuildTime, -1 * this.mnSortBuildTime);

		moElementsASorted = new ArrayList<ElementA>(this.moElementsA.values());
		Collections.sort(moElementsASorted, new Comparator<ElementA>() {
			@Override
			public int compare(ElementA arg0, ElementA arg1) {
				Element oElement0 = arg0.getElement();
				if (oElement0 == null) { Log.d(TAG, "oElement0 is null!"); return 1; }
				Element oElement1 = arg1.getElement();
				if (oElement1 == null) { Log.d(TAG, "oElement1 is null!"); return 1; }
				
				ElementData oElementData0 = oElement0.getElementData(arg0.getLevel()+1);
				if (oElementData0 == null) { 
					Log.d(TAG, oElement0.getName() + ": oElementData0 is null for level " + arg0.getLevel()+1); 
					return 1; 
				}
				ElementData oElementData1 = oElement1.getElementData(arg1.getLevel()+1);
				if (oElementData1 == null) { 
					Log.d(TAG, oElement1.getName() + ": oElementData1 is null for level " + arg1.getLevel()+1);
					return 1; 
				}
				return mnSortBuildTime * (oElementData0.getBuildTime() - oElementData1.getBuildTime());
			}
		});
	}
	
	public void sortByLevel() {
		if (this.mnSortLevel == 0)
			applySort(SortColumn.Level, 1);
		else
			applySort(SortColumn.Level, -1 * mnSortLevel);
		
		moElementsASorted = new ArrayList<ElementA>(this.moElementsA.values());
		Collections.sort(moElementsASorted, new Comparator<ElementA>() {
			@Override
			public int compare(ElementA arg0, ElementA arg1) {
				return mnSortLevel * (arg0.getLevel() - arg1.getLevel());
			}
		});
	}
	
	public void sortByQty() {
		if (this.mnSortQty == 0)
			applySort(SortColumn.Qty, 1);
		else
			applySort(SortColumn.Qty, -1 * mnSortQty);

		moElementsASorted = new ArrayList<ElementA>(this.moElementsA.values());
		Collections.sort(moElementsASorted, new Comparator<ElementA>() {
			@Override
			public int compare(ElementA arg0, ElementA arg1) {
				return mnSortQty * (arg0.getQuantity() - arg1.getQuantity());
			}
		});
	}
	
	public void sortByMaxLevel() {
		if (this.mnSortMaxLevel == 0)
			applySort(SortColumn.MaxLevel, 1);
		else
			applySort(SortColumn.MaxLevel, -1 * mnSortMaxLevel);

		moElementsASorted = new ArrayList<ElementA>(this.moElementsA.values());
		Collections.sort(moElementsASorted, new Comparator<ElementA>() {
			@Override
			public int compare(ElementA arg0, ElementA arg1) {
				Player oPlayer = Globals.INSTANCE.getPlayer();
				Element oElement0 = arg0.getElement();
				if (oElement0 == null) { Log.d("Player", "oElement0 is null!"); return 1; }
				Element oElement1 = arg1.getElement();
				if (oElement1 == null) { Log.d("Player", "oElement1 is null!"); return 1; }

				return mnSortMaxLevel * (oElement0.getMaxLevel(oPlayer.getTHLevel()) - oElement1.getMaxLevel(oPlayer.getTHLevel()));
			}
		});
	}
	
	// for a newly created player, add in the default buildings that come with the given TH level
	// for any new buildings, default to level 0
	// for townhall level 1, default all buildings to level 0
	private void LoadDefaults() {
		// array of elements for new town hall level
		THElements oTHElementsNew = new THElements(moDB, mnTHLevel);
		
		// array of elements for previous town hall level (if new townhall level >1)
        THElements oTHElementsOld = null;
        if (mnTHLevel > 1) oTHElementsOld = new THElements(moDB, mnTHLevel-1);
        
        // loop through new elements array
        for (int i=0; i<oTHElementsNew.size(); i++) {
        	// get new element
        	THElement thElement = oTHElementsNew.getTHElement();
        	
        	// get element at previous townhall level
        	THElement thElementOld = null;
        	if (oTHElementsOld != null) thElementOld = oTHElementsOld.getTHElement(thElement.getElement().getId());
        	
    		// for each element, add it assuming player has maxed out buildings at previous level
    		// for level 1, assume all buildings are level 0 (not built yet)
    		// EXCEPTION: make townhall level = mnTHLevel
        	for (int j=0; j<thElement.getQuantity(); j++) {
        		int nLevel;
        		Element oElement = thElement.getElement();
        		
        		// if this is the town hall, set it to current level
        		if (oElement.getName().equals(ClasherDBContract.TOWNHALL_NAME))
        			nLevel = mnTHLevel;
        		
        		// if this is a new element, make it level 0
        		else if (thElementOld == null)
        			nLevel = 0;
        		
        		// if there are more of this element now, make the new ones level 0
        		else if (mnTHLevel == 1 || j >= thElementOld.getQuantity())
        			nLevel = 0;
        		
        		// otherwise get the max level this element could be at the previous townhall level
        		else
        			nLevel = oElement.getMaxLevel(mnTHLevel-1);
        		
        		// and add the new element
        		this.addPlayerElement(oElement, nLevel);
        	}
        	oTHElementsNew.moveToNext();
        }
        
        // finally populate thet aggregate array and sort
        populateElementsA();
	}
	
	// for an existing player, load the buildings as saved in tblPlayerElement
	private void LoadExisting() {
		Cursor cursor = this.selectPlayerElements();
		if (cursor != null) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				long nPlayerElementID = cursor.getLong(0);
				if (nPlayerElementID != 0)
					this.addPlayerElement(nPlayerElementID);
				cursor.moveToNext();
			}
			cursor.close();
		}
	}
	
	public int getUpgradeTimeMax() {
		int nUpgradeTimeTotal = 0;
		
		for (int i=0; i<this.moPlayerElements.size(); i++) {
			long key = moPlayerElements.keyAt(i);
			PlayerElement oPlayerElement = moPlayerElements.get(key);
			int nUpgradeTime = oPlayerElement.getUpgradeTimeMax();
			nUpgradeTimeTotal = nUpgradeTimeTotal + nUpgradeTime;
			if (nUpgradeTime > 0 && Utils.DEBUG) Log.d("Player", "Upgrade time: " + oPlayerElement.getElement().getName() + " lvl " + oPlayerElement.getLevel() + 
					" " + nUpgradeTime);
		}
		return nUpgradeTimeTotal;
	}

	public int getUpgradeCostMax(long pnCostType) {
		int nUpgradeCost = 0;
		
		for (int i=0; i<this.moPlayerElements.size(); i++) {
			long key = moPlayerElements.keyAt(i);
			PlayerElement oPlayerElement = moPlayerElements.get(key);
			nUpgradeCost = nUpgradeCost + oPlayerElement.getUpgradeCostMax(pnCostType);
		}
		return nUpgradeCost;
	}
	
	public int size() {
		return moPlayerElements.size();
	}
	
	//region Database
	private void select() {
    	String[] columns = ClasherDBContract.ClasherPlayer.ALL_COLUMNS;
    	String selection = ClasherDBContract.ClasherPlayer.COLUMN_NAME_ID + " = ?";
		String[] selectionArgs = new String[] { String.valueOf(this.mnPlayerID) };
		Cursor cursor = moDB.query(
				ClasherDBContract.ClasherPlayer.TABLE_NAME, 
				columns, 
				selection, 
				selectionArgs, 
				null, null, null);
		
		if (cursor != null) {
			try {
				cursor.moveToFirst();
				int colIndex = cursor.getColumnIndexOrThrow(ClasherDBContract.ClasherPlayer.COLUMN_NAME_PLAYER_VILLAGE_NAME);
				this.msVillageName = cursor.getString(colIndex);
				colIndex = cursor.getColumnIndexOrThrow(ClasherDBContract.ClasherPlayer.COLUMN_NAME_TOWNHALL_LEVEL);
				this.mnTHLevel = cursor.getInt(colIndex);
			}
			catch (SQLiteException e) {
				Log.e(TAG, e.getMessage());
			}
			finally {
				cursor.close();
			}
		}
	}

	public void createTable() {
    	moDB.execSQL(ClasherDBContract.ClasherPlayer.SQL_DROP_TABLE);
    	moDB.execSQL(ClasherDBContract.ClasherPlayer.SQL_CREATE_TABLE);
    }
    
    private boolean insert() {
    	ContentValues values = new ContentValues();
    	values.put(ClasherDBContract.ClasherPlayer.COLUMN_NAME_PLAYER_VILLAGE_NAME, this.msVillageName);
    	values.put(ClasherDBContract.ClasherPlayer.COLUMN_NAME_TOWNHALL_LEVEL, this.mnTHLevel);

    	mnPlayerID = moDB.insert(ClasherDBContract.ClasherPlayer.TABLE_NAME, null, values);
		return mnPlayerID != 0;
    }

    private int update() {
    	ContentValues values = new ContentValues();
    	values.put(ClasherDBContract.ClasherPlayer.COLUMN_NAME_PLAYER_VILLAGE_NAME, this.msVillageName);
    	values.put(ClasherDBContract.ClasherPlayer.COLUMN_NAME_TOWNHALL_LEVEL, this.mnTHLevel);

    	String selection = "_id = ?";
		String[] selectionArgs = new String[] { String.valueOf(mnPlayerID) };

		return moDB.update(
    			ClasherDBContract.ClasherPlayer.TABLE_NAME, 
    			values, 
				selection, 
				selectionArgs); 
    }

    public int delete() {
    	String selection = "_id = ?";
		String[] selectionArgs = new String[] { String.valueOf(mnPlayerID) };
    	return moDB.delete(
    			ClasherDBContract.ClasherPlayer.TABLE_NAME, 
				selection, 
				selectionArgs); 
    }

    private Cursor selectPlayerElements() {
    	String[] columns = new String[] {"_id"};
    	String selection = ClasherDBContract.ClasherPlayerElement.COLUMN_NAME_PLAYER_ID + " = ?";
		String[] selectionArgs = new String[] { String.valueOf(this.mnPlayerID) };
		return moDB.query(
				ClasherDBContract.ClasherPlayerElement.TABLE_NAME, 
				columns, 
				selection, 
				selectionArgs, 
				null, null, null);        	
    }	
	//endregion
    
    //region Debugging
    private String pad(String item, int length) {
    	String rtn = item;
    	while (rtn.length() < length) {
    		rtn = rtn + " ";
    	}
    	return rtn;
    }
    
    public void showPlayerElements() {
    	for (int i=0; i<this.moPlayerElements.size(); i++) {
    		PlayerElement oPlayerElement = this.moPlayerElements.get(moPlayerElements.keyAt(i));
    		String village = oPlayerElement.getPlayer().getVillageName();
    		long id = oPlayerElement.getID();
    		String elementName = oPlayerElement.getElement().getName();
    		int level = oPlayerElement.getLevel();
    		
    		if (i==0) {
    			Log.v(TAG, "Village: " + village);
    			Log.v(TAG, pad("peid",6) + pad("element",20) + pad("level",5));
    		}
    		
			Log.v(TAG, pad(String.valueOf(id),6) + pad(elementName,20) + pad(String.valueOf(level),5));
    	}
    }
    //endregion

}
