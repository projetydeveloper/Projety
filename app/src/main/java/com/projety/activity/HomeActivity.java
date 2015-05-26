package com.projety.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.projety.app.R;
import com.projety.view.dialog.DanceFilterDialogFragment;
import com.projety.view.listview.HomeTabsPageAdapter;
import com.projety.view.tabs.SlidingTabLayout;


public class HomeActivity extends ActionBarActivity {

    private static final String FRAGMENT_YES_NO_DIALOG = "dialog";
    //FragmentManager fm = getSupportFragmentManager();
    Toolbar toolbar;

    SlidingTabLayout mTabs;
    private String[] dance_array = {
            "Toutes les danses", "Lindy Hop", "Rock", "Salsa", "Swing", "Batchata", "Kizomba", "Tango"
    };

    private HomeTabsPageAdapter mDanceCollectionPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.i("HomeActivity", "Welcome..." + getIntent().getStringExtra(LoginActivity.TAG));

        Toast.makeText(this, getIntent().getStringExtra(LoginActivity.TAG), Toast.LENGTH_LONG).show();


        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.

        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        String[] tabtitles = getResources().getStringArray(R.array.home_array_tabtitles);
        mDanceCollectionPageAdapter =
                new HomeTabsPageAdapter(
                        tabtitles, getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.home_viewpager);
        mViewPager.setAdapter(mDanceCollectionPageAdapter);
        mTabs = (SlidingTabLayout) findViewById(R.id.home_sliding_tablayout);
       // HorizontalScrollView hr;

        mTabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
        // Setting Custom Color for the Scroll bar indicator of the Tab View
        mTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
        mTabs.setViewPager(mViewPager);

    }

    public void showialog() {
        DanceFilterDialogFragment dm = new DanceFilterDialogFragment();
        dm.show(getFragmentManager(), FRAGMENT_YES_NO_DIALOG);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.home_activity_action_search) {
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.home_activity_action_filter) {
            Toast.makeText(this, "Choose", Toast.LENGTH_SHORT).show();
            showialog();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}

