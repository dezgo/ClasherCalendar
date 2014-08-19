package com.derekgillett.clashercalendar;

import java.util.ArrayList;

import android.content.Context;

public class Player {
	private Context moContext;
	private ArrayList<PlayerElement> moPlayerElements = new ArrayList<PlayerElement>();
	
	public Player(Context poContext) {
		moContext = poContext;
	}
	
	public void addElement(int pnElementID, int pnLevel) {
		PlayerElement oPlayerElement = new PlayerElement(moContext, pnElementID);
		oPlayerElement.setLevel(pnLevel);
		moPlayerElements.add(oPlayerElement);
	}
}
