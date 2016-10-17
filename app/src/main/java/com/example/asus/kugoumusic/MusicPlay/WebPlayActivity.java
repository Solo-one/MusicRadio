package com.example.asus.kugoumusic.MusicPlay;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.asus.kugoumusic.LoadData.LrcDownLoaderManager;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.entity.LrcModel;
import com.example.asus.kugoumusic.entity.WebMusic;
import com.example.asus.kugoumusic.http.CallBack;
import com.example.asus.kugoumusic.service.MusicBackService;
import com.example.asus.kugoumusic.util.StatusBarUtils;
import com.example.asus.kugoumusic.util.VolleyUtil;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class WebPlayActivity extends SwipeBackActivity implements LrcDownLoaderManager.OnLrcChangedListener{

    private MusicBackService musicBackService;
    private String song_id;
    private WebMusic.SonginfoBean songinfoBean;

    private SwipeBackLayout mSwipeBackLayout;

    @BindView(R.id.topText)
    TextView title;
    @BindView(R.id.singer)
    TextView singer;

    @OnClick(R.id.back)
    public void back(View v) {
        finish();
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicBackService.MusicBinder binder = (MusicBackService.MusicBinder) service;
            musicBackService = binder.getService();
//
//            Log.i("TAG",musicBackService.getSongID()+"sssss"+song_id);

            WebMusic.SonginfoBean SonginfoBean = musicBackService.getSonginfoBean();

            if (SonginfoBean != null) {
                if (SonginfoBean.getSong_id().equals(song_id)) {
                    title.setText(SonginfoBean.getTitle());
                    singer.setText(SonginfoBean.getAuthor());
                } else {
                    loadMore();
                }
            } else {
                loadMore();
            }


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.black);
        setContentView(R.layout.activity_web_play);
        ButterKnife.bind(this);
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeSize(200);
        //设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_ALL);

        song_id = getIntent().getStringExtra("song_id");

    }

    @Override
    protected void onStart() {
        super.onStart();
        //绑定服务
        bindService(new Intent(this, MusicBackService.class), conn, BIND_AUTO_CREATE);
    }


    @Override
    protected void onStop() {
        super.onStop();
        //解绑服务
        unbindService(conn);
    }

    @Override
    public void onLrcLoaded(List<LrcModel> lrcModels) {

    }

    @Override
    public void onLyricSentenceChanged(int index) {

    }

    /**
     * 网络监听回调方法
     */
    private class MyCallBack implements CallBack {

        @Override
        public void onSuccess(String response) {
            Gson gson = new Gson();

            WebMusic web = gson.fromJson(response, WebMusic.class);
            WebMusic.BitrateBean bean= web.getBitrate();
            songinfoBean = web.getSonginfo();
            musicBackService.startPlayWebSong(bean.getFile_link(),songinfoBean);

            changeUI();
        }

        @Override
        public void onErrer(VolleyError error) {

        }

    }

    public void changeUI() {
        title.setText(songinfoBean.getTitle());
        singer.setText(songinfoBean.getAuthor());
    }

    //上拉加载更多数据
    private void loadMore() {
        VolleyUtil.Get("http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.song.play&songid=" + song_id)
                .setCallBack(new MyCallBack())
                .build()
                .setPriority(Request.Priority.HIGH)
                .addRequestHeadrs("apikey", "4600fe45a7f631f4800368013fb1a76e")
                .start();
    }

}
