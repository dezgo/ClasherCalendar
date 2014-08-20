package com.derekgillett.clashercalendar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PlayerElement {
	private final String TABLE_NAME = "tblPlayerElement";
	private final String COLUMN_ID = "PlayerElementID";
	private final String COLUMN_ELEMENTID = "ElementID";
	private final String COLUMN_LEVEL = "Level";
	private final String[] ALL_COLUMNS = { this.COLUMN_ID, this.COLUMN_ELEMENTID, this.COLUMN_LEVEL };
	
	private SQLiteDatabase moDB;
	
	private long mnPlayerElementID;
	private Element moElement;
	private int mnLevel;

	private void init() {
		// 1. get reference to writable DB
		MySQLiteHelper oMySQLiteHelper = new MySQLiteHelper();
		moDB = oMySQLiteHelper.getWritableDatabase();		
	}
	
	public PlayerElement(int pnPlayerElementID) {
		init();
		mnPlayerElementID = pnPlayerElementID;
		Load();
	}
	
	public PlayerElement(Element poElement, int pnLevel) {
		init();
		moElement = poElement;
		mnLevel = pnLevel;
		insert();
	}
	
	public long getPlayerElementID() {
		return mnPlayerElementID;
	}
	
	public Element getElement() {
		return moElement;
	}
	
	public void setElement(Element poElement) {
		moElement = poElement;
		this.update();
	}
	
	public int getLevel() {
		return mnLevel;
	}
	
	public void setLevel(int pnLevel) {
		mnLevel = pnLevel;
		this.update();
	}
	
	public long getID() {
		return this.mnPlayerElementID;
	}
	
	private void Load() {
		Cursor cursor = moDB.query(this.TABLE_NAME, this.ALL_COLUMNS, "PlayerElementID = ?", 
				new String[] { String.valueOf(mnPlayerElementID) }, null, null, null );
		//Cursor cursor = moDB.rawQuery("SELECT ElementID, Level FROM tblPlayerElement WHERE PlayerElementID = ?",
		//		new String[] { String.valueOf(mnPlayerElementID) }); 
		if (cursor != null) {
			cursor.moveToFirst();
			this.moElement = cursor.getString(0) == null ? null : new Element(Integer.parseInt(cursor.getString(0)));
			this.mnLevel = cursor.getString(1) == null ? 0 : Integer.parseInt(cursor.getString(1));
		}
	}
	
	private void update() {
		ContentValues values = new ContentValues();
		values.put("Level", mnLevel);
		values.put("ElementID", moElement.getId());
		MyApplication.getDB().update(TABLE_NAME,values, 
				COLUMN_ID + " = ?", new String[] { String.valueOf(mnPlayerElementID) });
	}

	private boolean insert() {
		ContentValues values = new ContentValues();
		values.put("Level", mnLevel);
		values.put("ElementID", moElement.getId());
		mnPlayerElementID = MyApplication.getDB().insert(TABLE_NAME,  null,  values);
		return mnPlayerElementID != 0;
	}
}
