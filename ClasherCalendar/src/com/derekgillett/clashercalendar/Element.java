package com.derekgillett.clashercalendar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.util.LongSparseArray;
import android.util.Log;

public class Element {
	private SQLiteDatabase moDB;
	private static final String TAG = "Element";
	
	private long mnID = 0;
	private String msElementName = "";
	private CostType moCostType;
	private Category moCategory;
	private int mnMaxLevel;
	private LongSparseArray<ElementData> moElementData = new LongSparseArray<ElementData>();

	public Element(SQLiteDatabase poDB, long id) {
		mnID = id;
		moDB = poDB;
		this.loadElement();
		mnMaxLevel = getMaxLevel();
		for (int i=0; i<mnMaxLevel; i++) {
			ElementData oElementData = new ElementData(this,i+1);
			moElementData.put(i+1, oElementData);
		}
	}
	
	public Element(SQLiteDatabase poDB, String psElementName, Category poCategory, CostType poCostType) {
		moDB = poDB;
		msElementName = psElementName;
		moCostType = poCostType;
		moCategory = poCategory;
		this.insert();
		mnMaxLevel = getMaxLevel();
	}
	
	public Element clone() {
		return new Element(moDB, this.mnID);
	}
	
	public long getId() {
		return mnID;
	}
	
	public String getName() {
		return msElementName;
	}
	
	public void setName(String pelementName) {
		msElementName = pelementName;
	}
	
	public String toString() {
		return msElementName;
	}
	
	public CostType getCostType() {
		return moCostType;
	}
	
	public void setCostType(CostType pcostType) {
		moCostType = pcostType;
	}
	
	public Category getCategory() {
		return moCategory;
	}
	
	public void setCategory(Category poCategory) {
		moCategory = poCategory;
	}
	
	public ElementData getElementData(int pnLevel) {
		return moElementData.get(pnLevel);
	}

	/*
	private void updateElement(){
		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(Element.COLUMN_NAME, this.getName()); 
		values.put(Element.COLUMN_COSTTYPE, this.getCostType());
		values.put(Element.COLUMN_CATEGORY, this.getCategory());

		// 3. insert
		moDB.update(TABLE_ELEMENT, // table
				values, // key/value -> keys = column names/ values = column values
				" " + COLUMN_ID + " = ?", // c. selections 
				new String[] { String.valueOf(this.getId()) }); // d. selections args

	}
*/
	private int loadElement(){
		int colIndex;
		
		// 2. build query
		Cursor cursor = this.selectSingle();
		int nRtn = 0;
		 
		// 3. if we got results get the first one
		if (cursor == null)
			nRtn = 1;
		else {
			try {
				if (cursor.getCount() > 0) {
					cursor.moveToFirst();
				 
					// 4. build element object
					this.mnID = cursor.getLong(0);
					colIndex = cursor.getColumnIndexOrThrow(ClasherDBContract.ClasherElement.COLUMN_NAME_ELEMENT_NAME);
					this.setName(cursor.getString(colIndex));
					colIndex = cursor.getColumnIndexOrThrow(ClasherDBContract.ClasherElement.COLUMN_NAME_COST_TYPE);
					this.setCostType(new CostType(moDB, cursor.getInt(colIndex)));
					colIndex = cursor.getColumnIndexOrThrow(ClasherDBContract.ClasherElement.COLUMN_NAME_CATEGORY);
					this.setCategory(new Category(moDB, cursor.getInt(colIndex)));
				}
			} 
			catch (Exception e) {
				Log.e(TAG, e.getMessage());
				nRtn = 2;
			}
			finally {
				cursor.close();
			}
		}
		
		return nRtn;
	}	

	public int getMaxLevel() {
    	return getMaxLevel(11);
    }

    public int getMaxLevel(int pnTHLevel) {
		int rtn = 0;
		Cursor cursor = null;
		String[] columns = new String[] {"MAX(" + ClasherDBContract.ClasherElementData.COLUMN_NAME_ELEMENT_LEVEL + ")" };
    	String selection = ClasherDBContract.ClasherElementData.COLUMN_NAME_ELEMENT_ID + " = ? AND " + 
    			ClasherDBContract.ClasherElementData.COLUMN_NAME_TOWNHALL_MIN_LEVEL + " = ?";
		String[] selectionArgs = new String[] { String.valueOf(mnID), String.valueOf(pnTHLevel) };
    	try {
    		cursor = moDB.query(
    				ClasherDBContract.ClasherElementData.TABLE_NAME, 
    				columns, 
    				selection, 
    				selectionArgs, 
    				null, null, null);
    	}
    	catch (SQLiteException e) {
    		Log.e(TAG, e.getMessage());
    	}

		if (cursor != null) {
			try {
				if (cursor.getCount() > 0) {
					cursor.moveToFirst();
					rtn = cursor.getInt(0);
				}
				cursor.close();
			}
			catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}
		}
		return rtn;
    }


    private boolean insert() {
    	ContentValues values = new ContentValues();
    	values.put(ClasherDBContract.ClasherElement.COLUMN_NAME_CATEGORY, this.moCategory.getID());
    	values.put(ClasherDBContract.ClasherElement.COLUMN_NAME_COST_TYPE, this.moCostType.getID());
    	values.put(ClasherDBContract.ClasherElement.COLUMN_NAME_ELEMENT_NAME, this.msElementName);

    	mnID = moDB.insert(ClasherDBContract.ClasherElement.TABLE_NAME, null, values);
		return mnID != 0;
    }
/*
    private int update() {
    	ContentValues values = new ContentValues();
    	values.put(ClasherDBContract.ClasherElement.COLUMN_NAME_CATEGORY, this.moCategory.getID());
    	values.put(ClasherDBContract.ClasherElement.COLUMN_NAME_COST_TYPE, this.moCostType.getID());
    	values.put(ClasherDBContract.ClasherElement.COLUMN_NAME_ELEMENT_NAME, this.msElementName);

    	String selection = ClasherDBContract.ClasherElement.COLUMN_NAME_ID + " = ?";
		String[] selectionArgs = new String[] { String.valueOf(mnID) };

		return moDB.update(
    			ClasherDBContract.ClasherElement.TABLE_NAME, 
    			values, 
				selection, 
				selectionArgs); 
    }
*/
/*
    private int delete() {
    	String selection = ClasherDBContract.ClasherElement.COLUMN_NAME_ID + " = ?";
		String[] selectionArgs = new String[] { String.valueOf(mnID) };
    	return moDB.delete(
    			ClasherDBContract.ClasherElement.TABLE_NAME, 
				selection, 
				selectionArgs); 
    }
*/
    private Cursor selectSingle() {
    	String[] columns = ClasherDBContract.ClasherElement.ALL_COLUMNS;
    	String selection = ClasherDBContract.ClasherElement.COLUMN_NAME_ID + " = ?";
		String[] selectionArgs = new String[] { String.valueOf(this.mnID) };
		return moDB.query(
				ClasherDBContract.ClasherElement.TABLE_NAME, 
				columns, 
				selection, 
				selectionArgs, 
				null, null, null);        	
    }		
}
