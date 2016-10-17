package com.example.asus.kugoumusic.Guide;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.kugoumusic.Adapter.DiscoverAdapter;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.downloader.SYFragment;
import com.example.asus.kugoumusic.downloader.XZFragment;
import com.example.asus.kugoumusic.downloader.ZJFragment;
import com.example.asus.kugoumusic.subscribe.DYFragment;
import com.example.asus.kugoumusic.subscribe.LSFragment;
import com.example.asus.kugoumusic.subscribe.TJFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownLoaderFragment extends Fragment {

    private View v;
    private List<Fragment> mDataF;
    private List<String> mDataS;
    DiscoverAdapter adapter;

    @BindView(R.id.tabs)
    TabLayout tabs;

    @BindView(R.id.vp)
    ViewPager vp;

    public ViewPager getVp() {
        return vp;
    }

    public DownLoaderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_down_loader, container, false);
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
        mDataS.add("专辑");
        mDataS.add("声音");
        mDataS.add("下载中");

        ZJFragment zjFragment = new ZJFragment();
        mDataF.add(zjFragment);

        SYFragment syFragment = new SYFragment();
        mDataF.add(syFragment);

        XZFragment xzFragment = new XZFragment();
        mDataF.add(xzFragment);

    }


}
