package com.derekgillett.clashercalendar;

import java.util.ArrayList;
import java.util.List;

import com.derekgillett.clashercalendar.settings.ClasherSettings;
import com.derekgillett.clashercalendar.settings.SettingsActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class StartActivity extends ActionBarActivity {
	private SQLiteDatabase moDB;
	
	private final ClasherSettings moSettings = new ClasherSettings();

	/**
     * http://stackoverflow.com/questions/2257963/android-how-to-show-dialog-to-confirm-user-wishes-to-exit-activity
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Handle the back button
        if(moSettings.isConfirmExit() && keyCode == KeyEvent.KEYCODE_BACK && isTaskRoot()) {
            //Ask the user if they want to quit
            new AlertDialog.Builder(new ContextThemeWrapper(this, moSettings.getDialogTheme()))
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle(R.string.quit)
            .setMessage(R.string.really_quit)
            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //Stop the activity
                    finish();    
                }
            })
            .setNegativeButton(R.string.no, null)
            .show();

            return true;
        }
        else {
            return super.onKeyDown(keyCode, event);
        }
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.start, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_settings:
	        	ViewGroup vg = (ViewGroup) findViewById(android.R.id.content);
	        	vg.removeAllViews();
	        	getFragmentManager().beginTransaction()
	        		.replace(android.R.id.content, new SettingsActivity())
	        		.addToBackStack("Settings")
	        		.commit();
	    		return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		moSettings.loadClasherPreferences(this.getApplicationContext());
		setContentView(R.layout.activity_start);

		createTHSpinner();
		addNextButtonListener();
		
		// start task to init players object in background
		new GetPlayersTask().execute();
		
		MySQLiteHelper mySQLiteHelper = MySQLiteHelper.getInstance();
		moDB = mySQLiteHelper.getWritableDatabase();
	}
	
	//http://androidresearch.wordpress.com/2012/03/17/understanding-asynctask-once-and-forever/
	//TODO: implement the fixes here to avoid crashes when rotating screen while asynctask is running
	//http://androidresearch.wordpress.com/2013/05/10/dealing-with-asynctask-and-screen-orientation/
	private class GetPlayersTask extends AsyncTask<String, Integer, String> {
/*		@Override
		   protected void onPreExecute() {
		      super.onPreExecute();
		      Toast.makeText(MyApplication.getAppContext(), 
		                "Loading existing players...",
		                Toast.LENGTH_LONG).show();
		   }*/
		 
		@Override
		protected String doInBackground(String... params) {
			getPlayers(); 
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			drawPlayers();
		}
	}
	
	private void getPlayers() {
		Globals.INSTANCE.setPlayers(new Players());
	}
	
	private void drawPlayers() {
		Players players = Globals.INSTANCE.getPlayers();
		Player player;
		LinearLayout ll = (LinearLayout) this.findViewById(R.id.layout_existing_players);
		
		// this will be called each time a new player is added, so may have already been done
		// clear out existing buttons first
		ll.removeAllViews();
		
		players.moveToFirst();
		while ((player = players.getPlayer()) != null) {
			final Player player1 = player;
			Button button = new Button(this);
			button.setText(player.getVillageName() + " - TH " + player.getTHLevel());
			button.setOnClickListener(new View.OnClickListener() {				
				@Override
				public void onClick(View v) {
					Globals.INSTANCE.setPlayer(player1);
					GoToMain_(true);
				}
			});
			ll.addView(button);
			players.moveNext();
		}
	}
	
	private void GoToMain_(boolean isExisting) {
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("com.derekgillett.clashercalendar.existing",isExisting);
		startActivity(intent);
	}

	private void GoToMain() {
		EditText et = (EditText) this.findViewById(R.id.etVillageName);
		if (et.getText().toString().equals("")) {
	        Toast.makeText(this, 
	                "But wait! What's your village name?",
	                Toast.LENGTH_LONG).show();
		} else {
			Spinner spTHLevel = (Spinner) this.findViewById(R.id.spTHLevel);
	        new Player(moDB, Integer.parseInt(spTHLevel.getSelectedItem().toString()) , et.getText().toString() );
	        GoToMain_(false);
		}
	}
	
	private void addNextButtonListener() {
		Button button = (Button) this.findViewById(R.id.btnNext);
		button.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				// redraw buttons to add in new player just created
				new GetPlayersTask().execute();
				GoToMain();
			}
		});
	}
	
    private void createTHSpinner() {
    	if (Utils.DEBUG) Log.d("MainActivity", "createTHSpinner"); 

        // get reference to spinner
        Spinner spinner = (Spinner) this.findViewById(R.id.spTHLevel);

        List<String> list = new ArrayList<String>();
        for (int i=1; i<=10; i++) {
            list.add(String.valueOf(i));
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
        	(this, android.R.layout.simple_spinner_item,list);
        
        dataAdapter.setDropDownViewResource
        	(android.R.layout.simple_spinner_dropdown_item);
        
        spinner.setAdapter(dataAdapter);
        
        //spinner.setOnItemSelectedListener(new THLevelOnItemSelectedListener());
    }
}
