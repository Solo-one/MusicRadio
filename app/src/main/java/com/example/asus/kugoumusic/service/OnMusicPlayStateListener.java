package com.example.asus.kugoumusic.service;

/**
 * Created by asus on 2016/9/8.
 */
public interface OnMusicPlayStateListener {
    /**
     * 播放进度
     * @param currentMillis
     */
    public void onPlayProgressUpdate(int currentMillis);

    /**
     * 播放模式
     * @param playMode
     */
    public void onPlayModeChanged(int playMode);
}
