package com.esint.provide.company.supervisory.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.esint.provide.company.supervisory.fragment.ReadedFragment;
import com.esint.provide.company.supervisory.fragment.UnReadFragment;

/**
 * Created by MX on 2017/6/1.
 */

public class TabAdapter extends FragmentPagerAdapter {
    public TabAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                UnReadFragment unReadFragment = new UnReadFragment();
                return unReadFragment;
            case 1:
                ReadedFragment readedFragment = new ReadedFragment();
                return readedFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
