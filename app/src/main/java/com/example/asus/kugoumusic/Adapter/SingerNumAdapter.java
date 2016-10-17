package com.example.asus.kugoumusic.Adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.example.asus.kugoumusic.LoadData.TrackLoader;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.entity.Singer;
import com.example.asus.kugoumusic.entity.Track;
import com.example.asus.kugoumusic.selfwidget.CircleImageView;
import com.example.asus.kugoumusic.util.NetImageCache;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by asus on 2016/9/24.
 */
public class SingerNumAdapter extends BaseAdapter {

    private Context mContext;
    private List<Singer> mData;

    private ImageLoader mImageLoader;//volley图片加载库

    public SingerNumAdapter(Context mContext, List<Singer> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mImageLoader = new ImageLoader(Volley.newRequestQueue(mContext), new NetImageCache());
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Singer getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Singer singer = mData.get(position);

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.singer_num_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //方法一
       /* Glide.with(mContext)
                .load(singer.getUri())
                .placeholder(R.drawable.notification_default)
                .error(R.drawable.notification_default)
                .thumbnail(0.5f)
                .into(holder.img);*/
        //方法二
        Glide.with(mContext)
                .load("content://media/external/audio/media/" + singer.getId() + "/albumart")
                .placeholder(R.drawable.notification_default)
                .error(R.drawable.notification_default)
                .thumbnail(0.5f)
                .into(holder.img);

        if (singer.getArtist().equals("<unknown>")) {
            holder.artist.setText(mContext.getResources().getString(
                    R.string.unknown_artist));
        } else {
            holder.artist.setText(singer.getArtist());
        }

        return convertView;
    }


    public static class ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.artist)
        TextView artist;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }
}

