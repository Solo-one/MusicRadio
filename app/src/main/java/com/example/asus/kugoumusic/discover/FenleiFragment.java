package com.example.asus.kugoumusic.discover;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.asus.kugoumusic.Adapter.HeaderAdapter;
import com.example.asus.kugoumusic.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FenleiFragment extends Fragment {

    private View v;
    private DisplayMetrics dm;//屏幕尺寸管理器
    HeaderAdapter adapter;
    ArrayList<ImageView> arrayList;
    private int[] imageResId; // 图片ID

    @BindView(R.id.vp)
    ViewPager viewPager;


    public FenleiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_fenlei, container, false);
            ButterKnife.bind(this,v);
            dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

            ViewGroup.LayoutParams vp_layoutParams = viewPager.getLayoutParams();
            vp_layoutParams.height = dm.widthPixels * 3 / 7;// 设置控件的高强
            viewPager.setLayoutParams(vp_layoutParams); //使设置好的布局参数应用到控件

            //初始化数据
            imageResId = new int[]{R.drawable.picture1, R.drawable.picture2, R.drawable.picture3
                    , R.drawable.picture4};
            initData();

            adapter = new HeaderAdapter(arrayList);
            viewPager.setAdapter(adapter);
        }
        return v;
    }

    public void initData() {
        arrayList = new ArrayList<>();

        for (int i = 0; i < imageResId.length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(imageResId[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            arrayList.add(imageView);
        }

      /*  dots = new ArrayList<>();

        // 初始化点图
        dots.add(v.findViewById(R.id.v_dot0));
        dots.add(v.findViewById(R.id.v_dot1));
        dots.add(v.findViewById(R.id.v_dot2));
        dots.add(v.findViewById(R.id.v_dot3));
        dots.add(v.findViewById(R.id.v_dot4));

        titles = new String[imageResId.length];
        titles[0] = "喜马拉雅fm，听我想听";
        titles[1] = "休闲无聊，听喜马拉雅";
        titles[2] = "喜马拉雅fm，听我想听";
        titles[3] = "运动无聊，听喜马拉雅";
        titles[4] = "喜马拉雅fm，听我想听";
        tv_title.setText(titles[0]);//*/

    }
}
