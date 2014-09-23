package com.derekgillett.clashercalendar;

import android.provider.BaseColumns;

public class ClasherDBContract {
	public static final String TAG = "ClasherDBContract";
	
	public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String COMMA_SEP = ",";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public ClasherDBContract() {}

    public static abstract class ClasherCategory implements BaseColumns {
        public static final String TABLE_NAME = "tblCategory";
        public static final String COLUMN_NAME_ID = _ID;
        public static final String COLUMN_NAME_CATEGORY_NAME = "CategoryName";
    	public static final String[] ALL_COLUMNS = {COLUMN_NAME_ID, COLUMN_NAME_CATEGORY_NAME};
        
        public static final String SQL_CREATE_TABLE = 
        		"CREATE TABLE " + ClasherCategory.TABLE_NAME + " (" +
        		ClasherCategory._ID + INTEGER_TYPE + " PRIMARY KEY," + 
        		ClasherCategory.COLUMN_NAME_CATEGORY_NAME + TEXT_TYPE + " )";
        
        public static final String SQL_DROP_TABLE =
        		"DROP TABLE IF EXISTS " + ClasherCategory.TABLE_NAME;
    }

    public static abstract class ClasherCostType implements BaseColumns {
        public static final String TABLE_NAME = "tblCostType";
        public static final String COLUMN_NAME_ID = _ID;
        public static final String COLUMN_NAME_COST_TYPE_NAME = "CostTypeName";
    	public static final String[] ALL_COLUMNS = {COLUMN_NAME_ID, COLUMN_NAME_COST_TYPE_NAME};
        
        public static final String SQL_CREATE_TABLE = 
        		"CREATE TABLE " + ClasherCostType.TABLE_NAME + " (" +
        				ClasherCostType._ID + INTEGER_TYPE + " PRIMARY KEY," + 
        				ClasherCostType.COLUMN_NAME_COST_TYPE_NAME + TEXT_TYPE + " )";
        
        public static final String SQL_DROP_TABLE =
        		"DROP TABLE IF EXISTS " + ClasherCostType.TABLE_NAME;
    }
    
    public static abstract class ClasherElement implements BaseColumns {
        public static final String TABLE_NAME = "tblElement";
        public static final String COLUMN_NAME_ID = _ID;
        public static final String COLUMN_NAME_ELEMENT_NAME = "ElementName";
        public static final String COLUMN_NAME_COST_TYPE = "CostType";
        public static final String COLUMN_NAME_CATEGORY = "Category";
    	public static final String[] ALL_COLUMNS = {COLUMN_NAME_ID, COLUMN_NAME_ELEMENT_NAME, COLUMN_NAME_COST_TYPE, COLUMN_NAME_CATEGORY};
        
        public static final String SQL_CREATE_TABLE = 
        		"CREATE TABLE " + ClasherElement.TABLE_NAME + " (" +
        				ClasherElement._ID + INTEGER_TYPE + " PRIMARY KEY," + 
        				ClasherElement.COLUMN_NAME_ELEMENT_NAME + TEXT_TYPE + COMMA_SEP +  
        				ClasherElement.COLUMN_NAME_COST_TYPE + INTEGER_TYPE + COMMA_SEP +  
        				ClasherElement.COLUMN_NAME_CATEGORY + INTEGER_TYPE + " )";
        
        public static final String SQL_DROP_TABLE =
        		"DROP TABLE IF EXISTS " + ClasherElement.TABLE_NAME;
    }

    public static abstract class ClasherElementData implements BaseColumns {
        public static final String TABLE_NAME = "tblElementData";
        public static final String COLUMN_NAME_ID = _ID;
        public static final String COLUMN_NAME_ELEMENT_ID = "ElementID";
        public static final String COLUMN_NAME_ELEMENT_LEVEL = "ElementLevel";
        public static final String COLUMN_NAME_HIT_POINTS = "HitPoints";
        public static final String COLUMN_NAME_BUILD_COST = "BuildCost";
        public static final String COLUMN_NAME_BUILD_TIME = "BuildTime";
        public static final String COLUMN_NAME_TOWNHALL_MIN_LEVEL= "THMinLevel";
    	public static final String[] ALL_COLUMNS = {COLUMN_NAME_ID, COLUMN_NAME_ELEMENT_ID, COLUMN_NAME_ELEMENT_LEVEL, COLUMN_NAME_HIT_POINTS, COLUMN_NAME_BUILD_COST, COLUMN_NAME_BUILD_TIME, COLUMN_NAME_TOWNHALL_MIN_LEVEL};
        
        public static final String SQL_CREATE_TABLE = 
        		"CREATE TABLE " + ClasherElementData.TABLE_NAME + " (" +
        				ClasherElementData._ID + INTEGER_TYPE + " PRIMARY KEY," + 
        				ClasherElementData.COLUMN_NAME_ELEMENT_ID + TEXT_TYPE + COMMA_SEP +  
        				ClasherElementData.COLUMN_NAME_ELEMENT_LEVEL + INTEGER_TYPE + COMMA_SEP +  
        				ClasherElementData.COLUMN_NAME_HIT_POINTS + INTEGER_TYPE + COMMA_SEP +  
        				ClasherElementData.COLUMN_NAME_BUILD_COST + INTEGER_TYPE + COMMA_SEP +  
        				ClasherElementData.COLUMN_NAME_BUILD_TIME + INTEGER_TYPE + COMMA_SEP +  
        				ClasherElementData.COLUMN_NAME_TOWNHALL_MIN_LEVEL + INTEGER_TYPE + " )";
        
        public static final String SQL_DROP_TABLE =
        		"DROP TABLE IF EXISTS " + ClasherElementData.TABLE_NAME;
    }

    public static abstract class ClasherTHElement implements BaseColumns {
        public static final String TABLE_NAME = "tblTHElement";
        public static final String COLUMN_NAME_ID = _ID;
        public static final String COLUMN_NAME_TOWNHALL_LEVEL = "THLevel";
        public static final String COLUMN_NAME_ELEMENT_ID = "ElementID";
        public static final String COLUMN_NAME_QUANTITY = "Quantity";
    	public static final String[] ALL_COLUMNS = { COLUMN_NAME_ID, COLUMN_NAME_TOWNHALL_LEVEL, COLUMN_NAME_ELEMENT_ID, COLUMN_NAME_QUANTITY };

        public static final String SQL_CREATE_TABLE = 
        		"CREATE TABLE " + ClasherTHElement.TABLE_NAME + " (" +
        				ClasherTHElement._ID + INTEGER_TYPE + " PRIMARY KEY," + 
        				ClasherTHElement.COLUMN_NAME_ELEMENT_ID + INTEGER_TYPE + COMMA_SEP +  
        				ClasherTHElement.COLUMN_NAME_QUANTITY + INTEGER_TYPE + COMMA_SEP +  
        				ClasherTHElement.COLUMN_NAME_TOWNHALL_LEVEL + INTEGER_TYPE + " )";
        
        public static final String SQL_DROP_TABLE =
        		"DROP TABLE IF EXISTS " + ClasherTHElement.TABLE_NAME;
    }

    public static abstract class ClasherPlayerElement implements BaseColumns {
        public static final String TABLE_NAME = "tblPlayerElement";
        public static final String COLUMN_NAME_ID = _ID;
        public static final String COLUMN_NAME_PLAYER_ID = "PlayerID";
        public static final String COLUMN_NAME_ELEMENT_ID = "ElementID";
        public static final String COLUMN_NAME_LEVEL = "Level";
        public static final String COLUMN_NAME_UPGRADE_START = "UpgradeStart";
    	public static final String[] ALL_COLUMNS = { COLUMN_NAME_ID, COLUMN_NAME_PLAYER_ID, COLUMN_NAME_ELEMENT_ID, COLUMN_NAME_LEVEL, COLUMN_NAME_UPGRADE_START };

        public static final String SQL_CREATE_TABLE = 
        		"CREATE TABLE " + ClasherPlayerElement.TABLE_NAME + " (" +
        				ClasherPlayerElement._ID + INTEGER_TYPE + " PRIMARY KEY," + 
        				ClasherPlayerElement.COLUMN_NAME_ELEMENT_ID + INTEGER_TYPE + COMMA_SEP +  
        				ClasherPlayerElement.COLUMN_NAME_LEVEL + INTEGER_TYPE + COMMA_SEP +  
        				ClasherPlayerElement.COLUMN_NAME_PLAYER_ID + INTEGER_TYPE + COMMA_SEP +  
        				ClasherPlayerElement.COLUMN_NAME_UPGRADE_START + INTEGER_TYPE + " )";
        
        public static final String SQL_DROP_TABLE =
        		"DROP TABLE IF EXISTS " + ClasherPlayerElement.TABLE_NAME;
    }

    public static abstract class ClasherPlayer implements BaseColumns {
        public static final String TABLE_NAME = "tblPlayer";
        public static final String COLUMN_NAME_ID = _ID;
        public static final String COLUMN_NAME_PLAYER_VILLAGE_NAME = "VillageName";
        public static final String COLUMN_NAME_TOWNHALL_LEVEL = "THLevel";
    	public static final String[] ALL_COLUMNS = { COLUMN_NAME_ID, COLUMN_NAME_PLAYER_VILLAGE_NAME, COLUMN_NAME_TOWNHALL_LEVEL };

        public static final String SQL_CREATE_TABLE = 
        		"CREATE TABLE " + ClasherPlayer.TABLE_NAME + " (" +
        				ClasherPlayer._ID + INTEGER_TYPE + " PRIMARY KEY," + 
        				ClasherPlayer.COLUMN_NAME_PLAYER_VILLAGE_NAME + INTEGER_TYPE + COMMA_SEP +
						ClasherPlayer.COLUMN_NAME_TOWNHALL_LEVEL + INTEGER_TYPE + " )";
        
        public static final String SQL_DROP_TABLE =
        		"DROP TABLE IF EXISTS " + ClasherPlayer.TABLE_NAME;
    }
}
