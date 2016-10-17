package com.example.asus.kugoumusic.Application;

import android.app.Application;
import android.content.Intent;

import com.example.asus.kugoumusic.selfwidget.SelfToast;
import com.example.asus.kugoumusic.service.MusicBackService;
import com.example.asus.kugoumusic.util.VolleyUtil;

/**
 * Created by asus on 2016/9/4.
 */
public class MusicApplication extends Application{

    public static SelfToast toast;//自定义Toast

    @Override
    public void onCreate() {
        super.onCreate();
        //网络请求
        VolleyUtil.init(this);//初始化requestQueue 对象 只实例化一次
//        VolleyUtil.init(getApplicationContext());
        toast = new SelfToast(getApplicationContext());
        //startService启动服务 如需关闭Service，手动调用stopService()停止服务
        Intent intent = new Intent(this,MusicBackService.class);
        startService(intent);
    }
}
