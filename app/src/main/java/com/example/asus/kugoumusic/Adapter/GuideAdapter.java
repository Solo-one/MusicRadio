package com.example.asus.kugoumusic.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by asus on 2016/9/4.
 */
public class GuideAdapter extends FragmentPagerAdapter {
    private List<Fragment> mDataF;
    private List<String> mDataS;

    public GuideAdapter(FragmentManager fm, List<Fragment> mData) {
        super(fm);
        this.mDataF = mData;
    }

    public GuideAdapter(FragmentManager fm, List<Fragment> mData, List<String> string) {
        super(fm);
        this.mDataF = mData;
        this.mDataS = string;
    }

    @Override
    public Fragment getItem(int position) {
        return mDataF.get(position);
    }

    /**
     * @param object
     * @return
     */
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mDataF.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDataS.get(position);
    }

}