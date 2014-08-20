package com.derekgillett.clashercalendar;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class THElements {
	private SQLiteDatabase moDB;
	private ArrayList<THElement> moTHElements = new ArrayList<THElement>();
	
	public THElements(int pnLevel) {
		THElement oTHElement;
		int nElementID;
		int nQuantity;

		moDB = MyApplication.getDB();
		Cursor cursor = moDB.rawQuery("SELECT ElementID,Quantity FROM tblTHElement WHERE THLevel = ?",
				new String[] { String.valueOf(pnLevel) }); 
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				while (!cursor.isAfterLast())
				{
					nElementID = cursor.getString(0) == null ? 0 : Integer.parseInt(cursor.getString(0));
					nQuantity = cursor.getString(1) == null ? 0 : Integer.parseInt(cursor.getString(1));
					oTHElement = new THElement(pnLevel, nElementID, nQuantity);
					moTHElements.add(oTHElement);
					cursor.moveToNext();
				}
			}
		}
	}
	
	public ArrayList<THElement> getTHElements() {
		return moTHElements;
	}
}
