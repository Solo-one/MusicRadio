package com.example.asus.kugoumusic.entity;

import java.util.List;

/**
 * Created by asus on 2016/10/2.
 */
public class SongList {

    /**
     * songlist : [{"artist_id":"1814","all_artist_ting_uid":"7898","all_artist_id":"1814","language":"国语","publishtime":"2016-07-10","album_no":"1","versions":"","pic_big":"http://musicdata.baidu.com/data2/pic/e696d6c4da68bfb9ab61d1fc279c60e2/267472922/267472922.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/d25fb9f6c06a362b9f5f67810edec2ad/267472925/267472925.jpg","country":"港台","area":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/fb770e284785b7b4cf2e2965b9c9fb63/267473026/267473026.lrc","hot":"517076","file_duration":"232","del_status":"0","resource_type":"0","resource_type_ext":"0","copy_type":"1","relate_status":"0","all_rate":"64,128,256,320,flac","has_mv_mobile":0,"toneid":"0","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_id":"267473025","title":"夜空中最亮的星","ting_uid":"7898","author":"G.E.M.邓紫棋","album_id":"267472929","album_title":"夜空中最亮的星","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":1,"song_source":"web","piao_id":"0","korean_bb_song":"0","mv_provider":"0000000000","listen_total":"74500"},{"artist_id":"1814","all_artist_ting_uid":"7898","all_artist_id":"1814","language":"粤语","publishtime":"2014-08-22","album_no":"1","versions":"现场","pic_big":"http://musicdata.baidu.com/data2/pic/f14087029f4aff49c6c54978779af11c/122674110/122674110.jpg","pic_small":"http://musicdata.baidu.com/data2/pic/08192f4e360d9cd55384e6044ba2bf4b/122674113/122674113.jpg","country":"港台","area":"1","lrclink":"http://musicdata.baidu.com/data2/lrc/24aed3b50668ae5fb5da8f38a3efaeef/262701266/262701266.lrc","hot":"142706","file_duration":"259","del_status":"0","resource_type":"0","resource_type_ext":"0","copy_type":"1","relate_status":"0","all_rate":"64,128,192,256,320,flac","has_mv_mobile":0,"toneid":"0","bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","song_id":"122674119","title":"喜欢你","ting_uid":"7898","author":"G.E.M.邓紫棋","album_id":"122674120","album_title":"喜欢你","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","mv_provider":"0000000000","listen_total":"21020"}]
     * songnums : 281
     * havemore : 1
     * error_code : 22000
     */

    private String songnums;
    /**
     * artist_id : 1814
     * all_artist_ting_uid : 7898
     * all_artist_id : 1814
     * language : 国语
     * publishtime : 2016-07-10
     * album_no : 1
     * versions :
     * pic_big : http://musicdata.baidu.com/data2/pic/e696d6c4da68bfb9ab61d1fc279c60e2/267472922/267472922.jpg
     * pic_small : http://musicdata.baidu.com/data2/pic/d25fb9f6c06a362b9f5f67810edec2ad/267472925/267472925.jpg
     * country : 港台
     * area : 1
     * lrclink : http://musicdata.baidu.com/data2/lrc/fb770e284785b7b4cf2e2965b9c9fb63/267473026/267473026.lrc
     * hot : 517076
     * file_duration : 232
     * del_status : 0
     * resource_type : 0
     * resource_type_ext : 0
     * copy_type : 1
     * relate_status : 0
     * all_rate : 64,128,256,320,flac
     * has_mv_mobile : 0
     * toneid : 0
     * bitrate_fee : {"0":"0|0","1":"0|0"}
     * song_id : 267473025
     * title : 夜空中最亮的星
     * ting_uid : 7898
     * author : G.E.M.邓紫棋
     * album_id : 267472929
     * album_title : 夜空中最亮的星
     * is_first_publish : 0
     * havehigh : 2
     * charge : 0
     * has_mv : 0
     * learn : 1
     * song_source : web
     * piao_id : 0
     * korean_bb_song : 0
     * mv_provider : 0000000000
     * listen_total : 74500
     */

    private List<SonglistBean> songlist;

    public String getSongnums() {
        return songnums;
    }

    public void setSongnums(String songnums) {
        this.songnums = songnums;
    }

    public List<SonglistBean> getSonglist() {
        return songlist;
    }

    public void setSonglist(List<SonglistBean> songlist) {
        this.songlist = songlist;
    }

    public static class SonglistBean {
        private String artist_id;
        private String all_artist_ting_uid;
        private String language;
        private String publishtime;
        private String pic_big;
        private String pic_small;
        private String lrclink;
        private long hot;
        private int file_duration;
        private String song_id;
        private String title;
        private String ting_uid;
        private String author;
        private String album_id;
        private String album_title;
        private String listen_total;

        public String getArtist_id() {
            return artist_id;
        }

        public void setArtist_id(String artist_id) {
            this.artist_id = artist_id;
        }

        public String getAll_artist_ting_uid() {
            return all_artist_ting_uid;
        }

        public void setAll_artist_ting_uid(String all_artist_ting_uid) {
            this.all_artist_ting_uid = all_artist_ting_uid;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getPublishtime() {
            return publishtime;
        }

        public void setPublishtime(String publishtime) {
            this.publishtime = publishtime;
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

        public String getLrclink() {
            return lrclink;
        }

        public void setLrclink(String lrclink) {
            this.lrclink = lrclink;
        }

        public long getHot() {
            return hot;
        }

        public void setHot(long hot) {
            this.hot = hot;
        }

        public int getFile_duration() {
            return file_duration;
        }

        public void setFile_duration(int file_duration) {
            this.file_duration = file_duration;
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

        public String getListen_total() {
            return listen_total;
        }

        public void setListen_total(String listen_total) {
            this.listen_total = listen_total;
        }
    }
}
