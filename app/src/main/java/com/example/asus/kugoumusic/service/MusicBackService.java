package com.example.asus.kugoumusic.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.asus.kugoumusic.BootAnimation.BootActivity;
import com.example.asus.kugoumusic.Guide.GuideActivity;
import com.example.asus.kugoumusic.LoadData.LrcDownLoaderManager;
import com.example.asus.kugoumusic.MusicPlay.PlayListMusic;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.discover_bendi.SongFragment;
import com.example.asus.kugoumusic.entity.Track;
import com.example.asus.kugoumusic.entity.WebMusic;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by asus on 2016/9/8.
 */
public class MusicBackService extends Service implements MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener,
        MediaPlayer.OnSeekCompleteListener {

    private LrcDownLoaderManager.OnLrcChangedListener lrcChangedListener;

    public void setLrcChangedListener(LrcDownLoaderManager.OnLrcChangedListener lrcChangedListener){

        this.lrcChangedListener = lrcChangedListener;

        if(this.lrcDownLoadManager != null){
            this.lrcDownLoadManager.setOnLrcChangedListener(lrcChangedListener);
            loadLrc();


        }
    }

    public void loadLrc(){

        //下载歌词
        lrcDownLoadManager.searchMusicFromWeb(curTrack.getTitle(), curTrack.getArtist());

    }


    private LrcDownLoaderManager lrcDownLoadManager;//歌词下载管理器

    private static final int MSG_TYPE_PROGRESS = 1;   //处理播放进度的编号
    private MediaPlayer mediaPlayer = null;//定义音频播放器

    private Track curTrack;//当前播放的歌曲
    private WebMusic.SonginfoBean SonginfoBean;//当前网络播放歌曲

    private int mState = State.STOPPED;//初始状态停止状态
    private int mPlayMode = PlayMode.REPEAT;//初始状态列表循环
    private int isOrWeb = -1;//是否本地音乐


    private ArrayList<OnMusicPlayStateListener> musicPlayStateListeners;//播放模式监听

    private NotificationManager nm;    //通知管理器
    private static final int NOTIFICATIONID = 1; //通知请求码RequsetCode
    public static final String PAUSE = "com.example.asus.kugoumusic.PAUSE";
    public static final String PLAY = "com.example.asus.kugoumusic.PLAY";
    public static final String NEXT = "com.example.asus.kugoumusic.NEXT";
    public static final String PREVIOUS = "com.example.asus.kugoumusic.PREVIOUS";

    AudioManager am = null;//处理音频焦点

    //得到当前播放的音乐
    public Track getCurTrack() {
        return curTrack;
    }

    public WebMusic.SonginfoBean getSonginfoBean() {
        return SonginfoBean;
    }

    //得到当前音乐播放的状态
    public int getState() {
        return mState;
    }

    //得到是否是本地音乐
    public int getIsOrWeb() {
        return isOrWeb;
    }



    //音乐的播放的状态
    public class State {
        public static final int STOPPED = 0;//停止状态
        public static final int PREPARED = 1;//加载状态
        public static final int PLAYING = 2;//播放状态
        public static final int PAUSED = 3;//暂停状态
    }


    //得到当前音乐播放的状态
    public int getPlayMode() {
        return mPlayMode;
    }


    /**
     * 播放模式<br>
     * 0代表单曲循环，1代表列表循环，2代表顺序播放，3代表随机播放
     */
    public class PlayMode {

        public static final int REPEAT = 0;//列表循环
        public static final int REPEAT_SINGLE = 1;//单曲循环
        public static final int SEQUENTIAL = 2;//顺序播放
        public static final int SHUFFLE = 3;//随机播放
    }

    /**
     * 音乐播放Binder类
     */
    public class MusicBinder extends Binder {
        public MusicBackService getService() {
            return MusicBackService.this;
        }

    }

    /**
     * 服务的生命周期
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MusicBackService", "onCreate");
//        initMediaPlayer();
        musicPlayStateListeners = new ArrayList<>();
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);//通知管理器
        am = (AudioManager) getSystemService(AUDIO_SERVICE);//音频播放焦点

        lrcDownLoadManager = new LrcDownLoaderManager();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MusicBackService", "onStartCommand");

        String action = intent.getAction();
        SharedPreferences sp = getSharedPreferences("Position", Context.MODE_PRIVATE);
        int position = sp.getInt("position", 0);

        if (action != null) {
            if (action.equals(PAUSE)) {
                pausePlaySong();//通知栏暂停Action 停止音乐播放
            } else if (action.equals(PLAY)) {
                rePlaySong();
            } else if (action.equals(NEXT)) {

                if (position < PlayListMusic.PlayList.tracks.size() - 1) {
                    curTrack = PlayListMusic.PlayList.getList(++position);
                    startPlayNextSong(curTrack);
                    SongFragment.adapter.changeVisable(position);
                } else {
                    curTrack = PlayListMusic.PlayList.getList(PlayListMusic.PlayList.tracks.size() - 1);
                    startPlayNextSong(curTrack);
                    SongFragment.adapter.changeVisable(PlayListMusic.PlayList.tracks.size() - 1);
                    Toast.makeText(getApplicationContext(), "已经是最后一首了！", Toast.LENGTH_LONG).show();
                }

                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("position", position).commit();

//                if (listener != null) {
//                    listener.playNext(position);
//                }

            } else if (action.equals(PREVIOUS)) {

                if (position > 1) {
                    curTrack = PlayListMusic.PlayList.getList(--position);
                    startPlayNextSong(curTrack);
                    SongFragment.adapter.changeVisable(position);
                } else {
                    curTrack = PlayListMusic.PlayList.getList(0);
                    startPlayNextSong(curTrack);
                    SongFragment.adapter.changeVisable(0);
                    Toast.makeText(getApplicationContext(), "已经是第一首了！", Toast.LENGTH_LONG).show();
                }

                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("position", position).commit();



            } else if (action.equals("")) {
                //结束服务
                stopSelf();
            }

            if (listener != null) {
                listener.playNext(position);
            }

            createNotification();
        }

        return START_NOT_STICKY;//系统kill 掉后，自动重启 调用onStartCommand（）方法，并保留上一次的Intent值
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MusicBackService", "onDestroy");
        if (mediaPlayer != null) {
            mediaPlayer.release();//释放资源
            mediaPlayer = null;
        }
    }


    public void seekThePosition(int curPosition) {
        if (mState != State.STOPPED) {
            mState = State.PAUSED;
            mediaPlayer.pause();
            mediaPlayer.seekTo(curPosition);//注意这是个异步方法，拖动后不能保证立刻到达指定位置
        }
    }


    public void updateProgress() {
        // 更新进度条
        if (!mServiceHandler.hasMessages(MSG_TYPE_PROGRESS)) {
            mServiceHandler.sendEmptyMessage(MSG_TYPE_PROGRESS);
        }

    }

    @Override
    public void onPrepared(MediaPlayer mp) {

        Log.i("MusicBackService", "onPrepared");
        try {

            if (!mediaPlayer.isPlaying()) {
                playSong();
                //更新状态栏
                createNotification();
//                mState = State.PLAYING;
//                mediaPlayer.start();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 更新进度条
        if (!mServiceHandler.hasMessages(MSG_TYPE_PROGRESS)) {
            mServiceHandler.sendEmptyMessage(MSG_TYPE_PROGRESS);
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.start();

        if (mPlayMode != PlayMode.REPEAT_SINGLE) {
            SharedPreferences sp = getSharedPreferences("Position", Context.MODE_PRIVATE);
            int position = sp.getInt("position", 0);
            curTrack = PlayListMusic.PlayList.getList(++position);
            startPlayNextSong(curTrack);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("position", position).commit();

            SongFragment.adapter.changeVisable(position);

            if (listener != null) {
                listener.playNext(position);
            }
        }

        Log.i("MusicBackService", "onCompletion");
    }

    /**
     * 更新播放界面监听
     */
    private OnPlayCompletionListener listener;

    public void setListener(OnPlayCompletionListener listener) {
        this.listener = listener;
    }

    public interface OnPlayCompletionListener {
        public void playNext(int position);
    }

    /**
     * 更新主界面Ui 按钮
     */
    private OnBgChangeListener bgChangelistener;

    public void setBgChange(OnBgChangeListener listener) {
        this.bgChangelistener = listener;
    }

    public interface OnBgChangeListener {
        public void BgChange(Track track);
        public void BgWebChange(WebMusic.SonginfoBean songinfoBean);
    }


    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }


    @Override
    public void onSeekComplete(MediaPlayer mp) {
        Log.i("MusicBackService", "onSeekComplete");
        if (mState != State.PLAYING) {
            mState = State.PLAYING;
            mp.start();
        }

        rePlaySong();
    }

    /**
     * 初始化播放器
     */
    public void initMediaPlayer() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.setOnSeekCompleteListener(this);
        } else {
            mediaPlayer.reset();//重置音乐播放器
        }
    }

    /**
     *  开始播放音乐 外部调用
     *
     *
     */
    public void startPlaySong(Track song) {
        Log.i("MusicBackService", "startPlaySong");

        isOrWeb = 0;//本地音乐

        //获取音频焦点
        requestFocus();
        //初始化音乐播放器
        initMediaPlayer();

        bgChangelistener.BgChange(song);

        try {
            curTrack = song;
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            //mediaPlayer.setDataSource(getApplicationContext(), ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,track.getId()));
            mediaPlayer.setDataSource(song.getData());
            mState = State.PREPARED;//准备状态
            mediaPlayer.prepareAsync();//准备播放
            // 如果是单曲循环，给MediaPlayer启动单曲播放
            mediaPlayer.setLooping(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 更新进度条
        if (!mServiceHandler.hasMessages(MSG_TYPE_PROGRESS)) {
            mServiceHandler.sendEmptyMessage(MSG_TYPE_PROGRESS);
        }
    }


    /**
     * 在线播放
     * @param song
     */
    public void startPlayWebSong(String song,WebMusic.SonginfoBean songinfoBean) {

        isOrWeb = 1;//网络音乐

        SonginfoBean = songinfoBean;

        //获取音频焦点
        requestFocus();
        //初始化音乐播放器
        initMediaPlayer();

        bgChangelistener.BgWebChange(songinfoBean);

        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(song);
            mState = State.PREPARED;//准备状态
            mediaPlayer.prepareAsync();//准备播放

            // 如果是单曲循环，给MediaPlayer启动单曲播放
            mediaPlayer.setLooping(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //暂停播放音乐
    public void pausePlaySong() {
        if (mState == State.PLAYING) {
            mState = State.PAUSED;
            mediaPlayer.pause();
        }
    }

    //重新播放播放音乐
    public void rePlaySong() {
//        mState = State.PLAYING;
        if (!mediaPlayer.isPlaying())
//            mediaPlayer.start();
            playSong();
        // 更新进度条
        if (!mServiceHandler.hasMessages(MSG_TYPE_PROGRESS)) {
            mServiceHandler.sendEmptyMessage(MSG_TYPE_PROGRESS);
        }
    }

    //停止播放
    public void stopPlaySong() {
        mState = State.STOPPED;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            //mediaPlayer.release();
            //mediaPlayer = null;
        }

        mediaPlayer.reset();
        mediaPlayer.release();
        mediaPlayer = null;
        am.abandonAudioFocus(Audiolistener);

    }

    //播放上一首
    public void startPlayPreviousSong() {
    }

    //播放下一曲 或 上一曲
    public void startPlayNextSong(Track song) {
        startPlaySong(song);
    }

    /**
     * 改变播放模式
     */
    public void changePlayMode() {
        mPlayMode = (mPlayMode + 1) % 4;
        if (mediaPlayer != null) {
            // 如果正在播放歌曲
            switch (mPlayMode) {
                case PlayMode.REPEAT_SINGLE:
                    // 如果是单曲循环，给MediaPlayer启动单曲播放
                    mediaPlayer.setLooping(true);
                    break;
                default:
                    // 如果不是单曲循环，取消MediaPlayer的单曲播放
                    mediaPlayer.setLooping(false);
                    break;
            }
        }

        // 通知各个OnPlaybackStateChangeListener播放模式已经改变，并传递新的播放
        for (int i = 0; i < musicPlayStateListeners.size(); i++) {
            musicPlayStateListeners.get(i).onPlayModeChanged(mPlayMode);
        }
    }


    public void seekToSpecifiedPosition(int milliSeconds) {
        if (mState != State.STOPPED) {
            mState = State.PAUSED;
            mediaPlayer.pause();
            mediaPlayer.seekTo(milliSeconds);
        }
    }

    //判断音乐是否在播放
    public boolean isPlaying(String uri) {
        if (curTrack != null) {
            if (curTrack.getData() != null && curTrack.getData().equals(uri)) {
                return true;
            }
        }
        return false;

    }


    //注册音乐播放监听 进度 和 模式
    public void registerMusicPlayStateListener(OnMusicPlayStateListener listener) {
        this.musicPlayStateListeners.add(listener);
    }

    //解除注册音乐播放监听
    public void unRegisterMusicPlayStateListener(OnMusicPlayStateListener listener) {
        this.musicPlayStateListeners.remove(listener);
    }


    /**
     * 开启子线程
     */
    private Handler mServiceHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (mState == State.PLAYING) {
                    if (mediaPlayer == null) {
                        return;
                    }
                    int millisecond = mediaPlayer.getCurrentPosition();
                    // 通知所有音乐播放的观察者，进度更新了唉
                    for (int i = 0; i < musicPlayStateListeners.size(); i++) {
                        musicPlayStateListeners.get(i).onPlayProgressUpdate(millisecond);
                    }

                    //歌词
                    if(lrcDownLoadManager!=null){
                        if(lrcChangedListener!=null)
                            lrcChangedListener.onLyricSentenceChanged(lrcDownLoadManager.lrcIndex((int)curTrack.getDuration(),mediaPlayer.getCurrentPosition()));
                    }

                    mServiceHandler.sendEmptyMessageDelayed(1, 500);
                }
            }
        }
    };

    //初始化通知栏
    public void createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.icon);
        RemoteViews remoteViews = null;
        //不同系统SDK 版本
        if (Build.VERSION.SDK_INT  >= Build.VERSION_CODES.KITKAT) {
            remoteViews = new RemoteViews(getPackageName(), R.layout.notification_layout);
        } else {
            remoteViews = new RemoteViews(getPackageName(), R.layout.notification_layout_1);
        }

        remoteViews.setTextViewText(R.id.title, curTrack.getTitle());
        String str = curTrack.getTitle();
//        int index = str.indexOf("-");
        remoteViews.setTextViewText(R.id.singer, curTrack.getArtist());


        if (mediaPlayer.isPlaying()) {
            //操作
            Intent intent = new Intent(this, MusicBackService.class);
            intent.setAction(PAUSE);
            PendingIntent pendingIntent = PendingIntent.getService(this, NOTIFICATIONID, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.play_pause, pendingIntent);
            remoteViews.setImageViewResource(R.id.play_pause, R.drawable.notify_btn_light_pause2_normal);
        } else {
            //操作
            Intent intent1 = new Intent(this, MusicBackService.class);
            intent1.setAction(PLAY);
            PendingIntent pendingIntent1 = PendingIntent.getService(this, NOTIFICATIONID, intent1, PendingIntent.FLAG_CANCEL_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.play_pause, pendingIntent1);
            remoteViews.setImageViewResource(R.id.play_pause, R.drawable.notify_btn_light_play2_normal);
        }


        Intent intentNext = new Intent(this, MusicBackService.class);
        intentNext.setAction(NEXT);
        PendingIntent pendingIntentNext = PendingIntent.getService(this, NOTIFICATIONID, intentNext, PendingIntent.FLAG_CANCEL_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.next, pendingIntentNext);

        Intent intentPre = new Intent(this, MusicBackService.class);
        intentPre.setAction(PREVIOUS);
        PendingIntent pendingIntentPre = PendingIntent.getService(this, NOTIFICATIONID, intentPre, PendingIntent.FLAG_CANCEL_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.previous, pendingIntentPre);

        PendingIntent pi = PendingIntent.getActivity(this, NOTIFICATIONID, new Intent(this, BootActivity.class), PendingIntent.FLAG_CANCEL_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.text, pi);

        builder.setContent(remoteViews);
        nm.notify(NOTIFICATIONID, builder.build());
    }


    //焦点状态类
    public class FocusState {
        public static final int NoFocusNoDuck = 0;//失去焦点
        public static final int NoFocusCanDuck = 1;//躲避
        public static final int Focused = 2;//获取焦点
    }

    private int mFocus = FocusState.NoFocusNoDuck;//默认失去焦点

    /**
     * 请求焦点
     */
    public void requestFocus() {

        if (mFocus == FocusState.Focused) {
            return;
        }

        int result = am.requestAudioFocus(Audiolistener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            //TODO 获取音频焦点，可以播放音乐
            mFocus = FocusState.Focused;
        }
    }

    //焦点变化的监听
    private AudioManager.OnAudioFocusChangeListener Audiolistener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    //获取焦点
                    mFocus = FocusState.Focused;
                    break;

                case AudioManager.AUDIOFOCUS_LOSS:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    //失去焦点
                    mFocus = FocusState.NoFocusNoDuck;
//                    pausePlaySong();//暂停播放
                    playSong();
                    break;

                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mFocus = FocusState.NoFocusCanDuck;
//                    pausePlaySong();
                    playSong();
                    break;

                default:
                    break;
            }
        }
    };

    //开始播放
    private void playSong() {
        if (mFocus == FocusState.NoFocusNoDuck) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
            return;
        } else if (mFocus == FocusState.NoFocusCanDuck) {
            mediaPlayer.setVolume(0.1f, 0.1f);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.setVolume(1.0f, 1.0f);
                }
            },3500);

        } else {
            mediaPlayer.setVolume(1.0f, 1.0f);
        }
        if (!mediaPlayer.isPlaying()) {
            mState = State.PLAYING;
            mediaPlayer.start();
        }
    }
}
