package com.derekgillett.clashercalendar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.util.LongSparseArray;

public class Players {
	private SQLiteDatabase moDB;
	@SuppressWarnings("unused")
	private static final String TAG = "Players.java";
	
	private LongSparseArray<Player> moPlayers = new LongSparseArray<Player>();
	private int mnIndex = 0;
	
	public Players() {
		MySQLiteHelper mySQLiteHelper = MySQLiteHelper.getInstance();
		moDB = mySQLiteHelper.getWritableDatabase();
		
		// populate internal array with existing players from database
		Cursor cursor = selectAll();
		if (cursor != null) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				long nPlayerID = cursor.getLong(0);
				Player oPlayer = new Player(moDB, nPlayerID);
				moPlayers.put(nPlayerID,oPlayer);
				cursor.moveToNext();
			}
			cursor.close();
		}
	}
	
	public Player getPlayer(long pnPlayerID) {
		return moPlayers.get(pnPlayerID);
	}
	
	public void deletePlayer(long pnPlayerID) {
		moPlayers.get(pnPlayerID).delete();
		moPlayers.remove(pnPlayerID);
	}
	
	public void moveToFirst() {
		mnIndex = 0;
	}
	
	public void moveNext() {
		mnIndex++;
	}
	
	public boolean IsAfterLast() {
		return mnIndex >= moPlayers.size();
	}
	
	public Player getPlayer() {
		Player player = null;
		if (mnIndex <= moPlayers.size() ) {
			player = moPlayers.get(moPlayers.keyAt(mnIndex));
		}
		return player;
	}
	
	public int size() {
		return moPlayers.size();
	}
	
    private Cursor selectAll() {
    	String[] columns = ClasherDBContract.ClasherPlayer.ALL_COLUMNS;
		return moDB.query(
				ClasherDBContract.ClasherPlayer.TABLE_NAME, 
				columns, 
				null, null, null, null, null);        	
    }	
}
