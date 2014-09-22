package com.derekgillett.clashercalendar.test;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.derekgillett.clashercalendar.Globals;
import com.derekgillett.clashercalendar.MySQLiteHelper;
import com.derekgillett.clashercalendar.Players;

import junit.framework.TestCase;

public class PlayersTest extends TestCase {
	private SQLiteDatabase moDB;

	public PlayersTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		MySQLiteHelper mySQLiteHelper = new MySQLiteHelper();
		moDB = mySQLiteHelper.getWritableDatabase();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		moDB = null;
	}

	public void testGetPlayer() {
		Log.v("PlayersTest.testGetPlayer","Start test");
		Players oPlayers = new Players();
		oPlayers.moveToFirst();
		while (!oPlayers.IsAfterLast()) {
			Log.v("PlayersTest.testGetPlayer", oPlayers.getPlayer().getVillageName());
			oPlayers.moveNext();
		}
		Log.v("PlayersTest.testGetPlayer","End test");
	}

}
