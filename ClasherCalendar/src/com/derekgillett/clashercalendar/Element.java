package com.derekgillett.clashercalendar;

public class Element {
	private int mid;
	private String melementName;
	private int mcostType;

	public int getId() {
		return mid;
	}
	
	public void setId(int pid) {
		mid = pid;
	}
	
	public String getName() {
		return melementName;
	}
	
	public void setName(String pelementName) {
		melementName = pelementName;
	}
	
	public String toString() {
		return melementName;
	}
	
	public int getCostType() {
		return mcostType;
	}
	
	public void setCostType(int pcostType) {
		mcostType = pcostType;
	}
}
