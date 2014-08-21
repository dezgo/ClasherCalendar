package com.derekgillett.clashercalendar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView.LayoutParams;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		Log.d("MainActivity", "Constructor"); 

		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // set title
        TextView tvTitle = (TextView) this.findViewById(R.id.tvTitle);
        Player player = MyApplication.getPlayerElements();
        tvTitle.setText(player.getVillageName() + "'s Village");
        
        GetElements();
    }

    private void GetElements() {
        GridLayout vwMainLayout =  (GridLayout) this.findViewById(R.id.layoutMain);
        GridLayout.LayoutParams lp;

        // clear out previous stuff first
        vwMainLayout.removeAllViews();
        
        // get player elements, and check it's not null
        Player playerElements = MyApplication.getPlayerElements();
        junit.framework.Assert.assertNotNull("MyApplication.getPlayerElements() global variable null!", playerElements);

    	TextView tv_title = new TextView(MyApplication.getAppContext());
    	tv_title.setText( R.string.grid_title1);
        lp = new GridLayout.LayoutParams();
        lp.columnSpec = GridLayout.spec(0);
        lp.rowSpec = GridLayout.spec(0);
        tv_title.setLayoutParams(lp);
    	vwMainLayout.addView(tv_title);

    	tv_title = new TextView(MyApplication.getAppContext());
    	tv_title.setText( R.string.grid_title2);
        lp = new GridLayout.LayoutParams();
        lp.columnSpec = GridLayout.spec(1);
        lp.rowSpec = GridLayout.spec(0);
        tv_title.setLayoutParams(lp);
    	vwMainLayout.addView(tv_title);

    	for (int i=1; i<=playerElements.size(); i++) {
        	final PlayerElement oPlayerElement = playerElements.getPlayerElement();
        	Element oElement = oPlayerElement.getElement();
        	
        	TextView tv = new TextView(MyApplication.getAppContext());
        	tv.setText( oElement.getName());
            lp = new GridLayout.LayoutParams();
            lp.columnSpec = GridLayout.spec(0);
            lp.rowSpec = GridLayout.spec(i);
            lp.width = LayoutParams.WRAP_CONTENT;
            lp.height = LayoutParams.WRAP_CONTENT;
            tv.setLayoutParams(lp);
        	vwMainLayout.addView(tv);

        	final TextView tv1 = new TextView(MyApplication.getAppContext());
            lp = new GridLayout.LayoutParams();
            lp.columnSpec = GridLayout.spec(1);
            lp.rowSpec = GridLayout.spec(i);
            lp.width = LayoutParams.WRAP_CONTENT;
            lp.height = LayoutParams.WRAP_CONTENT;
            lp.setGravity(Gravity.CENTER_HORIZONTAL);
            tv1.setLayoutParams(lp);
        	tv1.setText(String.valueOf(oPlayerElement.getLevel()));
        	vwMainLayout.addView(tv1);

        	Button btn1 = new Button(MyApplication.getAppContext());
            lp = new GridLayout.LayoutParams();
            lp.columnSpec = GridLayout.spec(2);
            lp.rowSpec = GridLayout.spec(i);
            lp.width = LayoutParams.WRAP_CONTENT;
            lp.height = LayoutParams.WRAP_CONTENT;
            btn1.setLayoutParams(lp);
        	btn1.setText("  +  ");
        	btn1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					LevelChangeOnClickListener(tv1, 1, oPlayerElement);
				}
			}); 
        	vwMainLayout.addView(btn1);

        	Button btn2 = new Button(MyApplication.getAppContext());
            lp = new GridLayout.LayoutParams();
            lp.columnSpec = GridLayout.spec(3);
            lp.rowSpec = GridLayout.spec(i);
            lp.width = LayoutParams.WRAP_CONTENT;
            lp.height = LayoutParams.WRAP_CONTENT;
            btn2.setLayoutParams(lp);
        	btn2.setText("  -  ");
        	btn2.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					LevelChangeOnClickListener(tv1, -1, oPlayerElement);
				}
			}); 
        	vwMainLayout.addView(btn2);
    	}
    }
    
    private void LevelChangeOnClickListener(TextView poTextview, int pnIncrement, PlayerElement poPlayerElement) {
    	int nCurrentValue = Integer.parseInt(poTextview.getText().toString());
    	int nNewValue = nCurrentValue + pnIncrement;
    	
        Player player = MyApplication.getPlayerElements();
    	if (nNewValue >= 1 && nNewValue <= poPlayerElement.getElement().getMaxLevel(player.getTHLevel())) {
	    	poTextview.setText(String.valueOf(nNewValue));
	        PlayerElement playerElement = player.getPlayerElement(poPlayerElement.getID());
	        playerElement.setLevel(nNewValue);
	        MyApplication.setPlayerElements(player);
        }
    }

}
