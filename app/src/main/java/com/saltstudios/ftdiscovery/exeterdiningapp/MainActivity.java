package com.saltstudios.ftdiscovery.exeterdiningapp;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {




    int switchHalls = 0;
    Calendar cal = Calendar.getInstance();
    final int month = cal.get(Calendar.MONTH);
    final int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
    final String[] monthName = {"Jan", "Feb", "Mar", "Apr", "May", "Jun","Jul", "Aug","Sep","Oct","Nov","Dec"};
    //this by no means works on leap years but I don't have to change it during my time at exeter :) i'm so edgy.
    final int[] daysIn = {31,28,31,30,31,30,31,31,30,31,30,31};

    int curMonth = month;
    int curDay = dayOfMonth;
    int curYear = cal.get(Calendar.YEAR);

    String displayServer = "https://exeter-dining-app.firebaseio.com/" + monthName[curMonth] + String.valueOf(curDay) + "/";

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private WetherellSectionsPagerAdapter mWetherellSectionsPagerAdapter;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Elm Street");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Firebase.setAndroidContext(this);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mWetherellSectionsPagerAdapter = new WetherellSectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        final Button dayBefore = (Button) findViewById(R.id.dayBefore);
        final TextView displayDay = (TextView) findViewById(R.id.dayDisplay);
        displayDay.setText(monthName[curMonth]+" "+curDay+", "+curYear);
        dayBefore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(curDay>1) curDay--;
                else {
                    if(curMonth>0)curMonth--;
                    else {
                        curMonth=11;
                        curYear--;
                    }
                    curDay=daysIn[curMonth];
                }
                displayDay.setText(monthName[curMonth]+" "+curDay+", "+curYear);
                displayServer = "https://exeter-dining-app.firebaseio.com/" + monthName[curMonth] + String.valueOf(curDay) + "/";
                if(switchHalls%2==0) {
                    displayServer = "https://exeter-dining-app.firebaseio.com/" + monthName[curMonth] + String.valueOf(curDay) + "/";
                    mViewPager.setAdapter(mSectionsPagerAdapter);
                }
                else {
                    displayServer = "https://exeter-dining-app.firebaseio.com/" + monthName[curMonth] + String.valueOf(curDay) + "/";
                    mViewPager.setAdapter(mWetherellSectionsPagerAdapter);
                }
            }
        });
        final Button dayAfter = (Button) findViewById(R.id.dayAfter);
        dayAfter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(curDay<daysIn[curMonth]) curDay++;
                else {
                    curDay=1;
                    if(curMonth<11) curMonth++;
                    else {
                        curMonth=0;
                        curYear++;
                    }
                }
                displayDay.setText(monthName[curMonth]+" "+curDay+", "+curYear);
                displayServer = "https://exeter-dining-app.firebaseio.com/" + monthName[curMonth] + String.valueOf(curDay) + "/";
                mViewPager.setAdapter(mSectionsPagerAdapter);
                mViewPager.setAdapter(mWetherellSectionsPagerAdapter);
                if(switchHalls%2==0) {
                    displayServer = "https://exeter-dining-app.firebaseio.com/" + monthName[curMonth] + String.valueOf(curDay) + "/";
                    mViewPager.setAdapter(mSectionsPagerAdapter);
                }
                else {
                    displayServer = "https://exeter-dining-app.firebaseio.com/" + monthName[curMonth] + String.valueOf(curDay) + "/";
                    mViewPager.setAdapter(mWetherellSectionsPagerAdapter);
                }

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.dhallChange) {
            String hall;
            if(switchHalls%2==0) {
                hall="Wetherell";
                switchHalls++;
                mViewPager.removeAllViews();
                displayServer = "https://exeter-dining-app.firebaseio.com/" + monthName[curMonth] + String.valueOf(curDay) + "/";
                mViewPager.setAdapter(mWetherellSectionsPagerAdapter);

            }
            else{
                hall="Elm Street";
                switchHalls++;
                mViewPager.removeAllViews();
                displayServer = "https://exeter-dining-app.firebaseio.com/" + monthName[curMonth] + String.valueOf(curDay) + "/";
                mViewPager.setAdapter(mSectionsPagerAdapter);
            }
            setTitle(hall);

            return true;
        }
        if (id == R.id.dank_info) {
            Toast.makeText(MainActivity.this, "SALT STUDIOS - CREDITS\nPerson 1: Gordon Chi '19 K House\nPerson 2: oops no friends to help out\nPerson 3: he also has no money to hire somebody", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.refresh) {
            if(switchHalls%2==0) {
                mViewPager.setAdapter(mSectionsPagerAdapter);
            }
            else {
                mViewPager.setAdapter(mWetherellSectionsPagerAdapter);
            }

        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    Breakfast b = new Breakfast(displayServer);
                    return b;
                case 1:
                    Lunch l = new Lunch(displayServer);
                    return l;
                case 2:
                    Dinner d = new Dinner(displayServer);
                    return d;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "BREAKFAST";
                case 1:
                    return "LUNCH";
                case 2:
                    return "DINNER";
            }
            return null;
        }
    }

    public class WetherellSectionsPagerAdapter extends FragmentStatePagerAdapter {

        public WetherellSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case 0:
                    BreakfastW b = new BreakfastW(displayServer);
                    return b;
                case 1:
                    LunchW l = new LunchW(displayServer);
                    return l;
                case 2:
                    DinnerW d = new DinnerW(displayServer);
                    return d;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "BREAKFAST";
                case 1:
                    return "LUNCH";
                case 2:
                    return "DINNER";
            }
            return null;
        }
    }
}
