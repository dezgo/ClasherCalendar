package com.derekgillett.clashercalendar;

import java.util.ArrayList;

public class PlayerElements {
	private ArrayList<PlayerElement> moPlayerElements = new ArrayList<PlayerElement>();
	
	public PlayerElements() {
		//TODO add constructor code
	}
	
	public void addElement(Element poElement, int pnLevel) {
		PlayerElement oPlayerElement = new PlayerElement();
		oPlayerElement.setLevel(pnLevel);
		moPlayerElements.add(oPlayerElement);
	}
}
