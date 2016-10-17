package com.example.asus.kugoumusic.dao;

import android.content.Context;
import android.util.Log;

import com.example.asus.kugoumusic.db.MusicOrmLiteSqliteOpenHelper;
import com.example.asus.kugoumusic.entity.MusicWebAlbum;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/9/18.
 */
public class SongListBeanDao {
    //OrmLiteSqlite 封装的数据库操作对象
    private Dao<MusicWebAlbum.SongListBean, Integer> songListBeansDao;

    /**
     * @param context
     */
    public SongListBeanDao(Context context) {
        MusicOrmLiteSqliteOpenHelper dataBaseHelper = MusicOrmLiteSqliteOpenHelper.getInstance(context);//获得DataBaseHelper实例
        try {
            songListBeansDao = dataBaseHelper.getDao(MusicWebAlbum.SongListBean.class);//getDao()方法获得userdao对象
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过dao对象向数据库写入数据
     *
     * @param
     * @return
     */
    public long addSong(MusicWebAlbum.SongListBean song) {
        int id = 0;
        try {
            id = songListBeansDao.create(song);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * 批量写入
     * @param
     */
    public void addSongList(List<MusicWebAlbum.SongListBean> songlist) {
        for (MusicWebAlbum.SongListBean f : songlist) {
            addSong(f);
        }
    }


    /**
     * 通过dao对象查找所有数据库数据
     *
     * @return
     */
    public List<MusicWebAlbum.SongListBean> findAllSongs() {
        try {
            return songListBeansDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查找指定条件的数据库
     * @return
     */
    public boolean findSong(String song_id){
        boolean flag = false;
        List<MusicWebAlbum.SongListBean> is = new ArrayList<>();

        QueryBuilder<MusicWebAlbum.SongListBean, Integer> builder = songListBeansDao.queryBuilder();
        try {
            is = builder.where()
                    .eq("song_id", song_id).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (is.size() == 0) {
            flag = false;
        }else {
            flag = true;
        }
        return  flag;
    }

    /**
     * 删除所有音乐
     */
    public void deleteAllSong() {
        try {
            DeleteBuilder<MusicWebAlbum.SongListBean, Integer> builder = songListBeansDao.deleteBuilder();
            builder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定的数据元素
     */
    public void deleteSong(String song_id) {
        DeleteBuilder<MusicWebAlbum.SongListBean, Integer> builder = songListBeansDao.deleteBuilder();
        try {
            builder.where()
                    .eq("song_id", song_id);
            builder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
