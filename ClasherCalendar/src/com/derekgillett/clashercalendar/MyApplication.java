package com.derekgillett.clashercalendar;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MyApplication extends Application{
	private static Context context;
	private static SQLiteDatabase db;
	private static Player player;
	private static Players players;

    public void onCreate(){
        Log.d(MyApplication.class.getName(), "onCreate");

        super.onCreate();
        MyApplication.context = getApplicationContext();
        
        MySQLiteHelper SQLiteHelper = new MySQLiteHelper();
        db = SQLiteHelper.getWritableDatabase();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
    
    public static SQLiteDatabase getDB() {
    	return db;
    }
    
    public static Player getPlayer() {
    	return player;
    }
    
    public static void setPlayer(Player poPlayer) {
    	player = poPlayer;
    }

    public static Players getPlayers() {
    	return players;
    }
    
    public static void setPlayers(Players poPlayers) {
    	players = poPlayers;
    }
}