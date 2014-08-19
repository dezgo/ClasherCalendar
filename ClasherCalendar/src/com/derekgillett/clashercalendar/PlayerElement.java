package com.derekgillett.clashercalendar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PlayerElement {
	private SQLiteDatabase moDB;
	
	private int mPlayerElementID;
	private int mElementID;
	private int mLevel;

	public PlayerElement(Context poContext, int pPlayerElementID) {

		// 1. get reference to writable DB
		MySQLiteHelper oMySQLiteHelper = new MySQLiteHelper();
		moDB = oMySQLiteHelper.getWritableDatabase();
		
		mPlayerElementID = pPlayerElementID;
		Load();
	}
	
	public int getPlayerElementID() {
		return mPlayerElementID;
	}
	
	public int getElementID() {
		return mElementID;
	}
	
	public void setElementID(int pElementID) {
		mElementID = pElementID;

		moDB.rawQuery("UPDATE tblPlayerElement SET ElementID = ? WHERE PlayerElementID = ?", 
				new String[] { String.valueOf(mElementID), String.valueOf(mPlayerElementID) });
	}
	
	public int getLevel() {
		return mLevel;
	}
	
	public void setLevel(int pLevel) {
		mLevel = pLevel;

		moDB.rawQuery("UPDATE tblPlayerElement SET Level = ? WHERE PlayerElementID = ?", 
				new String[] { String.valueOf(mLevel), String.valueOf(mPlayerElementID) });
	}
	
	private void Load() {
		Cursor cursor = moDB.rawQuery("SELECT ElementID, Level FROM tblPlayerElement WHERE PlayerElementID = ?",
				new String[] { String.valueOf(mPlayerElementID) }); 
		if (cursor != null) {
			cursor.moveToFirst();
			this.mElementID = Integer.parseInt(cursor.getString(0));
			this.mLevel = Integer.parseInt(cursor.getString(1));
		}
	}
}
