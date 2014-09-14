package com.derekgillett.clashercalendar.test;

import android.util.Log;

import com.derekgillett.clashercalendar.Players;

import junit.framework.TestCase;

public class PlayersTest extends TestCase {

	public PlayersTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
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
