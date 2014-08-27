package com.derekgillett.clashercalendar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.util.LongSparseArray;
import android.util.Log;

public class Element {
	// Element table name
	private static final String TABLE_ELEMENT = "tblElement";

	// Element Table Columns names
	private static final String COLUMN_ID = "ElementID";
	private static final String COLUMN_NAME = "ElementName";
	private static final String COLUMN_COSTTYPE = "CostType";
	private static final String COLUMN_CATEGORY = "Category";

	private static final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_COSTTYPE, COLUMN_CATEGORY};
	  
	private SQLiteDatabase moDB;
	private int mnID;
	private String msElementName = "";
	private int mnCostType;
	private int mnCategory;
	private int mnMaxLevel;
	private LongSparseArray<ElementData> moElementData = new LongSparseArray<ElementData>();

	public Element() {
		Log.d("Element", "Constructor");

		// 1. get reference to writable DB
		moDB = MyApplication.getDB();
	}
	
	public Element(int id) {
		this();
		this.loadElement(id);
		mnMaxLevel = getMaxLevel();
		for (int i=0; i<mnMaxLevel; i++) {
			ElementData oElementData = new ElementData(this,i+1);
			moElementData.put(i+1, oElementData);
		}
	}
	
	// return the maximum level this element can be for the given TH level
	public int getMaxLevel(int pnTHLevel) {
		Log.d("Element", "getMaxLevel"); 
		
		int rtn = 0;
		
		Cursor cursor = moDB.rawQuery("SELECT Max(ElementLevel) FROM tblElementData WHERE ElementID = ? AND THMinLevel <= ?", 
				new String[] { String.valueOf(mnID), String.valueOf(pnTHLevel) }); 
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				if (cursor.getString(0) != null)
					rtn = Integer.parseInt(cursor.getString(0));
			}
			cursor.close();
		}
		return rtn;
	}
	
	public Element clone() {
		return new Element(this.mnID);
	}
	
	// maximum level this element can be
	private int getMaxLevel() {
		Log.d("Element", "getMaxLevel"); 
		
		int rtn = 0;
		
		Cursor cursor = moDB.rawQuery("SELECT Max(ElementLevel) FROM tblElementData WHERE ElementID = ?", 
				new String[] { String.valueOf(mnID) }); 
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				if (cursor.getString(0) != null)
					rtn = Integer.parseInt(cursor.getString(0));
			}
			cursor.close();
		}
		return rtn;
	}
	
	public int getId() {
		return mnID;
	}
	
	public void setId(int pid) {
		mnID = pid;
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

	public void updateElement(){
		Log.d("Element", "updateElement"); 

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

	public void addElement() {		
		Log.d("Element", "addElement"); 

		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(Element.COLUMN_NAME, this.getName()); 
		values.put(Element.COLUMN_COSTTYPE, this.getCostType());
		values.put(Element.COLUMN_CATEGORY,  this.getCategory());

		// 3. insert
		moDB.insert(TABLE_ELEMENT, // table
				null, //nullColumnHack
				values); // key/value -> keys = column names/ values = column values

	}

	public void loadElement(int id){
		Log.d("Element", "loadElement");
		  
		// 2. build query
		Cursor cursor = 
				moDB.query(TABLE_ELEMENT, // a. table
						COLUMNS, // b. column names
						" " + COLUMN_ID + " = ?", // c. selections 
						new String[] { String.valueOf(id) }, // d. selections args
						null, // e. group by
						null, // f. having
						null, // g. order by
						null); // h. limit
		 
		// 3. if we got results get the first one
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
			 
				// 4. build element object
				this.setId(cursor.getString(0) == null ? 0 : Integer.parseInt(cursor.getString(0)));
				this.setName(cursor.getString(1) == null ? "" : cursor.getString(1));
				this.setCostType(cursor.getString(2) == null ? 0 : Integer.parseInt(cursor.getString(2)));
				this.setCategory(cursor.getString(3) == null ? 0 : Integer.parseInt(cursor.getString(3)));
			}
			cursor.close();
		}

		//log 
		Log.d("getElement("+id+")", this.toString());

	}
	
	public int countElements() {
		Log.d("Element", "countElements"); 
		
		int rtn = 0;
		
		Cursor cursor = moDB.rawQuery("SELECT count(*) FROM tblElement", null); 
		if (cursor != null) {
			cursor.moveToFirst();
			rtn = cursor.getString(0) == null ? 0 : Integer.parseInt(cursor.getString(0));
			cursor.close();
		}
		return rtn;
	}
}
