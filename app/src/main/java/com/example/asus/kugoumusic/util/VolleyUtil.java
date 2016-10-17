package com.example.asus.kugoumusic.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.asus.kugoumusic.http.NetRequest;

/**
 * 网络请求封装类
 * Volley 网络加载库
 * Created by asus on 2016/9/4.
 */
public class VolleyUtil {

    //请求队列,static 内存中只有一份
    private static RequestQueue requestQueue;

    public static void init(Context context) {
        if (requestQueue == null) {
            //单例模式
            requestQueue = Volley.newRequestQueue(context);
        }
    }

    /**
     * GET  请求
     * @param url
     * @return
     */
    public static NetRequest.Builder Get(String url) {
        NetRequest.Builder builder = new NetRequest.Builder();
        builder.setMethod(Request.Method.GET).setUrl(url);
        return builder;
    }

    /**
     * POST 请求
     * @param url
     * @return
     */
    public static NetRequest.Builder Post(String url) {
        NetRequest.Builder builder = new NetRequest.Builder();
        builder.setMethod(Request.Method.POST).setUrl(url);
        return builder;
    }

    //将请求对象 加入到 请求队列
    public static void start(NetRequest nr) {
        requestQueue.add(nr);
    }

    public static void cancel(Object tag){
        requestQueue.cancelAll(tag);//取消请求
    }

    public static void cancel(RequestQueue.RequestFilter filter) {
        requestQueue.cancelAll(filter);
    }
}
