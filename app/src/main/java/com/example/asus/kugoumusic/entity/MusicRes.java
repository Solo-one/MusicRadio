package com.example.asus.kugoumusic.entity;

import java.util.List;

/**
 * Created by asus on 2016/9/12.
 * 音乐歌词类
 */
public class MusicRes {
    /**
     * code : 0
     * status : success
     * msg : 数据请求成功
     * data : {"current_page":1,"keyword":"Hit Da Stylin'","total_rows":1,"total_page":1,"page_size":15,"data":[{"filename":"群星 - hit da stylin","songname":"hit da stylin","m4afilesize":524454,"filesize":2032298,"bitrate":128,"album_name":"","isnew":0,"duration":126,"singername":"群星","extname":"mp3","hash":"d3d8121bc7169c1eb8ae52531287f58b","othername":""}]}
     */

    private int code;
    private String status;
    private String msg;
    /**
     * current_page : 1
     * keyword : Hit Da Stylin'
     * total_rows : 1
     * total_page : 1
     * page_size : 15
     * data : [{"filename":"群星 - hit da stylin","songname":"hit da stylin","m4afilesize":524454,"filesize":2032298,"bitrate":128,"album_name":"","isnew":0,"duration":126,"singername":"群星","extname":"mp3","hash":"d3d8121bc7169c1eb8ae52531287f58b","othername":""}]
     */

    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int current_page;
        private String keyword;
        private int total_rows;
        private int total_page;
        private int page_size;
        /**
         * filename : 群星 - hit da stylin
         * songname : hit da stylin
         * m4afilesize : 524454
         * filesize : 2032298
         * bitrate : 128
         * album_name :
         * isnew : 0
         * duration : 126
         * singername : 群星
         * extname : mp3
         * hash : d3d8121bc7169c1eb8ae52531287f58b
         * othername :
         */

        private List<MusicInfo> data;

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public int getTotal_rows() {
            return total_rows;
        }

        public void setTotal_rows(int total_rows) {
            this.total_rows = total_rows;
        }

        public int getTotal_page() {
            return total_page;
        }

        public void setTotal_page(int total_page) {
            this.total_page = total_page;
        }

        public int getPage_size() {
            return page_size;
        }

        public void setPage_size(int page_size) {
            this.page_size = page_size;
        }

        public List<MusicInfo> getData() {
            return data;
        }

        public void setData(List<MusicInfo> data) {
            this.data = data;
        }

        public static class MusicInfo {
            private String filename;
            private String songname;
            private int m4afilesize;
            private int filesize;
            private int bitrate;
            private String album_name;
            private int isnew;
            private int duration;
            private String singername;
            private String extname;
            private String hash;
            private String othername;

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public String getSongname() {
                return songname;
            }

            public void setSongname(String songname) {
                this.songname = songname;
            }

            public int getM4afilesize() {
                return m4afilesize;
            }

            public void setM4afilesize(int m4afilesize) {
                this.m4afilesize = m4afilesize;
            }

            public int getFilesize() {
                return filesize;
            }

            public void setFilesize(int filesize) {
                this.filesize = filesize;
            }

            public int getBitrate() {
                return bitrate;
            }

            public void setBitrate(int bitrate) {
                this.bitrate = bitrate;
            }

            public String getAlbum_name() {
                return album_name;
            }

            public void setAlbum_name(String album_name) {
                this.album_name = album_name;
            }

            public int getIsnew() {
                return isnew;
            }

            public void setIsnew(int isnew) {
                this.isnew = isnew;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public String getSingername() {
                return singername;
            }

            public void setSingername(String singername) {
                this.singername = singername;
            }

            public String getExtname() {
                return extname;
            }

            public void setExtname(String extname) {
                this.extname = extname;
            }

            public String getHash() {
                return hash;
            }

            public void setHash(String hash) {
                this.hash = hash;
            }

            public String getOthername() {
                return othername;
            }

            public void setOthername(String othername) {
                this.othername = othername;
            }
        }
    }
}
