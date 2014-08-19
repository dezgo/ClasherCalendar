package com.derekgillett.clashercalendar;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Element {
	// Element table name
	private static final String TABLE_ELEMENT = "tblElement";

	// Element Table Columns names
	private static final String KEY_ID = "ElementID";
	private static final String KEY_NAME = "ElementName";
	private static final String KEY_COSTTYPE = "CostType";

	private static final String[] COLUMNS = {KEY_ID,KEY_NAME,KEY_COSTTYPE};
	  
	private SQLiteDatabase moDB;
	private int mnID;
	private String msElementName;
	private int mnCostType;
	private int mnMaxLevel;
	private ArrayList<ElementData> moElementData = new ArrayList<ElementData>();

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
			moElementData.add(oElementData);
		}
	}
	
	// return the maximum level this element can be for the given TH level
	public int getMaxLevel(int pnTHLevel) {
		Log.d("Element", "getMaxLevel"); 
		
		Cursor cursor = moDB.rawQuery("SELECT Max(ElementLevel) FROM tblElementData WHERE ElementID = ? AND THMinLevel <= ?", 
				new String[] { String.valueOf(mnID), String.valueOf(pnTHLevel) }); 
		if (cursor == null)
			return 0;
		else if (cursor.getCount() < 1) 
			return 0;
		else {
			cursor.moveToFirst();
			return Integer.parseInt(cursor.getString(0));
		}
	}
	
	// maximum level this element can be
	private int getMaxLevel() {
		Log.d("Element", "getMaxLevel"); 
		
		Cursor cursor = moDB.rawQuery("SELECT Max(ElementLevel) FROM tblElementData WHERE ElementID = ?", 
				new String[] { String.valueOf(mnID) }); 
		if (cursor == null)
			return 0;
		else if (cursor.getCount() < 1) 
			return 0;
		else {
			cursor.moveToFirst();
			return Integer.parseInt(cursor.getString(0));
		}
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
	
	public ElementData getElementData(int pnLevel) {
		return moElementData.get(pnLevel-1);
	}

	public void updateElement(){
		Log.d("Element", "updateElement"); 

		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, this.getName()); 
		values.put(KEY_COSTTYPE, this.getCostType());

		// 3. insert
		moDB.update(TABLE_ELEMENT, // table
				values, // key/value -> keys = column names/ values = column values
				" " + KEY_ID + " = ?", // c. selections 
				new String[] { String.valueOf(this.getId()) }); // d. selections args

	}

	public void addElement() {		
		Log.d("Element", "addElement"); 

		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, this.getName()); 
		values.put(KEY_COSTTYPE, this.getCostType());

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
						" " + KEY_ID + " = ?", // c. selections 
						new String[] { String.valueOf(id) }, // d. selections args
						null, // e. group by
						null, // f. having
						null, // g. order by
						null); // h. limit
		 
		// 3. if we got results get the first one
		if (cursor != null)
			cursor.moveToFirst();
		 
		// 4. build element object
		this.setId(Integer.parseInt(cursor.getString(0)));
		this.setName(cursor.getString(1));
		this.setCostType(Integer.parseInt(cursor.getString(2)));
		 
		//log 
		Log.d("getElement("+id+")", this.toString());

	}
	
	public int countElements() {
		Log.d("Element", "countElements"); 
		
		Cursor cursor = moDB.rawQuery("SELECT count(*) FROM tblElement", null); 
		if (cursor == null)
			return 0;
		else {
			cursor.moveToFirst();
			return Integer.parseInt(cursor.getString(0));
		}
	}
}
