package com.example.asus.kugoumusic.downloader;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.asus.kugoumusic.Guide.DownLoaderFragment;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.selfwidget.MySwipMenuLv;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZJFragment extends Fragment {

    private View v;

    public ZJFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_zj, container, false);
            ButterKnife.bind(this, v);

           /* String[] data = {"DDD", "AAA", "BB", "CCCC", "FFFFFFF"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, data);
            swipe_lv.setAdapter(adapter);

            ViewPager v = ((DownLoaderFragment) getParentFragment()).getVp();

            swipe_lv.setNestedpParent(v);

            //滑动菜单
            SwipeMenuCreator creator = new SwipeMenuCreator() {
                @Override
                public void create(SwipeMenu menu) {
                    //创建菜单项
                    SwipeMenuItem openItem = new SwipeMenuItem(getActivity());

                    openItem.setBackground(new ColorDrawable(Color.GRAY));
                    openItem.setWidth(dp2px(getActivity(), 90));
                    openItem.setTitle("Open");
                    openItem.setTitleSize(18);
                    openItem.setTitleColor(Color.WHITE);

                    //添加到菜单
                    menu.addMenuItem(openItem);

                    SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity());

                    deleteItem.setBackground(new ColorDrawable(Color.RED));
                    deleteItem.setWidth(dp2px(getActivity(), 90));
                    deleteItem.setIcon(R.mipmap.ic_launcher);
                    deleteItem.setTitleColor(Color.WHITE);
                    //添加到菜单
                    menu.addMenuItem(deleteItem);
                }
            };

            swipe_lv.setMenuCreator(creator);//给ListView 设置菜单
            swipe_lv.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);//默认就是左滑

            swipe_lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                    switch (index) {
                        case 0:
                            Toast.makeText(getActivity(), "OPEN", Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Toast.makeText(getActivity(), "DELETE", Toast.LENGTH_SHORT).show();
                            break;
                    }

                    return false;
                }
            });

            swipe_lv.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
                @Override
                public void onSwipeStart(int position) {
                    Toast.makeText(getActivity(), "开始滑动", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSwipeEnd(int position) {
                    Toast.makeText(getActivity(), "结束滑动", Toast.LENGTH_SHORT).show();
                }
            });

*/

        }
        return v;
    }


    //像素px和dp的转化
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
