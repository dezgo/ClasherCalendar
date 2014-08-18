package com.derekgillett.clashercalendar;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        RelativeLayout vwMainLayout =  (RelativeLayout) this.findViewById(R.id.layoutMain);
        
        createTHSpinner(vwMainLayout);
        
        RelativeLayout.LayoutParams lp;

        Element element = new Element(this);
        int countElement = element.countElements(); 
        for (int i=1; i<=countElement; i++) {
        	TextView tv = new TextView(this);
        	tv.setId(1001+i);
            lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.BELOW, 1000+i);
            tv.setLayoutParams(lp);
            
        	element.loadElement(i);
        	tv.setText(element.getName());
        	//tv.setPadding(0, (i+3)*20,0,0);
        	vwMainLayout.addView(tv);
        }
    }

    private void createTHSpinner(RelativeLayout vw) {
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
        
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

    }

 }
