package com.derekgillett.clashercalendar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.provider.BaseColumns;
import android.util.Log;

public class ClasherDBContract {
	public static final String TAG = "ClasherDBContract";
	
	public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String COMMA_SEP = ",";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public ClasherDBContract() {}

    public static abstract class ClasherCategory implements BaseColumns {
        private static final String TABLE_NAME = "tblCategory";
        private static final String COLUMN_NAME_CATEGORY_NAME = "CategoryName";
        
        private static final String SQL_CREATE_TABLE = 
        		"CREATE TABLE " + ClasherCategory.TABLE_NAME + " (" +
        		ClasherCategory._ID + " INTEGER PRIMARY KEY," + 
        		ClasherCategory.COLUMN_NAME_CATEGORY_NAME + TEXT_TYPE + " )";
        
        private static final String SQL_DROP_TABLE =
        		"DROP TABLE IF EXISTS " + ClasherCategory.TABLE_NAME;
        
        public static void createTable(SQLiteDatabase poDB) {
        	poDB.execSQL(SQL_DROP_TABLE);
        	poDB.execSQL(SQL_CREATE_TABLE);
        }
        
        public static long insert(SQLiteDatabase poDB, String psCategoryName) {
        	ContentValues values = new ContentValues();
        	values.put(ClasherCategory.COLUMN_NAME_CATEGORY_NAME,  psCategoryName);
        	long newRowId = poDB.insert(ClasherCategory.TABLE_NAME, 
        			ClasherCategory.COLUMN_NAME_CATEGORY_NAME,
        			values);
        	return newRowId;
        }
    }

    public static abstract class ClasherCostType implements BaseColumns {
        private static final String TABLE_NAME = "tblCostType";
        private static final String COLUMN_NAME_COST_TYPE_NAME = "CostTypeName";
        
        private static final String SQL_CREATE_TABLE = 
        		"CREATE TABLE " + ClasherCostType.TABLE_NAME + " (" +
        				ClasherCostType._ID + " INTEGER PRIMARY KEY," + 
        				ClasherCostType.COLUMN_NAME_COST_TYPE_NAME + TEXT_TYPE + " )";
        
        private static final String SQL_DROP_TABLE =
        		"DROP TABLE IF EXISTS " + ClasherCostType.TABLE_NAME;

        
        public static void createTable(SQLiteDatabase poDB) {
        	poDB.execSQL(SQL_DROP_TABLE);
        	poDB.execSQL(SQL_CREATE_TABLE);
        }
        
        public static long insert(SQLiteDatabase poDB, String psCostTypeName) {
        	ContentValues values = new ContentValues();
        	values.put(ClasherCostType.COLUMN_NAME_COST_TYPE_NAME,  psCostTypeName);
        	return poDB.insert(ClasherCostType.TABLE_NAME, null, values);
        }
    }
    
    public static abstract class ClasherElement implements BaseColumns {
    	private static final SQLiteDatabase moDB = Globals.INSTANCE.getSQLiteDB();

        private static final String TABLE_NAME = "tblElement";
        private static final String COLUMN_NAME_ELEMENT_NAME = "ElementName";
        private static final String COLUMN_NAME_COST_TYPE = "CostType";
        private static final String COLUMN_NAME_CATEGORY = "Category";
    	private static final String[] COLUMNS = {COLUMN_NAME_ELEMENT_NAME, COLUMN_NAME_COST_TYPE, COLUMN_NAME_CATEGORY};
        
        private static final String SQL_CREATE_TABLE = 
        		"CREATE TABLE " + ClasherElement.TABLE_NAME + " (" +
        				ClasherElement._ID + " INTEGER PRIMARY KEY," + 
        				ClasherElement.COLUMN_NAME_ELEMENT_NAME + TEXT_TYPE + COMMA_SEP +  
        				ClasherElement.COLUMN_NAME_COST_TYPE + INTEGER_TYPE + COMMA_SEP +  
        				ClasherElement.COLUMN_NAME_CATEGORY + INTEGER_TYPE + " )";
        
        private static final String SQL_DROP_TABLE =
        		"DROP TABLE IF EXISTS " + ClasherElement.TABLE_NAME;

        public static void createTable(SQLiteDatabase poDB) {
        	poDB.execSQL(SQL_DROP_TABLE);
        	poDB.execSQL(SQL_CREATE_TABLE);
        }
        
        public static long insert(SQLiteDatabase poDB, String psElementName, long pnCategory, long pnCostType) {
        	ContentValues values = new ContentValues();
        	values.put(ClasherElement.COLUMN_NAME_ELEMENT_NAME,  psElementName);
        	values.put(ClasherElement.COLUMN_NAME_COST_TYPE,  pnCostType);
        	values.put(ClasherElement.COLUMN_NAME_CATEGORY,  pnCategory);
        	return poDB.insert(ClasherElement.TABLE_NAME, null, values);
        }
        
        public static Cursor select(long pnID) {
        	Cursor cursor = null;
        	
        	try {
				cursor = moDB.query(TABLE_NAME, // a. table
					COLUMNS, // b. column names
					"_ID = ?", // c. selections 
					new String[] { String.valueOf(pnID) }, // d. selections args
					null, // e. group by
					null, // f. having
					null, // g. order by
					null); // h. limit
	        }
        	catch (SQLiteException e) {
        		Log.e(TAG,e.getMessage());
        	}

        	return cursor;
        }
    }

    public static abstract class ClasherElementData implements BaseColumns {
    	private static final SQLiteDatabase moDB = Globals.INSTANCE.getSQLiteDB();

        private static final String TABLE_NAME = "tblElementData";
        private static final String COLUMN_NAME_ELEMENT_ID = "ElementID";
        private static final String COLUMN_NAME_ELEMENT_LEVEL = "ElementLevel";
        private static final String COLUMN_NAME_HIT_POINTS = "HitPoints";
        private static final String COLUMN_NAME_BUILD_COST = "BuildCost";
        private static final String COLUMN_NAME_BUILD_TIME = "BuildTime";
        private static final String COLUMN_NAME_TOWNHALL_MIN_LEVEL= "THMinLevel";
        
        private static final String SQL_CREATE_TABLE = 
        		"CREATE TABLE " + ClasherElementData.TABLE_NAME + " (" +
        				ClasherElementData._ID + " INTEGER PRIMARY KEY," + 
        				ClasherElementData.COLUMN_NAME_ELEMENT_ID + TEXT_TYPE + COMMA_SEP +  
        				ClasherElementData.COLUMN_NAME_ELEMENT_LEVEL + INTEGER_TYPE + COMMA_SEP +  
        				ClasherElementData.COLUMN_NAME_HIT_POINTS + INTEGER_TYPE + COMMA_SEP +  
        				ClasherElementData.COLUMN_NAME_BUILD_COST + INTEGER_TYPE + COMMA_SEP +  
        				ClasherElementData.COLUMN_NAME_BUILD_TIME + INTEGER_TYPE + COMMA_SEP +  
        				ClasherElementData.COLUMN_NAME_TOWNHALL_MIN_LEVEL + INTEGER_TYPE + " )";
        
        private static final String SQL_DROP_TABLE =
        		"DROP TABLE IF EXISTS " + ClasherElementData.TABLE_NAME;

        public static void createTable(SQLiteDatabase poDB) {
        	poDB.execSQL(SQL_DROP_TABLE);
        	poDB.execSQL(SQL_CREATE_TABLE);
        }
        
        public static long insert(SQLiteDatabase poDB, 
        		long pnElementID,
        		int pnElementLevel,
        		int pnHitPoints,
        		int pnBuildCost,
        		int pnBuildTime,
        		int pnTownHallMinLevel) {

        	ContentValues values = new ContentValues();
        	values.put(ClasherElementData.COLUMN_NAME_ELEMENT_ID, pnElementID);
        	values.put(ClasherElementData.COLUMN_NAME_ELEMENT_LEVEL, pnElementLevel);
        	values.put(ClasherElementData.COLUMN_NAME_HIT_POINTS, pnHitPoints);
        	values.put(ClasherElementData.COLUMN_NAME_BUILD_COST, pnBuildCost);
        	values.put(ClasherElementData.COLUMN_NAME_BUILD_TIME, pnBuildTime);
        	values.put(ClasherElementData.COLUMN_NAME_TOWNHALL_MIN_LEVEL, pnTownHallMinLevel);

        	return poDB.insert(ClasherElementData.TABLE_NAME, null, values);
        }
        
        public static int getMaxLevel(long pnID) {
        	return getMaxLevel(pnID, 11);
        }

        public static int getMaxLevel(long pnID, int pnTHLevel) {
    		int rtn = 0;
    		Cursor cursor = null;
        	try {
				cursor = moDB.rawQuery("SELECT Max(ElementLevel) FROM " + TABLE_NAME + " WHERE ElementID = ? AND THMinLevel <= ?", 
						new String[] { String.valueOf(pnID), String.valueOf(pnTHLevel) });
        	}
        	catch (SQLiteException e) {
        		Log.e(TAG, e.getMessage());
        	}

    		if (cursor != null) {
    			try {
    				if (cursor.getCount() > 0) {
    					cursor.moveToFirst();
    					if (cursor.getString(0) != null)
    						rtn = Integer.parseInt(cursor.getString(0));
    				}
    				cursor.close();
    			}
    			catch (Exception e) {
    				Log.e(TAG, e.getMessage());
    			}
    		}
    		return rtn;
        }
        
        public static void select(long pnElementID, int pnElementLevel, ElementData poElementData) {
    		Cursor cursor = moDB.rawQuery("SELECT HitPoints, BuildCost, BuildTime, THMinLevel " +
    				"FROM tblElementData WHERE ElementID = ? AND ElementLevel = ?",
    				new String[] { String.valueOf(pnElementID), String.valueOf(pnElementLevel) }); 
    		if (cursor != null) {
    			try {
	    			if (cursor.getCount() > 0) {
	    				cursor.moveToFirst();
	    				poElementData.setHitPoints(cursor.getString(0) == null ? 0 : Integer.parseInt(cursor.getString(0)));
	    				poElementData.setBuildCost(cursor.getString(1) == null ? 0 : Integer.parseInt(cursor.getString(1)));
	    				poElementData.setBuildTime(cursor.getString(2) == null ? 0 : Integer.parseInt(cursor.getString(2)));
	    				poElementData.setTHMinLevel(cursor.getString(3) == null ? 0 : Integer.parseInt(cursor.getString(3)));
	    			}
    			}
    			catch (SQLiteException e) {
    				Log.e(TAG, e.getMessage());
    			}
    			finally {
    				cursor.close();
    			}
    		}
        }
    }

    public static abstract class ClasherTHElement implements BaseColumns {
    	public static final SQLiteDatabase moDB = Globals.INSTANCE.getSQLiteDB();

        public static final String TABLE_NAME = "tblTHElement";
        public static final String COLUMN_NAME_TOWNHALL_LEVEL = "THLevel";
        public static final String COLUMN_NAME_ELEMENT_ID = "ElementID";
        public static final String COLUMN_NAME_QUANTITY = "Quantity";
    	public static final String[] ALL_COLUMNS = { _ID, COLUMN_NAME_TOWNHALL_LEVEL, COLUMN_NAME_ELEMENT_ID, COLUMN_NAME_QUANTITY };

        public static final String SQL_CREATE_TABLE = 
        		"CREATE TABLE " + ClasherTHElement.TABLE_NAME + " (" +
        				ClasherTHElement._ID + " INTEGER PRIMARY KEY," + 
        				ClasherTHElement.COLUMN_NAME_ELEMENT_ID + INTEGER_TYPE + COMMA_SEP +  
        				ClasherTHElement.COLUMN_NAME_QUANTITY + INTEGER_TYPE + COMMA_SEP +  
        				ClasherTHElement.COLUMN_NAME_TOWNHALL_LEVEL + INTEGER_TYPE + " )";
        
        public static final String SQL_DROP_TABLE =
        		"DROP TABLE IF EXISTS " + ClasherTHElement.TABLE_NAME;
    }

    public static abstract class ClasherPlayerElement implements BaseColumns {
    	public static final SQLiteDatabase moDB = Globals.INSTANCE.getSQLiteDB();

        public static final String TABLE_NAME = "tblPlayerElement";
        public static final String COLUMN_NAME_PLAYER_ID = "PlayerID";
        public static final String COLUMN_NAME_ELEMENT_ID = "ElementID";
        public static final String COLUMN_NAME_LEVEL = "Level";
        public static final String COLUMN_NAME_UPGRADE_START = "UpgradeStart";
    	public static final String[] ALL_COLUMNS = { _ID, COLUMN_NAME_PLAYER_ID, COLUMN_NAME_ELEMENT_ID, COLUMN_NAME_LEVEL, COLUMN_NAME_UPGRADE_START };

        public static final String SQL_CREATE_TABLE = 
        		"CREATE TABLE " + ClasherPlayerElement.TABLE_NAME + " (" +
        				ClasherPlayerElement._ID + " INTEGER PRIMARY KEY," + 
        				ClasherPlayerElement.COLUMN_NAME_ELEMENT_ID + INTEGER_TYPE + COMMA_SEP +  
        				ClasherPlayerElement.COLUMN_NAME_LEVEL + INTEGER_TYPE + COMMA_SEP +  
        				ClasherPlayerElement.COLUMN_NAME_PLAYER_ID + INTEGER_TYPE + COMMA_SEP +  
        				ClasherPlayerElement.COLUMN_NAME_UPGRADE_START + INTEGER_TYPE + " )";
        
        public static final String SQL_DROP_TABLE =
        		"DROP TABLE IF EXISTS " + ClasherPlayerElement.TABLE_NAME;
    }

    public static abstract class ClasherPlayer implements BaseColumns {
    	private static final SQLiteDatabase moDB = Globals.INSTANCE.getSQLiteDB();

        private static final String TABLE_NAME = "tblPlayer";
        private static final String COLUMN_NAME_PLAYER_VILLAGE_NAME = "VillageName";
        private static final String COLUMN_NAME_TOWNHALL_LEVEL = "THLevel";
    	private static final String[] ALL_COLUMNS = { _ID, COLUMN_NAME_PLAYER_VILLAGE_NAME, COLUMN_NAME_TOWNHALL_LEVEL };

        private static final String SQL_CREATE_TABLE = 
        		"CREATE TABLE " + ClasherPlayer.TABLE_NAME + " (" +
        				ClasherPlayer._ID + " INTEGER PRIMARY KEY," + 
        				ClasherPlayer.COLUMN_NAME_PLAYER_VILLAGE_NAME + INTEGER_TYPE + COMMA_SEP +
						ClasherPlayer.COLUMN_NAME_TOWNHALL_LEVEL + INTEGER_TYPE + " )";
        
        private static final String SQL_DROP_TABLE =
        		"DROP TABLE IF EXISTS " + ClasherPlayer.TABLE_NAME;
        
        public static void createTable(SQLiteDatabase poDB) {
        	poDB.execSQL(SQL_DROP_TABLE);
        	poDB.execSQL(SQL_CREATE_TABLE);
        }
        
        public static long insert( 
        		String psVillageName,
        		int pnTownhallLevel) {

        	ContentValues values = new ContentValues();
        	values.put(ClasherPlayer.COLUMN_NAME_PLAYER_VILLAGE_NAME, psVillageName);
        	values.put(ClasherPlayer.COLUMN_NAME_TOWNHALL_LEVEL, pnTownhallLevel);

        	return moDB.insert(ClasherPlayer.TABLE_NAME, null, values);
        }
        
        public static long update(
        		long pnPlayerID,
        		String psVillageName,
        		int pnTownhallLevel) {
        	
        	ContentValues values = new ContentValues();
        	values.put(ClasherPlayer.COLUMN_NAME_PLAYER_VILLAGE_NAME, psVillageName);
        	values.put(ClasherPlayer.COLUMN_NAME_TOWNHALL_LEVEL, pnTownhallLevel);

        	return moDB.update(TABLE_NAME, values, "_ID = " + pnPlayerID, null);
        }

        public static long delete(long pnPlayerID) {
        	ClasherPlayerElement.deletePlayer(pnPlayerID);
        	return moDB.delete(TABLE_NAME, "_ID = " + pnPlayerID, null);
        }
        
        public static Cursor selectSingle(long pnPlayerID) {
    		String[] selectionArgs = new String[] { String.valueOf(pnPlayerID) };
    		return moDB.query(TABLE_NAME, ALL_COLUMNS, "_ID = ?", selectionArgs, null, null, null);
        }

        public static Cursor selectAll() {
    		return moDB.query(TABLE_NAME, ALL_COLUMNS, null, null, null, null, null);
        }
        
    }
}
