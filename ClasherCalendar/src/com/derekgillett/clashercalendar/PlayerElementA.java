package com.derekgillett.clashercalendar;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.util.LongSparseArray;

public class PlayerElementA {
	private LongSparseArray<PlayerElement> moPlayerElements = new LongSparseArray<PlayerElement>();

	private final String TABLE_NAME = "tblPlayerElementA";
	private final String COLUMN_ID = "PlayerElementAID";
	private final String COLUMN_PLAYERID = "PlayerID";
	private final String COLUMN_ELEMENTID = "ELEMENTID";
	private final String COLUMN_LEVEL = "LEVEL";
	
	private final String[] ALL_COLUMNS = { this.COLUMN_ID, this.COLUMN_PLAYERID, this.COLUMN_ELEMENTID, this.COLUMN_LEVEL };

	private long mnPlayerElementAID;
	private Player moPlayer;
	private Element moElement;
	private int mnLevel;

	private boolean mbExclude = false;

	public PlayerElementA(Player poPlayer, long pnPlayerElementAID) {
		mnPlayerElementAID = pnPlayerElementAID;
		moPlayer = poPlayer;
		Load();
	}
	
	public PlayerElementA(Player poPlayer, Element poElement, int pnLevel) {
		moElement = poElement;
		mnLevel = pnLevel;
		moPlayer = poPlayer;
		insert();
	}
	
	public Element getElement() {
		return moElement;
	}
	
	public void setElement(Element poElement) {
		moElement = poElement;
		this.update();
	}
	
	public int getLevel() {
		return mnLevel;
	}
	
	public void incLevel() {
		mnLevel++;
		this.update();
	}
	
	public void decLevel() {
		mnLevel--;
		this.update();
	}
	
	public boolean isMax() {
		ElementData oElementData = moElement.getElementData(mnLevel+1);
		if (oElementData == null)
			return true;
		else {
			return oElementData.getTHMinLevel() > moPlayer.getTHLevel();
		}
	}
	
	public int getUpgradeCost() {
		if (isMax() || mbExclude) 
			return 0;
		else {
			ElementData oElementData = moElement.getElementData(mnLevel+1);
			return oElementData.getBuildCost();
		}
	}
	
	public int getUpgradeCostMax() {
		if (isMax() || mbExclude) 
			return 0;
		else {
			int nTargetLevel = moElement.getMaxLevel(moPlayer.getTHLevel());
			ElementData oElementData = moElement.getElementData(mnLevel+1);
			return oElementData.getBuildCost(nTargetLevel);
		}
	}
	
	public int getUpgradeTime() {
		if (isMax() || mbExclude) 
			return 0;
		else {
			ElementData oElementData = moElement.getElementData(mnLevel+1);
			return oElementData.getBuildTime(); 
		}
	}
	
	public int getUpgradeTimeMax() {
		if (isMax() || mbExclude) 
			return 0;
		else {
			int nTargetLevel = moElement.getMaxLevel(moPlayer.getTHLevel());
			ElementData oElementData = moElement.getElementData(mnLevel+1);
			return oElementData.getBuildTime(nTargetLevel); 
		}
	}
	
	public long getID() {
		return this.mnPlayerElementAID;
	}
	
	public boolean getExclude() {
		return mbExclude;
	}
	
	public void setExclude(boolean bExclude) {
		mbExclude = bExclude;
	}
	
	public PlayerElementA clone() {
		return new PlayerElementA(this.moPlayer, this.mnPlayerElementAID);
	}
	
	private void Load() {
		Cursor cursor = MyApplication.getDB().query(this.TABLE_NAME, this.ALL_COLUMNS, this.COLUMN_ID + " = ?", 
				new String[] { String.valueOf(mnPlayerElementAID) }, null, null, null );

		if (cursor != null) {
			cursor.moveToFirst();
			this.moElement = new Element(cursor.getInt(cursor.getColumnIndex(this.COLUMN_ELEMENTID)));
			this.mnLevel = cursor.getInt(cursor.getColumnIndex(this.COLUMN_LEVEL));
			cursor.close();
		}
	}

	public void delete() {
		MyApplication.getDB().delete("tblPlayerElement", 
				COLUMN_ID + " = ?", new String[] { String.valueOf(mnPlayerElementAID) });
		MyApplication.getDB().delete(TABLE_NAME, 
				COLUMN_ID + " = ?", new String[] { String.valueOf(mnPlayerElementAID) });
	}

	private void update() {
		ContentValues values = new ContentValues();
		values.put(this.COLUMN_ELEMENTID, this.moElement.getId());
		values.put(this.COLUMN_LEVEL, this.mnLevel);
		values.put(this.COLUMN_PLAYERID, this.moPlayer.getid());
		MyApplication.getDB().update(TABLE_NAME,values, 
				COLUMN_ID + " = ?", new String[] { String.valueOf(mnPlayerElementAID) });
	}

	private boolean insert() {
		ContentValues values = new ContentValues();
		values.put(this.COLUMN_ELEMENTID, this.moElement.getId());
		values.put(this.COLUMN_LEVEL, this.mnLevel);
		values.put(this.COLUMN_PLAYERID, this.moPlayer.getid());
		mnPlayerElementAID = MyApplication.getDB().insert(this.TABLE_NAME,  null,  values);
		return mnPlayerElementAID != 0;
	}
}
