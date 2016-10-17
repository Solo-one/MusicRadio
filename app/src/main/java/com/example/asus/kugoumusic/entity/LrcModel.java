package com.example.asus.kugoumusic.entity;

/**
 * Created by asus on 2016/9/18.
 */
public class LrcModel {

    private Integer currentTime;//时间点
    private String currentContent;//时间点上显示的内容

    public Integer getCurrentTime() {
        return currentTime;
    }
    public void setCurrentTime(Integer time) {
        this.currentTime = time;
    }
    public String getCurrentContent() {
        return currentContent;
    }

    public void setCurrentContent(String currentContent) {
        this.currentContent = currentContent;
    }

}
