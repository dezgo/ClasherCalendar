package com.derekgillett.clashercalendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        RelativeLayout vwMainLayout =  (RelativeLayout) this.findViewById(R.id.layoutMain);
        Element element = new Element(this);
        int countElement = element.countElements(); 
        for (int i=1; i<=countElement; i++) {
        	TextView tv = new TextView(this);
        	element.loadElement(i);
        	tv.setText(element.getName());
        	tv.setPadding(0, (i-1)*20,0,0);
        	vwMainLayout.addView(tv);
        }
    }

}
