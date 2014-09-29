package com.derekgillett.clashercalendar;

import com.derekgillett.clashercalendar.ClasherDBContract.ClasherTHElement;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class THElement {
	private SQLiteDatabase moDB;
	
	private long mnID;
	private int mnTHLevel;
	private Element moElement;
	private int mnQuantity;

	public THElement(SQLiteDatabase poDB, long pnID) {
		moDB = poDB;
		mnID = pnID;
		Cursor cursor = selectSingle();
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				this.mnQuantity = cursor.getInt(0);
				this.mnTHLevel = cursor.getInt(1);
				this.moElement = new Element(moDB, cursor.getLong(2));
				this.mnQuantity = cursor.getInt(3);
			}
			cursor.close();
		}
	}
	
	public THElement(SQLiteDatabase poDB, 
			int pnTownhallLevel,
    		Element poElement,
    		int pnQuantity) {
		moDB = poDB;
		this.mnTHLevel = pnTownhallLevel;
		this.moElement = poElement;
		this.mnQuantity = pnQuantity;
		this.insert();
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

    public void createTable(SQLiteDatabase poDB) {
    	poDB.execSQL(ClasherDBContract.ClasherTHElement.SQL_DROP_TABLE);
    	poDB.execSQL(ClasherDBContract.ClasherTHElement.SQL_CREATE_TABLE);
    }
    
    private boolean insert() {
    	ContentValues values = new ContentValues();
    	values.put(ClasherTHElement.COLUMN_NAME_ELEMENT_ID, moElement.getId());
    	values.put(ClasherTHElement.COLUMN_NAME_QUANTITY, mnQuantity);
    	values.put(ClasherTHElement.COLUMN_NAME_TOWNHALL_LEVEL, this.mnTHLevel);

    	mnID = moDB.insert(ClasherTHElement.TABLE_NAME, null, values);
    	return mnID != 0;
    }
/*
    // only allow update of quantity
    private long update() {

    	ContentValues values = new ContentValues();
    	values.put(ClasherTHElement.COLUMN_NAME_QUANTITY, mnQuantity);

    	return moDB.update(ClasherDBContract.ClasherTHElement.TABLE_NAME, values, "_ID = " + mnID, null);
    }
  */  
    private Cursor selectSingle() {
		String[] selectionArgs = new String[] { String.valueOf(mnID) };
		return moDB.query(ClasherDBContract.ClasherTHElement.TABLE_NAME, 
				ClasherDBContract.ClasherTHElement.ALL_COLUMNS,  
				ClasherDBContract.ClasherTHElement.COLUMN_NAME_ID + " = ?", 
				selectionArgs, 
				null, null, null);        	
    }
}
