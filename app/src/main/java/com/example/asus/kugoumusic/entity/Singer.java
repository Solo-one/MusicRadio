package com.example.asus.kugoumusic.entity;

import android.graphics.Bitmap;

/**
 * Created by asus on 2016/9/24.
 */
public class Singer {
    private long id;
    private String artist;
    private int  album_id;
    private String uri;
    private String album;

    public Singer() {
    }

    public Singer(long id, String artist, String uri) {
        this.id = id;
        this.artist = artist;
        this.uri = uri;
    }

    public Singer(String artist, int album_id, String uri) {
        this.artist = artist;
        this.album_id = album_id;
        this.uri = uri;
    }

    public Singer(long id, String artist, String album, String uri) {
        this.id = id;
        this.artist = artist;
        this.album = album;
        this.uri = uri;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
