package com.derekgillett.clashercalendar;

import android.content.ContentValues;
import android.content.Context;
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
	  
	private Context moContext;
	private SQLiteDatabase moDB;
	private int mid;
	private String melementName;
	private int mcostType;

	public Element(Context poContext) {
		moContext = poContext;

		// 1. get reference to writable DB
		MySQLiteHelper oMySQLiteHelper = new MySQLiteHelper(moContext);
		moDB = oMySQLiteHelper.getWritableDatabase();
	}
	
	public Element(Context poContext, int id) {
		this(poContext);
		this.loadElement(id);
	}
	
	public int getId() {
		return mid;
	}
	
	public void setId(int pid) {
		mid = pid;
	}
	
	public String getName() {
		return melementName;
	}
	
	public void setName(String pelementName) {
		melementName = pelementName;
	}
	
	public String toString() {
		return melementName;
	}
	
	public int getCostType() {
		return mcostType;
	}
	
	public void setCostType(int pcostType) {
		mcostType = pcostType;
	}

	public void updateElement(){
		
		//for logging
		Log.d("addElement", this.toString()); 

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

	public void addElement(){
		
		//for logging
		Log.d("addElement", this.toString()); 

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
		Cursor cursor = moDB.rawQuery("SELECT count(*) FROM tblElement", null); 
		if (cursor == null)
			return 0;
		else {
			cursor.moveToFirst();
			return Integer.parseInt(cursor.getString(0));
		}
	}
}
