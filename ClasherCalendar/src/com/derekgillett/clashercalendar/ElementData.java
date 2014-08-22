package com.derekgillett.clashercalendar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ElementData {
	private SQLiteDatabase moDB;

	private Element moElement;
	private int mnElementLevel;
	private int mnHitPoints = 0;
	private int mnBuildCost = 0;
	private int mnBuildTime = 0;
	private int mnTHMinLevel = 0;
	
	public ElementData(Element poElement, int pnElementLevel) {
		moDB = MyApplication.getDB();

		mnElementLevel = pnElementLevel;
		moElement = poElement;
		Load();
	}
	
	public Element getElement() {
		return moElement;
	}
	
	public int getElementLevel() {
		return mnElementLevel;
	}
	
	public int getHitPoints() {
		return mnHitPoints;
	}
	
	public int getBuildCost() {
		return mnBuildCost;
	}
	
	public int getBuildTime() {
		return mnBuildTime;
	}
	
	public int getTHMinLevel() {
		return mnTHMinLevel;
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
		Cursor cursor = moDB.rawQuery("SELECT HitPoints, BuildCost, BuildTime, THMinLevel " +
				"FROM tblElementData WHERE ElementID = ? AND ElementLevel = ?",
				new String[] { String.valueOf(moElement.getId()), String.valueOf(mnElementLevel) }); 
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				this.mnHitPoints = cursor.getString(0) == null ? 0 : Integer.parseInt(cursor.getString(0));
				this.mnBuildCost = cursor.getString(1) == null ? 0 : Integer.parseInt(cursor.getString(1));
				this.mnBuildTime = cursor.getString(2) == null ? 0 : Integer.parseInt(cursor.getString(2));
				this.mnTHMinLevel = cursor.getString(3) == null ? 0 : Integer.parseInt(cursor.getString(3));
			}
			cursor.close();
		}
	}
}
