package com.example.asus.kugoumusic.Adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by asus on 2016/9/14.
 */
public class HeaderAdapter extends PagerAdapter {

    ArrayList<ImageView> viewList;

    public HeaderAdapter() {
    }

    public HeaderAdapter(ArrayList<ImageView> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        try{
            ((ViewPager) container).addView((View) viewList.get(position % viewList.size()),0);
        }catch (Exception e) {
            // TODO: handle exception
        }

        return viewList.get(position % viewList.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(viewList.get(position % viewList.size()));
    }

    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return super.getItemPosition(object);
    }

}
