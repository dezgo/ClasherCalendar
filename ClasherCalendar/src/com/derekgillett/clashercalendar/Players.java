package com.derekgillett.clashercalendar;

import android.database.Cursor;
import android.support.v4.util.LongSparseArray;

public class Players {
	private LongSparseArray<Player> moPlayers = new LongSparseArray<Player>();
	private int mnIndex = 0;
	
	public Players() {
		// populate internal array with existing players from database
		Cursor cursor = ClasherDBContract.ClasherPlayer.selectAll();
		if (cursor != null) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				int nPlayerID = cursor.getString(0) == null ? 0 : Integer.parseInt(cursor.getString(0));
				Player oPlayer = new Player(nPlayerID);
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
		ClasherDBContract.ClasherPlayer.delete(pnPlayerID);
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
	
}
