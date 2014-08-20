package com.derekgillett.clashercalendar;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.GridLayout;
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
        GridLayout vwMainLayout =  (GridLayout) moActivity.findViewById(R.id.layoutMain);
        GridLayout.LayoutParams lp;

        // clear out previous stuff first
        vwMainLayout.removeAllViews();
        
        PlayerElements playerElements = new PlayerElements(mnTHLevel);
        MyApplication.setPlayerElements(playerElements);
        
//        THElements townHall = new THElements(mnTHLevel);
  //      ArrayList<THElement> oTHElements = townHall.getTHElements();

        for (int i=0; i<playerElements.size(); i++) {
        	final PlayerElement oPlayerElement = playerElements.getPlayerElement();
        	Element oElement = oPlayerElement.getElement();
        	
        	TextView tv = new TextView(MyApplication.getAppContext());
        	tv.setText( oElement.getName());
            lp = new GridLayout.LayoutParams();
            lp.columnSpec = GridLayout.spec(0);
            lp.rowSpec = GridLayout.spec(i);
            tv.setLayoutParams(lp);
        	vwMainLayout.addView(tv);

        	final TextView tv1 = new TextView(MyApplication.getAppContext());
            lp = new GridLayout.LayoutParams();
            lp.columnSpec = GridLayout.spec(1);
            lp.rowSpec = GridLayout.spec(i);
            tv1.setLayoutParams(lp);
        	tv1.setText(String.valueOf(oPlayerElement.getLevel()));
        	vwMainLayout.addView(tv1);

        	Button btn1 = new Button(MyApplication.getAppContext());
            lp = new GridLayout.LayoutParams();
            lp.columnSpec = GridLayout.spec(2);
            lp.rowSpec = GridLayout.spec(i);
            lp.width = 60; lp.height = 60;
            btn1.setLayoutParams(lp);
        	btn1.setText("+");
        	btn1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					LevelChangeOnClickListener(tv1, 1, oPlayerElement.getID());
				}
			}); 
        	vwMainLayout.addView(btn1);

        	Button btn2 = new Button(MyApplication.getAppContext());
            lp = new GridLayout.LayoutParams();
            lp.columnSpec = GridLayout.spec(3);
            lp.rowSpec = GridLayout.spec(i);
            lp.width = 60; lp.height = 60;
            btn2.setLayoutParams(lp);
        	btn2.setText("-");
        	btn2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					LevelChangeOnClickListener(tv1, -1, oPlayerElement.getID());
				}
			}); 
        	vwMainLayout.addView(btn2);
    	}
    }
    
    private void LevelChangeOnClickListener(TextView poTextview, int pnIncrement, long pnPlayerElementID) {
    	int nCurrentValue = Integer.parseInt(poTextview.getText().toString());
    	int nNewValue = nCurrentValue + pnIncrement;
    	poTextview.setText(String.valueOf(nNewValue));
        PlayerElements playerElements = MyApplication.getPlayerElements();
        PlayerElement playerElement = playerElements.getPlayerElement(pnPlayerElementID);
        playerElement.setLevel(nNewValue);
        MyApplication.setPlayerElements(playerElements);
    }
 
}
