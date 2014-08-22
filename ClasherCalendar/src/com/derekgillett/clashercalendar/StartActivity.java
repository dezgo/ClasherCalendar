package com.derekgillett.clashercalendar;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
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
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		// do this here so that when we come back to this screen, any new players are added
		getPlayers();
	}

	private void getPlayers() {
		Players players = new Players();
		Player player;
		LinearLayout ll = (LinearLayout) this.findViewById(R.id.layout_existing_players);
		ll.removeAllViews();
		while ((player = players.getPlayer()) != null) {
			final Player player1 = player;
			Button button = new Button(this);
			button.setText(player.getVillageName() + " - TH " + player.getTHLevel());
			button.setOnClickListener(new View.OnClickListener() {				
				@Override
				public void onClick(View v) {
					MyApplication.setPlayer(player1);
					GoToMain_();
				}
			});
			ll.addView(button);			
		}
	}
	
	private void GoToMain_() {
		Intent intent = new Intent(this, MainActivity.class);
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
	        GoToMain_();
		}
	}
	
	private void addNextButtonListener() {
		Button button = (Button) this.findViewById(R.id.btnNext);
		button.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				GoToMain();
			}
		});
	}
	
    private void createTHSpinner() {
		Log.d("MainActivity", "createTHSpinner"); 

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
        
        spinner.setOnItemSelectedListener(new THLevelOnItemSelectedListener());
    }
}
