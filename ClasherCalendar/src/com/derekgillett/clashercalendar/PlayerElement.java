package com.derekgillett.clashercalendar;

import java.util.Calendar;
import java.util.Date;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class PlayerElement extends PlayerElementA {
	private final String TABLE_NAME = "tblPlayerElement";
	private final String COLUMN_ID = "PlayerElementID";
	private final String COLUMN_PLAYERID = "PlayerID";
	private final String COLUMN_ELEMENTID = "ElementID";
	private final String COLUMN_LEVEL = "Level";
	private final String COLUMN_UPGRADESTART = "UpgradeStart";
	private final String[] ALL_COLUMNS = { this.COLUMN_ID, this.COLUMN_PLAYERID, this.COLUMN_ELEMENTID, this.COLUMN_LEVEL, this.COLUMN_UPGRADESTART };

	private Date mdUpgradeStart = null;
	private long mnPlayerElementID;

	public PlayerElement(Player poPlayer, Element poElement, int pnLevel) {
		super(poPlayer, poElement, pnLevel);
		// TODO Auto-generated constructor stub
	}
	
	public void startUpgrade() {
		if (!this.isMax()) {
			mdUpgradeStart = new Date();
			this.update();
		}
	}
	
	public int getSecondsRemaining() {
		if (this.mdUpgradeStart == null)
			return 0;
		else {
			Date afterUpgrade = new Date(this.mdUpgradeStart.getTime() + this.getUpgradeTime()*1000);
			Calendar c = Calendar.getInstance();
			if (afterUpgrade.getTime() < c.getTimeInMillis()) {
				this.mdUpgradeStart = null;
				this.mnLevel++;
				this.update();
				return 0;
			}
			else
				return (int) (afterUpgrade.getTime() - c.getTimeInMillis())/1000;
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
