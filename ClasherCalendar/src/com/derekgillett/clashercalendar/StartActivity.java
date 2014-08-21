package com.derekgillett.clashercalendar;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
	       
		createTHSpinner();
		addNextButtonListener();
	}

	private void GoToMain() {
		EditText et = (EditText) this.findViewById(R.id.etVillageName);
		if (et.getText().toString().equals("")) {
	        Toast.makeText(this, 
	                "But wait! What's your village name?",
	                Toast.LENGTH_LONG).show();
		} else {
			// if his hasn't been set yet, it's because they didn't change
			// the spinner
			// default position is 1, so probably happy with that
			// wasn't sure how to pull selecteditem out
			// so just hard-coded 1
			if (MyApplication.getPlayerElements() == null) {
		        MyApplication.setPlayerElements(new Player( 1, et.getText().toString() ));
	        }

	        Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
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
        
        spinner.setOnItemSelectedListener(new THLevelOnItemSelectedListener(this));
    }
}
