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
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	//private SQLiteDatabase moDB;
	
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
	
    @SuppressLint("InflateParams")
	private void GetElements(View poParent, GridLayout vwMainLayout) {
        // found situations where vwMainLayout was null. definitely get outta here if
        // that happens
        if (vwMainLayout == null) return;
        
        // clear out previous stuff first
        vwMainLayout.removeAllViews();
        
        // update column count - it might have been changed by viewing the dashboard
        vwMainLayout.setColumnCount(5);

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
    	player.moveToFirstA();
    	while (!player.isAfterLastA()) {
        	final ElementA oElementA = player.getElementA();
        	
        	Element oElement = oElementA.getElement();
        	
        	// quantity
        	TextView tv1 = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
        	tv1.setText(String.valueOf(oElementA.getQuantity()));
        	GridLayout.LayoutParams lp1 = new GridLayout.LayoutParams();
            lp1.setGravity(Gravity.CENTER_VERTICAL);
            tv1.setLayoutParams(lp1);
        	vwMainLayout.addView(tv1);

        	// element name
        	TextView tv2 = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
        	tv2.setText( oElement.getName());
        	GridLayout.LayoutParams lp2 = new GridLayout.LayoutParams();
            lp2.setGravity(Gravity.CENTER_VERTICAL);
            tv2.setLayoutParams(lp2);
        	vwMainLayout.addView(tv2);

        	TextView tv3 = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
        	GridLayout.LayoutParams lp3 = new GridLayout.LayoutParams();
            lp3.setGravity(Gravity.CENTER_VERTICAL);
            tv3.setLayoutParams(lp3);
        	tv3.setText("Lvl " + String.valueOf(oElementA.getLevel()));
        	vwMainLayout.addView(tv3);
        	final TextView tv4 = tv3;

        	// use this layout parameter if there's only one button
        	GridLayout.LayoutParams lp4 = new GridLayout.LayoutParams();
            lp4.setGravity(Gravity.CENTER);
        	lp4.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 2);

            // check if we've hit max or min level
        	int nCurrentValue = oElementA.getLevel();
        	boolean bMax = nCurrentValue+1 > oElementA.getElement().getMaxLevel(player.getTHLevel());
            boolean bMin = nCurrentValue-1 < 0;
            boolean bCentre = bMax && !bMin || !bMax && bMin;

            // just in case an element level can neither be increased nor decreased, put in a blank
            // element so the layout doesn't get mucked up
            if (bMax && bMin) {
            	TextView oTVMax = new TextView(this);
            	oTVMax.setLayoutParams(lp4);
            	oTVMax.setGravity(Gravity.CENTER);
            	vwMainLayout.addView(oTVMax);
            }
            
        	// Draw increment button (if appropriate)
            if (!bMax) {
	        	AutoRepeatButton btn1 = new AutoRepeatButton(this);
	        	btn1.setText("  +  ");
	        	btn1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						//v.performClick();
						LevelChangeOnClickListener(tv4, 1, oElementA);
					}
				});
	        	if (bCentre) btn1.setLayoutParams(lp4);
	        	btn1.setGravity(Gravity.CENTER);
//	        	lp4.columnSpec = GridLayout.spec(3);
//	        	btn1.setLayoutParams(lp4);
	        	vwMainLayout.addView(btn1);
            }

        	// Draw decrement button (if appropriate)
            if (!bMin) {
	        	AutoRepeatButton btn2 = new AutoRepeatButton(this);
	        	btn2.setText("  -  ");
	        	btn2.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						//v.performClick();
						LevelChangeOnClickListener(tv4, -1, oElementA);
					}
				});
	        	if (bCentre) btn2.setLayoutParams(lp4);
	        	btn2.setGravity(Gravity.CENTER);
//	        	lp4.columnSpec = GridLayout.spec(3);
//	        	btn2.setLayoutParams(lp4);
	        	vwMainLayout.addView(btn2);
	        }
            
            // move to next element in aggregated array
        	player.moveToNextA();
    	}

    	// show total upgrade time remaining (do this at the end after we've filtered items)
        TextView tv_upgrade_time = (TextView) poParent.findViewById(R.id.tvUpgradeTime);
    	tv_upgrade_time.setText(Utils.Time_ValToText(player.getUpgradeTimeMax()));    	
    }
    
    @SuppressLint("InflateParams")
	private void GetElements_Dashboard(View poParent, GridLayout vwMainLayout) {
        GridLayout.LayoutParams lp;

        // unsure if this could happen, but wanted to cover it off just in case
        if (vwMainLayout == null) {
        	if (Utils.DEBUG) Log.d("MainActivity","Exiting GetElements_Dashboard - vwMainLayout null");
        	return;
        }
        
        // clear out previous stuff first
        if (Utils.DEBUG) Log.d("MainActivity","Clear existing views");
        vwMainLayout.removeAllViews();
        if (Utils.DEBUG) Log.d("MainActivity","Clear existing views - done");
        
        // set number of columns (it might have been changed if they used the update qtys view)
        vwMainLayout.setColumnCount(6);

        // get player elements, and check it's not null
        Player player = Globals.INSTANCE.getPlayer();
        junit.framework.Assert.assertNotNull("MyApplication.getPlayerElements() global variable null!", player);

    	// quantity
    	TextView tv_title = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
    	tv_title.setText( R.string.grid_title1);
    	tv_title.setTypeface(null, Typeface.BOLD);
    	vwMainLayout.addView(tv_title);

    	// building
    	tv_title = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
    	tv_title.setText( R.string.grid_title2);
    	tv_title.setTypeface(null, Typeface.BOLD);
    	vwMainLayout.addView(tv_title);

    	// level
    	tv_title = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
    	tv_title.setText( R.string.grid_title3);
    	tv_title.setTypeface(null, Typeface.BOLD);
    	vwMainLayout.addView(tv_title);

    	// max level
    	tv_title = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
    	tv_title.setText( R.string.grid_title4);
    	tv_title.setMaxEms(3);
    	tv_title.setTypeface(null, Typeface.BOLD);
    	vwMainLayout.addView(tv_title);

    	// cost
    	tv_title = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
    	tv_title.setText( R.string.grid_title5);
    	tv_title.setTypeface(null, Typeface.BOLD);
    	vwMainLayout.addView(tv_title);

    	// build time
    	tv_title = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
    	tv_title.setText( R.string.grid_title6);
    	tv_title.setMaxEms(3);
    	tv_title.setTypeface(null, Typeface.BOLD);
    	vwMainLayout.addView(tv_title);
    	
    	player.moveToFirstA();
    	while (!player.isAfterLastA()) {
        	ElementA oElementA = player.getElementA();
            junit.framework.Assert.assertNotNull("oElementA variable null!", oElementA);

            Element oElement = oElementA.getElement();
            PlayerElement oPlayerElement = player.getPlayerElement(oElement.getId(), oElementA.getLevel());
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
        	player.setExclude(oElementA, bExclude);
            if (!bExclude) {
	            
            	// qty
	        	TextView tv = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
	        	tv.setText(String.valueOf(oElementA.getQuantity()));
	        	vwMainLayout.addView(tv);
	
	        	// element name
	        	tv = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
	        	tv.setText( oElement.getName());
	        	vwMainLayout.addView(tv);
	
	        	// element level
	        	TextView tv1 = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
	            lp = new GridLayout.LayoutParams();
	            lp.setGravity(Gravity.CENTER_HORIZONTAL);
	            tv1.setLayoutParams(lp);
	        	tv1.setText(String.valueOf(oPlayerElement.getLevel()));
	        	vwMainLayout.addView(tv1);
	
	        	// element max level
	        	TextView tv5 = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
	            lp = new GridLayout.LayoutParams();
	            lp.setGravity(Gravity.CENTER_HORIZONTAL);
	            tv5.setLayoutParams(lp);
	        	tv5.setText(String.valueOf(oPlayerElement.getElement().getMaxLevel(player.getTHLevel())));
	        	vwMainLayout.addView(tv5);
	
	        	// element cost / cost type
	        	LinearLayout ll = new LinearLayout(this);
	        	
	        	TextView tv2 = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
	        	if (oPlayerElement.isMax())
	        		tv2.setText("Maxed");
	        	else
	        		tv2.setText( NumberFormat.getInstance().format(oPlayerElement.getUpgradeCost()));
	        	ll.addView(tv2);
	
	        	// element cost type
	        	ImageView iv1 = (ImageView) getLayoutInflater().inflate(R.layout.img_template, null);
	        	iv1.setImageResource(oElement.getCostType().getResID());
	
	        	ll.addView(iv1);
	        	vwMainLayout.addView(ll);
	        	
	        	// element time to build
	        	TextView tv3;
	        	if (oPlayerElement.isMax()) {
		        	tv3 = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
	        		tv3.setText("n/a");
	        	} else {
	        		if (Utils.DEBUG) Log.d("Dashboard", "Is PlayerElement " + oPlayerElement.getID() + " being upgraded?");
	        		ItemUpgrade oItemUpgrade = this.moItemUpgrades.get(oPlayerElement.getID());
	            	if (oItemUpgrade == null) {
	            		if (Utils.DEBUG) Log.d("Dashboard", "No, or upgrades array not yet setup");
			        	tv3 = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
		            	
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
        	player.moveToNextA();
    	}

    	// show total upgrade time remaining (do this at the end after we've filtered items)
        TextView tv_upgrade_time = (TextView) poParent.findViewById(R.id.tvUpgradeTime);
    	tv_upgrade_time.setText(Utils.Time_ValToText(player.getUpgradeTimeMax()));    	
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
    private void LevelChangeOnClickListener(TextView poTextview, int pnIncrement, ElementA poElementA) {
    	int nCurrentValue = poElementA.getLevel();
    	int nNewValue = nCurrentValue + pnIncrement;
    	
        Player player = Globals.INSTANCE.getPlayer();
        if (nNewValue >= 0 && nNewValue <= poElementA.getElement().getMaxLevel(player.getTHLevel())) {
        	long nElementID = poElementA.getElement().getId();
        	PlayerElement oPlayerElement = player.getPlayerElement(nElementID,poElementA.getLevel());
        	if (oPlayerElement != null) {
	        	player.incPlayerElementLevel(oPlayerElement, pnIncrement);
	        	this.GetElements(findViewById(R.id.rlMain), (GridLayout) this.findViewById(R.id.layoutMain));
        	}
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
