package com.example.asus.kugoumusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.entity.MusicWebAlbum;
import com.example.asus.kugoumusic.util.NetImageCache;

import java.util.List;

/**
 * Created by asus on 2016/9/16.
 */
public class TuiJianAdapter extends BaseAdapter {

    private List<MusicWebAlbum.SongListBean> mData;
    private Context context;

    private ImageLoader mImageLoader;//volley图片加载库

    public TuiJianAdapter(List<MusicWebAlbum.SongListBean> mData, Context context) {
        this.mData = mData;
        this.context = context;
        mImageLoader = new ImageLoader(Volley.newRequestQueue(context), new NetImageCache());
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
        MusicWebAlbum.SongListBean song = mData.get(position);
        //布局加载器
        LayoutInflater inflater = LayoutInflater.from(context);
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tuijian_item, null, false);
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


        ImageLoader.ImageListener listener = ImageLoader.getImageListener(
                viewHolder.img,R.drawable.image_default_64,R.drawable.image_default_64);
        mImageLoader.get(song.getPic_big(), listener);

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
