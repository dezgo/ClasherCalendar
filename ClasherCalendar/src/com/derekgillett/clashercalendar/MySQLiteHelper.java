package com.derekgillett.clashercalendar;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	// tag used when logging
  private static final String TAG = "MySQLiteHelper";
  
  private static final String DATABASE_NAME = "ClasherCalendar.db";
  private static final int DATABASE_VERSION = 4;
  
  private static MySQLiteHelper sInstance;

  private MySQLiteHelper() {
	  super(MyApplication.getAppContext(), DATABASE_NAME, null, DATABASE_VERSION);
  }
  
  public static MySQLiteHelper getInstance() {

	    if (sInstance == null) {
	      sInstance = new MySQLiteHelper();
	    }
	    return sInstance;
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    if (Utils.DEBUG) Log.w(TAG, "Creating / populating database");
    
    // Cost Types
	database.execSQL(ClasherDBContract.ClasherCostType.SQL_DROP_TABLE);
	database.execSQL(ClasherDBContract.ClasherCostType.SQL_CREATE_TABLE);
	CostType oCostTypeGold = new CostType(database, Utils.CostTypeEnum.Gold.getId(), "Gold");
	CostType oCostTypeElixir = new CostType(database, Utils.CostTypeEnum.Elixir.getId(), "Elixir");
	CostType oCostTypeDarkElixir = new CostType(database, Utils.CostTypeEnum.DarkElixir.getId(), "Dark Elixir");
	CostType oCostTypeGems = new CostType(database, Utils.CostTypeEnum.Gems.getId(), "Gems");
	
	// Categories
	database.execSQL(ClasherDBContract.ClasherCategory.SQL_DROP_TABLE);
	database.execSQL(ClasherDBContract.ClasherCategory.SQL_CREATE_TABLE);
	Category oCategoryDefence = new Category(database, "Defence");
	Category oCategoryResource = new Category(database, "Resource");
	Category oCategoryArmy = new Category(database, "Army");
	Category oCategoryOther = new Category(database, "Other");
	Category oCategoryTrap = new Category(database, "Trap");
	Category oCategoryWall = new Category(database, "Wall");

	database.execSQL(ClasherDBContract.ClasherElement.SQL_DROP_TABLE);
	database.execSQL(ClasherDBContract.ClasherElement.SQL_CREATE_TABLE);
	database.execSQL(ClasherDBContract.ClasherElementData.SQL_DROP_TABLE);
	database.execSQL(ClasherDBContract.ClasherElementData.SQL_CREATE_TABLE);
	database.execSQL(ClasherDBContract.ClasherTHElement.SQL_DROP_TABLE);
	database.execSQL(ClasherDBContract.ClasherTHElement.SQL_CREATE_TABLE);
	
	Element oElement;

	//traps
	oElement = new Element(database, "Air Bomb", oCategoryTrap, oCostTypeGold);
	new ElementData(database, oElement, 1, 0, 4000, 0, 5);
	new ElementData(database, oElement, 2, 0, 20000, 14400, 5);
	new ElementData(database, oElement, 3, 0, 200000, 43200, 7);
	new ElementData(database, oElement, 4, 0, 1500000, 86400, 9);
	new THElement(database, 5, oElement, 2);
	new THElement(database, 6, oElement, 2);
	new THElement(database, 7, oElement, 2);
	new THElement(database, 8, oElement, 4);
	new THElement(database, 9, oElement, 4);
	new THElement(database, 10, oElement, 5);

	oElement = new Element(database, "Bomb", oCategoryTrap, oCostTypeGold);
	new ElementData(database, oElement, 1, 0, 400, 0, 3);
	new ElementData(database, oElement, 2, 0, 1000, 0, 3);
	new ElementData(database, oElement, 3, 0, 10000, 0, 5);
	new ElementData(database, oElement, 4, 0, 100000, 0, 7);
	new ElementData(database, oElement, 5, 0, 1000000, 0, 8);
	new ElementData(database, oElement, 6, 0, 1500000, 0, 9);
	new THElement(database, 3, oElement, 2);
	new THElement(database, 4, oElement, 2);
	new THElement(database, 5, oElement, 4);
	new THElement(database, 6, oElement, 4);
	new THElement(database, 7, oElement, 6);
	new THElement(database, 8, oElement, 6);
	new THElement(database, 9, oElement, 6);
	new THElement(database, 10, oElement, 6);
	
	oElement = new Element(database, "Giant Bomb", oCategoryTrap, oCostTypeGold);
	new ElementData(database, oElement, 1, 0, 12500, 0, 6);
	new ElementData(database, oElement, 2, 0, 75000, 0, 6);
	new ElementData(database, oElement, 3, 0, 750000, 0, 8);
	new ElementData(database, oElement, 4, 0, 2500000, 0, 10);
	new THElement(database, 6, oElement, 1);
	new THElement(database, 7, oElement, 2);
	new THElement(database, 8, oElement, 3);
	new THElement(database, 9, oElement, 4);
	new THElement(database, 10, oElement, 5);
	
	oElement = new Element(database, "Seeking Air Mine", oCategoryTrap, oCostTypeGold);
	new ElementData(database, oElement, 1, 0, 15000, 0, 7);
	new ElementData(database, oElement, 2, 0, 2000000, 0, 9);
	new ElementData(database, oElement, 3, 0, 4000000, 0, 10);
	new THElement(database, 7, oElement, 1);
	new THElement(database, 8, oElement, 2);
	new THElement(database, 9, oElement, 4);
	new THElement(database, 10, oElement, 5);

	oElement = new Element(database, "Spring Trap", oCategoryTrap, oCostTypeGold);
	new ElementData(database, oElement, 0, 0, 2000, 0, 4);
	new THElement(database, 4, oElement, 2);
	new THElement(database, 5, oElement, 2);
	new THElement(database, 6, oElement, 4);
	new THElement(database, 7, oElement, 4);
	new THElement(database, 8, oElement, 6);
	new THElement(database, 9, oElement, 6);
	new THElement(database, 10, oElement, 6);
	
	oElement = new Element(database, "Skeleton Trap", oCategoryTrap, oCostTypeGold);
	new ElementData(database, oElement, 1, 0,6000, 0, 8);
	new ElementData(database, oElement, 2, 0,600000, 21600, 8);
	new ElementData(database, oElement, 3, 0,1300000, 86400, 9);
	new THElement(database, 8, oElement, 2);
	new THElement(database, 9, oElement, 2);

	//defence
	oElement = new Element(database, "Air Defence", oCategoryDefence, oCostTypeGold);
	new ElementData(database, oElement, 1, 800, 22500, 18000, 4);
	new ElementData(database, oElement, 2, 860, 90500, 86400, 4);
	new ElementData(database, oElement, 3, 900, 270000, 259200, 5);
	new ElementData(database, oElement, 4, 940, 540000, 432000, 6);
	new ElementData(database, oElement, 5, 990, 1080000, 518400, 7);
	new ElementData(database, oElement, 6, 1040, 2160000, 691200, 8);
	new ElementData(database, oElement, 7, 1100, 4320000, 864000, 9);
	new ElementData(database, oElement, 8, 1160, 7560000, 1036800, 10);
	new THElement(database, 4, oElement, 1);
	new THElement(database, 5, oElement, 1);
	new THElement(database, 6, oElement, 1);
	new THElement(database, 7, oElement, 2);
	new THElement(database, 8, oElement, 3);
	new THElement(database, 9, oElement, 4);
	new THElement(database, 10, oElement, 4);

	oElement = new Element(database, "Archer Tower", oCategoryDefence, oCostTypeGold);
	new ElementData(database, oElement, 1, 400, 1000, 900, 2);
	new ElementData(database, oElement, 2, 450, 2000, 1800, 2);
	new ElementData(database, oElement, 3, 500, 5000, 2700, 3);
	new ElementData(database, oElement, 4, 550, 20000, 14400, 4);
	new ElementData(database, oElement, 5, 590, 80000, 43200, 5);
	new ElementData(database, oElement, 6, 610, 180000, 86400, 5);
	new ElementData(database, oElement, 7, 630, 360000, 172800, 6);
	new ElementData(database, oElement, 8, 660, 720000, 259200, 7);
	new ElementData(database, oElement, 9, 690, 1500000, 345600, 8);
	new ElementData(database, oElement, 10, 720, 2500000, 432000, 8);
	new ElementData(database, oElement, 11, 750, 5000000, 518400, 9);
	new ElementData(database, oElement, 12, 790, 7500000, 604800, 10);
	new THElement(database, 2, oElement, 1);
	new THElement(database, 3, oElement, 1);
	new THElement(database, 4, oElement, 2);
	new THElement(database, 5, oElement, 3);
	new THElement(database, 6, oElement, 3);
	new THElement(database, 7, oElement, 4);
	new THElement(database, 8, oElement, 5);
	new THElement(database, 9, oElement, 6);
	new THElement(database, 10, oElement, 7);

	oElement = new Element(database, "Canon", oCategoryDefence, oCostTypeGold);
	new ElementData(database, oElement, 1, 400, 250, 60, 1);
	new ElementData(database, oElement, 2, 450, 1000, 900, 1);
	new ElementData(database, oElement, 3, 500, 4000, 2700, 2);
	new ElementData(database, oElement, 4, 550, 16000, 7200, 3);
	new ElementData(database, oElement, 5, 590, 50000, 21600, 4);
	new ElementData(database, oElement, 6, 610, 100000, 43200, 5);
	new ElementData(database, oElement, 7, 630, 200000, 86400, 6);
	new ElementData(database, oElement, 8, 660, 400000, 172800, 7);
	new ElementData(database, oElement, 9, 690, 800000, 259200, 8);
	new ElementData(database, oElement, 10, 750, 1600000, 345600, 8);
	new ElementData(database, oElement, 11, 900, 3200000, 432000, 9);
	new ElementData(database, oElement, 12, 1080, 6400000, 518400, 10);
	new THElement(database, 1, oElement, 2);
	new THElement(database, 2, oElement, 2);
	new THElement(database, 3, oElement, 2);
	new THElement(database, 4, oElement, 2);
	new THElement(database, 5, oElement, 3);
	new THElement(database, 6, oElement, 3);
	new THElement(database, 7, oElement, 5);
	new THElement(database, 8, oElement, 5);
	new THElement(database, 9, oElement, 5);
	new THElement(database, 10, oElement, 6);

	oElement = new Element(database, "Hidden Tesla", oCategoryDefence, oCostTypeGold);
	new ElementData(database, oElement, 1, 600, 1000000,86400,7);
	new ElementData(database, oElement, 2, 630, 1250000,259200,7);
	new ElementData(database, oElement, 3, 660, 1500000,432000,7);
	new ElementData(database, oElement, 4, 690, 2000000,518400,8);
	new ElementData(database, oElement, 5, 730, 2500000,691200,8);
	new ElementData(database, oElement, 6, 770, 3000000,864000,8);
	new ElementData(database, oElement, 7, 810, 3500000,1036800,9);
	new ElementData(database, oElement, 8, 850, 5000000,1209600,10);
	new THElement(database, 7, oElement, 2);
	new THElement(database, 8, oElement, 3);
	new THElement(database, 9, oElement, 4);
	new THElement(database, 10, oElement, 4);

	oElement = new Element(database, "Mortar", oCategoryDefence, oCostTypeGold);
	new ElementData(database, oElement, 1, 400, 8000,28800,3);
	new ElementData(database, oElement, 2, 450, 32000,43200,4);
	new ElementData(database, oElement, 3, 500, 120000,86400,5);
	new ElementData(database, oElement, 4, 550, 400000,172800,6);
	new ElementData(database, oElement, 5, 590, 800000,345600,7);
	new ElementData(database, oElement, 6, 610, 1600000,432000,8);
	new ElementData(database, oElement, 7, 640, 3200000,604800,9);
	new ElementData(database, oElement, 8, 670, 6400000,864000,10);
	new THElement(database, 3, oElement, 1);
	new THElement(database, 4, oElement, 1);
	new THElement(database, 5, oElement, 1);
	new THElement(database, 6, oElement, 2);
	new THElement(database, 7, oElement, 3);
	new THElement(database, 8, oElement, 4);
	new THElement(database, 9, oElement, 4);
	new THElement(database, 10, oElement, 4);

	oElement = new Element(database, "Wizard Tower", oCategoryDefence, oCostTypeGold);
	new ElementData(database, oElement, 1, 620, 180000,43200,5);
	new ElementData(database, oElement, 2, 660, 360000,86400,5);
	new ElementData(database, oElement, 3, 690, 720000,172800,6);
	new ElementData(database, oElement, 4, 720, 1280000,259200,7);
	new ElementData(database, oElement, 5, 760, 1960000,345600,8);
	new ElementData(database, oElement, 6, 800, 2680000,432000,8);
	new ElementData(database, oElement, 7, 840, 5360000,604800,9);
	new ElementData(database, oElement, 8, 880, 6480000,864000,10);
	new THElement(database, 5, oElement, 1);
	new THElement(database, 6, oElement, 2);
	new THElement(database, 7, oElement, 2);
	new THElement(database, 8, oElement, 3);
	new THElement(database, 9, oElement, 4);
	new THElement(database, 10, oElement, 4);

	oElement = new Element(database, "X-Bow", oCategoryDefence, oCostTypeGold);
	new ElementData(database, oElement, 1, 1500, 3000000, 604800, 9);
	new ElementData(database, oElement, 2, 1900, 5000000, 864000, 9);
	new ElementData(database, oElement, 3, 2400, 7000000, 1209600, 9);
	new ElementData(database, oElement, 4, 2800, 8000000, 1209600, 10);
	new THElement(database, 9, oElement, 2);
	new THElement(database, 10, oElement, 3);

	oElement = new Element(database, "Wall", oCategoryWall, oCostTypeGold);
	new ElementData(database, oElement, 1, 300, 200, 0, 2);
	new ElementData(database, oElement, 2, 500, 1000, 0, 2);
	new ElementData(database, oElement, 3, 700, 5000, 0, 3);
	new ElementData(database, oElement, 4, 900, 10000, 0, 4);
	new ElementData(database, oElement, 5, 1400, 30000, 0, 5);
	new ElementData(database, oElement, 6, 2000, 75000, 0, 6);
	new ElementData(database, oElement, 7, 2500, 200000, 0, 7);
	new ElementData(database, oElement, 8, 3000, 500000, 0, 8);
	new ElementData(database, oElement, 9, 4000, 1000000, 0, 9);
	new ElementData(database, oElement, 10, 5500, 3000000, 0, 9);
	new ElementData(database, oElement, 11, 7000, 4000000, 0, 10);
	new THElement(database, 2, oElement, 25);
	new THElement(database, 3, oElement, 50);
	new THElement(database, 4, oElement, 75);
	new THElement(database, 5, oElement, 100);
	new THElement(database, 6, oElement, 125);
	new THElement(database, 7, oElement, 175);
	new THElement(database, 8, oElement, 225);
	new THElement(database, 9, oElement, 250);
	new THElement(database, 10, oElement, 250);

	oElement = new Element(database, "Inferno Tower", oCategoryDefence, oCostTypeGold);
	new ElementData(database, oElement, 1, 1500, 5000000, 604800, 10);
	new ElementData(database, oElement, 2, 1900, 6500000, 864000, 10);
	new ElementData(database, oElement, 3, 2200, 8000000, 1209600, 10);
	new THElement(database, 10, oElement, 2);

	//resource
	oElement = new Element(database, "Dark Elixir Storage", oCategoryResource, oCostTypeGold);
	new ElementData(database, oElement, 1, 2000, 600000,86400,7);
	new ElementData(database, oElement, 2, 2200, 1200000,172800,7);
	new ElementData(database, oElement, 3, 2400, 1800000,259200,8);
	new ElementData(database, oElement, 4, 2600, 2400000,345600,8);
	new ElementData(database, oElement, 5, 2900, 3000000,432000,9);
	new ElementData(database, oElement, 6, 3200, 3600000,518400,9);
	new THElement(database, 7, oElement, 1);
	new THElement(database, 8, oElement, 1);
	new THElement(database, 9, oElement, 1);
	new THElement(database, 10, oElement, 1);

	oElement = new Element(database, "Elixir Collector", oCategoryResource, oCostTypeGold);
	new ElementData(database, oElement, 1, 400, 150,60,1);
	new ElementData(database, oElement, 2, 450, 300,300,1);
	new ElementData(database, oElement, 3, 500, 700,900,2);
	new ElementData(database, oElement, 4, 550, 1400,3600,2);
	new ElementData(database, oElement, 5, 590, 3500,7200,3);
	new ElementData(database, oElement, 6, 610, 7000,21600,3);
	new ElementData(database, oElement, 7, 630, 14000,43200,4);
	new ElementData(database, oElement, 8, 660, 28000,86400,4);
	new ElementData(database, oElement, 9, 680, 56000,172800,5);
	new ElementData(database, oElement, 10, 710, 84000,259200,5);
	new ElementData(database, oElement, 11, 750, 168000,345600,7);
	new THElement(database, 1, oElement, 1);
	new THElement(database, 2, oElement, 2);
	new THElement(database, 3, oElement, 3);
	new THElement(database, 4, oElement, 4);
	new THElement(database, 5, oElement, 5);
	new THElement(database, 6, oElement, 6);
	new THElement(database, 7, oElement, 6);
	new THElement(database, 8, oElement, 6);
	new THElement(database, 9, oElement, 6);
	new THElement(database, 10, oElement, 7);

	oElement = new Element(database, "Elixir Storage", oCategoryResource, oCostTypeGold);
	new ElementData(database, oElement, 1, 400, 300,60,1);
	new ElementData(database, oElement, 2, 600, 750,1800,2);
	new ElementData(database, oElement, 3, 800, 1500,3600,2);
	new ElementData(database, oElement, 4, 1000, 3000,7200,3);
	new ElementData(database, oElement, 5, 1200, 6000,10800,3);
	new ElementData(database, oElement, 6, 1500, 12000,14400,3);
	new ElementData(database, oElement, 7, 1650, 25000,21600,4);
	new ElementData(database, oElement, 8, 1740, 50000,28800,4);
	new ElementData(database, oElement, 9, 1820, 100000,43200,5);
	new ElementData(database, oElement, 10, 1920, 250000,86400,6);
	new ElementData(database, oElement, 11, 2016, 500000,172800,7);
	new THElement(database, 1, oElement, 1);
	new THElement(database, 2, oElement, 1);
	new THElement(database, 3, oElement, 2);
	new THElement(database, 4, oElement, 2);
	new THElement(database, 5, oElement, 2);
	new THElement(database, 6, oElement, 2);
	new THElement(database, 7, oElement, 2);
	new THElement(database, 8, oElement, 3);
	new THElement(database, 9, oElement, 4);
	new THElement(database, 10, oElement, 4);

	oElement = new Element(database, "Gold Mine", oCategoryResource, oCostTypeElixir);
	new ElementData(database, oElement, 1, 400, 150,60,1);
	new ElementData(database, oElement, 2, 450, 300,300,1);
	new ElementData(database, oElement, 3, 500, 700,900,2);
	new ElementData(database, oElement, 4, 550, 1400,3600,2);
	new ElementData(database, oElement, 5, 590, 3000,7200,3);
	new ElementData(database, oElement, 6, 610, 7000,21600,3);
	new ElementData(database, oElement, 7, 630, 14000,43200,4);
	new ElementData(database, oElement, 8, 660, 28000,86400,4);
	new ElementData(database, oElement, 9, 680, 56000,172800,5);
	new ElementData(database, oElement, 10, 710, 84000,259200,5);
	new ElementData(database, oElement, 11, 750, 168000,345600,7);
	new THElement(database, 1, oElement, 1);
	new THElement(database, 2, oElement, 2);
	new THElement(database, 3, oElement, 3);
	new THElement(database, 4, oElement, 4);
	new THElement(database, 5, oElement, 5);
	new THElement(database, 6, oElement, 6);
	new THElement(database, 7, oElement, 6);
	new THElement(database, 8, oElement, 6);
	new THElement(database, 9, oElement, 6);
	new THElement(database, 10, oElement, 7);

	oElement = new Element(database, "Gold Storage", oCategoryResource, oCostTypeElixir);
	new ElementData(database, oElement, 1, 400, 300,60,1);
	new ElementData(database, oElement, 2, 600, 750,1800,2);
	new ElementData(database, oElement, 3, 800, 1500,3600,2);
	new ElementData(database, oElement, 4, 1000, 3000,7200,3);
	new ElementData(database, oElement, 5, 1200, 6000,10800,3);
	new ElementData(database, oElement, 6, 1500, 12000,14400,3);
	new ElementData(database, oElement, 7, 1650, 25000,21600,4);
	new ElementData(database, oElement, 8, 1740, 50000,28800,4);
	new ElementData(database, oElement, 9, 1820, 100000,43200,5);
	new ElementData(database, oElement, 10, 1920, 250000,86400,6);
	new ElementData(database, oElement, 11, 2016, 500000,172800,7);
	new THElement(database, 1, oElement, 1);
	new THElement(database, 2, oElement, 1);
	new THElement(database, 3, oElement, 2);
	new THElement(database, 4, oElement, 2);
	new THElement(database, 5, oElement, 2);
	new THElement(database, 6, oElement, 2);
	new THElement(database, 7, oElement, 2);
	new THElement(database, 8, oElement, 3);
	new THElement(database, 9, oElement, 4);
	new THElement(database, 10, oElement, 4);

	oElement = new Element(database, "Dark Elixir Drill", oCategoryResource, oCostTypeElixir);
	new ElementData(database, oElement, 1, 400, 1000000, 86400,8);
	new ElementData(database, oElement, 2, 480, 1500000, 172800,8);
	new ElementData(database, oElement, 3, 580, 2000000, 259200,8);
	new ElementData(database, oElement, 4, 690, 3000000, 345600,9);
	new ElementData(database, oElement, 5, 830, 4000000, 518400,9);
	new ElementData(database, oElement, 6, 1000, 5000000, 691200,9);
	new THElement(database, 8, oElement, 1);
	new THElement(database, 9, oElement, 2);
	new THElement(database, 10, oElement, 3);

	oElement = new Element(database, "Builder's Hut", oCategoryResource, oCostTypeGems);
	new ElementData(database, oElement, 1, 250, 0, 0, 1);

	//army
	oElement = new Element(database, "Army Camp", oCategoryArmy, oCostTypeElixir);
	new ElementData(database, oElement, 1, 400, 250, 300, 1);
	new ElementData(database, oElement, 2, 500, 2500, 3600, 2);
	new ElementData(database, oElement, 3, 600, 10000, 10800, 3);
	new ElementData(database, oElement, 4, 700, 100000, 28800, 4);
	new ElementData(database, oElement, 5, 800, 250000, 86400, 5);
	new ElementData(database, oElement, 6, 1000, 750000, 259200, 6);
	new ElementData(database, oElement, 7, 1200, 2250000, 432000, 9);
	new ElementData(database, oElement, 8, 1400, 6750000, 864000, 10);
	new THElement(database, 1, oElement, 1);
	new THElement(database, 2, oElement, 1);
	new THElement(database, 3, oElement, 2);
	new THElement(database, 4, oElement, 2);
	new THElement(database, 5, oElement, 3);
	new THElement(database, 6, oElement, 3);
	new THElement(database, 7, oElement, 4);
	new THElement(database, 8, oElement, 4);
	new THElement(database, 9, oElement, 4);
	new THElement(database, 10, oElement, 4);

	oElement = new Element(database, "Barracks", oCategoryArmy, oCostTypeElixir);
	new ElementData(database, oElement, 1, 250, 200, 60, 1);
	new ElementData(database, oElement, 2, 270, 1000, 900, 1);
	new ElementData(database, oElement, 3, 280, 2500, 7200, 1);
	new ElementData(database, oElement, 4, 290, 5000, 14400, 2);
	new ElementData(database, oElement, 5, 310, 10000, 36000, 3);
	new ElementData(database, oElement, 6, 320, 80000, 57600, 4);
	new ElementData(database, oElement, 7, 340, 240000, 86400, 5);
	new ElementData(database, oElement, 8, 350, 700000, 172800, 6);
	new ElementData(database, oElement, 9, 390, 1500000, 345600, 7);
	new ElementData(database, oElement, 10, 420, 2000000, 518400, 8);
	new THElement(database, 1, oElement, 1);
	new THElement(database, 2, oElement, 2);
	new THElement(database, 3, oElement, 2);
	new THElement(database, 4, oElement, 3);
	new THElement(database, 5, oElement, 3);
	new THElement(database, 6, oElement, 3);
	new THElement(database, 7, oElement, 4);
	new THElement(database, 8, oElement, 4);
	new THElement(database, 9, oElement, 4);
	new THElement(database, 10, oElement, 4);

	oElement = new Element(database, "Dark Barracks", oCategoryArmy, oCostTypeElixir);
	new ElementData(database, oElement, 1, 250, 750000, 259200, 7);
	new ElementData(database, oElement, 2, 300, 1250000, 432000, 7);
	new ElementData(database, oElement, 3, 350, 1750000, 518400, 8);
	new ElementData(database, oElement, 4, 400, 2250000, 604800, 8);
	new ElementData(database, oElement, 5, 450, 2750000, 691200, 9);
	new ElementData(database, oElement, 6, 500, 3500000, 777600, 9);
	new THElement(database, 7, oElement, 1);
	new THElement(database, 8, oElement, 2);
	new THElement(database, 9, oElement, 2);
	new THElement(database, 10, oElement, 2);

	oElement = new Element(database, "Laboratory", oCategoryArmy, oCostTypeElixir);
	new ElementData(database, oElement, 1, 250, 25000,1800,3);
	new ElementData(database, oElement, 2, 270, 50000,18000,4);
	new ElementData(database, oElement, 3, 280, 90000,43200,5);
	new ElementData(database, oElement, 4, 290, 270000,86400,6);
	new ElementData(database, oElement, 5, 310, 500000,172800,7);
	new ElementData(database, oElement, 6, 330, 1000000,345600,8);
	new ElementData(database, oElement, 7, 350, 2500000,432000,9);
	new ElementData(database, oElement, 8, 370, 4000000,518400,10);
	new THElement(database, 3, oElement, 1);
	new THElement(database, 4, oElement, 1);
	new THElement(database, 5, oElement, 1);
	new THElement(database, 6, oElement, 1);
	new THElement(database, 7, oElement, 1);
	new THElement(database, 8, oElement, 1);
	new THElement(database, 9, oElement, 1);
	new THElement(database, 10, oElement, 1);

	oElement = new Element(database, "Spell Factory", oCategoryArmy, oCostTypeElixir);
	new ElementData(database, oElement, 1, 200, 200000,86400,5);
	new ElementData(database, oElement, 2, 300, 400000,172800,6);
	new ElementData(database, oElement, 3, 400, 800000,345600,7);
	new ElementData(database, oElement, 4, 500, 1600000,432000,9);
	new ElementData(database, oElement, 5, 600, 3200000,518400,10);
	new THElement(database, 5, oElement, 1);
	new THElement(database, 6, oElement, 1);
	new THElement(database, 7, oElement, 1);
	new THElement(database, 8, oElement, 1);
	new THElement(database, 9, oElement, 1);
	new THElement(database, 10, oElement, 1);

	oElement = new Element(database, "Barbarian King Alter", oCategoryArmy, oCostTypeDarkElixir);
	new ElementData(database, oElement, 1, 250, 10000, 0, 7);
	new THElement(database, 7, oElement, 1);
	new THElement(database, 8, oElement, 1);
	new THElement(database, 9, oElement, 1);
	new THElement(database, 10, oElement, 1);

	oElement = new Element(database, "Archer Queen Alter", oCategoryArmy, oCostTypeDarkElixir);
	new ElementData(database, oElement, 1, 250, 40000, 0, 9);
	new THElement(database, 9, oElement, 1);
	new THElement(database, 10, oElement, 1);

	// other
	oElement = new Element(database, "Clan Castle", oCategoryOther, oCostTypeGold);
	new ElementData(database, oElement, 1, 1000, 40000, 0, 3);
	new ElementData(database, oElement, 2, 1400, 100000, 21600, 4);
	new ElementData(database, oElement, 3, 2000, 800000, 86400, 6);
	new ElementData(database, oElement, 4, 2600, 1800000, 172800, 8);
	new ElementData(database, oElement, 5, 3000, 5000000, 604800, 9);
	new ElementData(database, oElement, 6, 3400, 7000000, 1209600, 10);
	new THElement(database, 1, oElement, 1);
	new THElement(database, 2, oElement, 1);
	new THElement(database, 3, oElement, 1);
	new THElement(database, 4, oElement, 1);
	new THElement(database, 5, oElement, 1);
	new THElement(database, 6, oElement, 1);
	new THElement(database, 7, oElement, 1);
	new THElement(database, 8, oElement, 1);
	new THElement(database, 9, oElement, 1);
	new THElement(database, 10, oElement, 1);

	oElement = new Element(database, Constants.TOWNHALL_NAME, oCategoryOther, oCostTypeGold);
	new ElementData(database, oElement, 1, 1500, 0, 0, 0);
	new ElementData(database, oElement, 2, 1600, 1000, 300, 1);
	new ElementData(database, oElement, 3, 1850, 4000, 10800, 2);
	new ElementData(database, oElement, 4, 2100, 25000, 86400, 3);
	new ElementData(database, oElement, 5, 2400, 150000, 172800, 4);
	new ElementData(database, oElement, 6, 2800, 750000, 345600, 5);
	new ElementData(database, oElement, 7, 3200, 1200000, 518400, 6);
	new ElementData(database, oElement, 8, 3700, 2000000, 691200, 7);
	new ElementData(database, oElement, 9, 4200, 3000000, 864000, 8);
	new ElementData(database, oElement, 10, 5000, 4000000, 1209600, 9);
	new THElement(database, 1, oElement, 1);
	new THElement(database, 2, oElement, 1);
	new THElement(database, 3, oElement, 1);
	new THElement(database, 4, oElement, 1);
	new THElement(database, 5, oElement, 1);
	new THElement(database, 6, oElement, 1);
	new THElement(database, 7, oElement, 1);
	new THElement(database, 8, oElement, 1);
	new THElement(database, 9, oElement, 1);
	new THElement(database, 10, oElement, 1);

	database.execSQL(ClasherDBContract.ClasherPlayer.SQL_DROP_TABLE);
	database.execSQL(ClasherDBContract.ClasherPlayer.SQL_CREATE_TABLE);
	database.execSQL(ClasherDBContract.ClasherPlayerElement.SQL_DROP_TABLE);
	database.execSQL(ClasherDBContract.ClasherPlayerElement.SQL_CREATE_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    if (Utils.DEBUG) Log.w(TAG,
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    onCreate(db);
  }
} 
