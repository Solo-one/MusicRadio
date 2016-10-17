package com.example.asus.kugoumusic.discover.discover_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asus.kugoumusic.Adapter.TuiJianAdapter;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.dao.SongListBeanDao;
import com.example.asus.kugoumusic.entity.MusicWebAlbum;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreActivity extends AppCompatActivity {

    private List<MusicWebAlbum.SongListBean> mData;
    private SongListBeanDao dao;
    TuiJianAdapter adapter;

    @BindView(R.id.pull_lv)
    PullToRefreshListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);
        dao = new SongListBeanDao(this);
        mData = new ArrayList<>();
        mData.addAll(dao.findAllSongs());
        adapter = new TuiJianAdapter(mData,this);
        listView.setAdapter(adapter);
    }

}
