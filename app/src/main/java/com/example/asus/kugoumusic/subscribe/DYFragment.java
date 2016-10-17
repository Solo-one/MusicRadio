package com.example.asus.kugoumusic.subscribe;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.kugoumusic.LoadData.TrackLoader;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.lazyFragment.LazyFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DYFragment extends LazyFragment {

    private View v;

    private boolean isPrepared = false;//要加载视图是否准备好
    private boolean isFirst = true;//是否是第一次加载

    public DYFragment() {
        // Required empty public constructor
    }

    @Override
    public void lazyInitData() {
        if (isPrepared && isVisiable && isFirst) {
            isFirst = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_dy, container, false);

            //进行懒加载
            isPrepared = true;
            lazyInitData();
        }
        return v;
    }

}
