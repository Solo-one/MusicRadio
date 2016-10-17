package com.example.asus.kugoumusic.LoadData;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.asus.kugoumusic.entity.Singer;
import com.example.asus.kugoumusic.entity.Track;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by asus on 2016/9/11.
 */
public class TrackLoader {
    /**
     * 要从MediaStore检索的列
     */
    private final String[] mProjection = new String[]{
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DISPLAY_NAME};
    // 数据库查询相关参数
    private String mSelection = null;
    private String[] mSelectionArgs = null;
    private String mSortOrder = null;
    private Pattern mFolerPattern = null;
    private static ContentResolver mContentResolver = null;
    private Context context;

    int index_id;
    int index_title;
    int index_data;
    int index_artist;
    int index_album;
    int index_album_id;
    int index_duration;
    int index_size;
    int index_displayname;

    public TrackLoader(Context context) {
        mContentResolver = context.getContentResolver();
        this.context = context;
    }

    //获取本地音乐文件列表
    public ArrayList<Track> loadTrackInBackground() {

        ArrayList<Track> tracks = new ArrayList<>();

        //动态获取权限
        if (Build.VERSION.SDK_INT  >= Build.VERSION_CODES.KITKAT) {
            //判断有没有权限
            int flag = ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (flag != PackageManager.PERMISSION_GRANTED){
                //请求权限
                ActivityCompat.requestPermissions((Activity)context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},2);
            } else {
               tracks.addAll(loadTrackInBackground1());
            }
        } else {
            tracks.addAll(loadTrackInBackground1());
        }

        return  tracks;
    }


    public ArrayList<Track> loadTrackInBackground1() {
        ArrayList<Track> itemsList = new ArrayList<Track>();
        Track item = null;
        Cursor cursor = mContentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                mProjection, mSelection, mSelectionArgs, mSortOrder);
        // 将数据库查询结果保存到一个List集合中(存放在RAM)
        if (cursor != null) {
            index_id = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
            index_title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            index_data = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            index_artist = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            index_album = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            index_album_id = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID);//专辑ID
            index_duration = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            index_size = cursor.getColumnIndex(MediaStore.Audio.Media.SIZE);
            index_displayname = cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);
            while (cursor.moveToNext()) {
                // 如果设置了文件夹过滤
                if (mFolerPattern != null) {
                    // 过滤出指定的文件夹下的文件，忽略子目录
                    Matcher matcher = mFolerPattern.matcher(cursor
                            .getString(index_data));
                    // 如果是以xxx.xxx结尾的路径，则就是当前目录下的文件了
                    if (matcher.find() && matcher.group().matches(".*\\..*")) {
                        item = createNewItem(cursor);
                    } else {// 是文件夹就忽略了
                        continue;
                    }
                } else {// 正常的创建新的条目
                    item = createNewItem(cursor);
                }
                itemsList.add(item);
            }
            cursor.close();
        }
        // 如果没有扫描到媒体文件，itemsList的size为0，因为上面new过了
        return itemsList;
    }

    private Track createNewItem(Cursor cursor) {
        Track item = new Track();
        item.setTitle(cursor.getString(index_title));
        item.setArtist(cursor.getString(index_artist));
        item.setDisplay_name(cursor.getString(index_displayname));
        item.setId(cursor.getLong(index_id));
        item.setAlbum(cursor.getString(index_album));
        item.setAlbum_id(cursor.getInt(index_album_id));
        item.setDuration(cursor.getLong(index_duration));
        item.setSize(cursor.getLong(index_size));
        item.setData(cursor.getString(index_data));
        return item;
    }

    //方法一 根据 音乐专辑id 获得专辑图片地址
    private static String getAlbumArt(int album_id) {
        String[] projection;
        if (album_id < 0) {
            return null;
        }
        String mUriAlbums = "content://media/external/audio/albums";
        projection = new String[]{android.provider.MediaStore.Audio.AlbumColumns.ALBUM_ART};
        Cursor cursor = mContentResolver.query(Uri.parse(mUriAlbums + "/" + Integer.toString(album_id)), projection,
                null, null, null);
        String album_art = null;

        if (cursor.getCount() > 0 && cursor.getColumnCount() > 0) {
            cursor.moveToNext();
            album_art = cursor.getString(0);
        }
        cursor.close();
        cursor = null;
        return album_art;
    }

    //获取指定音乐的图片文件地址
    public static String getBitMapPath(Track track) {
        String path = getAlbumArt(track.getAlbum_id());
        return path;
    }

    //获取指定音乐的图片文件地址
    public static String getBitMapPath(int album_id) {
        String path = getAlbumArt(album_id);
        return path;
    }


    //方法二 根据 音乐id 获得专辑图片地址
    public static Bitmap getArtAlbum(long audioId){
        String str = "content://media/external/audio/media/" + audioId+ "/albumart";
        Uri uri = Uri.parse(str);

        ParcelFileDescriptor pfd = null;
        try {
            pfd = mContentResolver.openFileDescriptor(uri, "r");
        } catch (FileNotFoundException e) {
            return null;
        }
        Bitmap bm;
        if (pfd != null) {
            FileDescriptor fd = pfd.getFileDescriptor();
            Log.i("TAG",fd.toString()+pfd+"ss0"+fd);
            bm = BitmapFactory.decodeFileDescriptor(fd);
            return bm;
        }
        return null;
    }

    //歌手
    public  List<Singer> loadSingerInBackground() {
        ArrayList<Singer> itemsList = new ArrayList<Singer>();
        Cursor cursor = mContentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{
                        MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.ALBUM_ID,
                }, mSelection, mSelectionArgs, mSortOrder);

        while(cursor.moveToNext()) {

            long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
            String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            int album_id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));

            String uri = getBitMapPath(album_id);

            Singer singer = new Singer(id,artist,uri);
            itemsList.add(singer);
        }

        cursor.close();
        // 如果没有扫描到媒体文件，itemsList的size为0，因为上面new过了
        return itemsList;
    }

    //专辑
    public  List<Singer> loadAlbumInBackground() {
        ArrayList<Singer> itemsList = new ArrayList<Singer>();
        Cursor cursor = mContentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{
                        MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.ALBUM,
                        MediaStore.Audio.Media.ALBUM_ID
                }, mSelection, mSelectionArgs, mSortOrder);

        while(cursor.moveToNext()) {

            long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
            String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
            int album_id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
            String uri = getBitMapPath(album_id);

            Singer singer = new Singer(id,artist,album,uri);
            itemsList.add(singer);
        }

        cursor.close();
        // 如果没有扫描到媒体文件，itemsList的size为0，因为上面new过了
        return itemsList;
    }


}

