package com.derekgillett.clashercalendar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CostType {
	private SQLiteDatabase moDB;

	@SuppressWarnings("unused")
	private static final String TAG = "Element";
	
	private long mnID;
	private String msCostType;
	private int mnResID = 0;
	
	public CostType(SQLiteDatabase poDB, long pnID) {
		moDB = poDB;
		mnID = pnID;
		this.setResID();
		this.selectSingle();
	}
	
	public CostType(SQLiteDatabase poDB, long pnID, String psCostType) {
		moDB = poDB;
		mnID = pnID;
		msCostType = psCostType;
		this.insert();
		this.setResID();
	}

	public int getResID() {
		return mnResID;
	}

	private void setResID() {
    	if (mnID == 1)
    		mnResID = R.drawable.gold;

    	else if (mnID == 2)
    		mnResID = R.drawable.elixir;

    	else if (mnID == 3)
    		mnResID = R.drawable.dark_elixir;
		
	}
	
	public long getID() {
		return mnID;
	}

	public String getCostType() {
		return msCostType;
	}

    private void insert() {
    	ContentValues values = new ContentValues();
    	values.put(ClasherDBContract.ClasherCostType.COLUMN_NAME_ID, this.mnID);
    	values.put(ClasherDBContract.ClasherCostType.COLUMN_NAME_COST_TYPE_NAME, this.msCostType);
    	moDB.insert(ClasherDBContract.ClasherCostType.TABLE_NAME, null, values);
    }
/*
    private int update() {
    	ContentValues values = new ContentValues();
    	values.put(ClasherDBContract.ClasherCostType.COLUMN_NAME_COST_TYPE_NAME, this.msCostType);

    	String selection = ClasherDBContract.ClasherCostType.COLUMN_NAME_ID + " = ?";
		String[] selectionArgs = new String[] { String.valueOf(mnID) };

		return moDB.update(
    			ClasherDBContract.ClasherCostType.TABLE_NAME, 
    			values, 
				selection, 
				selectionArgs); 
    }

    private int delete() {
    	String selection = ClasherDBContract.ClasherCostType.COLUMN_NAME_ID + " = ?";
		String[] selectionArgs = new String[] { String.valueOf(mnID) };
    	return moDB.delete(
    			ClasherDBContract.ClasherCostType.TABLE_NAME, 
				selection, 
				selectionArgs); 
    }
*/
    private Cursor selectSingle() {
    	String[] columns = ClasherDBContract.ClasherCostType.ALL_COLUMNS;
    	String selection = ClasherDBContract.ClasherCostType.COLUMN_NAME_ID + " = ?";
		String[] selectionArgs = new String[] { String.valueOf(this.mnID) };
		return moDB.query(
				ClasherDBContract.ClasherCostType.TABLE_NAME, 
				columns, 
				selection, 
				selectionArgs, 
				null, null, null);        	
    }
 }
