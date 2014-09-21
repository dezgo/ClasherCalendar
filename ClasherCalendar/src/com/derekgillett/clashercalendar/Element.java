package com.derekgillett.clashercalendar;

import android.database.Cursor;
import android.support.v4.util.LongSparseArray;
import android.util.Log;

public class Element {
	// TAG for logging
	private static final String TAG = "Element";
	
	private long mnID = 0;
	private String msElementName = "";
	private int mnCostType;
	private int mnCategory;
	private int mnMaxLevel;
	private LongSparseArray<ElementData> moElementData = new LongSparseArray<ElementData>();

	public Element(long id) {
		this.loadElement(id);
		mnMaxLevel = getMaxLevel();
		for (int i=0; i<mnMaxLevel; i++) {
			ElementData oElementData = new ElementData(this,i+1);
			moElementData.put(i+1, oElementData);
		}
	}
	
	// return the maximum level this element can be for the given TH level
	public int getMaxLevel(int pnTHLevel) {
		return ClasherDBContract.ClasherElementData.getMaxLevel(mnID, pnTHLevel);
	}
	
	public Element clone() {
		return new Element(this.mnID);
	}
	
	// maximum level this element can be
	private int getMaxLevel() {
		return ClasherDBContract.ClasherElementData.getMaxLevel(mnID);
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
	
	public int getCostType() {
		return mnCostType;
	}
	
	public void setCostType(int pcostType) {
		mnCostType = pcostType;
	}
	
	public int getCategory() {
		return mnCategory;
	}
	
	public void setCategory(int pnCategory) {
		mnCategory = pnCategory;
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
	private int loadElement(long id){
		// 2. build query
		Cursor cursor = ClasherDBContract.ClasherElement.select(id);
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
					this.setName(cursor.getString(1) == null ? "" : cursor.getString(1));
					this.setCostType(cursor.getString(2) == null ? 0 : Integer.parseInt(cursor.getString(2)));
					this.setCategory(cursor.getString(3) == null ? 0 : Integer.parseInt(cursor.getString(3)));
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
}
