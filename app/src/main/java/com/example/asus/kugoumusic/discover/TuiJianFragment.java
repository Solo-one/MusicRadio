package com.example.asus.kugoumusic.discover;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.example.asus.kugoumusic.Adapter.GridAdapter;
import com.example.asus.kugoumusic.Adapter.HeaderAdapter;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.dao.SongListBeanDao;
import com.example.asus.kugoumusic.discover.discover_activity.MoreActivity;
import com.example.asus.kugoumusic.entity.MusicWebAlbum;
import com.example.asus.kugoumusic.http.CallBack;
import com.example.asus.kugoumusic.lazyFragment.LazyFragment;
import com.example.asus.kugoumusic.selfwidget.MyGridView;
import com.example.asus.kugoumusic.util.ThreadPoolManager;
import com.example.asus.kugoumusic.util.VolleyUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TuiJianFragment extends LazyFragment {

    private View v;
    @BindView(R.id.vp)
    ViewPager viewPager;
    @BindView(R.id.vp1)
    ViewPager viewPager1;
    //    @BindView(R.id.circle)
//    CircleIndicator circleIndicator;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ptr_scroll)
    PullToRefreshScrollView ptr_scroll;

    @BindView(R.id.scroll)
    HorizontalScrollView scroll;

    @BindView(R.id.gridview)
    MyGridView gridView;

    @BindView(R.id.gridview1)
    MyGridView gridView1;

    @BindView(R.id.container_gridview)
    FrameLayout container_gridview;
    @BindView(R.id.container_gridview1)
    FrameLayout container_gridview1;

    @OnClick(R.id.more1)
    public void more1(View v) {
        Intent intent = new Intent(getActivity(),MoreActivity.class);
        startActivity(intent);
    }

    GridAdapter gridAdapter,gridAdapter1;
    HeaderAdapter adapter, adapter1;
    ArrayList<ImageView> arrayList;
    ArrayList<ImageView> arrayList1;
    List<MusicWebAlbum.SongListBean> mData;
    List<MusicWebAlbum.SongListBean> mData1;
    List<MusicWebAlbum.SongListBean> mData2;

    private int[] imageResId; // 图片ID
    private int[] imageResId1; // 图片ID
    private List<View> dots; // 图片标题正文的那些点
    private List<View> dots1; // 图片标题正文的那些点
    private String[] titles; // 图片标题
    private int num = 600;
    private int num1 = 600;

    DisplayMetrics dm;//屏幕尺寸管理器
    private SongListBeanDao dao;

    ThreadPoolManager tm = ThreadPoolManager.getInstance();//线程池管理
    ScheduledExecutorService ses = tm.getScheduledExcutorService();

    private boolean isPrepared = false;//要加载视图是否准备好
    private boolean isFirst = true;//是否是第一次加载

    public TuiJianFragment() {
        // Required empty public constructor
    }

    @Override
    public void lazyInitData() {
        if (isPrepared && isVisiable && isFirst) {
            ptr_scroll.setRefreshing();//自动下拉
            loaderDataBase();
            loadLast();
            loadLast1();
            isFirst = false;
        }
    }

    /**
     * 加载本地数据库
     */
    public void loaderDataBase() {

        List<MusicWebAlbum.SongListBean> tmp = dao.findAllSongs();

        if (tmp.size() != 0) {
            for (int i = 0; i < 8 ; i++) {
                mData.add(tmp.get(i));
            }

            for (int i = 8; i < 11 ; i++) {
                mData1.add(tmp.get(i));
            }

            for (int i = 2; i < 5;i++) {
                mData2.add(tmp.get(i));
            }

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_tui_jian, container, false);
            ButterKnife.bind(this, v);
            dao = new SongListBeanDao(getActivity());

            //初始化数据
            imageResId = new int[]{R.drawable.picture1, R.drawable.picture2, R.drawable.picture3
                    , R.drawable.picture4, R.drawable.picture5};
            //初始化数据
            imageResId1 = new int[]{R.drawable.picture1, R.drawable.picture2, R.drawable.picture3
                    , R.drawable.picture4};

            dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            initData();

            mData = new ArrayList<>();
            mData1 = new ArrayList<>();
            mData2 = new ArrayList<>();

            adapter = new HeaderAdapter(arrayList);
            adapter1 = new HeaderAdapter(arrayList1);
            gridAdapter = new GridAdapter(mData, getActivity(), dm);
            gridAdapter1 = new GridAdapter(mData1, getActivity(), dm);

            viewPager.setAdapter(adapter);
            viewPager1.setAdapter(adapter1);
            gridView.setAdapter(gridAdapter);
            gridView1.setAdapter(gridAdapter1);

            ViewGroup.LayoutParams vp_layoutParams = (ViewGroup.LayoutParams) viewPager.getLayoutParams(); //取控件textView当前的布局参数
            ViewGroup.LayoutParams vp1_layoutParams = viewPager1.getLayoutParams();
            vp_layoutParams.height = dm.widthPixels * 3 / 7;// 设置控件的高强
            vp1_layoutParams.height = dm.widthPixels/3;
            viewPager.setLayoutParams(vp_layoutParams); //使设置好的布局参数应用到控件
            viewPager1.setLayoutParams(vp1_layoutParams); //使设置好的布局参数应用到控件

            //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
            //viewPager.setCurrentItem((imageViews.size()) * 100);*/
            viewPager.setCurrentItem(num);
            viewPager1.setCurrentItem(num1);
            ses.scheduleWithFixedDelay(mRunnable, 3000, 4000, TimeUnit.MILLISECONDS);

            MyGridView aa = buildGridView(mData1);
            MyGridView bb = buildGridView(mData2);

            container_gridview.addView(aa);
            container_gridview1.addView(bb);


            // 设置一个监听器，当ViewPager中的页面改变时调用
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                private int oldPosition = 0;

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    num = position;
                    position = position % arrayList.size();
                    dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                    dots.get(position).setBackgroundResource(R.drawable.dot_focused);

                    oldPosition = position;
                    tv_title.setText(titles[position]);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            // 设置一个监听器，当ViewPager中的页面改变时调用
            viewPager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                private int oldPosition = 0;

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    num1 = position;
                    position = position % arrayList1.size();
                    dots1.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                    dots1.get(position).setBackgroundResource(R.drawable.dot_focused);
                    oldPosition = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });


            //开始就按viewPager 置顶
            viewPager.setFocusable(true);
            viewPager.setFocusableInTouchMode(true);
            viewPager.requestFocus();

            ptr_scroll.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            ptr_scroll.setScrollingWhileRefreshingEnabled(true);
            ILoadingLayout startLabels = ptr_scroll.getLoadingLayoutProxy(true, false);
            startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
            startLabels.setRefreshingLabel("喜马拉雅,正在载入...");// 刷新时
            startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示

            ptr_scroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
                @Override
                public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                    loadLast();
                    loadLast1();
                }
            });

            //进行懒加载
            isPrepared = true;
            lazyInitData();
        }

        return v;
    }


    public void initData() {
        arrayList = new ArrayList<>();
        arrayList1 = new ArrayList<>();

        for (int i = 0; i < imageResId.length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(imageResId[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            arrayList.add(imageView);
        }

        for (int i = 0; i < imageResId1.length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(imageResId1[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            arrayList1.add(imageView);
        }

        dots = new ArrayList<>();
        dots1 = new ArrayList<>();
        // 初始化点图
        dots.add(v.findViewById(R.id.v_dot0));
        dots.add(v.findViewById(R.id.v_dot1));
        dots.add(v.findViewById(R.id.v_dot2));
        dots.add(v.findViewById(R.id.v_dot3));
        dots.add(v.findViewById(R.id.v_dot4));

        dots1.add(v.findViewById(R.id.v_dot0_0));
        dots1.add(v.findViewById(R.id.v_dot1_1));
        dots1.add(v.findViewById(R.id.v_dot2_2));
        dots1.add(v.findViewById(R.id.v_dot3_3));

        titles = new String[imageResId.length];
        titles[0] = "喜马拉雅fm，听我想听";
        titles[1] = "休闲无聊，听喜马拉雅";
        titles[2] = "喜马拉雅fm，听我想听";
        titles[3] = "运动无聊，听喜马拉雅";
        titles[4] = "喜马拉雅fm，听我想听";
        tv_title.setText(titles[0]);//

    }


    private Runnable mRunnable = new Runnable() {
        public void run() {
            num++;//num 增加
            num1++;
            Message message = viewHandler.obtainMessage(1);
            message.arg1 = num;
            message.arg2 = num1;
            viewHandler.sendMessage(message);
//            viewHandler.sendEmptyMessage(num);
        }

    };

    private final Handler viewHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                viewPager.setCurrentItem(msg.arg1);//ViewPager换到下一页。
                viewPager1.setCurrentItem(msg.arg2);//ViewPager换到下一页。
            }
            super.handleMessage(msg);
        }

    };


    /**
     * 网络监听回调方法
     */
    private class MyCallBack implements CallBack {

        @Override
        public void onSuccess(String response) {
            Gson gson = new Gson();
            MusicWebAlbum musicWebAlbum = gson.fromJson(response, MusicWebAlbum.class);
            List<MusicWebAlbum.SongListBean> items = musicWebAlbum.getSong_list();
            mData.clear();
            mData.addAll(items);
            gridAdapter.notifyDataSetChanged();
            ptr_scroll.onRefreshComplete();
        }

        @Override
        public void onErrer(VolleyError error) {
            Toast.makeText(getActivity(),"网络异常",Toast.LENGTH_SHORT).show();
            gridAdapter.notifyDataSetChanged();
            ptr_scroll.onRefreshComplete();
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

    //上拉加载更多数据
    private void loadLast1() {
        VolleyUtil.Get("http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=25&size=3&offset=0")
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
            MusicWebAlbum musicWebAlbum = gson.fromJson(response, MusicWebAlbum.class);
            List<MusicWebAlbum.SongListBean> items = musicWebAlbum.getSong_list();
            mData1.clear();
            mData1.addAll(items);
            gridAdapter1.notifyDataSetChanged();
        }

        @Override
        public void onErrer(VolleyError error) {
            gridAdapter1.notifyDataSetChanged();
        }

    }


    //上拉加载更多数据
    private void loadLast2() {
        VolleyUtil.Get("http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=8&size=3&offset=0")
                .setCallBack(new MyCallBack2())
                .build()
                .setPriority(Request.Priority.HIGH)
                .addRequestHeadrs("apikey", "4600fe45a7f631f4800368013fb1a76e")
                .start();
    }

    /**
     * 网络监听回调方法
     */
    private class MyCallBack2 implements CallBack {

        @Override
        public void onSuccess(String response) {
            Gson gson = new Gson();
            MusicWebAlbum musicWebAlbum = gson.fromJson(response, MusicWebAlbum.class);
            List<MusicWebAlbum.SongListBean> items = musicWebAlbum.getSong_list();
            mData1.clear();
            mData1.addAll(items);
            gridAdapter1.notifyDataSetChanged();
        }

        @Override
        public void onErrer(VolleyError error) {

        }

    }

    //MyGridView 组件
    private MyGridView buildGridView(List<MusicWebAlbum.SongListBean> mData) {

        AbsListView.LayoutParams params = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        GridAdapter gridAdapter = new GridAdapter(mData,getActivity(),dm);//设置适配器

        MyGridView gridView = new MyGridView(getActivity());

        gridView.setLayoutParams(params);
        gridView.setHorizontalSpacing(dip2px(getActivity(), 1));
        gridView.setVerticalSpacing(dip2px(getActivity(), 10));
        gridView.setNumColumns(3);
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

        gridView.setAdapter(gridAdapter);

        return gridView;
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @param dipValue
     *            （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
