package com.example.asus.kugoumusic.subscribe.TuiJian;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.asus.kugoumusic.Adapter.AlbumItemAdapter;
import com.example.asus.kugoumusic.Adapter.TuiJianAdapter;
import com.example.asus.kugoumusic.Adapter.ZhuangjiAdapter;
import com.example.asus.kugoumusic.Application.MusicApplication;
import com.example.asus.kugoumusic.MusicPlay.WebPlayActivity;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.entity.Album;
import com.example.asus.kugoumusic.entity.MusicWebAlbum;
import com.example.asus.kugoumusic.entity.SearchMusic;
import com.example.asus.kugoumusic.entity.SongList;
import com.example.asus.kugoumusic.entity.Track;
import com.example.asus.kugoumusic.http.CallBack;
import com.example.asus.kugoumusic.util.StatusBarUtils;
import com.example.asus.kugoumusic.util.VolleyUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class ZhuanJiActivity extends SwipeBackActivity {


    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.style)
    TextView style;
    @BindView(R.id.time)
    TextView time;


    @OnClick(R.id.back)
    public void back(View v) {
        finish();
    }

    TextView content;
    TextView none;

    private String album_id;
    private String ting_uid;
    private List<View> mData;//集合存放 View 对象
    private ZhuangjiAdapter adapter;
    private AlbumItemAdapter adapter2;
    private SwipeBackLayout mSwipeBackLayout;
    private List<SongList.SonglistBean> mData_Songlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBarUtils.setWindowStatusBarColor(this, R.color.black);//修改状态栏颜色

        setContentView(R.layout.activity_zhuan_ji);
        ButterKnife.bind(this);
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        album_id = getIntent().getStringExtra("album_id");
        loadLast();

        initData();
        adapter = new ZhuangjiAdapter(mData);
        vp.setAdapter(adapter);


        ((RadioButton) rg.getChildAt(1)).setChecked(true);
        vp.setCurrentItem(1);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton tb = (RadioButton) rg.getChildAt(position);
                tb.setChecked(true);//设置为选中
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.item1:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.item2:
                        vp.setCurrentItem(1);
                        break;
                    default:
                        break;
                }
            }
        });
    }


    private void initData() {
        mData = new ArrayList<>();

        View view1 = LayoutInflater.from(this)
                .inflate(R.layout.detail, null, false);
        content =(TextView) view1.findViewById(R.id.content);
        mData.add(view1);


        View view2 = LayoutInflater.from(this)
                .inflate(R.layout.jiemu, null, false);
        none = (TextView) view2.findViewById(R.id.none);
        ListView listView2 = (ListView) view2.findViewById(R.id.lv);
        mData_Songlist = new ArrayList<>();
        adapter2 = new AlbumItemAdapter(ZhuanJiActivity.this,mData_Songlist);
        listView2.setAdapter(adapter2);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SongList.SonglistBean bean = mData_Songlist.get(position);
                Intent intent = new Intent(ZhuanJiActivity.this, WebPlayActivity.class);
                intent.putExtra("song_id", bean.getSong_id());
                startActivity(intent);
            }
        });
        mData.add(view2);


    }


    /**
     * 网络监听回调方法
     */
    private class MyCallBack implements CallBack {

        @Override
        public void onSuccess(String response) {
            Gson gson = new Gson();
            Album album = gson.fromJson(response, Album.class);
            Album.AlbumInfoBean albumInfoBean = album.getAlbumInfo();
            ting_uid = albumInfoBean.getArtist_ting_uid();

            loadSong();
            changeUI(albumInfoBean);
        }

        @Override
        public void onErrer(VolleyError error) {
            MusicApplication.toast.toastShow("加载失败");
        }

    }


    public void changeUI(Album.AlbumInfoBean albumInfoBean) {
        Glide.with(this).load(albumInfoBean.getPic_big()).into(img);
        title.setText(albumInfoBean.getTitle());
        author.setText(albumInfoBean.getAuthor());
        num.setText("播放次数 :"+albumInfoBean.getArtist_ting_uid());
        style.setText("风格 :" + albumInfoBean.getStyles());
        time.setText("出版时间 :" + albumInfoBean.getPublishtime());

        if (albumInfoBean.getInfo().equals("")) {
            content.setText("专辑暂无简介...");
        } else {
            content.setText(albumInfoBean.getInfo());
        }

    }

    //上拉加载更多数据
    private void loadLast() {

        VolleyUtil.Get("http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.album.getAlbumInfo&format=json&album_id=" + album_id)
                .setCallBack(new MyCallBack())
                .build()
                .setPriority(Request.Priority.HIGH)
                .addRequestHeadrs("apikey", "4600fe45a7f631f4800368013fb1a76e")
                .start();
    }

    //上拉加载更多数据
    private void loadSong() {

        VolleyUtil.Get("http://tingapi.ting.baidu.com/v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.artist.getSongList&format=json&tinguid="+ting_uid+"&limits=10&use_cluster=1&order=2")
                .setCallBack(new MyCallBack1())
                .build()
                .setPriority(Request.Priority.HIGH)
                .addRequestHeadrs("apikey", "4600fe45a7f631f4800368013fb1a76e")
                .start();
    }

    /**
     * 网络监听回调方法
     */
    private class MyCallBack1 implements CallBack {

        @Override
        public void onSuccess(String response) {
            Gson gson = new Gson();
            SongList songList = gson.fromJson(response,SongList.class);
            List<SongList.SonglistBean> songlistBean = songList.getSonglist();
            if (songlistBean != null) {
                mData_Songlist.addAll(songlistBean);
            } else {
                none.setVisibility(View.VISIBLE);
            }
            adapter2.notifyDataSetChanged();
        }

        @Override
        public void onErrer(VolleyError error) {
            MusicApplication.toast.toastShow("加载失败");
        }

    }
}
