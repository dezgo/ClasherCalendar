package com.derekgillett.clashercalendar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.util.LongSparseArray;

public class THElements {
	private LongSparseArray<THElement> moTHElements = new LongSparseArray<THElement>();
	private int mnIndex = 0;
	
	public THElements(int pnLevel) {
		THElement oTHElement;
		long nID;

		Cursor cursor = ClasherDBContract.ClasherTHElement.selectByTHLevel(pnLevel);
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				while (!cursor.isAfterLast())
				{
					nID = cursor.getLong(0);
					oTHElement = new THElement(nID);
					moTHElements.put(nID, oTHElement);
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
