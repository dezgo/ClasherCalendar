package com.derekgillett.clashercalendar;

import java.util.Calendar;
import java.util.Date;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PlayerElement {
	private final String TABLE_NAME = "tblPlayerElement";
	private final String COLUMN_ID = "PlayerElementID";
	private final String COLUMN_PLAYERID = "PlayerID";
	private final String COLUMN_ELEMENTID = "ElementID";
	private final int 	 COLUMN_ELEMENTID_NUM = 2;
	private final String COLUMN_LEVEL = "Level";
	private final int 	 COLUMN_LEVEL_NUM = 3;
	private final String COLUMN_UPGRADESTART = "UpgradeStart";
	private final int 	 COLUMN_UPGRADESTART_NUM = 4;
	private final String[] ALL_COLUMNS = { this.COLUMN_ID, this.COLUMN_PLAYERID, this.COLUMN_ELEMENTID, this.COLUMN_LEVEL, this.COLUMN_UPGRADESTART };

	private SQLiteDatabase moDB;

	private long mnPlayerElementID;
	private Player moPlayer;
	private Element moElement;
	private int mnLevel;
	private Date mdUpgradeStart = null;
	private boolean mbExclude = false;

	private void init() {
		// 1. get reference to writable DB
		MySQLiteHelper oMySQLiteHelper = new MySQLiteHelper();
		moDB = oMySQLiteHelper.getWritableDatabase();
	}
	
	public PlayerElement(Player poPlayer, long pnPlayerElementID) {
		init();
		mnPlayerElementID = pnPlayerElementID;
		moPlayer = poPlayer;
		Load();
	}
	
	public PlayerElement(Player poPlayer, Element poElement, int pnLevel) {
		init();
		moElement = poElement;
		mnLevel = pnLevel;
		moPlayer = poPlayer;
		insert();
	}
	
	public long getPlayerElementID() {
		return mnPlayerElementID;
	}
	
	public void startUpgrade() {
		if (!this.isMax()) {
			Calendar c = Calendar.getInstance();
			this.mdUpgradeStart = c.getTime();
			this.update();
		}
	}
	
	public void finishUpgrade() {
		this.mdUpgradeStart = null;
		this.mnLevel++;
		this.update();
	}
	
	public int getSecondsRemaining() {
		if (this.mdUpgradeStart == null)
			return 0;
		else {
			Date afterUpgrade = new Date(this.mdUpgradeStart.getTime() + this.getUpgradeTime()*1000);
			Calendar c = Calendar.getInstance();
			if (afterUpgrade.getTime() < c.getTimeInMillis())
				return 0;
			else
				return (int) (afterUpgrade.getTime() - c.getTimeInMillis())/1000;
		}
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
		return this.mnPlayerElementID;
	}
	
	public boolean getExclude() {
		return mbExclude;
	}
	
	public void setExclude(boolean bExclude) {
		mbExclude = bExclude;
	}
	
	public PlayerElement clone() {
		return new PlayerElement(this.moPlayer, this.mnPlayerElementID);
	}
	
	private void Load() {
		Cursor cursor = moDB.query(this.TABLE_NAME, this.ALL_COLUMNS, this.COLUMN_ID + " = ?", 
				new String[] { String.valueOf(mnPlayerElementID) }, null, null, null );

		if (cursor != null) {
			cursor.moveToFirst();
			this.moElement = new Element(cursor.getInt(this.COLUMN_ELEMENTID_NUM));
			this.mnLevel = cursor.getInt(this.COLUMN_LEVEL_NUM);
			this.mdUpgradeStart = new Date(cursor.getInt(this.COLUMN_UPGRADESTART_NUM)*1000);
			cursor.close();
		}
	}

	private void update() {
		ContentValues values = new ContentValues();
		values.put(this.COLUMN_LEVEL, mnLevel);
		values.put(this.COLUMN_ELEMENTID, moElement.getId());
		if (this.mdUpgradeStart != null)
			values.put(this.COLUMN_UPGRADESTART, this.mdUpgradeStart.getTime()/1000);
		MyApplication.getDB().update(TABLE_NAME,values, 
				COLUMN_ID + " = ?", new String[] { String.valueOf(mnPlayerElementID) });
	}

	private boolean insert() {
		ContentValues values = new ContentValues();
		values.put(this.COLUMN_LEVEL, mnLevel);
		values.put(this.COLUMN_ELEMENTID, moElement.getId());
		values.put(this.COLUMN_PLAYERID, this.moPlayer.getid());
		if (this.mdUpgradeStart != null) 
			values.put(this.COLUMN_UPGRADESTART, this.mdUpgradeStart.getTime()/1000);
		mnPlayerElementID = MyApplication.getDB().insert(TABLE_NAME,  null,  values);
		return mnPlayerElementID != 0;
	}
}
