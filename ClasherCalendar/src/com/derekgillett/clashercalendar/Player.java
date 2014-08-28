package com.derekgillett.clashercalendar;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.util.LongSparseArray;

public class Player {
	private final String TABLE_NAME = "tblPlayer";
	private final String COLUMN_ID = "PlayerID";
	private final String COLUMN_VILLAGENAME = "VillageName";
	private final String COLUMN_THLEVEL = "THLevel";
	private final String[] ALL_COLUMNS = { this.COLUMN_ID, this.COLUMN_VILLAGENAME, this.COLUMN_THLEVEL };

	private long mnPlayerID;
	private String msVillageName;
	private int mnTHLevel = 1;

	// aggregated player elements where items with same element id and level are grouped together
	private LongSparseArray<PlayerElementA> moPlayerElementsA = new LongSparseArray<PlayerElementA>();
	
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
	}
	
	public void incPlayerElementLevel(PlayerElementA poPlayerElement, int pnIncrement) {
		junit.framework.Assert.assertTrue("increment can only be -1 or 1, not " + pnIncrement, pnIncrement == -1 || pnIncrement == 1);
		
		// only increment if we're not already at max level
		// only decrement if we're at level > 0
		if (((pnIncrement ==  1) && (poPlayerElement.getLevel() < poPlayerElement.getElement().getMaxLevel(this.mnTHLevel)) ||
			((pnIncrement == -1) && (poPlayerElement.getLevel() > 0)))) {

			// for the standard array of player elements, just increment/decrement the level
			if (pnIncrement == 1)
				this.moPlayerElements.get(poPlayerElement.getID()).incLevel();
			else
				this.moPlayerElements.get(poPlayerElement.getID()).decLevel();
			
			// get a playerelement from the aggregated array
			long key = this.getKeyA(poPlayerElement.getElement(), poPlayerElement.getLevel());
			PlayerElementA oPlayerElementOld = this.moPlayerElementsA.get(key);
			
			// remove 1 from the array (i.e. remove it altogether if there is only 1, otherwise reduce qty by 1)
			if (oPlayerElementOld.getQty() == 1)
				moPlayerElementsA.remove(key);
			else
				oPlayerElementOld.setQtyDec();

			// add 1 to array for element at new level
			key = this.getKeyA(poPlayerElement.getElement(), poPlayerElement.getLevel()+pnIncrement);
			PlayerElementA oPlayerElementNew = this.moPlayerElementsA.get(key);
			if (oPlayerElementNew == null)
				moPlayerElementsA.put(key, oPlayerElementNew);
			else
				oPlayerElementNew.setQtyInc();
		}
	}
	
	public long getid() {
		return this.mnPlayerID;
	}
	
	private void addPlayerElement(int pnPlayerElementID) {
		PlayerElementA oPlayerElement = new PlayerElementA(this, pnPlayerElementID);
		addPlayerElement(oPlayerElement);
	}
	
	private void addPlayerElement(Element poElement, int pnLevel) {
		PlayerElementA oPlayerElement = new PlayerElementA(this, poElement, pnLevel);
		addPlayerElement(oPlayerElement);
	}
	
	private void addPlayerElement(PlayerElementA poPlayerElement) {
		// add to standard list of player elements
		moPlayerElements.put(poPlayerElement.getID(), poPlayerElement);

		// add to aggregated list of player elements
		Element oElement = poPlayerElement.getElement();
		int nLevel = poPlayerElement.getLevel();
		long key = getKeyA(oElement, nLevel);
		PlayerElementA oPlayerElementA = this.moPlayerElementsA.get(key);
		if (oPlayerElementA == null) {
			// want to create a new playerelement object for this array rather than using the one already
			// in the other array
			PlayerElementA oPlayerElementANew = poPlayerElement.clone();
			moPlayerElementsA.put(key, oPlayerElementANew);
		} else
			oPlayerElementA.setQtyInc();			
	}
	
	private long getKeyA(Element poElement, int pnLevel) {
		return poElement.getId() * 100 + pnLevel;
	}
	
	public PlayerElementA getPlayerElement(long pnPlayerElementID) {
		return moPlayerElements.get(pnPlayerElementID);
	}
	
	public PlayerElementA getPlayerElementA(Element poElement, int pnLevel) {
		long key = getKeyA(poElement, pnLevel);
		return moPlayerElementsA.get(key);
	}
	
	public void moveToFirst() {
		this.mnIndex = 0;
	}
	
	public void moveToFirstA() {
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
		return mnIndexA >= this.moPlayerElementsA.size();
	}
	
	public PlayerElementA getPlayerElement() {
		PlayerElementA playerElement = null;
		if (mnIndex <= moPlayerElements.size() ) {
			playerElement = moPlayerElements.get(moPlayerElements.keyAt(mnIndex));
		}
		return playerElement;
	}
	
	public PlayerElementA getPlayerElementA() {
		PlayerElementA playerElementA = null;
		if (mnIndexA <= moPlayerElementsA.size() ) {
			playerElementA = moPlayerElementsA.get(moPlayerElementsA.keyAt(mnIndexA));
		}
		return playerElementA;
	}
	
	public void setExclude(long key, boolean pbExclude) {
		PlayerElementA playerElement = moPlayerElements.get(key);
		playerElement.setExclude(pbExclude);

		long keyA = getKeyA(playerElement.getElement(), playerElement.getLevel());
		PlayerElementA playerElementA = moPlayerElementsA.get(keyA);
		playerElementA.setExclude(pbExclude);
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
		int nUpgradeTime = 0;
		
		for (int i=0; i<this.moPlayerElements.size(); i++) {
			long key = moPlayerElements.keyAt(i);
			PlayerElementA oPlayerElement = moPlayerElements.get(key);
			nUpgradeTime = nUpgradeTime + oPlayerElement.getUpgradeTimeMax();
		}
		return nUpgradeTime;
	}
	
	public int getUpgradeCostMax() {
		int nUpgradeCost = 0;
		
		for (int i=0; i<this.moPlayerElements.size(); i++) {
			long key = moPlayerElements.keyAt(i);
			PlayerElementA oPlayerElement = moPlayerElements.get(key);
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
