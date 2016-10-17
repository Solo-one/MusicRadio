package com.example.asus.kugoumusic.discover_bendi;


import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.asus.kugoumusic.Adapter.TrackAdapter;
import com.example.asus.kugoumusic.Guide.GuideActivity;
import com.example.asus.kugoumusic.LoadData.TrackLoader;
import com.example.asus.kugoumusic.MusicPlay.MusicPlayActivity;
import com.example.asus.kugoumusic.MusicPlay.PlayListMusic;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.entity.Track;
import com.example.asus.kugoumusic.lazyFragment.LazyFragment;
import com.example.asus.kugoumusic.service.MusicBackService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongFragment extends LazyFragment {

    private View v;
    public static TrackAdapter adapter;
    private List<Track> mData;

    @BindView(R.id.lv)
    ListView lv;

    MusicBackService musicBackService;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    int num;

    private boolean isPrepared = false;//要加载视图是否准备好
    private boolean isFirst = true;//是否是第一次加载


    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicBackService.MusicBinder binder = (MusicBackService.MusicBinder) service;
            musicBackService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBackService = null;
        }
    };


    public SongFragment() {
        // Required empty public constructor
    }


    @Override
    public void lazyInitData() {
        if (isPrepared && isVisiable && isFirst) {
            initData();
            PlayListMusic.PlayList.setTracks(mData);//给音乐清单添加数据
            isFirst = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_song, container, false);
            ButterKnife.bind(this, v);
            sp = getActivity().getSharedPreferences("Position", Context.MODE_PRIVATE);
            editor = sp.edit();
            num = sp.getInt("position", 0);

            mData = new ArrayList<>();
            adapter = new TrackAdapter(getActivity(), mData);
            lv.setAdapter(adapter);

            lv.setSelectionFromTop(num,300);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                    adapter.changeVisable(position);
                    editor.putInt("position", position).commit();

                    if (musicBackService != null) {
                        Track track = mData.get(position);

                        if (musicBackService.isPlaying(track.getData())) {
                            Intent intent = new Intent(getActivity(), MusicPlayActivity.class);
                            intent.putExtra("track", track);
                            intent.putExtra("position", position);
                            startActivity(intent);
                        } else {
                            if (num == position) {
                                Intent intent = new Intent(getActivity(), MusicPlayActivity.class);
                                intent.putExtra("track", track);
                                intent.putExtra("position", position);
                                startActivity(intent);
                            }
                            musicBackService.startPlaySong(track);
                        }
                    }
                }
            });

            //进行懒加载
            isPrepared = true;
            lazyInitData();

        }

        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        num = sp.getInt("position", 0);
        adapter.changeVisable(num);
        getActivity().bindService(new Intent(getActivity(), MusicBackService.class), conn, Context.BIND_AUTO_CREATE);//绑定服务
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unbindService(conn);//解除绑定
    }

    public void initData() {
        TrackLoader loader = new TrackLoader(getActivity());
        mData.addAll(loader.loadTrackInBackground());
    }

}
