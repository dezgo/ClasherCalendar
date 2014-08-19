package com.derekgillett.clashercalendar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class THElement {
	private SQLiteDatabase moDB;
	
	private int mTHLevel;
	private int mElementID;
	private int mQuantity;

	public THElement(int pTHLevel, int pElementID) {
		// 1. get reference to writable DB
		MySQLiteHelper oMySQLiteHelper = new MySQLiteHelper();
		moDB = oMySQLiteHelper.getWritableDatabase();
		
		mTHLevel = pTHLevel;
		mElementID = pElementID;
		Load();
	}
	
	public int getTHLevel() {
		return mTHLevel;
	}
	
	public int getElementID() {
		return mElementID;
	}
	
	public int getQuantity() {
		return mQuantity;
	}
	
	public void setQuantity(int pQuantity) {
		mQuantity = pQuantity;

		moDB.rawQuery("UPDATE tblTHElement SET Quantity = ? WHERE THLevel = ? AND ElementID = ?", 
				new String[] { String.valueOf(mQuantity), String.valueOf(mTHLevel), String.valueOf(mElementID) });
	}
	
	private void Load() {
		Cursor cursor = moDB.rawQuery("SELECT Quantity FROM tblTHElement WHERE THLevel = ? AND ElementID = ?",
				new String[] { String.valueOf(mTHLevel), String.valueOf(mElementID) }); 
		if (cursor != null) {
			cursor.moveToFirst();
			this.mQuantity = Integer.parseInt(cursor.getString(0));
		}
	}
}
