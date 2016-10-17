package com.example.asus.kugoumusic.entity;

import java.util.List;

/**
 * Created by asus on 2016/10/2.
 */
public class Album {

    /**
     * album_id : 267472929
     * author : G.E.M.邓紫棋
     * title : 夜空中最亮的星
     * publishcompany : 蜂鸟音乐
     * prodcompany :
     * country : 港台
     * language : 国语
     * songs_total : 1
     * info : 有时，走在白天，也像走在夜里。孤单和茫然，都会让人坠入黑暗。每当这时我都会仰起头。是的，我愿意成为一个仰望星空的人，拥有透明的心灵、和会流泪的眼睛。因为懂得痛，才能面对痛。而其实，黑不可怕，只要能抬头看到你。这是唱这首歌时，邓紫棋心里的话。
     邓紫棋推出新的翻唱作品《夜空中最亮的星》（原唱：逃跑计划乐队），丰富电音的加入，将这首摇滚歌曲演绎得更为磅礴生动。
     * styles : 流行
     * style_id : 3
     * publishtime : 2016-07-17
     * artist_ting_uid : 7898
     * all_artist_ting_uid : null
     * gender : 1
     * area : 1
     * pic_small : http://musicdata.baidu.com/data2/pic/d25fb9f6c06a362b9f5f67810edec2ad/267472925/267472925.jpg
     * pic_big : http://musicdata.baidu.com/data2/pic/e696d6c4da68bfb9ab61d1fc279c60e2/267472922/267472922.jpg
     * hot :
     * favorites_num : 0
     * recommend_num : 0
     * collect_num : 7
     * share_num : 2
     * comment_num : 7
     * artist_id : 1814
     * all_artist_id : 1814
     * pic_radio : http://musicdata.baidu.com/data2/pic/290b31d70f4598c3efdf53a9e6f1198a/267472919/267472919.jpg
     * pic_s500 : http://musicdata.baidu.com/data2/pic/a9ecba11542840fd4e50b62b82b5aa38/267472942/267472942.jpg
     * pic_s1000 : http://musicdata.baidu.com/data2/pic/323388cc1fa21195bd5fa830cf96a3ef/267472941/267472941.jpg
     * ai_presale_flag : 0
     * resource_type_ext : 0
     * listen_num : 6321280
     * buy_url :
     */

    private AlbumInfoBean albumInfo;
    /**
     * artist_id : 1814
     * all_artist_id : 1814
     * all_artist_ting_uid : 7898
     * language : 国语
     * publishtime : 2016-07-10
     * album_no : 1
     * versions :
     * pic_big : http://musicdata.baidu.com/data2/pic/e696d6c4da68bfb9ab61d1fc279c60e2/267472922/267472922.jpg
     * pic_small : http://musicdata.baidu.com/data2/pic/d25fb9f6c06a362b9f5f67810edec2ad/267472925/267472925.jpg
     * hot : 517076
     * file_duration : 232
     * del_status : 0
     * resource_type : 0
     * copy_type : 1
     * has_mv_mobile : 0
     * all_rate : 64,128,256,320,flac
     * toneid : 0
     * country : 港台
     * area : 1
     * lrclink : http://musicdata.baidu.com/data2/lrc/fb770e284785b7b4cf2e2965b9c9fb63/267473026/267473026.lrc
     * bitrate_fee : {"0":"0|0","1":"0|0"}
     * si_presale_flag : 0
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
     * resource_type_ext : 0
     * mv_provider : 0000000000
     */

    private List<SonglistBean> songlist;

    public AlbumInfoBean getAlbumInfo() {
        return albumInfo;
    }

    public void setAlbumInfo(AlbumInfoBean albumInfo) {
        this.albumInfo = albumInfo;
    }

    public List<SonglistBean> getSonglist() {
        return songlist;
    }

    public void setSonglist(List<SonglistBean> songlist) {
        this.songlist = songlist;
    }

    public static class AlbumInfoBean {
        private String album_id;
        private String author;
        private String title;
        private String publishcompany;
        private String country;
        private String language;
        private String songs_total;
        private String info;
        private String styles;
        private String style_id;
        private String publishtime;
        private String artist_ting_uid;
        private String pic_small;
        private String pic_big;
        private String artist_id;
        private String pic_radio;
        private String pic_s500;
        private String pic_s1000;

        public String getAlbum_id() {
            return album_id;
        }

        public void setAlbum_id(String album_id) {
            this.album_id = album_id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPublishcompany() {
            return publishcompany;
        }

        public void setPublishcompany(String publishcompany) {
            this.publishcompany = publishcompany;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getSongs_total() {
            return songs_total;
        }

        public void setSongs_total(String songs_total) {
            this.songs_total = songs_total;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getStyles() {
            return styles;
        }

        public void setStyles(String styles) {
            this.styles = styles;
        }

        public String getStyle_id() {
            return style_id;
        }

        public void setStyle_id(String style_id) {
            this.style_id = style_id;
        }

        public String getPublishtime() {
            return publishtime;
        }

        public void setPublishtime(String publishtime) {
            this.publishtime = publishtime;
        }

        public String getArtist_ting_uid() {
            return artist_ting_uid;
        }

        public void setArtist_ting_uid(String artist_ting_uid) {
            this.artist_ting_uid = artist_ting_uid;
        }

        public String getPic_small() {
            return pic_small;
        }

        public void setPic_small(String pic_small) {
            this.pic_small = pic_small;
        }

        public String getPic_big() {
            return pic_big;
        }

        public void setPic_big(String pic_big) {
            this.pic_big = pic_big;
        }

        public String getArtist_id() {
            return artist_id;
        }

        public void setArtist_id(String artist_id) {
            this.artist_id = artist_id;
        }

        public String getPic_radio() {
            return pic_radio;
        }

        public void setPic_radio(String pic_radio) {
            this.pic_radio = pic_radio;
        }

        public String getPic_s500() {
            return pic_s500;
        }

        public void setPic_s500(String pic_s500) {
            this.pic_s500 = pic_s500;
        }

        public String getPic_s1000() {
            return pic_s1000;
        }

        public void setPic_s1000(String pic_s1000) {
            this.pic_s1000 = pic_s1000;
        }
    }

    public static class SonglistBean {
        private String artist_id;
        private String all_artist_id;
        private String all_artist_ting_uid;
        private String language;
        private String publishtime;
        private String pic_big;
        private String pic_small;
        private String hot;
        private String file_duration;
        private String country;
        private String lrclink;
        private String song_id;
        private String title;
        private String ting_uid;
        private String author;
        private String album_id;
        private String album_title;
        private String song_source;

        public String getArtist_id() {
            return artist_id;
        }

        public void setArtist_id(String artist_id) {
            this.artist_id = artist_id;
        }

        public String getAll_artist_id() {
            return all_artist_id;
        }

        public void setAll_artist_id(String all_artist_id) {
            this.all_artist_id = all_artist_id;
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

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }

        public String getFile_duration() {
            return file_duration;
        }

        public void setFile_duration(String file_duration) {
            this.file_duration = file_duration;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getLrclink() {
            return lrclink;
        }

        public void setLrclink(String lrclink) {
            this.lrclink = lrclink;
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
    }
}
