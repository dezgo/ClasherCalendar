package com.derekgillett.clashercalendar;

public class ElementA {
	private Element moElement;
	private int mnLevel;
	private int mnQuantity;
	
	public ElementA(Element poElement, int pnLevel) {
		moElement = poElement;
		mnLevel = pnLevel;
		mnQuantity = 1;
	}
	
	public void add(int pnIncrement) {
		mnQuantity = mnQuantity + pnIncrement;
	}
	
	public int getLevel() {
		return mnLevel;
	}
	
	public int getQuantity() {
		return mnQuantity;
	}
	
	public Element getElement() {
		return moElement;
	}
}
