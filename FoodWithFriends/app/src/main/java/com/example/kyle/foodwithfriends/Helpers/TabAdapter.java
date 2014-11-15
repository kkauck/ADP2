package com.example.kyle.foodwithfriends.Helpers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kyle.foodwithfriends.CreateRecipe;
import com.example.kyle.foodwithfriends.MainFragment;

public class TabAdapter extends FragmentStatePagerAdapter {

    public TabAdapter(FragmentManager fm) {

        super(fm);

    }

    @Override
    public Fragment getItem(int i) {

        if (i == 0){

            return new MainFragment();

        } else if (i == 1){

            return new CreateRecipe();

        } else if (i == 2){

            return new CreateRecipe();

        }

        return null;

    }

    @Override
    public int getCount() {

        return 3;

    }

    @Override
    public int getItemPosition(Object object) {

        return POSITION_NONE;

    }
}
