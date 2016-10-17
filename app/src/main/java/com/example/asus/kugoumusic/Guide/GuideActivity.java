package com.example.asus.kugoumusic.Guide;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.IBinder;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.asus.kugoumusic.Adapter.GuideAdapter;
import com.example.asus.kugoumusic.LoadData.TrackLoader;
import com.example.asus.kugoumusic.MusicPlay.MusicPlayActivity;
import com.example.asus.kugoumusic.MusicPlay.WebPlayActivity;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.entity.Track;
import com.example.asus.kugoumusic.entity.WebMusic;
import com.example.asus.kugoumusic.service.MusicBackService;
import com.example.asus.kugoumusic.util.NetImageCache;
import com.example.asus.kugoumusic.util.StatusBarUtils;
import com.example.asus.kugoumusic.util.TimeHelper;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends AppCompatActivity implements MusicBackService.OnBgChangeListener {


    @BindView(R.id.left)
    RelativeLayout left;
    @BindView(R.id.main)
    RelativeLayout main;
    @BindView(R.id.bottom)
    RelativeLayout bottom;

    @BindView(R.id.slidingPane)
    SlidingPaneLayout slidingPane;

    @BindView(R.id.viewpageRadiogroup)
    RadioGroup radioGroup;

    @BindView(R.id.album)
    ImageView album;

    @BindView(R.id.sliding_layout)
    SlidingUpPanelLayout sliding_layout;

    @BindView(R.id.title_song)
    TextView title;
    @BindView(R.id.artist_song)
    TextView artist;
    @BindView(R.id.play_song_total_time)
    TextView total_time;


    private DiscoverFragment discoverFragment;
    private SubscribeFragment subscribeFragment;
    private DownLoaderFragment downLoaderFragment;
    private MineFragment mineFragment;

    private MusicBackService musicBackService;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicBackService.MusicBinder binder = (MusicBackService.MusicBinder) service;
            musicBackService = binder.getService();
            musicBackService.setBgChange(GuideActivity.this);
            Track track = musicBackService.getCurTrack();
            if (track != null) {
                if (musicBackService.isPlaying(track.getData())) {
                    String path = TrackLoader.getBitMapPath(track);
                    if (path == null) {
                        album.setImageDrawable(getResources().getDrawable(R.drawable.notification_default));
                    } else {
                        BitmapDrawable bmpDraw = new BitmapDrawable(path);
                        album.setImageDrawable(bmpDraw);
                    }
                }
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏android系统的状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 隐藏应用程序的标题栏，即当前activity的标题栏
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);//全屏显示，但不覆盖通知栏

//        StatusBarUtils.setWindowStatusBarColor(this, R.color.black);//修改状态栏颜色

        int s = StatusBarUtils.getStatusHeight(this);///得到状态栏高度 单位像素

        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initData();//初始化碎片布局

        slidingPane.setPadding(0,s,0,0);

        Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        LinearInterpolator lin = new LinearInterpolator();//匀速
//        DecelerateInterpolator lin = new DecelerateInterpolator();//减速
        operatingAnim.setInterpolator(lin);
        album.setAnimation(operatingAnim);

        sliding_layout.setOverlayed(true);
        sliding_layout.setParallaxOffset(50);
////        sliding_layout.setDragView(R.layout.test);
//        sliding_layout.setAnchorPoint(0.6f);

        slidingPane.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {

            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                left.setScaleY(slideOffset / 2 + 0.5F);
                left.setScaleX(slideOffset / 2 + 0.5F);
//                left.setAlpha(slideOffset);
                main.setScaleY(1 - slideOffset / 5);
            }

            @Override
            public void onPanelOpened(View arg0) {

            }

            @Override
            public void onPanelClosed(View arg0) {
            }

        });

        sliding_layout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
//                bottom.setScaleY(slideOffset / 2 + 0.5F);
//                bottom.setScaleY(slideOffset / 2 + 0.5F);
                radioGroup.setAlpha(1 - slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
               /* Log.i("TAG", previousState + "pre");
                Log.i("TAG", newState + "new");
                Log.i("TAG", sliding_layout.getPanelState() + "呢我");
                if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) {
                    radioGroup.setAlpha(0.2f);
                } else if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    radioGroup.setAlpha(1.0f);
                    bottom.setScaleY(1.0f);
                    bottom.setScaleX(1.0f);
                } else if (sliding_layout.getPanelState() == SlidingUpPanelLayout.PanelState.DRAGGING){
                    radioGroup.setAlpha(0.5f);
                }*/

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                switch (checkedId) {
                    case R.id.item1:
                        ft.show(discoverFragment).hide(subscribeFragment).hide(downLoaderFragment).hide(mineFragment).commit();
                        break;
                    case R.id.item2:
                        ft.show(subscribeFragment).hide(discoverFragment).hide(downLoaderFragment).hide(mineFragment).commit();
                        break;
                    case R.id.item3:
                        ft.show(downLoaderFragment).hide(subscribeFragment).hide(discoverFragment).hide(mineFragment).commit();
                        break;
                    case R.id.item4:
                        ft.show(mineFragment).hide(downLoaderFragment).hide(subscribeFragment).hide(discoverFragment).commit();
                        break;
                    default:
                        break;
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        //重新绑定服务
        bindService(new Intent(this, MusicBackService.class), conn, BIND_AUTO_CREATE);
    }


    @Override
    protected void onDestroy() {
        //解绑服务
        unbindService(conn);
        super.onDestroy();

    }


    /**
     * 初始化数据信息，
     * 将Fragment 添加到ViewPager 上面去
     */
    public void initData() {
        discoverFragment = new DiscoverFragment();
        subscribeFragment = new SubscribeFragment();
        downLoaderFragment = new DownLoaderFragment();
        mineFragment = new MineFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, discoverFragment)
                .add(R.id.container, subscribeFragment)
                .add(R.id.container, downLoaderFragment)
                .add(R.id.container, mineFragment)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .show(discoverFragment).hide(subscribeFragment).hide(downLoaderFragment).hide(mineFragment).commit();

    }

    //GuideActivity底部图片跳转到播放Activity
    @OnClick(R.id.stop)
    public void stop(View v) {

        int isOrWeb = musicBackService.getIsOrWeb();
        if (isOrWeb == 0) {
            Track track = musicBackService.getCurTrack();
            if (track != null) {
                Intent intent = new Intent(this, MusicPlayActivity.class);
                intent.putExtra("track", track);
                SharedPreferences sp = getSharedPreferences("Position", Context.MODE_PRIVATE);
                intent.putExtra("position", sp.getInt("position", 0));
                startActivity(intent);
            }
        } else if (isOrWeb == 1){
            WebMusic.SonginfoBean SonginfoBean = musicBackService.getSonginfoBean();
            Intent intent = new Intent(this, WebPlayActivity.class);
            intent.putExtra("song_id", SonginfoBean.getSong_id());
            startActivity(intent);
        }


    }

    //回调方法，用来更新GuideActivity 底部旋转图片。
    @Override
    public void BgChange(Track track) {

        if (track != null) {
            Bitmap bitmap = TrackLoader.getArtAlbum(track.getId());

            if (bitmap == null) {
                album.setImageDrawable(getResources().getDrawable(R.drawable.notification_default));
            } else {
                BitmapDrawable bmpDraw = new BitmapDrawable(bitmap);
                album.setImageDrawable(bmpDraw);
            }

            title.setText(track.getTitle());
            String str = track.getDisplay_name();
            int index = str.indexOf("-");
            if (index > 0) {
                artist.setText(str.substring(0, index));
            } else {
                artist.setText(track.getArtist());
            }

            total_time.setText(TimeHelper.milliSecondsToFormatTimeString(track.getDuration()));
        }

    }

    @Override
    public void BgWebChange(WebMusic.SonginfoBean songinfoBean) {

        ImageLoader mImageLoader = new ImageLoader(Volley.newRequestQueue(this), new NetImageCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(
                album, R.drawable.notification_default, R.drawable.notification_default);
        mImageLoader.get(songinfoBean.getPic_big(), listener);

        title.setText(songinfoBean.getTitle());
        artist.setText(songinfoBean.getAuthor());
    }

    /**
     * 打开侧滑菜单
     */
    public void openSlidingPane() {
        slidingPane.openPane();
    }
}
