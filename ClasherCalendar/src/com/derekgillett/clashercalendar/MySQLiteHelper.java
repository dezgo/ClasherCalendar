package com.derekgillett.clashercalendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "ClasherCalendar.db";
  private static final int DATABASE_VERSION = 1;
  private Context mContext;

  private static void execDDL(SQLiteDatabase database, Context ctx) {
	  InputStream inputStream;
	  try {
		  inputStream = ctx.getAssets().open("ddl.sql");
	  } catch (IOException e1) {
		  // TODO Auto-generated catch block
		  e1.printStackTrace();
		  inputStream = null;
	  }
	  
	  InputStreamReader inputReader = new InputStreamReader(inputStream);
	  BufferedReader buffreader = new BufferedReader(inputReader);
	  String line;
	  StringBuilder text = new StringBuilder();

	  try {
		  while (( line = buffreader.readLine()) != null) {
			  if (line.substring(0, 1) == ";") {
				  database.execSQL(text.toString());
				  text = null;
				  text = new StringBuilder();
			  } else
				  text.append(line);
		  }
	  } catch (IOException e) {

	  }
  }
  
  public MySQLiteHelper(Context context) {
	  super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  mContext = context;
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    Log.w(MySQLiteHelper.class.getName(), "Creating database");
    execDDL(database, mContext);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    onCreate(db);
  }

  // Element table name
  private static final String TABLE_ELEMENT = "tblElement";

  // Element Table Columns names
  private static final String KEY_ID = "ElementID";
  private static final String KEY_NAME = "ElementName";
  private static final String KEY_COSTTYPE = "CostType";

  private static final String[] COLUMNS = {KEY_ID,KEY_NAME,KEY_COSTTYPE};
  
  public void createDB() {
	  SQLiteDatabase db = this.getWritableDatabase();
  }
  
  public void addElement(Element pelement){
	  //for logging
	  Log.d("addElement", pelement.toString()); 

	  // 1. get reference to writable DB
	  SQLiteDatabase db = this.getWritableDatabase();

	  // 2. create ContentValues to add key "column"/value
	  ContentValues values = new ContentValues();
	  values.put(KEY_NAME, pelement.getName()); 
	  values.put(KEY_COSTTYPE, pelement.getCostType());

	  // 3. insert
	  db.insert(TABLE_ELEMENT, // table
			  null, //nullColumnHack
			  values); // key/value -> keys = column names/ values = column values

	  // 4. close
	  db.close(); 
  }

  public Element getElement(int id){
	  
	    // 1. get reference to readable DB
	    SQLiteDatabase db = this.getReadableDatabase();
	 
	    // 2. build query
	    Cursor cursor = 
	            db.query(TABLE_ELEMENT, // a. table
	            COLUMNS, // b. column names
	            " id = ?", // c. selections 
	            new String[] { String.valueOf(id) }, // d. selections args
	            null, // e. group by
	            null, // f. having
	            null, // g. order by
	            null); // h. limit
	 
	    // 3. if we got results get the first one
	    if (cursor != null)
	        cursor.moveToFirst();
	 
	    // 4. build element object
	    Element element = new Element();
	    element.setId(Integer.parseInt(cursor.getString(0)));
	    element.setName(cursor.getString(1));
	    element.setCostType(Integer.parseInt(cursor.getString(2)));
	 
	    //log 
	Log.d("getElement("+id+")", element.toString());
	 
	    // 5. return element
	    return element;
	}
} 