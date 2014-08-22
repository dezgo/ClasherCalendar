package com.derekgillett.clashercalendar;

import android.R.anim;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_delete:
	            deletePlayer();
	            return true;
	        case R.id.home:
	        	NavUtils.navigateUpFromSameTask(this);
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		Log.d("MainActivity", "Constructor"); 

		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // show up button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        // set title
        TextView tvTitle = (TextView) this.findViewById(R.id.tvTitle);
        Player player = MyApplication.getPlayer();
        tvTitle.setText(player.getVillageName() + "'s TH " + player.getTHLevel() + " Village");
        
        GridLayout vwMainLayout =  (GridLayout) this.findViewById(R.id.layoutMain);
        GetElements(vwMainLayout);
    }

	private void deletePlayer() {
        Toast.makeText(this, 
                "Delete current player",
                Toast.LENGTH_LONG).show();
        MyApplication.getPlayer().delete();
        MyApplication.setPlayer(null);
		Intent intent = new Intent(this, StartActivity.class);
		startActivity(intent);
	}
	
    @SuppressLint("InflateParams")
	private void GetElements(GridLayout vwMainLayout) {
        GridLayout.LayoutParams lp;

        // clear out previous stuff first
        vwMainLayout.removeAllViews();
        
        // get player elements, and check it's not null
        Player player = MyApplication.getPlayer();
        junit.framework.Assert.assertNotNull("MyApplication.getPlayerElements() global variable null!", player);

    	TextView tv_title = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
    	tv_title.setText( R.string.grid_title1);
    	vwMainLayout.addView(tv_title);

    	tv_title = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
    	tv_title.setText( R.string.grid_title2);
    	vwMainLayout.addView(tv_title);

    	// add a couple of blank cells so we don't have to explicitly 
    	// specifiy the row and col
    	tv_title = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
    	tv_title.setText("");
    	vwMainLayout.addView(tv_title);

    	tv_title = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
    	tv_title.setText("");
    	vwMainLayout.addView(tv_title);

    	for (int i=1; i<=player.size(); i++) {
        	final PlayerElement oPlayerElement = player.getPlayerElement();
        	Element oElement = oPlayerElement.getElement();
        	
        	TextView tv = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
        	tv.setText( oElement.getName());
        	vwMainLayout.addView(tv);

        	final TextView tv1 = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
            lp = new GridLayout.LayoutParams();
            lp.setGravity(Gravity.CENTER_HORIZONTAL);
            tv1.setLayoutParams(lp);
        	tv1.setText(String.valueOf(oPlayerElement.getLevel()));
        	vwMainLayout.addView(tv1);

        	Button btn1 = (Button) getLayoutInflater().inflate(R.layout.btn_template, null);
        	btn1.setText("  +  ");
        	btn1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					LevelChangeOnClickListener(tv1, 1, oPlayerElement);
				}
			}); 
        	vwMainLayout.addView(btn1);

        	Button btn2 = (Button) getLayoutInflater().inflate(R.layout.btn_template, null);
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
    
    // change the level of the selected item
    private void LevelChangeOnClickListener(TextView poTextview, int pnIncrement, PlayerElement poPlayerElement) {
    	int nCurrentValue = Integer.parseInt(poTextview.getText().toString());
    	int nNewValue = nCurrentValue + pnIncrement;
    	
        Player player = MyApplication.getPlayer();
    	if (nNewValue >= 1 && nNewValue <= poPlayerElement.getElement().getMaxLevel(player.getTHLevel())) {
	    	poTextview.setText(String.valueOf(nNewValue));
	        PlayerElement playerElement = player.getPlayerElement(poPlayerElement.getID());
	        playerElement.setLevel(nNewValue);
	        MyApplication.setPlayer(player);
        }
    }

}
