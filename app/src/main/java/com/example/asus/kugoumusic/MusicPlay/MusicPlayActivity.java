package com.example.asus.kugoumusic.MusicPlay;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.kugoumusic.LoadData.LrcDownLoaderManager;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.discover_bendi.SongFragment;
import com.example.asus.kugoumusic.entity.LrcModel;
import com.example.asus.kugoumusic.entity.Track;
import com.example.asus.kugoumusic.selfwidget.LrcView;
import com.example.asus.kugoumusic.service.MusicBackService;
import com.example.asus.kugoumusic.service.OnMusicPlayStateListener;
import com.example.asus.kugoumusic.util.StatusBarUtils;
import com.example.asus.kugoumusic.util.TimeHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class MusicPlayActivity extends SwipeBackActivity implements OnMusicPlayStateListener, MusicBackService.OnPlayCompletionListener
,LrcDownLoaderManager.OnLrcChangedListener{

    @BindView(R.id.play_pause)
    ImageView play_pause;
    @BindView(R.id.previous)
    ImageView previous;
    @BindView(R.id.next)
    ImageView next;
    @BindView(R.id.play_mode)
    ImageView play_mode;

    @BindView(R.id.play_progress)
    SeekBar seekBar;
    @BindView(R.id.play_current_time)
    TextView currentTime;
    @BindView(R.id.play_song_total_time)
    TextView totalTime;

    @BindView(R.id.topText)
    TextView title;

    @BindView(R.id.lrcView)
    LrcView lrcView;

    @OnClick(R.id.back)
    public void back(View v) {
        finish();
    }

    private Track track;
    private MusicBackService musicBackService;
    int time;
    public static int position;


    private SwipeBackLayout mSwipeBackLayout;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicBackService.MusicBinder binder = (MusicBackService.MusicBinder) service;
            musicBackService = binder.getService();
            //注册音乐播放状态监听
            musicBackService.registerMusicPlayStateListener(MusicPlayActivity.this);
            //更新UI界面
            musicBackService.setListener(MusicPlayActivity.this);

            musicBackService.setLrcChangedListener(MusicPlayActivity.this);

            track = musicBackService.getCurTrack();//注意此代码
            //初始化当前播放信息
            initCurrentPlayInfo();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.black);
        setContentView(R.layout.activity_music_play);
        ButterKnife.bind(this);
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeSize(200);

        //设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_ALL);

        track = getIntent().getParcelableExtra("track");
        position = getIntent().getIntExtra("position", 0);
        title.setText(track.getTitle());


    }

    @Override
    protected void onStart() {
        super.onStart();
        //绑定服务
        bindService(new Intent(this, MusicBackService.class), conn, BIND_AUTO_CREATE);

        Log.i("MusicPlayActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MusicPlayActivity", "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //解除注册
        musicBackService.unRegisterMusicPlayStateListener(MusicPlayActivity.this);
        //解绑服务
        unbindService(conn);
    }

    /**
     * 初始化当前播放信息
     */
    private void initCurrentPlayInfo() {

        setPlayModeImage(musicBackService.getPlayMode());//设置播放模式图标

        if (!musicBackService.isPlaying(track.getData())) {
            if (track != null) {
                totalTime.setText(TimeHelper.milliSecondsToFormatTimeString(track.getDuration()));
                currentTime.setText(TimeHelper.milliSecondsToFormatTimeString(0));
                seekBar.setProgress(0 * seekBar.getMax() / (int) track.getDuration());
                if (musicBackService != null) {
//                    musicBackService.stopPlaySong();
                    musicBackService.startPlaySong(track);
                }
            }
        } else {
            totalTime.setText(TimeHelper.milliSecondsToFormatTimeString(track.getDuration()));
            musicBackService.updateProgress();
        }


        if (musicBackService != null) {
            if (musicBackService.getState() == MusicBackService.State.STOPPED) {

                play_pause.setImageResource(R.drawable.player_toolbar_play_normal);

            } else if (musicBackService.getState() == MusicBackService.State.PLAYING) {

                play_pause.setImageResource(R.drawable.player_toolbar_pause_normal);

            } else if (musicBackService.getState() == MusicBackService.State.PAUSED) {

                play_pause.setImageResource(R.drawable.player_toolbar_play_normal);

            }
        }


        //更新进度条
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    int curPosition = progress * (int) track.getDuration() / 100;
                    //调用Service方法，将mediaPlayer 播放的时间点改为curPosition
                    musicBackService.seekThePosition(curPosition);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                int p = seekBar.getProgress() * (int) track.getDuration() / seekBar.getMax();
                musicBackService.seekToSpecifiedPosition(p);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onPlayProgressUpdate(int currentMillis) {

        time = currentMillis;
        // 更新当前播放时间
        currentTime.setText(TimeHelper.milliSecondsToFormatTimeString(currentMillis));
        // 更新当前播放进度
        seekBar.setProgress(currentMillis * seekBar.getMax() / (int) track.getDuration());
    }

    @OnClick(R.id.play_pause)
    public void play_pause(View v) {
        if (musicBackService != null) {
            if (musicBackService.getState() == MusicBackService.State.STOPPED) {
                musicBackService.startPlaySong(track);
                play_pause.setImageResource(R.drawable.player_toolbar_pause_normal);

            } else if (musicBackService.getState() == MusicBackService.State.PLAYING) {
                musicBackService.pausePlaySong();
                play_pause.setImageResource(R.drawable.player_toolbar_play_normal);

            } else if (musicBackService.getState() == MusicBackService.State.PAUSED) {
                musicBackService.rePlaySong();
                play_pause.setImageResource(R.drawable.player_toolbar_pause_normal);

            }

            musicBackService.createNotification();
        }
    }

    @OnClick(R.id.next)
    public void next(View v) {
        position++;

        if (position <  PlayListMusic.PlayList.tracks.size()) {
            Track track = PlayListMusic.PlayList.getList(position);
            title.setText(track.getTitle());
            musicBackService.startPlayNextSong(track);
            play_pause.setImageResource(R.drawable.player_toolbar_pause_normal);
            totalTime.setText(TimeHelper.milliSecondsToFormatTimeString(track.getDuration()));
            SongFragment.adapter.changeVisable(position);

            SharedPreferences sp = getSharedPreferences("Position", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("position", position).commit();
        } else {
            position = PlayListMusic.PlayList.tracks.size()-1;
            Toast.makeText(MusicPlayActivity.this,"已经是最后一首了！",Toast.LENGTH_SHORT).show();
        }


    }

    @OnClick(R.id.previous)
    public void previous(View v) {
        position--;
        if (position >= 0) {
            Track track = PlayListMusic.PlayList.getList(position);
            title.setText(track.getTitle());
            musicBackService.startPlayNextSong(track);
            play_pause.setImageResource(R.drawable.player_toolbar_pause_normal);
            totalTime.setText(TimeHelper.milliSecondsToFormatTimeString(track.getDuration()));
            SongFragment.adapter.changeVisable(position);

            SharedPreferences sp = getSharedPreferences("Position", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("position", position).commit();
        } else {
            position = 0;
            Toast.makeText(MusicPlayActivity.this,"已经是第一首了！",Toast.LENGTH_SHORT).show();
        }

    }


    //设置播放模式
    @OnClick(R.id.play_mode)
    public void changePlayMode(View v) {
        if (musicBackService != null)
            musicBackService.changePlayMode();
    }

    private void setPlayModeImage(int mode) {
        switch (mode) {
            case MusicBackService.PlayMode.REPEAT_SINGLE://单曲循环
                play_mode.setImageResource(R.drawable.playmode_single);
                break;
            case MusicBackService.PlayMode.REPEAT://列表循环
                play_mode.setImageResource(R.drawable.playmode_order);
                break;
            case MusicBackService.PlayMode.SEQUENTIAL://顺序播放
                play_mode.setImageResource(R.drawable.playmode_loop);
                break;
            case MusicBackService.PlayMode.SHUFFLE://随机播放
                play_mode.setImageResource(R.drawable.playmode_random);
                break;
            default:
                break;
        }
    }


    @Override
    public void onPlayModeChanged(int playMode) {
        setPlayModeImage(playMode);
    }

    @Override
    public void playNext(int position) {
        Track track = PlayListMusic.PlayList.getList(position);
        title.setText(track.getTitle());
        play_pause.setImageResource(R.drawable.player_toolbar_pause_normal);
        if (musicBackService != null) {

            if (musicBackService.getState() == MusicBackService.State.STOPPED) {

                play_pause.setImageResource(R.drawable.player_toolbar_play_normal);

            } else if (musicBackService.getState() == MusicBackService.State.PLAYING) {

                play_pause.setImageResource(R.drawable.player_toolbar_pause_normal);

            } else if (musicBackService.getState() == MusicBackService.State.PAUSED) {

                play_pause.setImageResource(R.drawable.player_toolbar_play_normal);

            }
        }
    }


    @Override
    public void onLrcLoaded(List<LrcModel> lrcModels) {
        lrcView.setLrcList(lrcModels);

        Log.i("TAG", "lrcModels");
    }

    @Override
    public void onLyricSentenceChanged(int index) {
        lrcView.setIndex(index);
        lrcView.invalidate();

        Log.i("TAG", "onLyricSentenceChanged");
    }
}
