package com.example.asus.kugoumusic.subscribe;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.asus.kugoumusic.Adapter.TuiJianAdapter;
import com.example.asus.kugoumusic.MusicPlay.WebPlayActivity;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.dao.SongListBeanDao;
import com.example.asus.kugoumusic.entity.MusicWebAlbum;
import com.example.asus.kugoumusic.http.CallBack;
import com.example.asus.kugoumusic.subscribe.TuiJian.ZhuanJiActivity;
import com.example.asus.kugoumusic.util.VolleyUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TJFragment extends Fragment {

    private View v;
    private List<MusicWebAlbum.SongListBean> mData;
    private int offset = 0;
    private SongListBeanDao dao;

    TuiJianAdapter adapter;

    @BindView(R.id.pull_lv)
    PullToRefreshListView listView;


    public TJFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_tj, container, false);
            ButterKnife.bind(this,v);
            dao = new SongListBeanDao(getActivity());
            mData = new ArrayList<>();
            mData.addAll(dao.findAllSongs());
            adapter = new TuiJianAdapter(mData,getActivity());
            listView.setAdapter(adapter);
            loadLast();

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MusicWebAlbum.SongListBean bean = mData.get(position - 1);
                   /* Intent intent = new Intent(getActivity(), WebPlayActivity.class);
                    intent.putExtra("song_id", bean.getSong_id());*/

                    Intent intent = new Intent(getActivity(), ZhuanJiActivity.class);
                    intent.putExtra("album_id", bean.getAlbum_id());
                    startActivity(intent);
                }
            });

            listView.setMode(PullToRefreshBase.Mode.BOTH);
            listView.setScrollingWhileRefreshingEnabled(true);
            init();

            listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                    loadLast();
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    loadMore();
                }
            });



        }
        return v;
    }

    private void init()
    {
        ILoadingLayout startLabels = listView.getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("喜马拉雅,正在载入...");// 刷新时
        startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示

        ILoadingLayout endLabels = listView.getLoadingLayoutProxy(
                false, true);
        endLabels.setPullLabel("上拉刷新...");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("喜马拉雅,正在载入...");// 刷新时
        endLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示

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

            if (offset == 0) {
                mData.clear();
            }

            mData.addAll(items);
            adapter.notifyDataSetChanged();
            listView.onRefreshComplete();

            if (offset == 0) {
                dao.deleteAllSong();//删除数据库所有数据
                dao.addSongList(items);
            }

        }

        @Override
        public void onErrer(VolleyError error) {
            Toast.makeText(getActivity(),"网络异常，请检查网络",Toast.LENGTH_SHORT).show();
            listView.onRefreshComplete();
        }

    }



    //上拉加载更多数据
    private void loadLast() {
        offset = 0;
        VolleyUtil.Get("http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=2&size=12&offset="+offset)
                .setCallBack(new MyCallBack())
                .build()
                .setPriority(Request.Priority.HIGH)
                .addRequestHeadrs("apikey", "4600fe45a7f631f4800368013fb1a76e")
                .start();
    }

    //上拉加载更多数据
    private void loadMore() {
        offset = offset + 12;
        VolleyUtil.Get("http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=2&size=12&offset="+offset)
                .setCallBack(new MyCallBack())
                .build()
                .setPriority(Request.Priority.HIGH)
                .addRequestHeadrs("apikey", "4600fe45a7f631f4800368013fb1a76e")
                .start();
    }

}
