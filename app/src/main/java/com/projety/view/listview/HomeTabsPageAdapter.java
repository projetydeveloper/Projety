package com.projety.view.listview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.projety.view.listview.party.HomePartyListFragment;

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
                Fragment fragment2 = new HomePartyListFragment();
                return fragment2;


            default:

             /**   Fragment fragment = new HomePartyListFragment();
                Bundle args = new Bundle();
                args.putInt(HomeEventListFragment.ARG_EVENT_TYPE, position);
                fragment.setArguments(args);
                return fragment;
              **/
                Fragment fragment1 = new HomePartyListFragment();
                return fragment1;
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
