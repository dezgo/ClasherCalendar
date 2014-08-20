package com.derekgillett.clashercalendar;

import java.util.ArrayList;

import android.support.v4.util.LongSparseArray;

public class PlayerElements {
	private int mnTHLevel = 1;
	private LongSparseArray<PlayerElement> moPlayerElements = new LongSparseArray<PlayerElement>();
	private int mnIndex = 0;
	
	public PlayerElements(int pnTHLevel) {
		mnTHLevel = pnTHLevel;
		LoadDefaults();
	}
	
	public void addPlayerElement(Element poElement, int pnLevel) {
		PlayerElement oPlayerElement = new PlayerElement(poElement, pnLevel);
		moPlayerElements.put(oPlayerElement.getID(),oPlayerElement);
	}
	
	public PlayerElement getPlayerElement(long pnPlayerElementID) {
		return moPlayerElements.get(pnPlayerElementID);
	}
	
	public PlayerElement getPlayerElement() {
		PlayerElement playerElement = null;
		if (mnIndex <= moPlayerElements.size() ) {
			playerElement = moPlayerElements.get(moPlayerElements.keyAt(mnIndex));
			mnIndex++;
		}
		return playerElement;
	}
	
	private void LoadDefaults() {
        THElements townHall = new THElements(mnTHLevel);
        ArrayList<THElement> oTHElements = townHall.getTHElements();

        for (int i=0; i<oTHElements.size(); i++) {
        	THElement thElement = oTHElements.get(i);
        	for (int j=0; j<thElement.getQuantity(); j++) {
        		PlayerElement playerElement = new PlayerElement(
        				thElement.getElement(), thElement.getElement().getMaxLevel(mnTHLevel));
        		moPlayerElements.put(playerElement.getID(),playerElement);
        	}
        }
	}
	
	public int size() {
		return moPlayerElements.size();
	}
}
