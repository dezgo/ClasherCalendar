package com.derekgillett.clashercalendar.settings;

import com.derekgillett.clashercalendar.Constants;
import com.derekgillett.clashercalendar.R;
import com.derekgillett.clashercalendar.R.style;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ClasherSettings {
	private static final String TAG = "ClasherSettings";
	
	private boolean mbConfirmExit = true;
	private int mnTheme = R.style.Theme_AppCompat_Light_DarkActionBar;
	
	
	public boolean isConfirmExit() {
		return mbConfirmExit;
	}
	
	public void setConfirmExit(boolean pbConfirmExit) {
		this.mbConfirmExit = pbConfirmExit;
	}
	
	public int getDialogTheme() {
		return mnTheme;
	}

	public void saveClasherPreferences(Context context) {
    	SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
    	SharedPreferences.Editor editor = settings.edit();
    	
    	editor.putBoolean(Constants.PREF_CONFIRM_EXIT, this.mbConfirmExit);
    	
    	editor.commit();
	}	
    
	public void loadClasherPreferences(Context context) {
		 // Session
    	SharedPreferences sessionPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    	this.setConfirmExit(sessionPrefs.getBoolean(Constants.PREF_CONFIRM_EXIT, true));
	}
}
