package com.derekgillett.clashercalendar.test;

import android.util.Log;

import com.derekgillett.clashercalendar.Player;
import com.derekgillett.clashercalendar.PlayerElement;

import junit.framework.TestCase;

public class PlayerActions extends TestCase {

	private Player moPlayer;
	private int mnTHLevel = 3;
	
	public PlayerActions(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		moPlayer = new Player(mnTHLevel, "Test Village");
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
}
