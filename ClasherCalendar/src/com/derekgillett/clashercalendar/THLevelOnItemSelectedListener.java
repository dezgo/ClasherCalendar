package com.derekgillett.clashercalendar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
 
public class THLevelOnItemSelectedListener implements OnItemSelectedListener {
 
	private boolean mbInitial = true;
	
    public void onItemSelected(AdapterView<?> parent, View view, int pos,
            long id) {
    	
    	// little trick to avoid calling this code the first time it's drawn
    	if (mbInitial)
    		mbInitial = false;
    	else {
	        Toast.makeText(parent.getContext(), 
	                "Change to Town Hall Level " + parent.getItemAtPosition(pos).toString(),
	                Toast.LENGTH_LONG).show();
        }
    }

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}    
}
