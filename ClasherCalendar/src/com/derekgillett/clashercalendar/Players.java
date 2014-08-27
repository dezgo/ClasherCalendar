package com.derekgillett.clashercalendar;

import android.database.Cursor;
import android.support.v4.util.LongSparseArray;

public class Players {
	private final String TABLE_NAME = "tblPlayer";
	private final String COLUMN_ID = "PlayerID";
	private final String COLUMN_VILLAGENAME = "VillageName";
	private final String COLUMN_THLEVEL = "THLevel";
	private final String[] ALL_COLUMNS = { this.COLUMN_ID, this.COLUMN_VILLAGENAME, this.COLUMN_THLEVEL };

	private LongSparseArray<Player> moPlayers = new LongSparseArray<Player>();
	private int mnIndex = 0;
	
	public Players() {
		this.select();
	}
	
	private void addPlayer(Player poPlayer) {
		moPlayers.put(poPlayer.getid(),poPlayer);
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
	
	private void select() {
		Cursor cursor = MyApplication.getDB().query(this.TABLE_NAME, this.ALL_COLUMNS, null, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				int nPlayerID = cursor.getString(0) == null ? 0 : Integer.parseInt(cursor.getString(0));
				this.addPlayer(new Player(nPlayerID));
				cursor.moveToNext();
			}
			cursor.close();
		}
	}

}
