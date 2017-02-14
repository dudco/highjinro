package com.example.dudco.highjinro;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dudco.highjinro.Fragment.MyInfoFragment;
import com.example.dudco.highjinro.Fragment.SchoolFragment;
import com.example.dudco.highjinro.Fragment.TalkFragment;

/**
 * Created by dudco on 2017. 2. 14..
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position){
            case 0: return new TalkFragment();
            case 1: return new SchoolFragment();
            case 2: return new MyInfoFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
