package com.derekgillett.clashercalendar.test;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.derekgillett.clashercalendar.MySQLiteHelper;
import com.derekgillett.clashercalendar.Player;
import com.derekgillett.clashercalendar.PlayerElement;

import junit.framework.TestCase;

public class PlayerActions extends TestCase {
	private static final String TAG = "PlayerActions";

	private Player moPlayer;
	private int mnTHLevel = 3;
	
	public PlayerActions(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		MySQLiteHelper mySQLiteHelper = MySQLiteHelper.getInstance();
		SQLiteDatabase poDB = mySQLiteHelper.getWritableDatabase();
		moPlayer = new Player(poDB, mnTHLevel, "Test Village");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		moPlayer.delete();
	}
	
	public void testIncTHLevel() {
		moPlayer.incTHLevel();
		assertTrue("Townhall level should now be " + mnTHLevel+1, moPlayer.getTHLevel() == mnTHLevel+1);
	}

	public void testGetPlayerElements() {
		moPlayer.moveToFirst();
		while (!moPlayer.isAfterLast()) {
			PlayerElement oPlayerElement = moPlayer.getPlayerElement();
			Log.v("PlayerActions.testGetPlayerElements",oPlayerElement.getElement().getName() + ", lvl " + oPlayerElement.getLevel());
			moPlayer.moveToNext();
		}
	}
	
	public void testStartUpgrade() {
		PlayerElement oPlayerElement = moPlayer.getPlayerElement();
		oPlayerElement.startUpgrade();
	}
	
	public void testOrderByName() {
		moPlayer.sortByName(true);
	}
	
	public void testIsMax() {
		moPlayer.moveToFirst();
		while (!moPlayer.isAfterLast()) {
			PlayerElement playerElement = moPlayer.getPlayerElement();
			String elementName = playerElement.getElement().getName();
			int level = playerElement.getLevel();
			int maxLevel = playerElement.getElement().getMaxLevel();
			boolean isMax = playerElement.isMax();
			String sMsg = elementName + ", level " + level + ", " +
					"max level " + maxLevel + " - isMax? " + (isMax ? "Yes" : "No");
			Log.v(TAG, sMsg);
			
			if (isMax)
				assertTrue(sMsg, level == maxLevel);
			else
				assertTrue(sMsg, level < maxLevel);
			
			moPlayer.moveToNext();
		}
	}
}
