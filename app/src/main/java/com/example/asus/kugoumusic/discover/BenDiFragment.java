package com.example.asus.kugoumusic.discover;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.asus.kugoumusic.Adapter.DiscoverAdapter;
import com.example.asus.kugoumusic.Adapter.TrackAdapter;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.discover_bendi.AlbumFragment;
import com.example.asus.kugoumusic.discover_bendi.SingerFragment;
import com.example.asus.kugoumusic.discover_bendi.SongFragment;
import com.example.asus.kugoumusic.entity.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BenDiFragment extends Fragment {

    private View v;
    private List<Fragment> mDataF;
    private List<String> mDataS;
    DiscoverAdapter adapter;

    @BindView(R.id.tabs)
    TabLayout tabs;

    @BindView(R.id.vp)
    ViewPager vp;

    public BenDiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_ben_di, container, false);
            ButterKnife.bind(this, v);
            tabs = (TabLayout) v.findViewById(R.id.tabs);
            vp = (ViewPager) v.findViewById(R.id.vp);

            tabs.setTabMode(TabLayout.MODE_FIXED);//设置可以滑动
            tabs.setTabGravity(TabLayout.GRAVITY_FILL);

            initData();

            //适配器
            adapter = new
                    DiscoverAdapter(getChildFragmentManager(), mDataF, mDataS);
            vp.setAdapter(adapter);
            //关联tabs 和 ViewPage
            tabs.setupWithViewPager(vp);

        }
        return v;
    }


    /**
     * 初始化数据信息，
     * 将Fragment 添加到ViewPager 上面去
     */
    public void initData() {
        mDataF = new ArrayList<>();
        mDataS = new ArrayList<>();
        mDataS.add("单曲");
        mDataS.add("歌手");
        mDataS.add("专辑");

        SongFragment Song = new SongFragment();
        mDataF.add(Song);

        SingerFragment Singer = new SingerFragment();
        mDataF.add(Singer);

        AlbumFragment Album = new AlbumFragment();
        mDataF.add(Album);

    }


}
