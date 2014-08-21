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
	
	public Player getPlayer() {
		Player player = null;
		if (mnIndex <= moPlayers.size() ) {
			player = moPlayers.get(moPlayers.keyAt(mnIndex));
			mnIndex++;
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
				String sVillageName = cursor.getString(1) == null ? "" : cursor.getString(1);
				int nTHLevel = cursor.getString(2) == null ? 0 : Integer.parseInt(cursor.getString(2));
				this.addPlayer(new Player(nTHLevel, sVillageName));
				cursor.moveToNext();
			}
		}
	}

}
