package com.derekgillett.clashercalendar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.util.LongSparseArray;

public class THElements {
	private SQLiteDatabase moDB;
	@SuppressWarnings("unused")
	private static final String TAG = "THElements";
	
	private LongSparseArray<THElement> moTHElements = new LongSparseArray<THElement>();
	private int mnIndex = 0;
	
	public THElements(SQLiteDatabase poDB, int pnLevel) {
		moDB = poDB;
		moTHElements = createArray(pnLevel);
	}
	
	// create an array of townhall elements for a given townhall level
	private LongSparseArray<THElement> createArray(int pnTHLevel) {
		LongSparseArray<THElement> oTHElements = new LongSparseArray<THElement>();
		THElement oTHElement;
		long nID;

		Cursor cursor = selectByTHLevel(pnTHLevel);
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				while (!cursor.isAfterLast())
				{
					// use element ID as the key for this array (given it's only for a single townhall level)
					nID = cursor.getLong(cursor.getColumnIndexOrThrow(ClasherDBContract.ClasherTHElement.COLUMN_NAME_ELEMENT_ID));
					oTHElement = new THElement(moDB, nID);
					oTHElements.put(nID, oTHElement);
					cursor.moveToNext();
				}
			}
			cursor.close();
		}
		return oTHElements;
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

    private Cursor selectByTHLevel(int pnTHLevel) {
		String[] selectionArgs = new String[] { String.valueOf(pnTHLevel) };
		return moDB.query(ClasherDBContract.ClasherTHElement.TABLE_NAME, ClasherDBContract.ClasherTHElement.ALL_COLUMNS, ClasherDBContract.ClasherTHElement.COLUMN_NAME_TOWNHALL_LEVEL + " = ?", selectionArgs, null, null, null);        	
    }
}
