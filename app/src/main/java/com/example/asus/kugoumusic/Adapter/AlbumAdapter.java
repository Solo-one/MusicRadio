package com.example.asus.kugoumusic.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.example.asus.kugoumusic.LoadData.TrackLoader;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.entity.Singer;
import com.example.asus.kugoumusic.selfwidget.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by asus on 2016/9/24.
 */
public class AlbumAdapter extends BaseAdapter {

    private Context mContext;
    private List<Singer> mData;

    public AlbumAdapter(Context mContext, List<Singer> mData) {
        this.mContext = mContext;
        this.mData = mData;
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
                    R.layout.album_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Glide.with(mContext)
                .load("content://media/external/audio/media/" + singer.getId() + "/albumart")
                .placeholder(R.drawable.notification_default)//占位图片
                .error(R.drawable.notification_default)
                .thumbnail(0.5f)
                .into(holder.img);


        holder.artist.setText(singer.getAlbum());

        if (singer.getArtist().equals("<unknown>")) {
            holder.num.setText(mContext.getResources().getString(
                    R.string.unknown_artist));
        } else {
            holder.num.setText(singer.getArtist());
        }

        return convertView;
    }


    public static class ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.artist)
        TextView artist;
        @BindView(R.id.num)
        TextView num;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
