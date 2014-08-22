package com.derekgillett.clashercalendar;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.util.LongSparseArray;

public class Player {
	private final String TABLE_NAME = "tblPlayer";
	private final String COLUMN_ID = "PlayerID";
	private final String COLUMN_VILLAGENAME = "VillageName";
	private final String COLUMN_THLEVEL = "THLevel";
	private final String[] ALL_COLUMNS = { this.COLUMN_ID, this.COLUMN_VILLAGENAME, this.COLUMN_THLEVEL };

	private long mnPlayerID;
	private String msVillageName;
	private int mnTHLevel = 1;
	private LongSparseArray<PlayerElement> moPlayerElements = new LongSparseArray<PlayerElement>();
	private int mnIndex = 0;
	
	public Player(long pnPlayerID) {
		mnPlayerID = pnPlayerID;
		this.select();
		this.LoadExisting();
        MyApplication.setPlayer(this);
	}
	
	public Player(int pnTHLevel, String psVillageName) {
		mnTHLevel = pnTHLevel;
		msVillageName = psVillageName;
		this.insert();
		LoadDefaults();
        MyApplication.setPlayer(this);
	}
	
	public String getVillageName() {
		return this.msVillageName;
	}
	
	public int getTHLevel() {
		return mnTHLevel;
	}
	
	public void setTHLevel(int pnTHLevel) {
		mnTHLevel = pnTHLevel;
		this.update();
	}
	
	public long getid() {
		return this.mnPlayerID;
	}
	
	public void addPlayerElement(Element poElement, int pnLevel) {
		PlayerElement oPlayerElement = new PlayerElement(this, poElement, pnLevel);
		moPlayerElements.put(oPlayerElement.getID(),oPlayerElement);
	}
	
	public PlayerElement getPlayerElement(long pnPlayerElementID) {
		return moPlayerElements.get(pnPlayerElementID);
	}
	
	public void moveToFirst() {
		this.mnIndex = 0;
	}
	
	public PlayerElement getPlayerElement() {
		PlayerElement playerElement = null;
		if (mnIndex <= moPlayerElements.size() ) {
			playerElement = moPlayerElements.get(moPlayerElements.keyAt(mnIndex));
			mnIndex++;
		}
		return playerElement;
	}
	
	// for a newly created player, add in the default buildings that come with the given TH level
	private void LoadDefaults() {
        THElements townHall = new THElements(mnTHLevel);
        ArrayList<THElement> oTHElements = townHall.getTHElements();

        for (int i=0; i<oTHElements.size(); i++) {
        	THElement thElement = oTHElements.get(i);
        	for (int j=0; j<thElement.getQuantity(); j++) {
        		PlayerElement playerElement = new PlayerElement(this,
        				thElement.getElement(), thElement.getElement().getMaxLevel(mnTHLevel));
        		moPlayerElements.put(playerElement.getID(),playerElement);
        	}
        }
	}
	
	// for an existing player, load the buildings as saved in tblPlayerElement
	private void LoadExisting() {
		String[] selectionArgs = new String[] { String.valueOf(mnPlayerID) };
		ContentValues values = new ContentValues();
		values.put(COLUMN_ID, mnPlayerID);
		Cursor cursor = MyApplication.getDB().query("tblPlayerElement", new String[] {"PlayerElementID"}, "PlayerID = ?", selectionArgs, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				int nPlayerElementID = cursor.getString(0) == null ? 0 : Integer.parseInt(cursor.getString(0));
				if (nPlayerElementID != 0) {
					PlayerElement playerElement = new PlayerElement(nPlayerElementID,this);
					moPlayerElements.put(playerElement.getID(), playerElement);
				}
				cursor.moveToNext();
			}
		}
	}
	
	public int size() {
		return moPlayerElements.size();
	}
	
	public void delete() {
		// cheeky reference to PlayerElement table here
		// didn't want to implement a delete method over there when it's only used here
		MyApplication.getDB().delete("tblPlayerElement", 
				COLUMN_ID + " = ?", new String[] { String.valueOf(mnPlayerID) });
		MyApplication.getDB().delete(TABLE_NAME, 
				COLUMN_ID + " = ?", new String[] { String.valueOf(mnPlayerID) });
	}

	private void update() {
		ContentValues values = new ContentValues();
		values.put(this.COLUMN_VILLAGENAME, msVillageName);
		values.put(this.COLUMN_THLEVEL, mnTHLevel);
		MyApplication.getDB().update(TABLE_NAME,values, 
				COLUMN_ID + " = ?", new String[] { String.valueOf(mnPlayerID) });
	}

	private boolean insert() {
		ContentValues values = new ContentValues();
		values.put("VillageName", msVillageName);
		values.put("THLevel", mnTHLevel);
		mnPlayerID = MyApplication.getDB().insert("tblPlayer",  null,  values);
		return mnPlayerID != 0;
	}
	
	private void select() {
		String[] selectionArgs = new String[] { String.valueOf(mnPlayerID) };
		Cursor cursor = MyApplication.getDB().query(this.TABLE_NAME, this.ALL_COLUMNS, COLUMN_ID + " = ?", selectionArgs, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			this.msVillageName = cursor.getString(1) == null ? "" : cursor.getString(1);
			this.mnTHLevel = cursor.getString(2) == null ? 0 : Integer.parseInt(cursor.getString(2));
		}
	}

}
