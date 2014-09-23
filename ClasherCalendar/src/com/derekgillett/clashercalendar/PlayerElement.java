package com.derekgillett.clashercalendar;

import java.util.Calendar;
import java.util.Date;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PlayerElement {
	private SQLiteDatabase moDB;

	private long mnPlayerElementID;
	private Player moPlayer;
	private Element moElement;
	private int mnLevel;
	private Date mdUpgradeStart = null;
	private boolean mbExclude = false;

	// load an existing player element
	public PlayerElement(SQLiteDatabase poDB, long pnPlayerElementID, Player poPlayer) {
		moDB = poDB;
		mnPlayerElementID = pnPlayerElementID;

		int colIndex;
		Cursor cursor = this.selectSingle();

		if (cursor != null) {
			cursor.moveToFirst();
			this.moPlayer = poPlayer;
			colIndex = cursor.getColumnIndexOrThrow(ClasherDBContract.ClasherPlayerElement.COLUMN_NAME_ELEMENT_ID);
			this.moElement = new Element(moDB, cursor.getLong(colIndex));
			colIndex = cursor.getColumnIndexOrThrow(ClasherDBContract.ClasherPlayerElement.COLUMN_NAME_LEVEL);
			this.mnLevel = cursor.getInt(colIndex);
			colIndex = cursor.getColumnIndexOrThrow(ClasherDBContract.ClasherPlayerElement.COLUMN_NAME_UPGRADE_START);
			this.mdUpgradeStart = new Date(cursor.getInt(colIndex)*1000);
			cursor.close();
		}
	}
	
	// create a new player element
	public PlayerElement(SQLiteDatabase poDB, Player poPlayer, Element poElement, int pnLevel) {
		moDB = poDB;
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
		return new PlayerElement(moDB, moPlayer, moElement, mnLevel);
	}
	
    public void createTable() {
    	moDB.execSQL(ClasherDBContract.ClasherPlayerElement.SQL_DROP_TABLE);
    	moDB.execSQL(ClasherDBContract.ClasherPlayerElement.SQL_CREATE_TABLE);
    }
    
    private boolean insert() {
    	ContentValues values = new ContentValues();
    	values.put(ClasherDBContract.ClasherPlayerElement.COLUMN_NAME_ELEMENT_ID, moElement.getId());
    	values.put(ClasherDBContract.ClasherPlayerElement.COLUMN_NAME_LEVEL, mnLevel);
    	values.put(ClasherDBContract.ClasherPlayerElement.COLUMN_NAME_PLAYER_ID, moPlayer.getid());
    	values.put(ClasherDBContract.ClasherPlayerElement.COLUMN_NAME_UPGRADE_START, this.mdUpgradeStart != null ? this.mdUpgradeStart.getTime()/1000 : 0);

    	mnPlayerElementID = moDB.insert(ClasherDBContract.ClasherPlayerElement.TABLE_NAME, null, values);
		return mnPlayerElementID != 0;
    }

    private int update() {
    	ContentValues values = new ContentValues();
    	values.put(ClasherDBContract.ClasherPlayerElement.COLUMN_NAME_ELEMENT_ID, moElement.getId());
    	values.put(ClasherDBContract.ClasherPlayerElement.COLUMN_NAME_LEVEL, mnLevel);
    	values.put(ClasherDBContract.ClasherPlayerElement.COLUMN_NAME_PLAYER_ID, moPlayer.getid());
    	values.put(ClasherDBContract.ClasherPlayerElement.COLUMN_NAME_UPGRADE_START, this.mdUpgradeStart != null ? this.mdUpgradeStart.getTime()/1000 : 0);

    	String selection = "_id = ?";
		String[] selectionArgs = new String[] { String.valueOf(mnPlayerElementID) };

		return moDB.update(
    			ClasherDBContract.ClasherPlayerElement.TABLE_NAME, 
    			values, 
				selection, 
				selectionArgs); 
    }
/*
    private int deletePlayer() {
    	String selection = ClasherDBContract.ClasherPlayerElement.COLUMN_NAME_PLAYER_ID + " = ?";
		String[] selectionArgs = new String[] { String.valueOf(moPlayer.getid()) };
    	return moDB.delete(
    			ClasherDBContract.ClasherPlayerElement.TABLE_NAME, 
				selection, 
				selectionArgs); 
    }
*/
    private Cursor selectSingle() {
    	String[] columns = ClasherDBContract.ClasherPlayerElement.ALL_COLUMNS;
    	String selection = ClasherDBContract.ClasherPlayerElement.COLUMN_NAME_ID + " = ?";
		String[] selectionArgs = new String[] { String.valueOf(this.mnPlayerElementID) };
		return moDB.query(
				ClasherDBContract.ClasherPlayerElement.TABLE_NAME, 
				columns, 
				selection, 
				selectionArgs, 
				null, null, null);        	
    }	
}
