package com.derekgillett.clashercalendar;

import java.text.NumberFormat;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	@SuppressWarnings("unused")
	private final static String TAG = "MainActivity";
	
	// holds list of all textviews showing the countdown times
	@SuppressLint("UseSparseArrays")
	private HashMap<Long, ItemUpgrade> moItemUpgrades = new HashMap<Long, ItemUpgrade>();

    private DrawerLayout mDrawerLayout;
    private GridLayout mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private boolean mbMaxed = true;
    private boolean mbElixir = true;
    private boolean mbGold = true;
    private boolean mbDefence = true;
    private boolean mbResource = true;
    private boolean mbArmy = true;
    private boolean mbOther = true;
    private boolean mbTroops = true;
    private boolean mbTrap = true;
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		
		// Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
		
		// Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_delete:
	            deletePlayer();
	            return true;
	        case R.id.home:
	        	NavUtils.navigateUpFromSameTask(this);
	        	return true;
	        case R.id.action_settings:
	    		intent = new Intent(this, SettingsActivity.class);
	    		startActivity(intent);
	    		return true;
	        case R.id.action_fix_levels:
	        	this.GetElements(findViewById(R.id.rlMain), (GridLayout) this.findViewById(R.id.layoutMain));
	        	return true;
	        case R.id.action_dashboard:
	        	this.GetElements_Dashboard(findViewById(R.id.rlMain), (GridLayout) this.findViewById(R.id.layoutMain));
	        	return true;
	        case R.id.action_inc_th_level:
	        	Globals.INSTANCE.getPlayer().incTHLevel();
	        	this.initialSetup(findViewById(R.id.rlMain));
//	        	this.GetElements_Dashboard(findViewById(R.id.rlMain), (GridLayout) this.findViewById(R.id.layoutMain));
	        	return true;
	        case R.id.action_home:
	    		intent = new Intent(this, StartActivity.class);
	    		startActivity(intent);
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	private void setNavCheckboxes() {
        CheckBox chkView;
        chkView = (CheckBox) this.findViewById(R.id.chkArmy);
        chkView.setOnCheckedChangeListener(new NavCheckboxChangeListener());
        chkView.setChecked(this.mbArmy);
        chkView = (CheckBox) this.findViewById(R.id.chkDefence);
        chkView.setOnCheckedChangeListener(new NavCheckboxChangeListener());
        chkView.setChecked(this.mbDefence);
        chkView = (CheckBox) this.findViewById(R.id.chkElixir);
        chkView.setOnCheckedChangeListener(new NavCheckboxChangeListener());
        chkView.setChecked(this.mbElixir);
        chkView = (CheckBox) this.findViewById(R.id.chkGold);
        chkView.setOnCheckedChangeListener(new NavCheckboxChangeListener());
        chkView.setChecked(this.mbGold);
        chkView = (CheckBox) this.findViewById(R.id.chkMaxed);
        chkView.setOnCheckedChangeListener(new NavCheckboxChangeListener());
        chkView.setChecked(this.mbMaxed);
        chkView = (CheckBox) this.findViewById(R.id.chkOther);
        chkView.setOnCheckedChangeListener(new NavCheckboxChangeListener());
        chkView.setChecked(this.mbOther);
        chkView = (CheckBox) this.findViewById(R.id.chkResource);
        chkView.setOnCheckedChangeListener(new NavCheckboxChangeListener());
        chkView.setChecked(this.mbResource);
        chkView = (CheckBox) this.findViewById(R.id.chkTroops);
        chkView.setOnCheckedChangeListener(new NavCheckboxChangeListener());
        chkView.setChecked(this.mbTroops);
        chkView = (CheckBox) this.findViewById(R.id.chkTrap);
        chkView.setOnCheckedChangeListener(new NavCheckboxChangeListener());
        chkView.setChecked(this.mbTrap);
	}
	
	private class NavCheckboxChangeListener implements CheckBox.OnCheckedChangeListener {
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			switch (buttonView.getId()) {
				case R.id.chkArmy: 
					mbArmy = isChecked;
					break;
				case R.id.chkDefence: 
					mbDefence = isChecked;
					break;
				case R.id.chkElixir:
					mbElixir = isChecked;
					break;
				case R.id.chkGold:
					mbGold = isChecked;
					break;
				case R.id.chkMaxed:
					mbMaxed = isChecked;
					break;
				case R.id.chkOther:
					mbOther = isChecked;
					break;
				case R.id.chkResource:
					mbResource = isChecked;
					break;
				case R.id.chkTroops:
					mbTroops = isChecked;
					break;
				case R.id.chkTrap:
					mbTrap = isChecked;
					break;
			}
		}
    }	    
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		if (Utils.DEBUG) Log.d("MainActivity", "Constructor"); 

		super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);
        
		//MySQLiteHelper mySQLiteHelper = new MySQLiteHelper();
		//moDB = mySQLiteHelper.getWritableDatabase();

		// get extra info - is this an existing player?
        /*mbIsExisting = false;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
        	mbIsExisting = extras.getBoolean("com.derekgillett.clashercalendar.existing");
        }*/
        
        // nav drawer stuff
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (GridLayout) findViewById(R.id.left_drawer);

       // mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                if (findViewById(R.id.layoutMain) != null) {
                	GetElements_Dashboard(findViewById(R.id.rlMain), (GridLayout) findViewById(R.id.layoutMain));
                }
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        
        // show the main view
        showElementView();
        
        // setup navigation checkboxes
        setNavCheckboxes();
	}

	// show a view of the element list
	private void showElementView() {
        Fragment fragment = new ElementFragment();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        mDrawerLayout.closeDrawer(mDrawerList);
	}
	
  /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        //boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerLayout);
//        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    private void deletePlayer() {
        Toast.makeText(this, 
                "Delete current player",
                Toast.LENGTH_LONG).show();
        Globals.INSTANCE.getPlayer().delete();
        Globals.INSTANCE.setPlayer(null);
		Intent intent = new Intent(this, StartActivity.class);
		startActivity(intent);
	}
	
    private void removeRow(GridLayout vwMainLayout, 
		int row, 	         // row number (zero-based) to add/update
		int numCols) { 		 // number of column displayed
    
    	int index = row*numCols;
    	for (int i=0; i<numCols; i++) {
    		vwMainLayout.removeViewAt(index+i);
    	}
    }
    
    private void drawRow(GridLayout vwMainLayout, 
    		final ElementA poElementA, 
    		int pnTHLevel, 		 // current town hall level (to check if we're at max level)
    		final int row,       // row number (zero-based) to add/update
    		final int numCols, 		 // number of column displayed
    		boolean pbUpdate) {  // update the row, or append?
    	
    	int index = row*numCols;
    	
    	// quantity
    	TextView tvQuantity;
    	String sQty = String.valueOf(poElementA.getQuantity());
    	if (pbUpdate) {
    		tvQuantity = (TextView) vwMainLayout.getChildAt(index);
    		tvQuantity.setText(sQty);
    	} else {
    		tvQuantity = (TextView) getLayoutInflater().inflate(R.layout.tv_template, vwMainLayout, false);
    		tvQuantity.setText(sQty);
        	GridLayout.LayoutParams lp1 = new GridLayout.LayoutParams();
            lp1.setGravity(Gravity.CENTER_VERTICAL);
        	vwMainLayout.addView(tvQuantity, index, lp1);
    	}
    	index++;

    	// element name
    	if (!pbUpdate) {
	    	TextView tvElementName = (TextView) getLayoutInflater().inflate(R.layout.tv_template, vwMainLayout, false);
	    	tvElementName.setText( poElementA.getElement().getName());
	    	GridLayout.LayoutParams lp2 = new GridLayout.LayoutParams();
	        lp2.setGravity(Gravity.CENTER_VERTICAL);
	    	vwMainLayout.addView(tvElementName, index, lp2);
    	}
    	index++;

    	// level
    	final TextView tvLevel;
    	if (pbUpdate) {
    		tvLevel = (TextView) vwMainLayout.getChildAt(index);
    	} else {
    		tvLevel = (TextView) getLayoutInflater().inflate(R.layout.tv_template, vwMainLayout, false);
	    	GridLayout.LayoutParams lp3 = new GridLayout.LayoutParams();
	        lp3.setGravity(Gravity.CENTER_VERTICAL);
	        tvLevel.setText("Lvl " + String.valueOf(poElementA.getLevel()));
	    	vwMainLayout.addView(tvLevel, index, lp3);
    	}
    	index++;

        // check if we've hit max or min level
    	int nCurrentValue = poElementA.getLevel();
    	boolean bDrawInc = nCurrentValue+1 <= poElementA.getElement().getMaxLevel(pnTHLevel);
        boolean bDrawDec = nCurrentValue-1 >= 0;

        // container to hold buttons
        LinearLayout ll;
        if (pbUpdate) {
        	ll = (LinearLayout) vwMainLayout.getChildAt(index);
        } else {
        	ll = new LinearLayout(this);
        }
        
    	// Increment / Decrement buttons
        // tricky bit to save adding and removing buttons unnecessarily
        if (pbUpdate) {
        	for (int nBtnIndex=ll.getChildCount()-1; nBtnIndex >= 0; nBtnIndex--) {
        		Button btn = (Button) ll.getChildAt(nBtnIndex);
        		if (btn.getText().equals("  +  ")) {
        			if (bDrawInc) bDrawInc = false; else ll.removeViewAt(nBtnIndex);
        		}
        		if (btn.getText().equals("  -  ")) {
        			if (bDrawDec) bDrawDec = false; else ll.removeViewAt(nBtnIndex);
        		}
        	}
        }

        if (bDrawInc) {
        	Button btn1 = (Button) getLayoutInflater().inflate(R.layout.btn_template, ll, false);
        	btn1.setText("  +  ");
        	btn1.setOnTouchListener(new RepeatListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					LevelChangeOnClickListener(row, numCols, true, poElementA);
				}
			}));
        	ll.addView(btn1, 0);	// add at index zero to make this the first button
        }

        if (bDrawDec) {
        	Button btn2 = (Button) getLayoutInflater().inflate(R.layout.btn_template, ll, false);
        	btn2.setText("  -  ");
        	btn2.setOnTouchListener(new RepeatListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					LevelChangeOnClickListener(row, numCols, false, poElementA);
				}
			}));
        	ll.addView(btn2);
        }
        
        if (!pbUpdate) vwMainLayout.addView(ll, index);

        // last element, so no need to increment index. 
        // remember to add that in if adding more columns though
    }

	private void GetElements(View poParent, GridLayout vwMainLayout) {
		final int numCols = 4;
		
        // found situations where vwMainLayout was null. definitely get outta here if
        // that happens
        if (vwMainLayout == null) return;
        
        // clear out previous stuff first
        //TODO: don't do this, very inefficient
        // work out another 
        vwMainLayout.removeAllViews();
        
        // update column count - it might have been changed by viewing the dashboard
        vwMainLayout.setColumnCount(numCols);

        // get player elements, and check it's not null
        Player player = Globals.INSTANCE.getPlayer();
        junit.framework.Assert.assertNotNull("MyApplication.getPlayerElements() global variable null!", player);
/*
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
*/
    	//player.sortByBuilding(1);
        player.moveToFirstA();
        int row=0;
    	while (!player.isAfterLastA()) {
    		drawRow(vwMainLayout, player.getElementA(), player.getTHLevel(), row++, numCols, false);
        	
            // move to next element in aggregated array
        	player.moveToNextA();
    	}

    	// show total upgrade time remaining (do this at the end after we've filtered items)
        TextView tv_upgrade_time = (TextView) poParent.findViewById(R.id.tvUpgradeTime);
    	tv_upgrade_time.setText(Utils.Time_ValToText(player.getUpgradeTimeMax()));    	
    }
    
	private void GetElements_Dashboard(View poParent, GridLayout vwMainLayout) {
        GridLayout.LayoutParams lp;

        // unsure if this could happen, but wanted to cover it off just in case
        if (vwMainLayout == null) {
        	if (Utils.DEBUG) Log.d("MainActivity","Exiting GetElements_Dashboard - vwMainLayout null");
        	return;
        }
        
        // clear out previous stuff first
        vwMainLayout.removeAllViews();
        
        // set number of columns (it might have been changed if they used the update qtys view)
        vwMainLayout.setColumnCount(12);

        // get player elements, and check it's not null
        // final so it can be used in subclasses. also apparently it's good practice!
        // I *think* it's ok to make this final even though data in object player is changing because
        // the reference to the object itself doesn't change
        final Player oPlayer = Globals.INSTANCE.getPlayer();
        junit.framework.Assert.assertNotNull("MyApplication.getPlayerElements() global variable null!", oPlayer);

    	// quantity
    	TextView tv_title = (TextView) getLayoutInflater().inflate(R.layout.tv_template, vwMainLayout, false);
    	tv_title.setText( R.string.grid_title1);
    	tv_title.setTypeface(null, Typeface.BOLD);
    	vwMainLayout.addView(tv_title);
    	
    	// quantity order
    	final ImageView ivOrderQty = (ImageView) getLayoutInflater().inflate(R.layout.img_template, vwMainLayout, false);
    	int resId = oPlayer.getSortArrowRes(oPlayer.getSortQty());
    	if (resId != 0) ivOrderQty.setImageResource(resId);
    	vwMainLayout.addView(ivOrderQty);

    	tv_title.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				oPlayer.sortByQty();
				ivOrderQty.setImageResource(oPlayer.getSortArrowRes(oPlayer.getSortQty()));
				MainActivity.this.GetElements_Dashboard(findViewById(R.id.rlMain), (GridLayout) findViewById(R.id.layoutMain));
			}
		});
    	
    	// building
    	tv_title = (TextView) getLayoutInflater().inflate(R.layout.tv_template, vwMainLayout, false);
    	tv_title.setText( R.string.grid_title2);
    	tv_title.setTypeface(null, Typeface.BOLD);
    	vwMainLayout.addView(tv_title);

    	// building order
    	final ImageView ivOrderBuilding = (ImageView) getLayoutInflater().inflate(R.layout.img_template, vwMainLayout, false);
    	resId = oPlayer.getSortArrowRes(oPlayer.getSortBuilding());
    	if (resId != 0) ivOrderBuilding.setImageResource(resId);
    	vwMainLayout.addView(ivOrderBuilding);
    	
    	tv_title.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				oPlayer.sortByBuilding();
				ivOrderBuilding.setImageResource(oPlayer.getSortArrowRes(oPlayer.getSortBuilding()));
				MainActivity.this.GetElements_Dashboard(findViewById(R.id.rlMain), (GridLayout) findViewById(R.id.layoutMain));
			}
		});

    	// level
    	tv_title = (TextView) getLayoutInflater().inflate(R.layout.tv_template, vwMainLayout, false);
    	tv_title.setText( R.string.grid_title3);
    	tv_title.setTypeface(null, Typeface.BOLD);
    	vwMainLayout.addView(tv_title);

    	// level order
    	final ImageView ivOrderLevel = (ImageView) getLayoutInflater().inflate(R.layout.img_template, vwMainLayout, false);
    	resId = oPlayer.getSortArrowRes(oPlayer.getSortLevel());
    	if (resId != 0) ivOrderLevel.setImageResource(resId);
    	vwMainLayout.addView(ivOrderLevel);
    	
    	tv_title.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				oPlayer.sortByLevel();
				ivOrderLevel.setImageResource(oPlayer.getSortArrowRes(oPlayer.getSortLevel()));
				MainActivity.this.GetElements_Dashboard(findViewById(R.id.rlMain), (GridLayout) findViewById(R.id.layoutMain));
			}
		});

    	// max level
    	tv_title = (TextView) getLayoutInflater().inflate(R.layout.tv_template, vwMainLayout, false);
    	tv_title.setText( R.string.grid_title4);
    	tv_title.setMaxEms(3);
    	tv_title.setTypeface(null, Typeface.BOLD);
    	vwMainLayout.addView(tv_title);

    	// max level order
    	final ImageView ivOrderMaxLevel = (ImageView) getLayoutInflater().inflate(R.layout.img_template, vwMainLayout, false);
    	resId = oPlayer.getSortArrowRes(oPlayer.getSortMaxLevel());
    	if (resId != 0) ivOrderMaxLevel.setImageResource(resId);
    	vwMainLayout.addView(ivOrderMaxLevel);
    	
    	tv_title.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				oPlayer.sortByMaxLevel();
				ivOrderMaxLevel.setImageResource(oPlayer.getSortArrowRes(oPlayer.getSortMaxLevel()));
				MainActivity.this.GetElements_Dashboard(findViewById(R.id.rlMain), (GridLayout) findViewById(R.id.layoutMain));
			}
		});

    	// cost
    	tv_title = (TextView) getLayoutInflater().inflate(R.layout.tv_template, vwMainLayout, false);
    	tv_title.setText( R.string.grid_title5);
    	tv_title.setTypeface(null, Typeface.BOLD);
    	vwMainLayout.addView(tv_title);

    	// cost order
    	final ImageView ivOrderCost = (ImageView) getLayoutInflater().inflate(R.layout.img_template, vwMainLayout, false);
    	resId = oPlayer.getSortArrowRes(oPlayer.getSortCost());
    	if (resId != 0) ivOrderCost.setImageResource(resId);
    	vwMainLayout.addView(ivOrderCost);
    	
    	tv_title.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				oPlayer.sortByCost();
				ivOrderCost.setImageResource(oPlayer.getSortArrowRes(oPlayer.getSortCost()));
				MainActivity.this.GetElements_Dashboard(findViewById(R.id.rlMain), (GridLayout) findViewById(R.id.layoutMain));
			}
		});

    	// build time
    	tv_title = (TextView) getLayoutInflater().inflate(R.layout.tv_template, vwMainLayout, false);
    	tv_title.setText( R.string.grid_title6);
    	tv_title.setMaxEms(3);
    	tv_title.setTypeface(null, Typeface.BOLD);
    	vwMainLayout.addView(tv_title);
    	
    	// build time order
    	final ImageView ivOrderBuildTime = (ImageView) getLayoutInflater().inflate(R.layout.img_template, vwMainLayout, false);
    	resId = oPlayer.getSortArrowRes(oPlayer.getSortBuildTime());
    	if (resId != 0) ivOrderBuildTime.setImageResource(resId);
    	vwMainLayout.addView(ivOrderBuildTime);
    	
    	tv_title.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				oPlayer.sortByBuildTime();
				ivOrderBuildTime.setImageResource(oPlayer.getSortArrowRes(oPlayer.getSortBuildTime()));
				MainActivity.this.GetElements_Dashboard(findViewById(R.id.rlMain), (GridLayout) findViewById(R.id.layoutMain));
			}
		});

    	oPlayer.moveToFirstA();
    	while (!oPlayer.isAfterLastA()) {
        	ElementA oElementA = oPlayer.getElementA();
            junit.framework.Assert.assertNotNull("oElementA variable null!", oElementA);

            Element oElement = oElementA.getElement();
            PlayerElement oPlayerElement = oPlayer.getPlayerElement(oElement.getId(), oElementA.getLevel());
            junit.framework.Assert.assertNotNull("oPlayerElement variable null!", oPlayerElement);

            // check on the filter. if this item should be excluded, do it now
            boolean bExclude = false;
            if (oPlayerElement.isMax() && !this.mbMaxed) bExclude = true;
            if (oElement.getCostType().getID() == Utils.CostTypeEnum.Elixir.getId() && !this.mbElixir) bExclude = true;
            if (oElement.getCostType().getID() == Utils.CostTypeEnum.Gold.getId() && !this.mbGold) bExclude = true;
            if (oElement.getCategory().getID() == Utils.CategoryEnum.Army.getId() && !this.mbArmy) bExclude = true;
            if (oElement.getCategory().getID() == Utils.CategoryEnum.Defence.getId() && !this.mbDefence) bExclude = true;
            if (oElement.getCategory().getID() == Utils.CategoryEnum.Other.getId() && !this.mbOther) bExclude = true;
            if (oElement.getCategory().getID() == Utils.CategoryEnum.Resource.getId() && !this.mbResource) bExclude = true;
            if (oElement.getCategory().getID() == Utils.CategoryEnum.Trap.getId() && !this.mbTrap) bExclude = true;
            
            // now if the item is excluded, update that in the players global var
            oPlayer.setExclude(oElementA, bExclude);
            if (!bExclude) {
	            
            	// qty
	        	TextView tv = (TextView) getLayoutInflater().inflate(R.layout.tv_template, vwMainLayout, false);
	        	tv.setText(String.valueOf(oElementA.getQuantity()));
	        	lp = new GridLayout.LayoutParams();
	        	GridLayout.Spec s = GridLayout.spec(GridLayout.UNDEFINED, 2);	//colspan=2
	        	lp.columnSpec = s;
	        	tv.setLayoutParams(lp);
	        	vwMainLayout.addView(tv);
	
	        	// element name
	        	tv = (TextView) getLayoutInflater().inflate(R.layout.tv_template, vwMainLayout, false);
	        	tv.setText( oElement.getName());
	        	lp = new GridLayout.LayoutParams();
	        	lp.columnSpec = s;
	        	tv.setLayoutParams(lp);
	        	vwMainLayout.addView(tv);
	
	        	// element level
	        	TextView tv1 = (TextView) getLayoutInflater().inflate(R.layout.tv_template, vwMainLayout, false);
	            lp = new GridLayout.LayoutParams();
	        	lp.columnSpec = s;
	            lp.setGravity(Gravity.CENTER_HORIZONTAL);
	            tv1.setLayoutParams(lp);
	        	tv1.setText(String.valueOf(oPlayerElement.getLevel()));
	        	vwMainLayout.addView(tv1);
	
	        	// element max level
	        	TextView tv5 = (TextView) getLayoutInflater().inflate(R.layout.tv_template, vwMainLayout, false);
	            lp = new GridLayout.LayoutParams();
	        	lp.columnSpec = s;
	            lp.setGravity(Gravity.CENTER_HORIZONTAL);
	            tv5.setLayoutParams(lp);
	        	tv5.setText(String.valueOf(oPlayerElement.getElement().getMaxLevel(oPlayer.getTHLevel())));
	        	vwMainLayout.addView(tv5);
	
	        	// element cost / cost type
	        	LinearLayout ll = new LinearLayout(this);
	        	
	        	TextView tv2 = (TextView) getLayoutInflater().inflate(R.layout.tv_template, vwMainLayout, false);
	        	if (oPlayerElement.isMax())
	        		tv2.setText("Maxed");
	        	else
	        		tv2.setText( NumberFormat.getInstance().format(oPlayerElement.getUpgradeCost(null)));
	        	ll.addView(tv2);
	
	        	// element cost type
	        	ImageView ivCostType = (ImageView) getLayoutInflater().inflate(R.layout.img_template, vwMainLayout, false);
	        	ivCostType.setImageResource(oElement.getCostType().getResID());
	        	ll.addView(ivCostType);

	            lp = new GridLayout.LayoutParams();
	        	lp.columnSpec = s;
	        	ll.setLayoutParams(lp);
	        	vwMainLayout.addView(ll);
	        	
	        	// element time to build
	        	TextView tv3;
	        	if (oPlayerElement.isMax()) {
		        	tv3 = (TextView) getLayoutInflater().inflate(R.layout.tv_template, vwMainLayout, false);
	        		tv3.setText("n/a");
	        	} else {
	        		if (Utils.DEBUG) Log.d("Dashboard", "Is PlayerElement " + oPlayerElement.getID() + " being upgraded?");
	        		ItemUpgrade oItemUpgrade = this.moItemUpgrades.get(oPlayerElement.getID());
	            	if (oItemUpgrade == null) {
	            		if (Utils.DEBUG) Log.d("Dashboard", "No, or upgrades array not yet setup");
			        	tv3 = (TextView) getLayoutInflater().inflate(R.layout.tv_template, vwMainLayout, false);
		            	
			        	// if this item is actually being upgraded, add it to the upgrades array now
			        	if (oPlayerElement.getSecondsRemaining() > 0) {
		            		ItemUpgrade itemUpgrade = new ItemUpgrade(this, tv3, oPlayerElement);
		            		this.moItemUpgrades.put(oPlayerElement.getID(), itemUpgrade);
		            	}
		        		
	            	} else {
	            		if (Utils.DEBUG) Log.d("Dashboard", "Yes - get textview from upgrades array");
	            		tv3 = oItemUpgrade.getTVBuildTime();
	            	}
	        		tv3.setText(Utils.Time_ValToText(oPlayerElement.getUpgradeTime()));
	        	}
	        	
	        	final PlayerElement oPlayerElementFinal = oPlayerElement;

	        	// colspan 2
	        	lp = new GridLayout.LayoutParams();
	        	lp.columnSpec = s;
	        	tv3.setLayoutParams(lp);
	        	
	        	tv3.setOnTouchListener(new OnTouchListener() {
					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						arg0.performClick();
						onTouchHandler(arg0, oPlayerElementFinal);
						return false;
					} });
	        	if (tv3.getParent() == null) vwMainLayout.addView(tv3);
            }

        	// move to the next player element in the player object
            oPlayer.moveToNextA();
    	}

    	// show total upgrade time remaining (do this at the end after we've filtered items)
        TextView tv_upgrade_time = (TextView) poParent.findViewById(R.id.tvUpgradeTime);
    	tv_upgrade_time.setText(Utils.Time_ValToText(oPlayer.getUpgradeTimeMax()));    	
    	
    	// show total upgrade cost 
    	int nUpgradeCost = oPlayer.getUpgradeCostMax(Utils.CostTypeEnum.Elixir.getId());
    	String sCost = NumberFormat.getInstance().format(nUpgradeCost);
    	TextView tv_upgrade_cost_elixir = (TextView) poParent.findViewById(R.id.tvUpgradeCostElixir);
    	tv_upgrade_cost_elixir.setText(sCost);

    	// show total upgrade cost 
    	nUpgradeCost = oPlayer.getUpgradeCostMax(Utils.CostTypeEnum.Gold.getId());
    	sCost = NumberFormat.getInstance().format(nUpgradeCost);
    	TextView tv_upgrade_cost_gold = (TextView) poParent.findViewById(R.id.tvUpgradeCostGold);
    	tv_upgrade_cost_gold.setText(sCost);
    }
    
    private void onTouchHandler(View arg0, PlayerElement poPlayerElement) {
//    	switch (arg1.getAction()) {
//			case MotionEvent.ACTION_DOWN:
//				break;
//    	}
// not actually looking for any particular touch event, so could just do it here without checking
    	// what happened

    	// only do this if the element isn't currently being upgraded
    	if (this.moItemUpgrades.get(poPlayerElement.getID()) == null) {
    		ItemUpgrade oItemUpgrade = new ItemUpgrade(this, (TextView) arg0, poPlayerElement);
    		moItemUpgrades.put(ItemUpgrade.getKey(poPlayerElement), oItemUpgrade);
    		Player player = Globals.INSTANCE.getPlayer();
    		player.forceRepopulate();
    		this.GetElements_Dashboard(findViewById(R.id.rlMain), (GridLayout) findViewById(R.id.layoutMain));
	    }
    }
    
    public void upgradeDone(long pnPlayerElementID) {
    	// get global reference to player
    	Player oPlayer = Globals.INSTANCE.getPlayer();
    	
    	// get player element that's just been upgraded
    	PlayerElement oPlayerElement = oPlayer.getPlayerElement(pnPlayerElementID);
    	
    	// finish upgrade increments level and other stuff
    	oPlayerElement.finishUpgrade();

    	// remove from upgrades array
    	moItemUpgrades.remove(pnPlayerElementID);
    	
    	// and redraw
    	oPlayer.forceRepopulate();
    	GetElements_Dashboard(findViewById(R.id.rlMain), (GridLayout) findViewById(R.id.layoutMain));
    }

    // change the level of the selected item
    // IMPORTANT: assuming the list is in alpha order by element name, then by element level
    private void LevelChangeOnClickListener(int pnRow, int pnCols, boolean pbIncrement, ElementA poElementA) {
		// do the actual change first
        Player player = Globals.INSTANCE.getPlayer();
    	long nElementID = poElementA.getElement().getId();
    	PlayerElement oPlayerElement = player.getPlayerElement(nElementID,poElementA.getLevel());
    	if (oPlayerElement != null) player.incPlayerElementLevel(oPlayerElement, pbIncrement);

    	// now update the UI
    	GridLayout gl = (GridLayout) this.findViewById(R.id.layoutMain);
    	
    	// retrieve the surrounding element info
    	TextView tvPrevQty = null; int nPrevQty = 0;
    	TextView tvPrevName = null; String sPrevName = "";
    	TextView tvPrevLevel = null; int nPrevLevel = 0;
    	TextView tvQty = (TextView) gl.getChildAt(pnRow * pnCols); int nQty = Integer.parseInt(tvQty.getText().toString());
    	TextView tvName = (TextView) gl.getChildAt(pnRow * pnCols+1); String sName = tvName.getText().toString();
    	TextView tvLevel = (TextView) gl.getChildAt(pnRow * pnCols+2); int nLevel = Integer.parseInt(tvLevel.getText().toString().substring(4));
    	TextView tvNextQty = null; int nNextQty = 0;
    	TextView tvNextName = null; String sNextName = "";
    	TextView tvNextLevel = null; int nNextLevel = 0;
    	if (pnRow > 0) {	// only do this if we're not on the first row
    		tvPrevQty = (TextView) gl.getChildAt((pnRow-1) * pnCols); nPrevQty = Integer.parseInt(tvPrevQty.getText().toString());
    		tvPrevName = (TextView) gl.getChildAt((pnRow-1) * pnCols+1); sPrevName = tvPrevName.getText().toString(); 
    		tvPrevLevel = (TextView) gl.getChildAt((pnRow-1) * pnCols+2); nPrevLevel = Integer.parseInt(tvPrevLevel.getText().toString().substring(4));
    	}
    	if (gl.getChildCount() > (pnRow+1)*pnCols) {	// only do this if we're before the last row
    		tvNextQty = (TextView) gl.getChildAt((pnRow+1) * pnCols); nNextQty = Integer.parseInt(tvNextQty.getText().toString());
    		tvNextName = (TextView) gl.getChildAt((pnRow+1) * pnCols+1); sNextName = tvNextName.getText().toString();
    		tvNextLevel = (TextView) gl.getChildAt((pnRow+1) * pnCols+2); nNextLevel = Integer.parseInt(tvNextLevel.getText().toString().substring(4));
    	}
    	
    	// if we only had one of these before, remove the row (assuming increments of 1
    	if (tvQty.getText().equals("1")) removeRow(gl, pnRow, pnCols);
    	
    	// if we're going down, check if the previous row is what we're going to
    	// if it is, just add to qty, otherwise create a new row
    	if (!pbIncrement && pnRow > 0) {
    		if (sPrevName.equals(sName) && nPrevLevel == nLevel-1) 
    			tvPrevQty.setText(String.valueOf(nPrevQty+1));
    		else
    			drawRow(gl, poElementA, player.getTHLevel(), pnRow, pnCols, false);
   		}

    	// if we're going up, check if the next row is what we're going to
    	// if it is, just add to qty, otherwise create a new row
    	if (pbIncrement && gl.getChildCount() > (pnRow+1)*pnCols) {
    		if (sNextName.equals(sName) && nNextLevel == nLevel+1) 
    			tvNextQty.setText(String.valueOf(nNextQty+1));
    		else
    			drawRow(gl, poElementA, player.getTHLevel(), pnRow+1, pnCols, false);
   		}
    }

    private void initialSetup(View poView) {
        // set title
        TextView tvTitle = (TextView) poView.findViewById(R.id.tvTitle);
        Player player = Globals.INSTANCE.getPlayer();
        tvTitle.setText(player.getVillageName() + "'s Village (Townhall " + player.getTHLevel() + ")");
        
        // show total upgrade time remaining
        TextView tv_upgrade_time = (TextView) poView.findViewById(R.id.tvUpgradeTime);
    	tv_upgrade_time.setText(Utils.Time_ValToText(player.getUpgradeTimeMax()));
    	
    	// then show elements
    	GetElements_Dashboard(poView, (GridLayout) poView.findViewById(R.id.layoutMain));
    }
    
    /**
     * Fragment that appears in the "content_frame"
     */
    public class ElementFragment extends Fragment {
        public ElementFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_main, container, false);

            initialSetup(rootView);
            return rootView;
        }
    }
}
