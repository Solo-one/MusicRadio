package com.example.asus.kugoumusic.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by asus on 2016/10/2.
 */
public class ZhuangjiAdapter extends PagerAdapter {
    private List<View> mData;


    public ZhuangjiAdapter(List<View> mData) {
        this.mData = mData;
    }

    /**
     * 类似与 ListView  getView()方法
     * @param container 参数就是ViewPage
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v= mData.get(position);
        container.addView(v);
        return v;
    }

    /**
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mData.get(position));//删除指定位置的ViewPage
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}