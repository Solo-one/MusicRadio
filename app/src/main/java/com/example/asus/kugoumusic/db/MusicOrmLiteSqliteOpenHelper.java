package com.example.asus.kugoumusic.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.asus.kugoumusic.entity.MusicWebAlbum;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by asus on 2016/9/18.
 */
public class MusicOrmLiteSqliteOpenHelper extends OrmLiteSqliteOpenHelper {


    private static final String DB_NAME = "Music.db";//数据库名称
    private static final int DB_VERSION = 1;//数据库版本

    /**
     * 单例模式 私有化构造函数
     *
     * @param context
     */
    private MusicOrmLiteSqliteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private static MusicOrmLiteSqliteOpenHelper dataBaseHelper;//保证只实例化一个 DataBaseHelper

    /**
     * synchronized //线程同步，保证线程安全
     *
     * @param context
     * @return
     */
    public synchronized static MusicOrmLiteSqliteOpenHelper getInstance(Context context) {
        if (dataBaseHelper == null) {
            dataBaseHelper = new MusicOrmLiteSqliteOpenHelper(context);
        }
        return dataBaseHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, MusicWebAlbum.SongListBean.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, MusicWebAlbum.SongListBean.class, true);
            TableUtils.createTable(connectionSource, MusicWebAlbum.SongListBean.class);//更新新版本，需要重新创建表
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
    }

}