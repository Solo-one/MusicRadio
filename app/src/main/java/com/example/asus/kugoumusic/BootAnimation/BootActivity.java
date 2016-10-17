package com.example.asus.kugoumusic.BootAnimation;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.asus.kugoumusic.Guide.GuideActivity;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.util.ThreadPoolManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BootActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏android系统的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_boot);

        ThreadPoolManager tm = ThreadPoolManager.getInstance();
        ScheduledExecutorService scheduledExecutorService = tm.getScheduledExcutorService();

        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(BootActivity.this, GuideActivity.class);
                startActivity(intent);
//                overridePendingTransition(R.anim.up, R.anim.pause);
                finish();
            }
        },1000, TimeUnit.MILLISECONDS);


    }
}
