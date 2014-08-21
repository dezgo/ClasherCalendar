package com.derekgillett.clashercalendar;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
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
        
        //GetElements();
        EditText et = (EditText) moActivity.findViewById(R.id.etVillageName);
        if (!et.getText().toString().equals("")) {
	        new Player( mnTHLevel, et.getText().toString() );
        }
    }
 
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
 
    }
    
    public THLevelOnItemSelectedListener(Activity poActivity) {
    	moActivity = poActivity;
    }
    
}
