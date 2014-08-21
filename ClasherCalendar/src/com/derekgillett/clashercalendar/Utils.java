package com.derekgillett.clashercalendar;

import android.app.Activity;
import android.app.ProgressDialog;

public class Utils {
	static private ProgressDialog dialog;
	
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
}
