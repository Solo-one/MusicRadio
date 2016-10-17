package com.example.asus.kugoumusic.tmp;

import android.content.Context;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.tmp.myView.LrcView;

public class LrcViewTestActivity extends AppCompatActivity {


    private WindowManager wm;
    private  LrcView lv;
    private LinearLayout ll;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lrc_view_test);
    }

    public  void open(View v){
        createFloatView(200);

    }

    public  void close(View v){
        wm.removeViewImmediate(view);

    }

    public void createFloatView(int paddingBottom){
        wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        view = LayoutInflater.from(this).inflate(R.layout.activity_lic_view,null,false);
        lv = (LrcView)view.findViewById(R.id.gc);
        final LinearLayout gone =(LinearLayout) view.findViewById(R.id.lrcgone);
        lv.setText("我是桌面歌词好吗");
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        params.format = PixelFormat.TRANSLUCENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        params.x = screenWidth;
        params.y = screenHeight-paddingBottom;
        view.setOnTouchListener(new View.OnTouchListener() {
            float lastX, lastY;
            int oldX, oldY;
            int tag = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                float x = event.getX();
                float y = event.getY();
                if (tag == 0) {
                    oldX = params.x;
                    oldY = params.y;
                }
                if (action == MotionEvent.ACTION_DOWN) {
                    lastX = x;
                    lastY = y;

                } else if (action == MotionEvent.ACTION_MOVE) {
                    params.x += (int) (x - lastX) / 3;
                    params.y += (int) (y - lastY) / 3;
                    tag = 1;
                    wm.updateViewLayout(view, params);

                } else if (action == MotionEvent.ACTION_UP) {
                    int newX = params.x;
                    int newY = params.y;
                    if (Math.abs(oldX - newX) < 20 && Math.abs(oldY - newY) < 20) {
                        //点击后座的事
                        if (gone.getVisibility() == View.GONE) {
                            gone.setVisibility(View.VISIBLE);
                        } else {
                            gone.setVisibility(View.GONE);
                        }
                    } else {
                        tag = 0;
                    }
                }
                return true;
            }
        });

        wm.addView(view,params);
    }

}
