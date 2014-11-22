//Kyle Kauck

package com.example.kyle.foodwithfriends;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.example.kyle.foodwithfriends.Helpers.TabAdapter;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    private ViewPager mNewPage;
    private TabAdapter mTabAdapter;
    private ActionBar mActionBar;
    private String[] sections = {"Home", "Create Recipe", "Grocery List"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mNewPage = (ViewPager) findViewById(R.id.content);
        mActionBar = getActionBar();
        mTabAdapter = new TabAdapter(getSupportFragmentManager());
        mNewPage.setAdapter(mTabAdapter);

        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (String name : sections){

            mActionBar.addTab(mActionBar.newTab().setText(name).setTabListener(this));

        }

        mNewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){


            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {

                mActionBar.setSelectedNavigationItem(i);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        mNewPage.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        mNewPage.getAdapter().notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#571B7E")));

        return true;

    }

}
