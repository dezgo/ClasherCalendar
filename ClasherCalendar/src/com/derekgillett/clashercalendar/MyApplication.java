package com.derekgillett.clashercalendar;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MyApplication extends Application{
	private static Context context;

    public void onCreate(){
    	if (Utils.DEBUG) Log.d(MyApplication.class.getName(), "onCreate");

        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}
