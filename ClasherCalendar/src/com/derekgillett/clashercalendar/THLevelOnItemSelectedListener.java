package com.derekgillett.clashercalendar;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
 
public class THLevelOnItemSelectedListener implements OnItemSelectedListener {
 
	private Activity moActivity;
	
    public void onItemSelected(AdapterView<?> parent, View view, int pos,
            long id) {
         
        Toast.makeText(parent.getContext(), 
                "Change to Town Hall Level " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_LONG).show();
        
        GetElements();
    }
 
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
 
    }
    
    public THLevelOnItemSelectedListener(Activity poActivity) {
    	moActivity = poActivity;
    }
    
    private void GetElements() {
        RelativeLayout vwMainLayout =  (RelativeLayout) moActivity.findViewById(R.id.layoutMain);
        RelativeLayout.LayoutParams lp;

        Element element = new Element();
        int countElement = element.countElements(); 
        for (int i=1; i<=countElement; i++) {
        	TextView tv = new TextView(MyApplication.getAppContext());
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
 
}
