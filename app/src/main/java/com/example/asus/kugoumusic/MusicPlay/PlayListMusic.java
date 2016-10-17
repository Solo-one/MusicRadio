package com.example.asus.kugoumusic.MusicPlay;

import com.example.asus.kugoumusic.entity.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/9/10.
 * 播放清单类
 */
public final class PlayListMusic {

    public static final class PlayList {

        public static List<Track> tracks = new ArrayList<>();;

        public static Track getList(int position){
            Track track = tracks.get(position);
            return track;
        }

        public static void setTracks(List<Track> tracks) {
            PlayList.tracks = tracks;
        }
    }
}
