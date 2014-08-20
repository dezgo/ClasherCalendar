package com.derekgillett.clashercalendar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class THElement {
	private SQLiteDatabase moDB;
	
	private int mnTHLevel;
	private Element moElement;
	private int mnQuantity;

	public THElement(int pnTHLevel, int pnElementID, int pnQuantity) {
		moDB = MyApplication.getDB();
		
		mnTHLevel = pnTHLevel;
		moElement = new Element(pnElementID);
		mnQuantity = pnQuantity;
		Load();
	}
	
	public int getTHLevel() {
		return mnTHLevel;
	}
	
	public Element getElement() {
		return moElement;
	}
	
	public int getQuantity() {
		return mnQuantity;
	}
	
	public void setQuantity(int pnQuantity) {
		mnQuantity = pnQuantity;

		moDB.rawQuery("UPDATE tblTHElement SET Quantity = ? WHERE THLevel = ? AND ElementID = ?", 
				new String[] { String.valueOf(mnQuantity), String.valueOf(mnTHLevel), String.valueOf(moElement.getId()) });
	}
	
	private void Load() {
		Cursor cursor = moDB.rawQuery("SELECT Quantity FROM tblTHElement WHERE THLevel = ? AND ElementID = ?",
				new String[] { String.valueOf(mnTHLevel), String.valueOf(moElement.getId()) }); 
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				this.mnQuantity = cursor.getString(0) == null ? 0 : Integer.parseInt(cursor.getString(0));
			} else
				this.mnQuantity = 0;
		}
	}
}
