package com.example.asus.kugoumusic.entity;

/**
 * Created by asus on 2016/9/16.
 */
public class WebMusic {

    /**
     * special_type : 0
     * pic_huge :
     * resource_type : 2
     * pic_premium : http://musicdata.baidu.com/data2/pic/f3cca738e49a182316a9a7af19c6ecbc/268143355/268143355.jpg
     * havehigh : 2
     * author : Beyond
     * toneid : 600902000004240302
     * has_mv : 1
     * song_id : 877578
     * piao_id : 0
     * artist_id : 130
     * lrclink : http://musicdata.baidu.com/data2/lrc/238975978/238975978.lrc
     * relate_status : 0
     * learn : 1
     * pic_big : http://musicdata.baidu.com/data2/pic/5517daa53d9146462c0a8763970964ed/88582715/88582715.jpg
     * play_type : 0
     * album_id : 197864
     * album_title : 海阔天空
     * bitrate_fee : {"0":"0|0","1":"0|0"}
     * song_source : web
     * all_artist_id : 130
     * all_artist_ting_uid : 1100
     * all_rate : 64,128,192,256,320,flac
     * charge : 0
     * copy_type : 1
     * is_first_publish : 0
     * korean_bb_song : 0
     * pic_radio : http://musicdata.baidu.com/data2/pic/dae103889023881fd004304bb8d06bbc/88582707/88582707.jpg
     * has_mv_mobile : 1
     * title : 海阔天空
     * pic_small : http://musicdata.baidu.com/data2/pic/a22537924f4100a4362413c160fe4650/88582728/88582728.jpg
     * album_no : 1
     * resource_type_ext : 0
     * ting_uid : 1100
     */

    private SonginfoBean songinfo;
    /**
     * show_link :
     * free : 1
     * song_file_id : 42783748
     * file_size : 2679447
     * file_extension : mp3
     * file_duration : 322
     * file_bitrate : 64
     * file_link : http://yinyueshiting.baidu.com/data2/music/42783748/42783748.mp3?xcode=fcc750fbbb06bc0bc85f964b2abbb8a2
     * hash : 74d926076dc8f2f857ffaa403d79935a65f80dec
     */

    private BitrateBean bitrate;

    public SonginfoBean getSonginfo() {
        return songinfo;
    }

    public void setSonginfo(SonginfoBean songinfo) {
        this.songinfo = songinfo;
    }

    public BitrateBean getBitrate() {
        return bitrate;
    }

    public void setBitrate(BitrateBean bitrate) {
        this.bitrate = bitrate;
    }

    public static class SonginfoBean {
        private String pic_premium;
        private String author;
        private String toneid;
        private String song_id;
        private String artist_id;
        private String lrclink;
        private String pic_big;
        private String album_id;
        private String album_title;
        private String bitrate_fee;
        private String song_source;
        private String all_artist_id;
        private String all_artist_ting_uid;
        private String all_rate;
        private String pic_radio;
        private String title;
        private String pic_small;
        private String ting_uid;

        public String getPic_premium() {
            return pic_premium;
        }

        public void setPic_premium(String pic_premium) {
            this.pic_premium = pic_premium;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getToneid() {
            return toneid;
        }

        public void setToneid(String toneid) {
            this.toneid = toneid;
        }

        public String getSong_id() {
            return song_id;
        }

        public void setSong_id(String song_id) {
            this.song_id = song_id;
        }

        public String getArtist_id() {
            return artist_id;
        }

        public void setArtist_id(String artist_id) {
            this.artist_id = artist_id;
        }

        public String getLrclink() {
            return lrclink;
        }

        public void setLrclink(String lrclink) {
            this.lrclink = lrclink;
        }

        public String getPic_big() {
            return pic_big;
        }

        public void setPic_big(String pic_big) {
            this.pic_big = pic_big;
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

        public String getBitrate_fee() {
            return bitrate_fee;
        }

        public void setBitrate_fee(String bitrate_fee) {
            this.bitrate_fee = bitrate_fee;
        }

        public String getSong_source() {
            return song_source;
        }

        public void setSong_source(String song_source) {
            this.song_source = song_source;
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

        public String getAll_rate() {
            return all_rate;
        }

        public void setAll_rate(String all_rate) {
            this.all_rate = all_rate;
        }

        public String getPic_radio() {
            return pic_radio;
        }

        public void setPic_radio(String pic_radio) {
            this.pic_radio = pic_radio;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic_small() {
            return pic_small;
        }

        public void setPic_small(String pic_small) {
            this.pic_small = pic_small;
        }

        public String getTing_uid() {
            return ting_uid;
        }

        public void setTing_uid(String ting_uid) {
            this.ting_uid = ting_uid;
        }
    }

    public static class BitrateBean {
        private int song_file_id;
        private int file_size;
        private String file_extension;
        private int file_duration;
        private int file_bitrate;
        private String file_link;
        private String hash;

        public int getSong_file_id() {
            return song_file_id;
        }

        public void setSong_file_id(int song_file_id) {
            this.song_file_id = song_file_id;
        }

        public int getFile_size() {
            return file_size;
        }

        public void setFile_size(int file_size) {
            this.file_size = file_size;
        }

        public String getFile_extension() {
            return file_extension;
        }

        public void setFile_extension(String file_extension) {
            this.file_extension = file_extension;
        }

        public int getFile_duration() {
            return file_duration;
        }

        public void setFile_duration(int file_duration) {
            this.file_duration = file_duration;
        }

        public int getFile_bitrate() {
            return file_bitrate;
        }

        public void setFile_bitrate(int file_bitrate) {
            this.file_bitrate = file_bitrate;
        }

        public String getFile_link() {
            return file_link;
        }

        public void setFile_link(String file_link) {
            this.file_link = file_link;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }
    }
}
