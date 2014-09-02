package com.derekgillett.clashercalendar;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.util.Log;
import android.widget.TextView;

public class ItemUpgrade {

	private MainActivity moMainActivity;
	private ScheduledExecutorService moSEService;
	private MySETask moMySETask;

	// holds textview showing the countdown time
	private TextView moBuildTime;
	
	// holds list of all elements being upgraded
	private PlayerElement moUpgradingElement;

	// textview which shows buildtime countdown
	private TextView moTVBuildTime;
	
	public ItemUpgrade(MainActivity poMainActivity,
			TextView poBuildTime,
			PlayerElement poUpgradingElement) {

		moMainActivity = poMainActivity;
		moSEService = Executors.newSingleThreadScheduledExecutor();
	    moMySETask = new MySETask();
		moBuildTime = poBuildTime;
		moUpgradingElement = poUpgradingElement;
		poUpgradingElement.startUpgrade();
	    moSEService.scheduleAtFixedRate(moMySETask,  0, 1, TimeUnit.SECONDS);

//		MyApplication.getAppContext();
//		LayoutInflater inflater = (LayoutInflater) MyApplication.getAppContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		moTVBuildTime = (TextView) moMainActivity.getLayoutInflater().inflate(R.layout.tv_template, null);
	}
	
	public TextView getTVBuildTime() {
		return moTVBuildTime;
	}
	
	static public long getKey(PlayerElement poPlayerElement) {
		return poPlayerElement.getID();
	}
	
	//http://android-er.blogspot.com/2013/12/example-of-using-timer-and-timertask-on.html, and
    //http://stackoverflow.com/questions/14315293/converting-to-scheduledthreadpoolexecutor
    class MySETask implements Runnable {
		@Override
		public void run() {
	    	final int secsRemaining = moUpgradingElement.getSecondsRemaining();

    		moMainActivity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					int nSecsLeft = moUpgradingElement.getSecondsRemaining();
					Log.d("ItemUpgrade","Countdown tick: " + nSecsLeft + "secs left for lvl " + moUpgradingElement.getLevel() + " " + moUpgradingElement.getElement().getName());
			    	if (secsRemaining <= 0) {
			    		if (moSEService != null) {
				    		moSEService.shutdown();
				    		moSEService = null;
				    		moMainActivity.upgradeDone(moUpgradingElement.getID());
				    	}
			    	} else
			    		moBuildTime.setText(Utils.Time_ValToText(secsRemaining));
				}
    		});
		}
    }	
}
