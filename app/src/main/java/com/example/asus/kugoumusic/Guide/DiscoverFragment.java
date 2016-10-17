package com.example.asus.kugoumusic.Guide;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.asus.kugoumusic.Adapter.DiscoverAdapter;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.discover.BenDiFragment;
import com.example.asus.kugoumusic.discover.FenleiFragment;
import com.example.asus.kugoumusic.discover.GuangBoFragment;
import com.example.asus.kugoumusic.discover.TuiJianFragment;
import com.example.asus.kugoumusic.discover.ZhuBoFragment;
import com.example.asus.kugoumusic.discover.discover_activity.SearchResultActivity;
import com.example.asus.kugoumusic.lazyFragment.LazyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {

    private View v;
    private Unbinder unbinder;

    DiscoverAdapter adapter;
    List<Fragment> mDataF;
    private List<String> mDataS;

    private String tmp;


    @BindView(R.id.tabs)
    TabLayout tabs;

    @BindView(R.id.vp)
    ViewPager vp;

    @BindView(R.id.edit)
    EditText editText;


    @OnClick(R.id.icon_header)
    public void header(View v) {
        GuideActivity guideActivity = (GuideActivity) getActivity();
        guideActivity.openSlidingPane();//打开主界面侧滑菜单
    }

    @OnClick(R.id.search)
    public void search(View v) {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        intent.putExtra("data",editText.getText().toString());
        startActivity(intent);
    }


    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_discover, container, false);
            unbinder = ButterKnife.bind(this, v);
            tabs = (TabLayout) v.findViewById(R.id.tabs);
            vp = (ViewPager) v.findViewById(R.id.vp);

            tabs.setTabMode(TabLayout.MODE_FIXED);//设置可以滑动
            tabs.setTabGravity(TabLayout.GRAVITY_FILL);

            initData();//初始化数据

            //适配器
            adapter = new DiscoverAdapter(getChildFragmentManager(), mDataF, mDataS);
            vp.setAdapter(adapter);
            //关联tabs 和 ViewPage
            tabs.setupWithViewPager(vp);

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                    if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                        intent.putExtra("data",editText.getText().toString());
                        startActivity(intent);
                        return false;
                    }

                    return false;
                }
            });



        }

        return v;
    }


    /**
     * 初始化数据信息，
     * 将Fragment 添加到ViewPager 上面去
     */
    public void initData() {
        mDataF = new ArrayList<>();
        mDataS = new ArrayList<>();
        mDataS.add("推荐");
        mDataS.add("本地");
        mDataS.add("分类");
        mDataS.add("广播");
        mDataS.add("主播");

        TuiJianFragment tuijian = new TuiJianFragment();
        mDataF.add(tuijian);

        BenDiFragment bendi = new BenDiFragment();
        mDataF.add(bendi);

        FenleiFragment fenlei = new FenleiFragment();
        mDataF.add(fenlei);

        GuangBoFragment guangbo = new GuangBoFragment();
        mDataF.add(guangbo);

        ZhuBoFragment zhubo = new ZhuBoFragment();
        mDataF.add(zhubo);
    }


}
