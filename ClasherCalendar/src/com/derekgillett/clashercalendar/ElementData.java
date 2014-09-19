package com.derekgillett.clashercalendar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ElementData {
	private Element moElement;
	private int mnElementLevel;
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

	private int mnHitPoints = 0;
	private int mnBuildCost = 0;
	private int mnBuildTime = 0;
	private int mnTHMinLevel = 0;
	
	public ElementData(Element poElement, int pnElementLevel) {
		mnElementLevel = pnElementLevel;
		moElement = poElement;
		Load();
	}
	
	public Element getElement() {
		return moElement;
	}
	
	// how long would it take to get this element to the specified level
	public int getBuildCost(int pnLevel) {
		int nBuildCost = mnBuildCost;

		// if pnLevel is <= mnElementLevel will return the build Cost
		for (int nLevel = mnElementLevel+1; nLevel <= pnLevel; nLevel++) {
			ElementData oElementData = new ElementData(this.getElement(), nLevel);
			nBuildCost = nBuildCost + oElementData.getBuildCost();
		}
		return nBuildCost;
	}
	
	// how long would it take to get this element to the specified level
	public int getBuildTime(int pnLevel) {
		int nBuildTime = mnBuildTime;

		// if pnLevel is <= mnElementLevel will return the build time
		for (int nLevel = mnElementLevel+1; nLevel <= pnLevel; nLevel++) {
			ElementData oElementData = new ElementData(this.getElement(), nLevel);
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
		ClasherDBContract.ClasherElementData.select(moElement.getId(), mnElementLevel, this);
	}
}
