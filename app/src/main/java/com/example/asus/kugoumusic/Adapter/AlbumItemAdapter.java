package com.example.asus.kugoumusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.entity.SongList;

import java.util.List;



/**
 * Created by asus on 2016/10/3.
 */
public class AlbumItemAdapter extends BaseAdapter {

    private Context mContext;
    private List<SongList.SonglistBean> mData;

    public AlbumItemAdapter(Context mContext, List<SongList.SonglistBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();

    }

    @Override
    public SongList.SonglistBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SongList.SonglistBean song = mData.get(position);

        //布局加载器
        LayoutInflater inflater = LayoutInflater.from(mContext);
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.album_item_item, null, false);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.img);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.singer = (TextView) convertView.findViewById(R.id.singer);
            viewHolder.hot = (TextView) convertView.findViewById(R.id.hot);
            viewHolder.albumnum = (TextView) convertView.findViewById(R.id.albumnum);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Glide.with(mContext)
                .load(song.getPic_big())
                .placeholder(R.drawable.image_default_64)//占位图片
                .error(R.drawable.image_default_64)
                .thumbnail(0.5f)
                .into(viewHolder.img);

        viewHolder.title.setText(song.getTitle());
        viewHolder.singer.setText(song.getAuthor());
        viewHolder.hot.setText(song.getHot()/1000+"万");
        viewHolder.albumnum.setText(song.getAlbum_title());
        viewHolder.time.setText(song.getFile_duration()/60+":"+song.getFile_duration()%60);

        return convertView;
    }

    static class ViewHolder {
        ImageView img;
        TextView title;
        TextView singer;
        TextView hot;
        TextView albumnum;
        TextView time;
    }
}