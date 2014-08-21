package com.derekgillett.clashercalendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "ClasherCalendar.db";
  private static final int DATABASE_VERSION = 2;

  private static void execInitSQL(SQLiteDatabase database, String fileName) {
	  InputStream inputStream;
	  try {
		  inputStream = MyApplication.getAppContext().getAssets().open(fileName);
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
			  // skip empty lines, comment lines, and remove tabs and carriage returns
			  line = line.replace("\t","");
			  line = line.replace("\n","");
			  if (line.equals("")) { }
			  else if (line.length()<2) { }
			  else if (line.substring(0, 2).equals("--")) { }
			  else {
				  text.append(line);
				  if (line.charAt(line.length()-1) == ';') {
					  database.execSQL(text.toString());
					  text = null;
					  text = new StringBuilder();
				  }
			  }
		  }
	  } catch (IOException e) {

	  }
  }
  
  public MySQLiteHelper() {
	  super(MyApplication.getAppContext(), DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    Log.w(MySQLiteHelper.class.getName(), "Creating database");
    execInitSQL(database, "ddl.sql");
    Log.w(MySQLiteHelper.class.getName(), "Populating database");
    execInitSQL(database, "dml.sql");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    onCreate(db);
  }
} 