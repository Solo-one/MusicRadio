package com.example.asus.kugoumusic.entity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by asus on 2016/9/7.
 */
public class Track implements Parcelable{

    //歌曲主键
    private long id;
    //不带扩展名的文件名
    private String title;
    //文件名
    private String display_name;
    //专辑名，一般为文件夹名
    private String album;
    //专辑id
    private int album_id;
    //艺术家
    private String artist;
    //文件路径
    private String data;
    //文件大小
    private long size;
    //时长
    private long duration;
    //歌曲标题索引，用来搜索、排序使用
    private String title_key;
    //艺术家名称索引，用来搜素、排序使用
    private String artist_key;

    public Track() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        bundle.putString("title", title);
        bundle.putString("display_name", display_name);
        bundle.putString("album", album);
        bundle.putString("artist", artist);
        bundle.putString("data", data);
        bundle.putLong("size", size);
        bundle.putLong("duration", duration);
        dest.writeBundle(bundle);
    }
    // 用来创建自定义的Parcelable的对象
    public static final Parcelable.Creator<Track> CREATOR = new Parcelable.Creator<Track>() {
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        public Track[] newArray(int size) {
            return new Track[size];
        }
    };
    // 读数据进行恢复
    private Track(Parcel in) {
        Bundle bundle = in.readBundle();
        id = bundle.getLong("id");
        title = bundle.getString("title");
        display_name = bundle.getString("display_name");
        album = bundle.getString("album");
        artist = bundle.getString("artist");
        data = bundle.getString("data");
        size = bundle.getLong("size");
        duration = bundle.getLong("duration");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getTitle_key() {
        return title_key;
    }

    public void setTitle_key(String title_key) {
        this.title_key = title_key;
    }

    public String getArtist_key() {
        return artist_key;
    }

    public void setArtist_key(String artist_key) {
        this.artist_key = artist_key;
    }



}
