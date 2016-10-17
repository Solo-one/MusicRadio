package com.example.asus.kugoumusic.LoadData;

import android.util.Log;

import com.android.volley.VolleyError;
import com.example.asus.kugoumusic.entity.LrcInfo;
import com.example.asus.kugoumusic.entity.LrcModel;
import com.example.asus.kugoumusic.entity.MusicRes;
import com.example.asus.kugoumusic.http.CallBack;
import com.example.asus.kugoumusic.util.VolleyUtil;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by asus on 2016/9/12.
 */
public class LrcDownLoaderManager {

    private static final String TAG = LrcDownLoaderManager.class.getSimpleName();

    public static final String GB2312 = "GB2312";
    public static final String UTF_8 = "utf-8";

    private Gson gson = new Gson();
    private List<LrcModel> lrcModels;
    private OnLrcChangedListener lrcChangedListener;
    public void setOnLrcChangedListener(OnLrcChangedListener lrcChangedListener){
        this.lrcChangedListener  = lrcChangedListener;
    }
    public LrcDownLoaderManager() {
        lrcModels = new ArrayList<LrcModel>();
    }

    /*
     * 根据歌曲名和歌手名取得该歌曲信息
     */
    public void searchMusicFromWeb(String musicName, final String singerName) {
        Log.i("TAG", "下载前，歌曲名:" + musicName + ",歌手名:" + singerName);
        // 传进来的如果是汉字，那么就要进行编码转化
        try {
            musicName = URLEncoder.encode(musicName, UTF_8);
            //singerName = URLEncoder.encode(singerName, UTF_8);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        String strUrl ="http://apis.baidu.com/geekery/music/query?s="+musicName;
        VolleyUtil.Get(strUrl)
                .setCallBack(new CallBack() {
                    @Override
                    public void onSuccess(String response) {

                        String s=response.substring(0,15);

                        Log.i("TAG", s+"歌名");
                        Log.i("TAG","歌名下是"+ response+"歌名");

                        if(s.equals("<!DOCTYPE html>")){
                            return;
                        }

                        lrcModels.clear();

                        MusicRes musicRes = null;
                        try {
                            musicRes = gson.fromJson(response, MusicRes.class);
                        } finally {
                            MusicRes.DataBean.MusicInfo mi = null;
                            if(musicRes!=null) {
                                List<MusicRes.DataBean.MusicInfo> list= musicRes.getData().getData();
                                for(MusicRes.DataBean.MusicInfo musicInfo:list){
                                    if(musicInfo.getSingername()!=null && musicInfo.getSingername().equals(singerName)){
                                        mi = musicInfo;
                                        break;
                                    }
                                }
                            }

                            Log.i("TAG",mi+"mi");
                            //请求歌词
                            fetchLrcContent(mi);
                        }
                    }

                    @Override
                    public void onErrer(VolleyError error) {

                    }
                })
                .build()
                .addRequestHeadrs("apikey", "4600fe45a7f631f4800368013fb1a76e")
                .start();
    }

    //
    private void fetchLrcContent(MusicRes.DataBean.MusicInfo mi) {
        if(mi!=null){
            String name=null;
            try {
                name=URLEncoder.encode(mi.getFilename(),UTF_8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String strUrl ="http://apis.baidu.com/geekery/music/krc?name="+name+"&hash="+mi.getHash()+"&time="+mi.getDuration();
            VolleyUtil.Get(strUrl)
                    .setCallBack(new CallBack() {
                        @Override
                        public void onSuccess(String response) {

                            String ggbom=response.substring(0,15);

                            Log.i("TAG", ggbom+"ggom");
                            if(ggbom.equals("<!DOCTYPE html>")){
                                return;
                            }

                            Log.i("TAG", "retuen");
                            LrcInfo lrcInfo = gson.fromJson(response,LrcInfo.class);
                            String[] lines = lrcInfo.getData().getContent().split("\\r\\n");
                            for(String l:lines) {
                                parseLrc(l);
                            }
                            if(lrcChangedListener!=null){
                                lrcChangedListener.onLrcLoaded(lrcModels);
                            }
                        }

                        @Override
                        public void onErrer(VolleyError error) {

                        }
                    })
                    .build()
                    .addRequestHeadrs("apikey", "4600fe45a7f631f4800368013fb1a76e")
                    .start();
        }
    }

    public void parseLrc(String line){
        String reg="\\[(\\d{2}:\\d{2}\\.\\d{2})\\]";
        Pattern pattern= Pattern.compile(reg);
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String time=matcher.group();
            LrcModel lModel=new LrcModel();
            lModel.setCurrentTime(parseTime(time));
            lModel.setCurrentContent(line.substring(time.length()));
            lrcModels.add(lModel);
        }
    }
    public Integer parseTime(String time){
        String temp=time.substring(1,time.length()-1);
        String[] s = temp.split(":");
        int min = Integer.parseInt(s[0]);
        String[] ss = s[1].split("\\.");
        int sec = Integer.parseInt(ss[0]);
        int mill = Integer.parseInt(ss[1]);
        return min * 60 * 1000 + sec * 1000 + mill * 10;
    }

    public int lrcIndex(int duration,int currentTime) {
        int index = 0;
        if (currentTime < duration) {
            for (int i = 0; i < lrcModels.size(); i++) {
                if (i < lrcModels.size() - 1) {
                    if (currentTime < lrcModels.get(i).getCurrentTime() && i == 0) {
                        index = i;
                    }
                    if ((currentTime > lrcModels.get(i).getCurrentTime())&& currentTime < lrcModels.get(i+1).getCurrentTime()) {
                        index = i;
                    }
                }
                if ((i == lrcModels.size() - 1)&& currentTime > lrcModels.get(i).getCurrentTime()) {
                    index = i;
                }
            }
        }
        return index;
    }


    /**
     * 歌词监听接口
     */
    public interface OnLrcChangedListener {
        public void onLrcLoaded(List<LrcModel> lrcModels);
        public void onLyricSentenceChanged(int index);
    }
}
