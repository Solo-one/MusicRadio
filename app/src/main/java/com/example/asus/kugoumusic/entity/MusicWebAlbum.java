package com.example.asus.kugoumusic.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * Created by asus on 2016/9/16.
 */
public class MusicWebAlbum {


    /**
     * artist_id : 1483
     * language : 国语
     * pic_big : http://musicdata.baidu.com/data2/pic/84f288be9b5bf4a7ff88e51e4741960f/269431223/269431223.jpg
     * pic_small : http://musicdata.baidu.com/data2/pic/8d898c3e46fecc2d9950d8d36ddeb517/269431226/269431226.jpg
     * country : 内地
     * area : 0
     * publishtime : 2016-08-31
     * album_no : 1
     * lrclink : http://musicdata.baidu.com/data2/lrc/bde87bd733f6b9199799ed0abb1e0f95/270638983/270638983.lrc
     * copy_type : 1
     * hot : 317235
     * all_artist_ting_uid : 1557
     * resource_type : 0
     * is_new : 1
     * rank_change : 0
     * rank : 1
     * all_artist_id : 1483
     * style : 流行
     * del_status : 0
     * relate_status : 0
     * toneid : 0
     * all_rate : 64,128,256,320,flac
     * sound_effect : 0
     * file_duration : 269
     * has_mv_mobile : 0
     * versions :
     * bitrate_fee : {"0":"0|0","1":"-1|-1"}
     * song_id : 269431488
     * title : 江湖
     * ting_uid : 1557
     * author : 许嵩
     * album_id : 269431547
     * album_title : 江湖
     * is_first_publish : 0
     * havehigh : 2
     * charge : 0
     * has_mv : 0
     * learn : 0
     * song_source : web
     * piao_id : 0
     * korean_bb_song : 1
     * resource_type_ext : 1
     * mv_provider : 0000000000
     * artist_name : 许嵩
     */

    private List<SongListBean> song_list;

    public List<SongListBean> getSong_list() {
        return song_list;
    }

    public void setSong_list(List<SongListBean> song_list) {
        this.song_list = song_list;
    }

    @DatabaseTable(tableName = "SongListBean")
    public static class SongListBean {

        @DatabaseField(generatedId = true)//删除所有数据时，需要加入主键，
        private int id;

        @DatabaseField
        private String artist_id;
        private String language;
        @DatabaseField
        private String pic_big;
        private String pic_small;
        private String country;
        @DatabaseField
        private String publishtime;
        @DatabaseField
        private String lrclink;
        @DatabaseField
        private int hot;
        private String all_artist_ting_uid;
        private String all_artist_id;
        @DatabaseField
        private String style;
        private String all_rate;
        @DatabaseField
        private int file_duration;
        private String bitrate_fee;
        @DatabaseField
        private String song_id;
        @DatabaseField
        private String title;
        private String ting_uid;
        @DatabaseField
        private String author;
        @DatabaseField
        private String album_id;
        @DatabaseField
        private String album_title;
        private String song_source;
        @DatabaseField
        private String artist_name;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getArtist_id() {
            return artist_id;
        }

        public void setArtist_id(String artist_id) {
            this.artist_id = artist_id;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getPic_big() {
            return pic_big;
        }

        public void setPic_big(String pic_big) {
            this.pic_big = pic_big;
        }

        public String getPic_small() {
            return pic_small;
        }

        public void setPic_small(String pic_small) {
            this.pic_small = pic_small;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getPublishtime() {
            return publishtime;
        }

        public void setPublishtime(String publishtime) {
            this.publishtime = publishtime;
        }

        public String getLrclink() {
            return lrclink;
        }

        public void setLrclink(String lrclink) {
            this.lrclink = lrclink;
        }

        public int getHot() {
            return hot;
        }

        public void setHot(int hot) {
            this.hot = hot;
        }

        public String getAll_artist_ting_uid() {
            return all_artist_ting_uid;
        }

        public void setAll_artist_ting_uid(String all_artist_ting_uid) {
            this.all_artist_ting_uid = all_artist_ting_uid;
        }

        public String getAll_artist_id() {
            return all_artist_id;
        }

        public void setAll_artist_id(String all_artist_id) {
            this.all_artist_id = all_artist_id;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public String getAll_rate() {
            return all_rate;
        }

        public void setAll_rate(String all_rate) {
            this.all_rate = all_rate;
        }

        public int getFile_duration() {
            return file_duration;
        }

        public void setFile_duration(int file_duration) {
            this.file_duration = file_duration;
        }

        public String getBitrate_fee() {
            return bitrate_fee;
        }

        public void setBitrate_fee(String bitrate_fee) {
            this.bitrate_fee = bitrate_fee;
        }

        public String getSong_id() {
            return song_id;
        }

        public void setSong_id(String song_id) {
            this.song_id = song_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTing_uid() {
            return ting_uid;
        }

        public void setTing_uid(String ting_uid) {
            this.ting_uid = ting_uid;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAlbum_id() {
            return album_id;
        }

        public void setAlbum_id(String album_id) {
            this.album_id = album_id;
        }

        public String getAlbum_title() {
            return album_title;
        }

        public void setAlbum_title(String album_title) {
            this.album_title = album_title;
        }

        public String getSong_source() {
            return song_source;
        }

        public void setSong_source(String song_source) {
            this.song_source = song_source;
        }

        public String getArtist_name() {
            return artist_name;
        }

        public void setArtist_name(String artist_name) {
            this.artist_name = artist_name;
        }
    }
}
