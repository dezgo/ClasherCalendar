package com.derekgillett.clashercalendar;

import java.text.NumberFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
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
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	private ScheduledExecutorService moSEService;
	private MySETask moMySETask;
	private TextView moBuildTime;
	private PlayerElement moPlayerElement;
	private boolean mbIsExisting;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
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
	    		Intent intent = new Intent(this, SettingsActivity.class);
	    		startActivity(intent);
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		Log.d("MainActivity", "Constructor"); 

		super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);
        
        // get extra info - is this an existing player?
        mbIsExisting = false;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
        	mbIsExisting = extras.getBoolean("com.derekgillett.clashercalendar.existing");
        }
        
        // nav drawer stuff
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);

        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

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
	}

	// show a view of the element list
	private void showElementView() {
        Fragment fragment = new ElementFragment();
    //    Bundle args = new Bundle();
//        args.putInt( Element PlanetFragment.ARG_PLANET_NUMBER, position);
  //      fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
//        mDrawerList.setItemChecked(position, true);
//        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
	}
	
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        	showElementView();
        }
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
    	player.moveToFirst();
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
        	tv1.setText("Lvl " + String.valueOf(oPlayerElement.getLevel()));
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
    
    @SuppressLint("InflateParams")
	private void GetElements_Dashboard(GridLayout vwMainLayout) {
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

    	tv_title = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
    	tv_title.setText( R.string.grid_title3);
    	vwMainLayout.addView(tv_title);

    	tv_title = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
    	tv_title.setText( R.string.grid_title4);
    	vwMainLayout.addView(tv_title);

    	player.moveToFirst();
    	for (int i=1; i<=player.size(); i++) {
        	final PlayerElement oPlayerElement = player.getPlayerElement();
            junit.framework.Assert.assertNotNull("oPlayerElement variable null!", oPlayerElement);

            Element oElement = oPlayerElement.getElement();
        	
        	// element name
        	TextView tv = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
        	tv.setText( oElement.getName());
        	vwMainLayout.addView(tv);

        	// element level
        	TextView tv1 = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
            lp = new GridLayout.LayoutParams();
            lp.setGravity(Gravity.CENTER_HORIZONTAL);
            tv1.setLayoutParams(lp);
        	tv1.setText(String.valueOf(oPlayerElement.getLevel()));
        	vwMainLayout.addView(tv1);

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
        	switch (oElement.getCostType()) {
	        	case 1:
	        		iv1.setImageResource(R.drawable.gold);
	        		break;
	        		
	        	case 2:
	        		iv1.setImageResource(R.drawable.elixir);
	        		break;
	        		
	        	case 3:
	        		iv1.setImageResource(R.drawable.dark_elixir);
	        		break;

        	}
        	ll.addView(iv1);
        	vwMainLayout.addView(ll);
        	
        	// element time to build
        	TextView tv3 = (TextView) getLayoutInflater().inflate(R.layout.tv_template, null);
        	if (oPlayerElement.isMax())
        		tv3.setText("n/a");
        	else
        		tv3.setText(Utils.Time_ValToText(oPlayerElement.getUpgradeTime()));
        	tv3.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					arg0.performClick();
					onTouchHandler(arg0, oPlayerElement);
					return false;
				} });
        	vwMainLayout.addView(tv3);

    	}
    }
    
    private void onTouchHandler(View arg0, PlayerElement poPlayerElement) {
//    	switch (arg1.getAction()) {
//			case MotionEvent.ACTION_DOWN:
//				break;
//    	}
// not actually looking for any particular touch event, so could just do it here without checking
    	// what happened
    	
    	if (poPlayerElement.getSecondsRemaining() == 0) {
    		poPlayerElement.startUpgrade();
    		moPlayerElement = poPlayerElement;
        	this.moBuildTime = (TextView) arg0;

        	// setup timer
    	    if (moSEService == null) {
    	    	moSEService = Executors.newSingleThreadScheduledExecutor();
	    	    moMySETask = new MySETask();
	    	    moSEService.scheduleAtFixedRate(moMySETask,  0, 1, TimeUnit.SECONDS);
    	    }
	    }
    }

	//http://android-er.blogspot.com/2013/12/example-of-using-timer-and-timertask-on.html, and
    //http://stackoverflow.com/questions/14315293/converting-to-scheduledthreadpoolexecutor
    class MySETask implements Runnable {
		@Override
		public void run() {
	    	final int secsRemaining = moPlayerElement.getSecondsRemaining();
	    	if (secsRemaining == 0) {
	    		moSEService.shutdown();
	    		moSEService = null;
	    	}
	    	else runOnUiThread(new Runnable() {
				@Override
				public void run() {
					moBuildTime.setText(Utils.Time_ValToText(secsRemaining));
				}
	    	});
		}
    }
    
    // change the level of the selected item
    private void LevelChangeOnClickListener(TextView poTextview, int pnIncrement, PlayerElement poPlayerElement) {
    	int nCurrentValue = poPlayerElement.getLevel();
    	int nNewValue = nCurrentValue + pnIncrement;
    	
        Player player = MyApplication.getPlayer();
    	if (nNewValue >= 0 && nNewValue <= poPlayerElement.getElement().getMaxLevel(player.getTHLevel())) {
	    	poTextview.setText("Lvl " + String.valueOf(nNewValue));
	        PlayerElement playerElement = player.getPlayerElement(poPlayerElement.getID());
	        playerElement.setLevel(nNewValue);
	        MyApplication.setPlayer(player);
        }
    }

    private void initialSetup(View poView) {
        // set title
        TextView tvTitle = (TextView) poView.findViewById(R.id.tvTitle);
        Player player = MyApplication.getPlayer();
        tvTitle.setText(player.getVillageName() + "'s TH " + player.getTHLevel() + " Village");
        
        GridLayout vwMainLayout = (GridLayout) poView.findViewById(R.id.layoutMain);
        if (mbIsExisting)
        	GetElements_Dashboard(vwMainLayout);
        else
        	GetElements(vwMainLayout);
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
