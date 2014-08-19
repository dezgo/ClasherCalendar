package com.derekgillett.clashercalendar;

import java.util.ArrayList;

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
	private int mnTHLevel;
	
    public void onItemSelected(AdapterView<?> parent, View view, int pos,
            long id) {
    	
    	mnTHLevel = Integer.parseInt(parent.getItemAtPosition(pos).toString());
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

        // clear out previous stuff first
        vwMainLayout.removeAllViews();
        
        TownHall townHall = new TownHall(mnTHLevel);
        ArrayList<THElement> oTHElements = townHall.getTHElements();

        for (int i=0; i<oTHElements.size(); i++) {
        	TextView tv = new TextView(MyApplication.getAppContext());
        	tv.setId(1002+i);
            lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.BELOW, 1001+i);
            tv.setLayoutParams(lp);
            
        	tv.setText(oTHElements.get(i).getElement().getName() + " - " + 
        			String.valueOf(oTHElements.get(i).getQuantity()));

        	vwMainLayout.addView(tv);
        }
    }
 
}
