package com.example.asus.kugoumusic.util;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by asus on 2016/9/16.
 */
public class NetImageCache implements ImageLoader.ImageCache {

    private LruCache<String,Bitmap> mCache;
    /**
     * 开辟图片缓存内存
     */
    public NetImageCache() {
        int maxSize = (int) Runtime.getRuntime().totalMemory()/8;//APP的八分之一的内存
        mCache = new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();//图片宽和高 得到图片大小
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url,bitmap);
    }
}

