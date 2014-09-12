package com.derekgillett.clashercalendar;

import android.database.sqlite.SQLiteDatabase;

public enum Globals {
	INSTANCE;

	private SQLiteDatabase db;
	private Player player;
	private Players players;

    Globals(){
    	MySQLiteHelper SQLiteHelper = new MySQLiteHelper();
    	db = SQLiteHelper.getWritableDatabase();
    }
    
    public SQLiteDatabase getDB() {
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
