package com.example.asus.kugoumusic.subscribe;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.asus.kugoumusic.Adapter.TuiJianAdapter;
import com.example.asus.kugoumusic.LoadData.TrackLoader;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.entity.MusicWebAlbum;
import com.example.asus.kugoumusic.http.CallBack;
import com.example.asus.kugoumusic.lazyFragment.LazyFragment;
import com.example.asus.kugoumusic.util.VolleyUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LSFragment extends LazyFragment {

    private View v;
    TuiJianAdapter adapter;
    private List<MusicWebAlbum.SongListBean> mData;

    private boolean isPrepared = false;//要加载视图是否准备好
    private boolean isFirst = true;//是否是第一次加载

    @BindView(R.id.lv)
    ListView lv;

    public LSFragment() {
        // Required empty public constructor
    }

    @Override
    public void lazyInitData() {
        if (isPrepared && isVisiable && isFirst) {
            loadLast();
            isFirst = false;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_l, container, false);
            ButterKnife.bind(this, v);
            mData = new ArrayList<>();
            adapter = new TuiJianAdapter(mData,getActivity());
            lv.setAdapter(adapter);

            //进行懒加载
            isPrepared = true;
            lazyInitData();

        }
        return v;
    }

    /**
     * 网络监听回调方法
     */
    private class MyCallBack implements CallBack {

        @Override
        public void onSuccess(String response) {
            Gson gson = new Gson();
            MusicWebAlbum musicWebAlbum = gson.fromJson(response,MusicWebAlbum.class);
            List<MusicWebAlbum.SongListBean> items= musicWebAlbum.getSong_list();
            mData.addAll(items);
            adapter.notifyDataSetChanged();

        }

        @Override
        public void onErrer(VolleyError error) {

        }

    }


    //上拉加载更多数据
    private void loadLast() {
        VolleyUtil.Get("http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=2&size=8&offset=0")
                .setCallBack(new MyCallBack())
                .build()
                .setPriority(Request.Priority.HIGH)
                .addRequestHeadrs("apikey", "4600fe45a7f631f4800368013fb1a76e")
                .start();
    }

}
