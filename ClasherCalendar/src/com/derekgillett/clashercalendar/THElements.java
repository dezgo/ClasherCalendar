package com.derekgillett.clashercalendar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.util.LongSparseArray;

public class THElements {
	private SQLiteDatabase moDB;
	private LongSparseArray<THElement> moTHElements = new LongSparseArray<THElement>();
	private int mnIndex = 0;
	
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
					moTHElements.put(nElementID, oTHElement);
					cursor.moveToNext();
				}
			}
			cursor.close();
		}
	}
	
	public int size() {
		return moTHElements.size();
	}
	
	public void moveToFirst() {
		mnIndex = 0;
	}
	
	public void moveToNext() {
		mnIndex++;
	}
	
	public boolean isAfterLast() {
		return mnIndex >= moTHElements.size();
	}
	
	public THElement getTHElement() {
		THElement oTHElement = null;
		if (mnIndex <= moTHElements.size() ) {
			oTHElement = moTHElements.get(moTHElements.keyAt(mnIndex));
		}
		return oTHElement;
	}
	
	public THElement getTHElement(long key) {
		return moTHElements.get(key);
	}
}
