package com.katana.itour;
/**
 * Created by MCTamayo on 12/14/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.katana.itour.MyTripTabBudget;
import com.katana.itour.MyTripTabOrganize;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MyTripTabBudget tab1 = new MyTripTabBudget();
                return tab1;
            case 1:
                MyTripTabOrganize tab2 = new MyTripTabOrganize();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}