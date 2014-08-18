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

  private static void execInitSQL(SQLiteDatabase database, Context ctx, String fileName) {
	  InputStream inputStream;
	  try {
		  inputStream = ctx.getAssets().open(fileName);
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
			  if (!line.equals("")) {
				  line = line.replace("\t","");
				  line = line.replace("\n","");
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
  
  public MySQLiteHelper(Context context) {
	  super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  mContext = context;
	  SQLiteDatabase database = this.getWritableDatabase();
	  this.onCreate(database);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    Log.w(MySQLiteHelper.class.getName(), "Creating database");
    execInitSQL(database, mContext, "ddl.sql");
    execInitSQL(database, mContext, "dml.sql");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    onCreate(db);
  }
} 