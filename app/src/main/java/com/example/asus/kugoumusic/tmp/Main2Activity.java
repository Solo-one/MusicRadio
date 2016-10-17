package com.example.asus.kugoumusic.tmp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.asus.kugoumusic.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.swipe_lv)
    SwipeMenuListView swipe_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        String[] data = {"DDD", "AAA", "BB", "CCCC", "FFFFFFF"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, data);
        swipe_lv.setAdapter(adapter);

        //滑动菜单
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                //创建菜单项
                SwipeMenuItem openItem = new SwipeMenuItem(Main2Activity.this);

                openItem.setBackground(new ColorDrawable(Color.GRAY));
                openItem.setWidth(dp2px(Main2Activity.this, 90));
                openItem.setTitle("Open");
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);

                //添加到菜单
                menu.addMenuItem(openItem);

                SwipeMenuItem deleteItem = new SwipeMenuItem(Main2Activity.this);

                deleteItem.setBackground(new ColorDrawable(Color.RED));
                deleteItem.setWidth(dp2px(Main2Activity.this, 90));
                deleteItem.setIcon(R.mipmap.ic_launcher);
                deleteItem.setTitleColor(Color.WHITE);
                //添加到菜单
                menu.addMenuItem(deleteItem);
            }
        };

        swipe_lv.setMenuCreator(creator);//给ListView 设置菜单
        swipe_lv.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);//默认就是左滑

        swipe_lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                switch (index) {
                    case 0:
                        Toast.makeText(Main2Activity.this, "OPEN", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(Main2Activity.this, "DELETE", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        });

        swipe_lv.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {
                Toast.makeText(Main2Activity.this, "开始滑动", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeEnd(int position) {
                Toast.makeText(Main2Activity.this, "结束滑动", Toast.LENGTH_SHORT).show();
            }
        });

    }


    //像素px和dp的转化
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
