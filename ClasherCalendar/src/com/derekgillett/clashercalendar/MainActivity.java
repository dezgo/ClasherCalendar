package com.derekgillett.clashercalendar;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends Activity {

	//private MyApplication moApp;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		Log.d(MainActivity.class.getName(), "Constructor"); 

		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //moApp = (MyApplication) getApplication();
        
        RelativeLayout vwMainLayout =  (RelativeLayout) this.findViewById(R.id.layoutMain);
        createTHSpinner(vwMainLayout);
    }

    private void createTHSpinner(RelativeLayout vw) {
		Log.d(MainActivity.class.getName(), "createTHSpinner"); 

		// add label
    	TextView textView = new TextView(this);
    	textView.setId(1001);
    	textView.setText("Town Hall Level:");
    	textView.setTextSize(20.0f);
    	vw.addView(textView);
    	
        // add a spinner to select town hall level
        Spinner spinner = new Spinner(this);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
        		LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.RIGHT_OF, 1001);
        spinner.setLayoutParams(lp);
        vw.addView(spinner);
        
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
