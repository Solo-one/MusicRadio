package com.example.asus.kugoumusic.discover_bendi;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.asus.kugoumusic.Adapter.SingerNumAdapter;
import com.example.asus.kugoumusic.Adapter.TrackAdapter;
import com.example.asus.kugoumusic.LoadData.TrackLoader;
import com.example.asus.kugoumusic.MusicPlay.MusicPlayActivity;
import com.example.asus.kugoumusic.MusicPlay.PlayListMusic;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.entity.Singer;
import com.example.asus.kugoumusic.entity.Track;
import com.example.asus.kugoumusic.lazyFragment.LazyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingerFragment extends LazyFragment {

    private View v;
    private List<Singer> mData;
    private SingerNumAdapter adapter;

    private boolean isPrepared = false;//要加载视图是否准备好
    private boolean isFirst = true;//是否是第一次加载

    @BindView(R.id.lv)
    ListView listView;

    public SingerFragment() {
        // Required empty public constructor
    }

    @Override
    public void lazyInitData() {
        if (isPrepared && isVisiable && isFirst) {
            TrackLoader loader = new TrackLoader(getActivity());
            mData.addAll(loader.loadSingerInBackground());
            adapter.notifyDataSetChanged();
            isFirst = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_singer, container, false);
            ButterKnife.bind(this, v);
            mData = new ArrayList<>();
            adapter = new SingerNumAdapter(getActivity(),mData);
            listView.setAdapter(adapter);

            //进行懒加载
            isPrepared = true;
            lazyInitData();
        }

        return v;
    }



}
