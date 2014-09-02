package com.derekgillett.clashercalendar;

import android.app.Activity;
import android.app.ProgressDialog;

public class Utils {
	static public boolean DEBUG = true;
	static private ProgressDialog dialog;
	
	static public enum CostType {
		Gold(1),
		Elixir(2);
		private int costType;
		private CostType(int costType) {
			this.costType = costType;
		}
		public int getId() {
			return costType;
		}
	}
	
	static public enum Category {
		Trap(5),
		Troop(6),
		Defence(1),
		Resource(2),
		Army(3),
		Other(4);
		private int categoryID;
		private Category(int categoryID) {
			this.categoryID = categoryID;
		}
		public int getId() {
			return categoryID;
		}
	}
	
	static public void startThinking(Activity activity, String msg) {
		dialog = new ProgressDialog(activity);
		dialog.setMessage(msg);
		dialog.setIndeterminate(true);
		dialog.setCancelable(false);
		dialog.show();
	}
	
	static public void stopThinking() {
		dialog.hide();
	}
	
	static public long Time_TextToVal(String psTime) {
	    String[] vTime;
	    int numticks;
	    long rtn = 0;

	    vTime = psTime.split(" ");
	    for (int item=0; item<vTime.length; item++) {
	        numticks = Integer.parseInt(vTime[item].toString().substring(0, vTime[item].length()-1));
	        String s = vTime[item].substring(vTime[item].length());
	        if (s == "d") rtn = rtn + numticks * 24 * 60 * 60;
	        if (s == "h") rtn = rtn + numticks * 60 * 60;
	        if (s == "m") rtn = rtn + numticks * 60;
	        if (s == "s") rtn = rtn + numticks;
	    }
	    
	    return rtn;
	}

	static public String Time_ValToText(long pnVal) {
		String sRtn = "";
		int parts = 0;	// only want to show 2 components to time, say days and hours, or hours and minutes, not whole time like 1d 2h 3m 14s
	    
	    if (pnVal <= 0) {
	        sRtn = "";
	    }
	    
	    if (pnVal >= 60*60*24 && parts<2) {
	        if (!sRtn.equals("")) sRtn = sRtn + " ";
	        sRtn = sRtn + Math.round(pnVal/60/60/24) + "d";
	        pnVal = pnVal - Math.round(pnVal/60/60/24)*60*60*24;
	        parts++;
	    }
	    
	    if (pnVal >= 60*60 && parts<2) {
	        if (!sRtn.equals("")) sRtn = sRtn + " ";
	        sRtn = sRtn + Math.round(pnVal/60/60) + "h";
	        pnVal = pnVal - Math.round(pnVal/60/60)*60*60;
	        parts++;
	    }
	
	    if (pnVal >= 60 && parts<2) {
	        if (!sRtn.equals("")) sRtn = sRtn + " ";
	        sRtn = sRtn + Math.round(pnVal/60) + "m";
	        pnVal = pnVal - Math.round(pnVal/60)*60;
	        parts++;
	    }
	
	    if (pnVal >= 1 && parts<2) {
	        if (!sRtn.equals("")) sRtn = sRtn + " ";
	        sRtn = sRtn + Math.round(pnVal) + "s";
	        pnVal = pnVal - Math.round(pnVal);
	        parts++;
	    }
	
	    return sRtn;
	}
}
