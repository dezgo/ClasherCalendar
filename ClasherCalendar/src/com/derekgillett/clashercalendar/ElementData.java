package com.derekgillett.clashercalendar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class ElementData {
	private SQLiteDatabase moDB;
	private static final String TAG = "ElementData";
	
	private long mnID;
	private Element moElement;
	private int mnElementLevel;
	private int mnHitPoints = 0;
	private int mnBuildCost = 0;
	private int mnBuildTime = 0;
	private int mnTHMinLevel = 0;
	

	public ElementData(SQLiteDatabase poDB, Element poElement, int pnElementLevel) {
		moDB = poDB;
		mnElementLevel = pnElementLevel;
		moElement = poElement;
		Load();
	}
	
	public ElementData(SQLiteDatabase poDB, 
			Element poElement, 
			int pnElementLevel, 
			int pnHitPoints,
			int pnBuildCost,
			int pnBuildTime,
			int pnTHMinLevel) {
		
		moDB = poDB;
		moElement = poElement;
		mnElementLevel = pnElementLevel;
		mnHitPoints = pnHitPoints;
		mnBuildCost = pnBuildCost;
		mnBuildTime = pnBuildTime;
		mnTHMinLevel = pnTHMinLevel;
		insert(); 
	}
	
	public Element getElement() {
		return moElement;
	}
	
	public int getMnElementLevel() {
		return mnElementLevel;
	}

	public void setElementLevel(int mnElementLevel) {
		this.mnElementLevel = mnElementLevel;
	}

	public int getHitPoints() {
		return mnHitPoints;
	}

	public void setHitPoints(int mnHitPoints) {
		this.mnHitPoints = mnHitPoints;
	}

	public int getBuildCost() {
		return mnBuildCost;
	}

	public void setBuildCost(int mnBuildCost) {
		this.mnBuildCost = mnBuildCost;
	}

	public int getBuildTime() {
		return mnBuildTime;
	}

	public void setBuildTime(int mnBuildTime) {
		this.mnBuildTime = mnBuildTime;
	}

	public int getTHMinLevel() {
		return mnTHMinLevel;
	}

	public void setTHMinLevel(int mnTHMinLevel) {
		this.mnTHMinLevel = mnTHMinLevel;
	}

	// how long would it take to get this element to the specified level
	public int getBuildCost(int pnLevel) {
		int nBuildCost = mnBuildCost;

		// if pnLevel is <= mnElementLevel will return the build Cost
		for (int nLevel = mnElementLevel+1; nLevel <= pnLevel; nLevel++) {
			ElementData oElementData = new ElementData(moDB, this.getElement(), nLevel);
			nBuildCost = nBuildCost + oElementData.getBuildCost();
		}
		return nBuildCost;
	}
	
	// how long would it take to get this element to the specified level
	public int getBuildTime(int pnLevel) {
		int nBuildTime = mnBuildTime;

		// if pnLevel is <= mnElementLevel will return the build time
		for (int nLevel = mnElementLevel+1; nLevel <= pnLevel; nLevel++) {
			ElementData oElementData = new ElementData(moDB, this.getElement(), nLevel);
			nBuildTime = nBuildTime + oElementData.getBuildTime();
		}
		return nBuildTime;
	}
	
	/*
	// put this in, but don't know why. I'll never make this updateable
	private void Update() {
		moDB.rawQuery("UPDATE tblElementData SET HitPoints = ?, BuildCost = ?, BuildTime = ?, THMinLevel = ? " +
				"WHERE ElementID = ? AND ElementLevel = ?",
				new String[] { String.valueOf(mnHitPoints), String.valueOf(this.mnBuildCost), String.valueOf(this.mnBuildTime),
					String.valueOf(this.mnTHMinLevel), String.valueOf(moElement.getId()), String.valueOf(mnElementLevel) }); 
	}*/
	
	private void Load() {
		select(moElement.getId(), mnElementLevel, this);
	}

    public void select(long pnElementID, int pnElementLevel, ElementData poElementData) {
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

    private boolean insert() {
    	ContentValues values = new ContentValues();
    	values.put(ClasherDBContract.ClasherElementData.COLUMN_NAME_BUILD_COST, this.mnBuildCost);
    	values.put(ClasherDBContract.ClasherElementData.COLUMN_NAME_BUILD_TIME, this.mnBuildTime);
    	values.put(ClasherDBContract.ClasherElementData.COLUMN_NAME_ELEMENT_ID, this.moElement.getId());
    	values.put(ClasherDBContract.ClasherElementData.COLUMN_NAME_ELEMENT_LEVEL, this.mnElementLevel);
    	values.put(ClasherDBContract.ClasherElementData.COLUMN_NAME_HIT_POINTS, this.mnHitPoints);
    	values.put(ClasherDBContract.ClasherElementData.COLUMN_NAME_TOWNHALL_MIN_LEVEL, this.mnTHMinLevel);

    	mnID = moDB.insert(ClasherDBContract.ClasherElementData.TABLE_NAME, null, values);
		return mnID != 0;
    }
/*
    private int update() {
    	ContentValues values = new ContentValues();
    	values.put(ClasherDBContract.ClasherElementData.COLUMN_NAME_BUILD_COST, this.mnBuildCost);
    	values.put(ClasherDBContract.ClasherElementData.COLUMN_NAME_BUILD_TIME, this.mnBuildTime);
    	values.put(ClasherDBContract.ClasherElementData.COLUMN_NAME_ELEMENT_ID, this.moElement.getId());
    	values.put(ClasherDBContract.ClasherElementData.COLUMN_NAME_ELEMENT_LEVEL, this.mnElementLevel);
    	values.put(ClasherDBContract.ClasherElementData.COLUMN_NAME_HIT_POINTS, this.mnHitPoints);
    	values.put(ClasherDBContract.ClasherElementData.COLUMN_NAME_TOWNHALL_MIN_LEVEL, this.mnTHMinLevel);

    	String selection = ClasherDBContract.ClasherElementData.COLUMN_NAME_ID + " = ?";
		String[] selectionArgs = new String[] { String.valueOf(mnID) };

		return moDB.update(
    			ClasherDBContract.ClasherElementData.TABLE_NAME, 
    			values, 
				selection, 
				selectionArgs); 
    }

    private int delete() {
    	String selection = ClasherDBContract.ClasherElementData.COLUMN_NAME_ID + " = ?";
		String[] selectionArgs = new String[] { String.valueOf(mnID) };
    	return moDB.delete(
    			ClasherDBContract.ClasherElementData.TABLE_NAME, 
				selection, 
				selectionArgs); 
    }

    private Cursor selectSingle() {
    	String[] columns = ClasherDBContract.ClasherElementData.ALL_COLUMNS;
    	String selection = ClasherDBContract.ClasherElementData.COLUMN_NAME_ID + " = ?";
		String[] selectionArgs = new String[] { String.valueOf(this.mnID) };
		return moDB.query(
				ClasherDBContract.ClasherElementData.TABLE_NAME, 
				columns, 
				selection, 
				selectionArgs, 
				null, null, null);        	
    }		*/
}
