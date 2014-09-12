package com.derekgillett.clashercalendar;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class StartActivity extends ActionBarActivity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.start, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		createTHSpinner();
		addNextButtonListener();
		
		// start task to init players object in background
		new GetPlayersTask().execute();
	}
	
	//http://androidresearch.wordpress.com/2012/03/17/understanding-asynctask-once-and-forever/
	//TODO: implement the fixes here to avoid crashes when rotating screen while asynctask is running
	//http://androidresearch.wordpress.com/2013/05/10/dealing-with-asynctask-and-screen-orientation/
	private class GetPlayersTask extends AsyncTask<String, Integer, String> {
		@Override
		   protected void onPreExecute() {
		      super.onPreExecute();
		      Toast.makeText(MyApplication.getAppContext(), 
		                "Loading existing players...",
		                Toast.LENGTH_LONG).show();
		   }
		 
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
	        new Player( Integer.parseInt(spTHLevel.getSelectedItem().toString()) , et.getText().toString() );
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
