package com.derekgillett.clashercalendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	// tag used when logging
  private static final String TAG = "MySQLiteHelper";
  
  private static final String DATABASE_NAME = "ClasherCalendar.db";
  private static final int DATABASE_VERSION = 4;

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
    if (Utils.DEBUG) Log.w(MySQLiteHelper.class.getName(), "Creating database");
    
	ClasherDBContract.ClasherCostType.createTable(database);
	long nCostTypeGold = ClasherDBContract.ClasherCostType.insert(database, "Gold");
	long nCostTypeElixir = ClasherDBContract.ClasherCostType.insert(database, "Elixir");
	long nCostTypeDarkElixir = ClasherDBContract.ClasherCostType.insert(database, "Dark Elixir");
	long nCostTypeGems = ClasherDBContract.ClasherCostType.insert(database, "Gems");
	
	ClasherDBContract.ClasherCategory.createTable(database);
	long nCategoryDefence = ClasherDBContract.ClasherCategory.insert(database, "Defence");
	long nCategoryResource = ClasherDBContract.ClasherCategory.insert(database, "Resource");
	long nCategoryArmy = ClasherDBContract.ClasherCategory.insert(database, "Army");
	long nCategoryOther = ClasherDBContract.ClasherCategory.insert(database, "Other");
	long nCategoryTrap = ClasherDBContract.ClasherCategory.insert(database, "Trap");

	ClasherDBContract.ClasherElement.createTable(database);
	
	//traps
	ClasherDBContract.ClasherElement.insert(database, "Air Bomb", nCategoryTrap, nCostTypeGold);
	ClasherDBContract.ClasherElement.insert(database, "Bomb", nCategoryTrap, nCostTypeGold);
	ClasherDBContract.ClasherElement.insert(database, "Giant Bomb", nCategoryTrap, nCostTypeGold);
	ClasherDBContract.ClasherElement.insert(database, "Seeking Air Mine", nCategoryTrap, nCostTypeGold);
	ClasherDBContract.ClasherElement.insert(database, "Spring Trap", nCategoryTrap, nCostTypeGold);

	//defence
	ClasherDBContract.ClasherElement.insert(database, "Air Defence", nCategoryDefence, nCostTypeGold);
	ClasherDBContract.ClasherElement.insert(database, "Archer Tower", nCategoryDefence, nCostTypeGold);
	ClasherDBContract.ClasherElement.insert(database, "Canon", nCategoryDefence, nCostTypeGold);
	ClasherDBContract.ClasherElement.insert(database, "Hidden Tesla", nCategoryDefence, nCostTypeGold);
	ClasherDBContract.ClasherElement.insert(database, "Mortar", nCategoryDefence, nCostTypeGold);
	ClasherDBContract.ClasherElement.insert(database, "Wizard Tower", nCategoryDefence, nCostTypeGold);
	ClasherDBContract.ClasherElement.insert(database, "X-Bow", nCategoryDefence, nCostTypeGold);
	ClasherDBContract.ClasherElement.insert(database, "Wall", nCategoryDefence, nCostTypeGold);
	ClasherDBContract.ClasherElement.insert(database, "Inferno Tower", nCategoryDefence, nCostTypeGold);

	//resource
	ClasherDBContract.ClasherElement.insert(database, "Dark Elixir Storage", nCategoryResource, nCostTypeGold);
	ClasherDBContract.ClasherElement.insert(database, "Elixir Collector", nCategoryResource, nCostTypeGold);
	ClasherDBContract.ClasherElement.insert(database, "Elixir Storage", nCategoryResource, nCostTypeGold);
	ClasherDBContract.ClasherElement.insert(database, "Gold Mine", nCategoryResource, nCostTypeElixir);
	ClasherDBContract.ClasherElement.insert(database, "Gold Storage", nCategoryResource, nCostTypeElixir);
	ClasherDBContract.ClasherElement.insert(database, "Dark Elixir Drill", nCategoryResource, nCostTypeElixir);
	ClasherDBContract.ClasherElement.insert(database, "Builder's Hut", nCategoryResource, nCostTypeGems);

	//army
	ClasherDBContract.ClasherElement.insert(database, "Army Camp", nCategoryArmy, nCostTypeElixir);
	ClasherDBContract.ClasherElement.insert(database, "Barracks", nCategoryArmy, nCostTypeElixir);
	ClasherDBContract.ClasherElement.insert(database, "Dark Barracks", nCategoryArmy, nCostTypeElixir);
	ClasherDBContract.ClasherElement.insert(database, "Laboratory", nCategoryArmy, nCostTypeElixir);
	ClasherDBContract.ClasherElement.insert(database, "Spell Factory", nCategoryArmy, nCostTypeElixir);
	ClasherDBContract.ClasherElement.insert(database, "Barbarian King Alter", nCategoryArmy, nCostTypeElixir);
	ClasherDBContract.ClasherElement.insert(database, "Archer Queen Alter", nCategoryArmy, nCostTypeElixir);

	// other
	ClasherDBContract.ClasherElement.insert(database, "Clan Castle", nCategoryOther, nCostTypeGold);
	ClasherDBContract.ClasherElement.insert(database, "Town Hall", nCategoryOther, nCostTypeGold);

	database.execSQL(ClasherDBContract.ClasherElementData.SQL_DROP_TABLE);
	database.execSQL(ClasherDBContract.ClasherElementData.SQL_CREATE_TABLE);
	database.execSQL(ClasherDBContract.ClasherPlayer.SQL_DROP_TABLE);
	database.execSQL(ClasherDBContract.ClasherPlayer.SQL_CREATE_TABLE);
	database.execSQL(ClasherDBContract.ClasherPlayerElement.SQL_DROP_TABLE);
	database.execSQL(ClasherDBContract.ClasherPlayerElement.SQL_CREATE_TABLE);
	database.execSQL(ClasherDBContract.ClasherTHElement.SQL_DROP_TABLE);
	database.execSQL(ClasherDBContract.ClasherTHElement.SQL_CREATE_TABLE);
    
    //execInitSQL(database, "ddl.sql");
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