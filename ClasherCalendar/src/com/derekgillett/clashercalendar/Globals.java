package com.derekgillett.clashercalendar;

import android.database.sqlite.SQLiteDatabase;

public enum Globals {
	INSTANCE;

	private SQLiteDatabase db = null;
	private Player player;
	private Players players;

    public void init() {
    	if (db == null) {
	    	MySQLiteHelper SQLiteHelper = new MySQLiteHelper();
	    	db = SQLiteHelper.getWritableDatabase();
    	}
    }
    
    public SQLiteDatabase getSQLiteDB() {
    	return db;
    }
    
    public Player getPlayer() {
    	return player;
    }
    
    public void setPlayer(Player poPlayer) {
    	player = poPlayer;
    }

    public Players getPlayers() {
    	return players;
    }
    
    public void setPlayers(Players poPlayers) {
    	players = poPlayers;
    }	
}
