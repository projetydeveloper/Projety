package com.projety.view.listview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Djeme Mahamat on 22/03/2015.
 */
public class HomeTabsPageAdapter extends FragmentStatePagerAdapter {

    String[] tabtitles;

    public HomeTabsPageAdapter(String[] tabtitles, android.support.v4.app.FragmentManager fm) {
        super(fm);
        this.tabtitles = tabtitles;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0 :
                Fragment fragment = new HomeEventListFragment();
                Bundle args = new Bundle();
                args.putInt(HomeEventListFragment.ARG_EVENT_TYPE, position);
                fragment.setArguments(args);
                return fragment;
            default:
                Fragment fragment2 = new HomeEventListFragment();
                Bundle args2 = new Bundle();
                args2.putInt(HomeEventListFragment.ARG_EVENT_TYPE, position);
                fragment2.setArguments(args2);
                return fragment2;
        }


    }

    @Override
    public int getCount() {
        return tabtitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabtitles[position];

    }
}
