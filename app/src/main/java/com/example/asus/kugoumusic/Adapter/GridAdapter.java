package com.example.asus.kugoumusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.asus.kugoumusic.MusicPlay.WebPlayActivity;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.entity.MusicWebAlbum;
import com.example.asus.kugoumusic.subscribe.TuiJian.ZhuanJiActivity;
import com.example.asus.kugoumusic.util.NetImageCache;

import java.util.List;

/**
 * Created by asus on 2016/9/17.
 */
public class GridAdapter extends BaseAdapter {

    private List<MusicWebAlbum.SongListBean> mData;
    private Context mContext;
    private ImageLoader mImageLoader;//volley图片加载库
    private DisplayMetrics dm;

    public GridAdapter(List<MusicWebAlbum.SongListBean> mData, Context mContext,DisplayMetrics dm) {
        this.mData = mData;
        this.mContext = mContext;
        this.dm = dm;
        mImageLoader = new ImageLoader(Volley.newRequestQueue(mContext), new NetImageCache());
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final MusicWebAlbum.SongListBean song = mData.get(position);
        //布局加载器
        LayoutInflater inflater = LayoutInflater.from(mContext);//参数要有 上下文 Context
        ViewHolder holder;
        /**
         * ConvertView 回收视图，效率提高 200%
         *
         * 如果原先没有convertView，就加载布局
         */
        if (convertView == null) {
            //加载布局
            convertView = inflater.inflate(R.layout.gridview_item, null, false);
            //缓存对象
            holder = new ViewHolder();//
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.cardView = (CardView) convertView.findViewById(R.id.cardview);
            holder.album_title = (TextView) convertView.findViewById(R.id.album_title);
            holder.artist_name = (TextView) convertView.findViewById(R.id.artist_name);

            convertView.setTag(holder);//View 关联 Holder
        } else {

            holder = (ViewHolder) convertView.getTag();//若果有 加载 ViewHolder
        }

        ImageLoader.ImageListener listener = ImageLoader.getImageListener(
                holder.img,R.drawable.image_default_64,R.drawable.image_default_64);
        mImageLoader.get(song.getPic_big(), listener);
        holder.img.setMinimumHeight(dm.widthPixels / 3 - 40);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ZhuanJiActivity.class);
                intent.putExtra("album_id", song.getAlbum_id());
                mContext.startActivity(intent);
            }
        });

        holder.album_title.setText(song.getAlbum_title());
        holder.artist_name.setText(song.getArtist_name()+"    风格："+song.getStyle());

        return convertView;//返回View对象
    }

    static class ViewHolder {
        ImageView img;
        CardView cardView;
        TextView album_title;
        TextView artist_name;
    }
}
