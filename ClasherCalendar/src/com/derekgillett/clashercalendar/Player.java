package com.derekgillett.clashercalendar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.v4.util.LongSparseArray;
import android.util.Log;

public class Player {
	private static final String TAG = "Player.java";
	
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
	
	public Player(long pnPlayerID) {
		mnPlayerID = pnPlayerID;
		this.select();
		this.LoadExisting();
		Globals.INSTANCE.setPlayer(this);
	}
	
	public Player(int pnTHLevel, String psVillageName) {
		mnTHLevel = pnTHLevel;
		msVillageName = psVillageName;
		this.insert();
		LoadDefaults();
		Globals.INSTANCE.setPlayer(this);
	}
	
	public String getVillageName() {
		return this.msVillageName;
	}
	
	public int getTHLevel() {
		return mnTHLevel;
	}
	
	public void incTHLevel() {
		mnTHLevel++;
		this.update();
		
		// now add in the new items
		THElements oTHElements = new THElements(mnTHLevel);
		THElements oTHElementsPrev = new THElements(mnTHLevel-1);
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
		//this.sortByName(true);
		//this.sortByCost(true);
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
	
	public long getid() {
		return this.mnPlayerID;
	}
	
	private void addPlayerElement(int pnPlayerElementID) {
		PlayerElement oPlayerElement = new PlayerElement(this, pnPlayerElementID);
		addPlayerElement(oPlayerElement);
	}
	
	private void addPlayerElement(Element poElement, int pnLevel) {
		PlayerElement oPlayerElement = new PlayerElement(this, poElement, pnLevel);
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
	
	/*
	public ElementA getElementA(Element poElement, int pnLevel) {
		if (mbRepopulateA) this.populateElementsA();
		
		long key = getKeyA(poElement, pnLevel);
		return moElementsA.get(key);
	}
	*/
	
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

		/*
		if (this.mnIndexA < this.moElementsA.size()) {
			oElementA = this.moElementsA.valueAt(mnIndexA);
			this.mnIndexA++;
		}*/
		
		if (this.mnIndexA < this.moElementsASorted.size()) {
			oElementA = this.moElementsASorted.get(mnIndexA);
			//this.mnIndexA++;
		}
		
		/*
		// do this if using an iterator
		if (moElementsAIterator.hasNext()) {
			oElementA = (ElementA) moElementsAIterator.next();
			moElementsAIterator.remove(); // avoids a ConcurrentModificationException
		}
		*/
				
		return oElementA;
	}
	
	public void setExclude(long key, boolean pbExclude) {
		PlayerElement playerElement = moPlayerElements.get(key);
		playerElement.setExclude(pbExclude);
	}
	
	// thanks to http://stackoverflow.com/questions/780541/how-to-sort-a-hashmap-in-java
	public void sortByName(final boolean pbAscending) {
		moElementsASorted = new ArrayList<ElementA>(this.moElementsA.values());
		Collections.sort(moElementsASorted, new Comparator<ElementA>() {
			@Override
			public int compare(ElementA arg0, ElementA arg1) {
				if (pbAscending)
					return arg0.getElement().getName().compareTo(arg1.getElement().getName());
				else
					return arg1.getElement().getName().compareTo(arg0.getElement().getName());
			}
		});
	}
	
	public void sortByCost(final boolean pbAscending) {
		moElementsASorted = new ArrayList<ElementA>(this.moElementsA.values());
		Collections.sort(moElementsASorted, new Comparator<ElementA>() {
			@Override
			public int compare(ElementA arg0, ElementA arg1) {
				Element oElement0 = arg0.getElement();
				if (oElement0 == null) { Log.d("Player", "oElement0 is null!"); return 1; }
				Element oElement1 = arg1.getElement();
				if (oElement1 == null) { Log.d("Player", "oElement1 is null!"); return 1; }
				
				ElementData oElementData0 = oElement0.getElementData(arg0.getLevel());
				if (oElementData0 == null) { Log.d("Player", oElement0.getName() + ": oElementData0 is null!"); return 1; }
				ElementData oElementData1 = oElement1.getElementData(arg1.getLevel());
				if (oElementData1 == null) { Log.d("Player", oElement1.getName() + ": oElementData1 is null!"); return 1; }
				if (pbAscending)
					return oElementData0.getBuildCost() - oElementData1.getBuildCost();
				else
					return oElementData1.getBuildCost() - oElementData0.getBuildCost();
			}
		});
	}
	
	// for a newly created player, add in the default buildings that come with the given TH level
	private void LoadDefaults() {
        THElements oTHElements = new THElements(mnTHLevel);

        for (int i=0; i<oTHElements.size(); i++) {
        	THElement thElement = oTHElements.getTHElement();
        	for (int j=0; j<thElement.getQuantity(); j++) {
        		// for each element, add it assuming player has maxed out buildings at previous level
        		// for level 1, assume all buildings are level 0 (not built yet)
        		// EXCEPTION: make townhall level = mnTHLevel
        		int nLevel = 0;
        		Element oElement = thElement.getElement();
        		if (oElement.getName().equals("Town Hall")) 
        			nLevel = mnTHLevel;
        		else
        			if (mnTHLevel > 1) nLevel = oElement.getMaxLevel(mnTHLevel-1);
        		this.addPlayerElement(oElement, nLevel);
        	}
        	oTHElements.moveToNext();
        }
	}
	
	// for an existing player, load the buildings as saved in tblPlayerElement
	private void LoadExisting() {
		Cursor cursor = ClasherDBContract.ClasherPlayerElement.selectByPlayer(mnPlayerID);
		if (cursor != null) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				int nPlayerElementID = cursor.getString(0) == null ? 0 : Integer.parseInt(cursor.getString(0));
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
	
	public int getUpgradeCostMax() {
		int nUpgradeCost = 0;
		
		for (int i=0; i<this.moPlayerElements.size(); i++) {
			long key = moPlayerElements.keyAt(i);
			PlayerElement oPlayerElement = moPlayerElements.get(key);
			nUpgradeCost = nUpgradeCost + oPlayerElement.getUpgradeCostMax();
		}
		return nUpgradeCost;
	}
	
	public int size() {
		return moPlayerElements.size();
	}
	
	public void delete() {
		ClasherDBContract.ClasherPlayer.delete(mnPlayerID);
	}

	private void update() {
		ClasherDBContract.ClasherPlayer.update(mnPlayerID, msVillageName, mnTHLevel);
	}

	private boolean insert() {
		mnPlayerID = ClasherDBContract.ClasherPlayer.insert(getVillageName(), mnIndexA);
		return mnPlayerID != 0;
	}
	
	private void select() {
		Cursor cursor = ClasherDBContract.ClasherPlayer.selectSingle(mnPlayerID);
		if (cursor != null) {
			try {
				cursor.moveToFirst();
				this.msVillageName = cursor.getString(1) == null ? "" : cursor.getString(1);
				this.mnTHLevel = cursor.getString(2) == null ? 0 : Integer.parseInt(cursor.getString(2));
			}
			catch (SQLiteException e) {
				Log.e(TAG, e.getMessage());
			}
			finally {
				cursor.close();
			}
		}
	}

}
