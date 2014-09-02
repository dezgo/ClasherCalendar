package com.derekgillett.clashercalendar;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.util.LongSparseArray;
import android.util.Log;

public class Player {
	private final String TABLE_NAME = "tblPlayer";
	private final String COLUMN_ID = "PlayerID";
	private final String COLUMN_VILLAGENAME = "VillageName";
	private final String COLUMN_THLEVEL = "THLevel";
	private final String[] ALL_COLUMNS = { this.COLUMN_ID, this.COLUMN_VILLAGENAME, this.COLUMN_THLEVEL };

	private boolean mbRepopulateA = true;	// flag indicating whether to repopulate aggregate array
	private long mnPlayerID;
	private String msVillageName;
	private int mnTHLevel = 1;
	private LongSparseArray<PlayerElement> moPlayerElements = new LongSparseArray<PlayerElement>();

	// aggregated player elements where items with same element id and level are grouped together
	private LongSparseArray<ElementA> moElementsA = new LongSparseArray<ElementA>();
	
	private int mnIndex = 0;
	private int mnIndexA = 0;
	
	public Player(long pnPlayerID) {
		mnPlayerID = pnPlayerID;
		this.select();
		this.LoadExisting();
        MyApplication.setPlayer(this);
	}
	
	public Player(int pnTHLevel, String psVillageName) {
		mnTHLevel = pnTHLevel;
		msVillageName = psVillageName;
		this.insert();
		LoadDefaults();
        MyApplication.setPlayer(this);
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
	
	private long getKeyA(Element poElement, int pnLevel) {
		return poElement.getId() * 100 + pnLevel;
	}
	
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
	
	public ElementA getElementA(Element poElement, int pnLevel) {
		if (mbRepopulateA) this.populateElementsA();
		
		long key = getKeyA(poElement, pnLevel);
		return moElementsA.get(key);
	}
	
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
	
	public ElementA getElementA() {
		ElementA oElementA = null;
		if (mnIndexA <= moElementsA.size() ) {
			oElementA = moElementsA.get(moElementsA.keyAt(mnIndexA));
		}
		return oElementA;
	}
	
	public void setExclude(long key, boolean pbExclude) {
		PlayerElement playerElement = moPlayerElements.get(key);
		playerElement.setExclude(pbExclude);
	}
	
	// for a newly created player, add in the default buildings that come with the given TH level
	private void LoadDefaults() {
        THElements oTHElements = new THElements(mnTHLevel);

        for (int i=0; i<oTHElements.size(); i++) {
        	THElement thElement = oTHElements.getTHElement();
        	for (int j=0; j<thElement.getQuantity(); j++) {
        		// for each element, add it assuming player has maxed out buildings at previous level
        		// for level 1, assume all buildings are level 0 (not built yet)
        		int nLevel = 0;
        		Element oElement = thElement.getElement();
        		if (mnTHLevel > 1) nLevel = oElement.getMaxLevel(mnTHLevel-1);
        		this.addPlayerElement(oElement, nLevel);
        	}
        	oTHElements.moveToNext();
        }
	}
	
	// for an existing player, load the buildings as saved in tblPlayerElement
	private void LoadExisting() {
		String[] selectionArgs = new String[] { String.valueOf(mnPlayerID) };
		ContentValues values = new ContentValues();
		values.put(COLUMN_ID, mnPlayerID);
		Cursor cursor = MyApplication.getDB().query("tblPlayerElement", new String[] {"PlayerElementID"}, "PlayerID = ?", selectionArgs, null, null, null);
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
		// cheeky reference to PlayerElement table here
		// didn't want to implement a delete method over there when it's only used here
		MyApplication.getDB().delete("tblPlayerElement", 
				COLUMN_ID + " = ?", new String[] { String.valueOf(mnPlayerID) });
		MyApplication.getDB().delete(TABLE_NAME, 
				COLUMN_ID + " = ?", new String[] { String.valueOf(mnPlayerID) });
	}

	private void update() {
		ContentValues values = new ContentValues();
		values.put(this.COLUMN_VILLAGENAME, msVillageName);
		values.put(this.COLUMN_THLEVEL, mnTHLevel);
		MyApplication.getDB().update(TABLE_NAME,values, 
				COLUMN_ID + " = ?", new String[] { String.valueOf(mnPlayerID) });
	}

	private boolean insert() {
		ContentValues values = new ContentValues();
		values.put("VillageName", msVillageName);
		values.put("THLevel", mnTHLevel);
		mnPlayerID = MyApplication.getDB().insert("tblPlayer",  null,  values);
		return mnPlayerID != 0;
	}
	
	private void select() {
		String[] selectionArgs = new String[] { String.valueOf(mnPlayerID) };
		Cursor cursor = MyApplication.getDB().query(this.TABLE_NAME, this.ALL_COLUMNS, COLUMN_ID + " = ?", selectionArgs, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			this.msVillageName = cursor.getString(1) == null ? "" : cursor.getString(1);
			this.mnTHLevel = cursor.getString(2) == null ? 0 : Integer.parseInt(cursor.getString(2));
			cursor.close();
		}
	}

}
