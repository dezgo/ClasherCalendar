package com.derekgillett.clashercalendar;

public class ElementA {
	private Element moElement;
	private int mnLevel;
	private int mnQuantity;
	private boolean mbIsPlayerElement;
	private long mnKey;
	
	// used when re-drawing row in update element levels view
	public ElementA(Element poElement, int pnLevel, int pnQuantity) {
		mnLevel = pnLevel;
		mnQuantity = pnQuantity;
		moElement = poElement;
		mbIsPlayerElement = false;
		mnKey = 0;
	}
	
	public ElementA(Element poElement, int pnLevel, boolean pbIsPlayerElement, long pnKey) {
		mnLevel = pnLevel;
		mnQuantity = 1;
		moElement = poElement;
		mbIsPlayerElement = pbIsPlayerElement;
		mnKey = pnKey;
	}
	
	public void add(int pnIncrement) {
		mnQuantity = mnQuantity + pnIncrement;
	}
	
	public int getLevel() {
		return mnLevel;
	}
	
	public void incLevel() {
		mnLevel++;
	}
	
	public void decLevel() {
		mnLevel--;
	}
	
	public int getQuantity() {
		return mnQuantity;
	}
	
	public Element getElement() {
		return moElement;
	}
	
	public boolean isPlayerElement() {
		return mbIsPlayerElement;
	}

	public long getKey() {
		return mnKey;
	}
	
	static public long getKey(long pnPlayerElementID, Element poElement, int pnLevel) {
		// if a playerelementid is passed in, use that as the key (*10000) to ensure it's
		// different to the alternate key
		// used so that upgrading elements can get a different key and be separated
		if (pnPlayerElementID != 0)
			return 10000 + pnPlayerElementID;
		else
			return poElement.getId()*100 + pnLevel;
	}
	
}
