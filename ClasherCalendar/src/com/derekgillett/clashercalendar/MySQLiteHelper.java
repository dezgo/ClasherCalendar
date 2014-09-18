package com.derekgillett.clashercalendar;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	// tag used when logging
  private static final String TAG = "MySQLiteHelper";
  
  private static final String DATABASE_NAME = "ClasherCalendar.db";
  private static final int DATABASE_VERSION = 4;

  /*
  private static void execInitSQL(SQLiteDatabase database, String fileName) {
	  InputStream inputStream;
	  try {
		  inputStream = MyApplication.getAppContext().getAssets().open(fileName);
	  } catch (IOException e1) {
		  Log.e(TAG, e1.getMessage());
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
  }*/
  
  public MySQLiteHelper() {
	  super(MyApplication.getAppContext(), DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    if (Utils.DEBUG) Log.w(TAG, "Creating / populating database");
    
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

	long nElementID;
	ClasherDBContract.ClasherElement.createTable(database);
	ClasherDBContract.ClasherElementData.createTable(database);
	ClasherDBContract.ClasherTHElement.createTable(database);
	
	//traps
	nElementID = ClasherDBContract.ClasherElement.insert(database, "Air Bomb", nCategoryTrap, nCostTypeGold);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 0, 4000, 0, 5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 0, 20000, 14400, 5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 0, 200000, 43200, 7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 0, 1500000, 86400, 9);
	ClasherDBContract.ClasherTHElement.insert(database, 5, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 6, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 5);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Bomb", nCategoryTrap, nCostTypeGold);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 0, 400, 0, 3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 0, 1000, 0, 3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 0, 10000, 0, 5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 0, 100000, 0, 7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 0, 1000000, 0, 8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 6, 0, 1500000, 0, 9);
	ClasherDBContract.ClasherTHElement.insert(database, 3, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 4, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 5, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 6, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 6);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 6);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 6);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 6);
	
	nElementID = ClasherDBContract.ClasherElement.insert(database, "Giant Bomb", nCategoryTrap, nCostTypeGold);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 0, 12500, 0, 6);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 0, 75000, 0, 6);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 0, 750000, 0, 8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 0, 2500000, 0, 10);
	ClasherDBContract.ClasherTHElement.insert(database, 6, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 5);
	
	nElementID = ClasherDBContract.ClasherElement.insert(database, "Seeking Air Mine", nCategoryTrap, nCostTypeGold);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 0, 15000, 0, 7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 0, 2000000, 0, 9);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 0, 4000000, 0, 10);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 5);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Spring Trap", nCategoryTrap, nCostTypeGold);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 0, 2000, 0, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 4, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 5, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 6, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 6);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 6);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 6);

	//defence
	nElementID = ClasherDBContract.ClasherElement.insert(database, "Air Defence", nCategoryDefence, nCostTypeGold);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 800, 22500, 18000, 4);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 860, 22500, 86400, 4);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 900, 22500, 259200, 5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 940, 22500, 432000, 6);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 990, 22500, 518400, 7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 6, 1040, 22500, 691200, 8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 7, 1100, 22500, 864000, 9);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 8, 1160, 22500, 1036800, 10);
	ClasherDBContract.ClasherTHElement.insert(database, 4, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 5, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 6, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 4);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Archer Tower", nCategoryDefence, nCostTypeGold);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 400, 1000, 900, 2);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 450, 2000, 1800, 2);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 500, 5000, 2700, 3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 550, 20000, 14400, 4);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 590, 80000, 43200, 5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 6, 610, 180000, 86400, 5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 7, 630, 360000, 172800, 6);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 8, 660, 720000, 259200, 7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 9, 690, 1500000, 345600, 8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 10, 720, 2500000, 432000, 8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 11, 750, 5000000, 518400, 9);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 12, 790, 7500000, 604800, 10);
	ClasherDBContract.ClasherTHElement.insert(database, 2, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 3, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 4, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 5, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 6, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 5);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 6);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 7);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Canon", nCategoryDefence, nCostTypeGold);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 400, 250, 60, 1);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 450, 1000, 900, 1);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 500, 4000, 2700, 2);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 550, 16000, 7200, 3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 590, 50000, 21600, 4);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 6, 610, 100000, 43200, 5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 7, 630, 200000, 86400, 6);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 8, 660, 400000, 172800, 7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 9, 690, 800000, 259200, 8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 10, 750, 1600000, 345600, 8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 11, 900, 3200000, 432000, 9);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 12, 1080, 6400000, 518400, 10);
	ClasherDBContract.ClasherTHElement.insert(database, 1, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 2, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 3, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 4, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 5, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 6, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 5);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 5);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 5);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 6);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Hidden Tesla", nCategoryDefence, nCostTypeGold);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 600, 1000000,86400,7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 630, 1250000,259200,7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 660, 1500000,432000,7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 690, 2000000,518400,8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 730, 2500000,691200,8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 6, 770, 3000000,864000,8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 7, 810, 3500000,1036800,9);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 8, 850, 5000000,1209600,10);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 4);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Mortar", nCategoryDefence, nCostTypeGold);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 400, 8000,28800,3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 450, 32000,43200,4);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 500, 120000,86400,5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 550, 400000,172800,6);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 590, 800000,345600,7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 6, 610, 1600000,432000,8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 7, 640, 3200000,604800,9);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 8, 670, 6400000,864000,10);
	ClasherDBContract.ClasherTHElement.insert(database, 3, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 4, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 5, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 6, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 3);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Wizard Tower", nCategoryDefence, nCostTypeGold);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 620, 180000,43200,5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 660, 360000,86400,5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 690, 720000,172800,6);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 720, 1280000,259200,7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 760, 1960000,345600,8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 6, 800, 2680000,432000,8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 7, 840, 5360000,604800,9);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 8, 880, 6480000,864000,10);
	ClasherDBContract.ClasherTHElement.insert(database, 5, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 6, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 4);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "X-Bow", nCategoryDefence, nCostTypeGold);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 1500, 3000000, 604800, 9);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 1900, 5000000, 864000, 9);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 2400, 7000000, 1209600, 9);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 2800, 8000000, 1209600, 10);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 3);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Wall", nCategoryDefence, nCostTypeGold);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 300, 200, 0, 2);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 500, 1000, 0, 2);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 700, 5000, 0, 3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 900, 10000, 0, 4);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 1400, 30000, 0, 5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 6, 2000, 75000, 0, 6);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 7, 2500, 200000, 0, 7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 8, 3000, 500000, 0, 8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 9, 4000, 1000000, 0, 9);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 10, 5500, 3000000, 0, 9);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 11, 7000, 4000000, 0, 10);
	ClasherDBContract.ClasherTHElement.insert(database, 2, nElementID, 25);
	ClasherDBContract.ClasherTHElement.insert(database, 3, nElementID, 50);
	ClasherDBContract.ClasherTHElement.insert(database, 4, nElementID, 75);
	ClasherDBContract.ClasherTHElement.insert(database, 5, nElementID, 100);
	ClasherDBContract.ClasherTHElement.insert(database, 6, nElementID, 125);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 175);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 225);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 250);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 250);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Inferno Tower", nCategoryDefence, nCostTypeGold);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 1500, 5000000, 604800, 10);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 1900, 6500000, 864000, 10);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 2200, 8000000, 1209600, 10);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 2);

	//resource
	nElementID = ClasherDBContract.ClasherElement.insert(database, "Dark Elixir Storage", nCategoryResource, nCostTypeGold);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 2000, 600000,86400,7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 2200, 1200000,172800,7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 2400, 1800000,259200,8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 2600, 2400000,345600,8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 2900, 3000000,432000,9);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 6, 3200, 3600000,518400,9);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 1);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Elixir Collector", nCategoryResource, nCostTypeGold);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 400, 150,60,1);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 450, 300,300,1);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 500, 700,900,2);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 550, 1400,3600,2);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 590, 3500,7200,3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 6, 610, 7000,21600,3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 7, 630, 14000,43200,4);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 8, 660, 28000,86400,4);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 9, 680, 56000,172800,5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 10, 710, 84000,259200,5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 11, 750, 168000,345600,7);
	ClasherDBContract.ClasherTHElement.insert(database, 1, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 2, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 3, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 4, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 5, nElementID, 5);
	ClasherDBContract.ClasherTHElement.insert(database, 6, nElementID, 6);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 6);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 6);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 6);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 7);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Elixir Storage", nCategoryResource, nCostTypeGold);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 400, 300,60,1);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 600, 750,1800,2);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 800, 1500,3600,2);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 1000, 3000,7200,3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 1200, 6000,10800,3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 6, 1500, 12000,14400,3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 7, 1650, 25000,21600,4);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 8, 1740, 50000,28800,4);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 9, 1820, 100000,43200,5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 10, 1920, 250000,86400,6);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 11, 2016, 500000,172800,7);
	ClasherDBContract.ClasherTHElement.insert(database, 1, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 2, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 3, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 4, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 5, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 6, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 4);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Gold Mine", nCategoryResource, nCostTypeElixir);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 400, 150,60,1);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 450, 300,300,1);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 500, 700,900,2);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 550, 1400,3600,2);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 590, 3000,7200,3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 6, 610, 7000,21600,3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 7, 630, 14000,43200,4);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 8, 660, 28000,86400,4);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 9, 680, 56000,172800,5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 10, 710, 84000,259200,5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 11, 750, 168000,345600,7);
	ClasherDBContract.ClasherTHElement.insert(database, 1, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 2, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 3, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 4, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 5, nElementID, 5);
	ClasherDBContract.ClasherTHElement.insert(database, 6, nElementID, 6);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 6);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 6);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 6);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 7);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Gold Storage", nCategoryResource, nCostTypeElixir);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 400, 300,60,1);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 600, 750,1800,2);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 800, 1500,3600,2);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 1000, 3000,7200,3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 1200, 6000,10800,3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 6, 1500, 12000,14400,3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 7, 1650, 25000,21600,4);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 8, 1740, 50000,28800,4);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 9, 1820, 100000,43200,5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 10, 1920, 250000,86400,6);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 11, 2016, 500000,172800,7);
	ClasherDBContract.ClasherTHElement.insert(database, 1, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 2, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 3, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 4, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 5, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 6, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 4);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Dark Elixir Drill", nCategoryResource, nCostTypeElixir);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 400, 1000000, 86400,8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 480, 1500000, 172800,8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 580, 2000000, 259200,8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 690, 3000000, 345600,9);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 830, 4000000, 518400,9);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 6, 1000, 5000000, 691200,9);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 3);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Builder's Hut", nCategoryResource, nCostTypeGems);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 250, 0, 0, 1);

	//army
	nElementID = ClasherDBContract.ClasherElement.insert(database, "Army Camp", nCategoryArmy, nCostTypeElixir);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 400, 250, 300, 1);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 500, 2500, 3600, 2);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 600, 10000, 10800, 3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 700, 100000, 28800, 4);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 800, 250000, 86400, 5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 6, 1000, 750000, 259200, 6);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 7, 1200, 2250000, 432000, 9);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 8, 1400, 6750000, 864000, 10);
	ClasherDBContract.ClasherTHElement.insert(database, 1, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 2, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 3, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 4, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 5, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 6, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 4);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Barracks", nCategoryArmy, nCostTypeElixir);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 250, 200, 60, 1);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 270, 1000, 900, 1);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 280, 2500, 7200, 1);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 290, 5000, 14400, 2);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 310, 10000, 36000, 3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 6, 320, 80000, 57600, 4);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 7, 340, 240000, 86400, 5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 8, 350, 700000, 172800, 6);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 9, 390, 1500000, 345600, 7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 10, 420, 2000000, 518400, 8);
	ClasherDBContract.ClasherTHElement.insert(database, 1, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 2, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 3, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 4, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 5, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 6, nElementID, 3);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 4);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 4);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Dark Barracks", nCategoryArmy, nCostTypeElixir);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 250, 750000, 259200, 7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 300, 1250000, 432000, 7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 350, 1750000, 518400, 8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 400, 2250000, 604800, 8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 450, 2750000, 691200, 9);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 2);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 2);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Laboratory", nCategoryArmy, nCostTypeElixir);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 250, 25000,1800,3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 270, 50000,18000,4);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 280, 90000,43200,5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 290, 270000,86400,6);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 310, 500000,172800,7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 6, 330, 1000000,345600,8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 7, 350, 2500000,432000,9);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 8, 370, 4000000,518400,10);
	ClasherDBContract.ClasherTHElement.insert(database, 3, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 4, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 5, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 6, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 1);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Spell Factory", nCategoryArmy, nCostTypeElixir);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 200, 200000,86400,5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 300, 400000,172800,6);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 400, 800000,345600,7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 500, 1600000,432000,9);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 600, 3200000,518400,10);
	ClasherDBContract.ClasherTHElement.insert(database, 5, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 6, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 1);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Barbarian King Alter", nCategoryArmy, nCostTypeDarkElixir);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 250, 10000, 0, 7);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 1);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Archer Queen Alter", nCategoryArmy, nCostTypeDarkElixir);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 250, 40000, 0, 9);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 1);

	// other
	nElementID = ClasherDBContract.ClasherElement.insert(database, "Clan Castle", nCategoryOther, nCostTypeGold);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 1000, 40000, 0, 3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 1400, 100000, 21600, 4);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 2000, 800000, 86400, 6);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 2600, 1800000, 172800, 8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 3000, 5000000, 604800, 9);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 6, 3400, 7000000, 1209600, 10);

	nElementID = ClasherDBContract.ClasherElement.insert(database, "Town Hall", nCategoryOther, nCostTypeGold);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 1, 1500, 0, 0, 0);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 2, 1600, 1000, 300, 1);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 3, 1850, 4000, 10800, 2);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 4, 2100, 25000, 86400, 3);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 5, 2400, 150000, 172800, 4);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 6, 2800, 750000, 345600, 5);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 7, 3200, 1200000, 518400, 6);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 8, 3700, 2000000, 691200, 7);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 9, 4200, 3000000, 864000, 8);
	ClasherDBContract.ClasherElementData.insert(database, nElementID, 10, 5000, 4000000, 1209600, 9);
	ClasherDBContract.ClasherTHElement.insert(database, 1, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 2, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 3, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 4, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 5, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 6, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 7, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 8, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 9, nElementID, 1);
	ClasherDBContract.ClasherTHElement.insert(database, 10, nElementID, 1);

	ClasherDBContract.ClasherPlayer.createTable(database);
	ClasherDBContract.ClasherPlayerElement.createTable(database);
    
    //execInitSQL(database, "ddl.sql");
    //execInitSQL(database, "dml.sql");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    if (Utils.DEBUG) Log.w(TAG,
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    onCreate(db);
  }
} 