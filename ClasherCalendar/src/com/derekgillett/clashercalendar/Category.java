package com.derekgillett.clashercalendar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Category {
	private SQLiteDatabase moDB;

	@SuppressWarnings("unused")
	private static final String TAG = "Element";
	
	private long mnID;
	private String msCategory;
	
	public Category(SQLiteDatabase poDB, long pnID) {
		moDB = poDB;
		mnID = pnID;
		this.selectSingle();
	}
	
	public Category(SQLiteDatabase poDB, String psCategory) {
		moDB = poDB;
		msCategory = psCategory;
		this.insert();
	}

	public long getID() {
		return mnID;
	}

	public String getCategory() {
		return this.msCategory;
	}

    private boolean insert() {
    	ContentValues values = new ContentValues();
    	values.put(ClasherDBContract.ClasherCategory.COLUMN_NAME_CATEGORY_NAME, this.msCategory);

    	mnID = moDB.insert(ClasherDBContract.ClasherCategory.TABLE_NAME, null, values);
		return mnID != 0;
    }
/*
    private int update() {
    	ContentValues values = new ContentValues();
    	values.put(ClasherDBContract.ClasherCategory.COLUMN_NAME_CATEGORY_NAME, this.msCategory);

    	String selection = ClasherDBContract.ClasherCategory.COLUMN_NAME_ID + " = ?";
		String[] selectionArgs = new String[] { String.valueOf(mnID) };

		return moDB.update(
    			ClasherDBContract.ClasherCategory.TABLE_NAME, 
    			values, 
				selection, 
				selectionArgs); 
    }
*/
    /*
    private int delete() {
    	String selection = ClasherDBContract.ClasherCategory.COLUMN_NAME_ID + " = ?";
		String[] selectionArgs = new String[] { String.valueOf(mnID) };
    	return moDB.delete(
    			ClasherDBContract.ClasherCategory.TABLE_NAME, 
				selection, 
				selectionArgs); 
    }
*/
    private Cursor selectSingle() {
    	String[] columns = ClasherDBContract.ClasherCategory.ALL_COLUMNS;
    	String selection = ClasherDBContract.ClasherCategory.COLUMN_NAME_ID + " = ?";
		String[] selectionArgs = new String[] { String.valueOf(this.mnID) };
		return moDB.query(
				ClasherDBContract.ClasherCategory.TABLE_NAME, 
				columns, 
				selection, 
				selectionArgs, 
				null, null, null);        	
    }
 }
