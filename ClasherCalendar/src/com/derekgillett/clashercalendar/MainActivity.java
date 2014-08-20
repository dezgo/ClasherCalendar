package com.derekgillett.clashercalendar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Activity moActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		Log.d("MainActivity", "Constructor"); 

		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void GetElements() {
        GridLayout vwMainLayout =  (GridLayout) moActivity.findViewById(R.id.layoutMain);
        GridLayout.LayoutParams lp;

        // clear out previous stuff first
        vwMainLayout.removeAllViews();
        
        Player playerElements = MyApplication.getPlayerElements();
        
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
        Player playerElements = MyApplication.getPlayerElements();
        PlayerElement playerElement = playerElements.getPlayerElement(pnPlayerElementID);
        playerElement.setLevel(nNewValue);
        MyApplication.setPlayerElements(playerElements);
    }

}
