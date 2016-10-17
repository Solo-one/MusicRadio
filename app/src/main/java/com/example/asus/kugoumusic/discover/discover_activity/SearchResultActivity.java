package com.example.asus.kugoumusic.discover.discover_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.asus.kugoumusic.Adapter.SearchAdapter;
import com.example.asus.kugoumusic.Adapter.TuiJianAdapter;
import com.example.asus.kugoumusic.Application.MusicApplication;
import com.example.asus.kugoumusic.MusicPlay.WebPlayActivity;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.entity.MusicWebAlbum;
import com.example.asus.kugoumusic.entity.SearchMusic;
import com.example.asus.kugoumusic.http.CallBack;
import com.example.asus.kugoumusic.subscribe.TuiJian.ZhuanJiActivity;
import com.example.asus.kugoumusic.util.StatusBarUtils;
import com.example.asus.kugoumusic.util.VolleyUtil;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class SearchResultActivity extends SwipeBackActivity {

    private List<SearchMusic.SongBean> mData;
    private SearchAdapter adapter;

    private String searchData = "下一秒";
    public static final String UTF_8 = "utf-8";

    private View headerView;

    private SwipeBackLayout mSwipeBackLayout;

    private String album_id;

    @BindView(R.id.lv)
    ListView lv;

    @BindView(R.id.edit)
    EditText editText;

    @OnClick(R.id.search)
    public void search(View v) {
        searchData = editText.getText().toString();
        loadLast();
    }

    @OnClick(R.id.back)
    public void back(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.black);//修改状态栏颜色
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);

        mSwipeBackLayout = getSwipeBackLayout();
        //设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        Intent intent = getIntent();
        String tmp = intent.getStringExtra("data");
        if (!TextUtils.isEmpty(tmp)) {
            searchData = tmp;
        }

        headerView = LayoutInflater
                .from(SearchResultActivity.this).inflate(R.layout.hearder_listview, null, false);
        lv.addHeaderView(headerView);


        mData = new ArrayList<>();
        adapter = new SearchAdapter(mData, SearchResultActivity.this);
        lv.setAdapter(adapter);
        loadLast();

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    searchData = editText.getText().toString();
                    loadLast();
                    return false;
                }

                return false;
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchMusic.SongBean bean = mData.get(position - 1);
                Intent intent = new Intent(SearchResultActivity.this, WebPlayActivity.class);
                intent.putExtra("song_id", bean.getSongid());
                startActivity(intent);
            }
        });

        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (album_id != null) {
                    Intent intent = new Intent(SearchResultActivity.this, ZhuanJiActivity.class);
                    intent.putExtra("album_id", album_id);
                    startActivity(intent);
                }

            }
        });
    }

    /**
     * 网络监听回调方法
     */
    private class MyCallBack implements CallBack {

        @Override
        public void onSuccess(String response) {

            Gson gson = new Gson();
            SearchMusic searchMusic = gson.fromJson(response, SearchMusic.class);
            List<SearchMusic.SongBean> items = searchMusic.getSong();
            List<SearchMusic.AlbumBean> albumBeans = searchMusic.getAlbum();

            TextView title = (TextView) headerView.findViewById(R.id.title);
            TextView singer = (TextView) headerView.findViewById(R.id.singer);
            ImageView img = (ImageView) headerView.findViewById(R.id.img);
            RelativeLayout album = (RelativeLayout) headerView.findViewById(R.id.album);
            TextView hot = (TextView) headerView.findViewById(R.id.hot);
            TextView time = (TextView) headerView.findViewById(R.id.time);
            TextView albumnum = (TextView) headerView.findViewById(R.id.albumnum);

            if (albumBeans != null) {
                album.setVisibility(View.VISIBLE);
                title.setText(albumBeans.get(0).getAlbumname());
                singer.setText(albumBeans.get(0).getArtistname());
                Glide.with(SearchResultActivity.this).load(albumBeans.get(0).getArtistpic()).into(img);
                hot.setText("234.5万");
                time.setText("2013-05-23");
                albumnum.setText("12");
                album_id = albumBeans.get(0).getAlbumid();
            } else {
                album.setVisibility(View.GONE);
            }

            if (items != null) {
                mData.clear();
                mData.addAll(items);
                adapter.notifyDataSetChanged();
            } else {
                mData.clear();
                adapter.notifyDataSetChanged();
                MusicApplication.toast.toastShow("未找到相关信息");
            }

        }

        @Override
        public void onErrer(VolleyError error) {
            MusicApplication.toast.toastShow("网络异常，请检查网络");
        }

    }


    //上拉加载更多数据
    private void loadLast() {
        String tmp = null;
        try {
            tmp = URLEncoder.encode(searchData, UTF_8);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        VolleyUtil.Get("http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.search.catalogSug&query=" + tmp)
                .setCallBack(new MyCallBack())
                .build()
                .setPriority(Request.Priority.HIGH)
                .addRequestHeadrs("apikey", "4600fe45a7f631f4800368013fb1a76e")
                .start();
    }
}
