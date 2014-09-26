package com.derekgillett.clashercalendar.test;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.derekgillett.clashercalendar.ElementA;
import com.derekgillett.clashercalendar.Element;
import com.derekgillett.clashercalendar.MySQLiteHelper;
import com.derekgillett.clashercalendar.Player;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {
	private static final String TAG = "PlayerTest";
	private SQLiteDatabase moDB;
	
	public PlayerTest(String name) {
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

	public void testCreatePlayerTH1() {
		String[] elements = {
				"Army Camp",
				"Barracks",
				"Canon",
				"Elixir Collector",
				"Elixir Storage",
				"Gold Mine",
				"Gold Storage",
				"Town Hall"
		};

		int[] elementQtys = {
				1,	// Army Camp
				1,	// Barracks
				2,	// Canon
				1,	// Elixir Collector
				1,	// Elixir Storage
				1,	// Gold Mine
				1,	// Gold Storage
				1	// Town Hall
		};
		
		CreatePlayer(1, "Dezgoville TH1", elements, elementQtys);
	}
	
	public void testCreatePlayerTH2() {
		String[] elements = {
				"Archer Tower",
				"Army Camp",
				"Barracks",
				"Canon",
				"Elixir Collector",
				"Elixir Storage",
				"Gold Mine",
				"Gold Storage",
				"Town Hall",
				"Wall"
		};

		int[] elementQtys = {
				1,	// Archer Tower
				1,	// Army Camp
				2,	// Barracks
				2,	// Canon
				2,	// Elixir Collector
				1,	// Elixir Storage
				2,	// Gold Mine
				1,	// Gold Storage
				1,	// Town Hall
				25	// Wall
		};
		
		CreatePlayer(2, "Dezgoville TH2", elements, elementQtys);
	}
	
	public void testCreatePlayerTH3() {
		String[] elements = {
				"Archer Tower",
				"Army Camp",
				"Barracks",
				"Bomb",
				"Canon",
				"Elixir Collector",
				"Elixir Storage",
				"Gold Mine",
				"Gold Storage",
				"Laboratory",
				"Mortar",
				"Town Hall",
				"Wall"
		};

		int[] elementQtys = {
				1,	// Archer Tower
				2,	// Army Camp
				2,	// Barracks
				2,	// Bomb
				2,	// Canon
				3,	// Elixir Collector
				2,	// Elixir Storage
				3,	// Gold Mine
				2,	// Gold Storage
				1,	// Laboratory
				1,	// Mortar
				1,	// Town Hall
				50	// Wall
		};
		
		CreatePlayer(3, "Dezgoville TH3", elements, elementQtys);
	}

	public void testCreatePlayerTH4() {
		String[] elements = {
				"Air Defence",
				"Archer Tower",
				"Army Camp",
				"Barracks",
				"Bomb",
				"Canon",
				"Elixir Collector",
				"Elixir Storage",
				"Gold Mine",
				"Gold Storage",
				"Laboratory",
				"Mortar",
				"Spring Trap",
				"Town Hall",
				"Wall"
		};

		int[] elementQtys = {
				1,	// Air Defence
				2,	// Archer Tower
				2,	// Army Camp
				3,	// Barracks
				2,	// Bomb
				2,	// Canon
				4,	// Elixir Collector
				2,	// Elixir Storage
				4,	// Gold Mine
				2,	// Gold Storage
				1,	// Laboratory
				1,	// Mortar
				2,	// Spring Trap
				1,	// Town Hall
				75	// Wall
		};

		CreatePlayer(4, "Dezgoville TH4", elements, elementQtys);
	}

	public void testCreatePlayerTH5() {
		String[] elements = {
				"Air Bomb",
				"Air Defence",
				"Archer Tower",
				"Army Camp",
				"Barracks",
				"Bomb",
				"Canon",
				"Elixir Collector",
				"Elixir Storage",
				"Gold Mine",
				"Gold Storage",
				"Laboratory",
				"Mortar",
				"Spell Factory",
				"Spring Trap",
				"Town Hall",
				"Wall",
				"Wizard Tower"
		};

		int[] elementQtys = {
				2,   // Air Bomb
				1,   // Air Defence
				3,   // Archer Tower
				3,   // Army Camp
				3,   // Barracks
				4,   // Bomb
				3,   // Canon
				5,   // Elixir Collector
				2,   // Elixir Storage
				5,   // Gold Mine
				2,   // Gold Storage
				1,   // Laboratory
				1,   // Mortar
				1,   // Spell Factory
				2,   // Spring Trap
				1,   // Town Hall
				100,   // Wall
				1   // Wizard Tower
		};		
		CreatePlayer(5, "Dezgoville TH5", elements, elementQtys);
	}

	public void testCreatePlayerTH6() {
		String[] elements = {
				"Air Bomb",
				"Air Defence",
				"Archer Tower",
				"Army Camp",
				"Barracks",
				"Bomb",
				"Canon",
				"Elixir Collector",
				"Elixir Storage",
				"Giant Bomb",
				"Gold Mine",
				"Gold Storage",
				"Laboratory",
				"Mortar",
				"Spell Factory",
				"Spring Trap",
				"Town Hall",
				"Wall",
				"Wizard Tower"
		};

		int[] elementQtys = {
				2,   // Air Bomb
				1,   // Air Defence
				3,   // Archer Tower
				3,   // Army Camp
				3,   // Barracks
				4,   // Bomb
				3,   // Canon
				6,   // Elixir Collector
				2,   // Elixir Storage
				1,   // Giant Bomb
				6,   // Gold Mine
				2,   // Gold Storage
				1,   // Laboratory
				2,   // Mortar
				1,   // Spell Factory
				4,   // Spring Trap
				1,   // Town Hall
				125,   // Wall
				2   // Wizard Tower
		};		
		CreatePlayer(6, "Dezgoville TH6", elements, elementQtys);
	}

	public void testCreatePlayerTH7() {
		String[] elements = {
				"Air Bomb",
				"Air Defence",
				"Archer Tower",
				"Army Camp",
				"Barbarian King Alter",
				"Barracks",
				"Bomb",
				"Canon",
				"Dark Barracks",
				"Dark Elixir Storage",
				"Elixir Collector",
				"Elixir Storage",
				"Giant Bomb",
				"Gold Mine",
				"Gold Storage",
				"Hidden Tesla",
				"Laboratory",
				"Mortar",
				"Seeking Air Mine",
				"Spell Factory",
				"Spring Trap",
				"Town Hall",
				"Wall",
				"Wizard Tower",
		};

		int[] elementQtys = {
				2,   // Air Bomb
				2,   // Air Defence
				4,   // Archer Tower
				4,   // Army Camp
				1,   // Barbarian King Alter
				4,   // Barracks
				6,   // Bomb
				5,   // Canon
				1,   // Dark Barracks
				1,   // Dark Elixir Storage
				6,   // Elixir Collector
				2,   // Elixir Storage
				2,   // Giant Bomb
				6,   // Gold Mine
				2,   // Gold Storage
				2,   // Hidden Tesla
				1,   // Laboratory
				3,   // Mortar
				1,   // Seeking Air Mine
				1,   // Spell Factory
				4,   // Spring Trap
				1,   // Town Hall
				175, // Wall
				2,   // Wizard Tower
		};		

		CreatePlayer(7, "Dezgoville TH7", elements, elementQtys);
	}

	public void testCreatePlayerTH8() {
		String[] elements = {
				"Air Bomb",
				"Air Defence",
				"Archer Tower",
				"Army Camp",
				"Barbarian King Alter",
				"Barracks",
				"Bomb",
				"Canon",
				"Dark Barracks",
				"Dark Elixir Drill",
				"Dark Elixir Storage",
				"Elixir Collector",
				"Elixir Storage",
				"Giant Bomb",
				"Gold Mine",
				"Gold Storage",
				"Hidden Tesla",
				"Laboratory",
				"Mortar",
				"Seeking Air Mine",
				"Spell Factory",
				"Spring Trap",
				"Town Hall",
				"Wall",
				"Wizard Tower"
		};

		int[] elementQtys = {
				4,   // Air Bomb
				3,   // Air Defence
				5,   // Archer Tower
				4,   // Army Camp
				1,   // Barbarian King Alter
				4,   // Barracks
				6,   // Bomb
				5,   // Canon
				2,   // Dark Barracks
				1,   // Dark Elixir Drill
				1,   // Dark Elixir Storage
				6,   // Elixir Collector
				3,   // Elixir Storage
				3,   // Giant Bomb
				6,   // Gold Mine
				3,   // Gold Storage
				3,   // Hidden Tesla
				1,   // Laboratory
				3,   // Mortar
				2,   // Seeking Air Mine
				1,   // Spell Factory
				6,   // Spring Trap
				1,   // Town Hall
				225,   // Wall
				3   // Wizard Tower
		};		
		
		CreatePlayer(8, "Dezgoville TH8", elements, elementQtys);
	}

	public void testCreatePlayerTH9() {
		String[] elements = {
				"Air Bomb",
				"Air Defence",
				"Archer Queen Alter",
				"Archer Tower",
				"Army Camp",
				"Barbarian King Alter",
				"Barracks",
				"Bomb",
				"Canon",
				"Dark Barracks",
				"Dark Elixir Drill",
				"Dark Elixir Storage",
				"Elixir Collector",
				"Elixir Storage",
				"Giant Bomb",
				"Gold Mine",
				"Gold Storage",
				"Hidden Tesla",
				"Laboratory",
				"Mortar",
				"Seeking Air Mine",
				"Spell Factory",
				"Spring Trap",
				"Town Hall",
				"Wall",
				"Wizard Tower",
				"X-Bow",
		};

		int[] elementQtys = {
				4,   // Air Bomb
				4,   // Air Defence
				1,   // Archer Queen Alter
				6,   // Archer Tower
				4,   // Army Camp
				1,   // Barbarian King Alter
				4,   // Barracks
				6,   // Bomb
				5,   // Canon
				2,   // Dark Barracks
				2,   // Dark Elixir Drill
				1,   // Dark Elixir Storage
				6,   // Elixir Collector
				4,   // Elixir Storage
				4,   // Giant Bomb
				6,   // Gold Mine
				4,   // Gold Storage
				4,   // Hidden Tesla
				1,   // Laboratory
				3,   // Mortar
				4,   // Seeking Air Mine
				1,   // Spell Factory
				6,   // Spring Trap
				1,   // Town Hall
				250,   // Wall
				4,   // Wizard Tower
				2   // X-Bow
		};		

		CreatePlayer(9, "Dezgoville TH9", elements, elementQtys);
	}

	public void testCreatePlayerTH10() {
		String[] elements = {
				"Air Bomb",
				"Air Defence",
				"Archer Queen Alter",
				"Archer Tower",
				"Army Camp",
				"Barbarian King Alter",
				"Barracks",
				"Bomb",
				"Canon",
				"Dark Barracks",
				"Dark Elixir Drill",
				"Dark Elixir Storage",
				"Elixir Collector",
				"Elixir Storage",
				"Giant Bomb",
				"Gold Mine",
				"Gold Storage",
				"Hidden Tesla",
				"Inferno Tower",
				"Laboratory",
				"Mortar",
				"Seeking Air Mine",
				"Spell Factory",
				"Spring Trap",
				"Town Hall",
				"Wall",
				"Wizard Tower",
				"X-Bow"
		};

		int[] elementQtys = {
				5,   // Air Bomb
				4,   // Air Defence
				1,    // Archer Queen Alter,
				7,   // Archer Tower
				4,   // Army Camp
				1,   // Barbarian King Alter
				4,   // Barracks
				6,   // Bomb
				6,   // Canon
				2,   // Dark Barracks
				3,   // Dark Elixir Drill
				1,   // Dark Elixir Storage
				7,   // Elixir Collector
				4,   // Elixir Storage
				5,   // Giant Bomb
				7,   // Gold Mine
				4,   // Gold Storage
				4,   // Hidden Tesla
				2,   // Inferno Tower
				1,   // Laboratory
				3,   // Mortar
				5,   // Seeking Air Mine
				1,   // Spell Factory
				6,   // Spring Trap
				1,   // Town Hall
				250,   // Wall
				4,   // Wizard Tower
				3   // X-Bow
		};		

		CreatePlayer(10, "Dezgoville TH10", elements, elementQtys);
	}

	private void CreatePlayer(int pnTHLevel, String psVillageName, String[] poElements, int[] poElementQtys) {

		assertTrue("Test setup issue - elementQtys size <> elements size", poElementQtys.length == poElements.length);

		int numElements_Expected = poElements.length;
		int numElements_Actual = 0;
		
		Player oPlayer = new Player(moDB, pnTHLevel, psVillageName);
		Log.v("PlayerTest.testCreatePlayer", "Start Create Plater TH Level " + pnTHLevel);
		//oPlayer.sortByName(true);
		oPlayer.moveToFirstA();
		while (!oPlayer.isAfterLastA()) {
			ElementA oElementA = oPlayer.getElementA();
			Element oElement = oElementA.getElement();
			//Log.v("PlayerTest.testCreatePlayer", oElementA.getQuantity() + " x " + oElement.getName());

			if (numElements_Actual < numElements_Expected) {
				Log.v(TAG, "Expecting " + poElementQtys[numElements_Actual] + "x" + poElements[numElements_Actual] + 
						 ", retrieved " + oElementA.getQuantity() + "x" + oElement.getName());
				
				assertTrue("Expecting " + poElementQtys[numElements_Actual] + "x" + poElements[numElements_Actual] + 
						 ", retrieved " + oElementA.getQuantity() + "x" + oElement.getName(),
						oElement.getName().equals(poElements[numElements_Actual]) && 
						oElementA.getQuantity() == poElementQtys[numElements_Actual]);
			}
			numElements_Actual++;
			oPlayer.moveToNextA();
		}
		
		assertTrue("Found " + numElements_Actual + ", expecting " + numElements_Expected,numElements_Expected == numElements_Actual);
		
		oPlayer.delete();
		Log.v("PlayerTest.testCreatePlayer", "Finish");
	}
	
}