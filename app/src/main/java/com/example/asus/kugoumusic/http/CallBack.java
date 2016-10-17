package com.example.asus.kugoumusic.http;

import com.android.volley.VolleyError;

/**
 * Created by asus on 2016/9/4.
 */
public interface CallBack {
    public void onSuccess(String response);//请求成功
    public void onErrer(VolleyError error);//请求失败
}
