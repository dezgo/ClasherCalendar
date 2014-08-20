package com.derekgillett.clashercalendar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
 
public class THLevelOnItemSelectedListener implements OnItemSelectedListener {
 
	private int mnTHLevel;
	
    public void onItemSelected(AdapterView<?> parent, View view, int pos,
            long id) {
    	
    	mnTHLevel = Integer.parseInt(parent.getItemAtPosition(pos).toString());
        Toast.makeText(parent.getContext(), 
                "Change to Town Hall Level " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_LONG).show();
        
        //GetElements();
        Player playerElements = new Player(mnTHLevel);
        MyApplication.setPlayerElements(playerElements);
    }
 
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
 
    }
    
}
