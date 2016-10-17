package com.example.asus.kugoumusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.entity.SearchMusic;

import java.util.List;

/**
 * Created by asus on 2016/9/26.
 */
public class SearchAdapter extends BaseAdapter {

    private List<SearchMusic.SongBean> mData;
    private Context context;
    private int mLastPosition = -1;

    public SearchAdapter(List<SearchMusic.SongBean> mData, Context context) {
        this.mData = mData;
        this.context = context;
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
        SearchMusic.SongBean song = mData.get(position);
        //布局加载器
        LayoutInflater inflater = LayoutInflater.from(context);
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.search_item, null, false);
            viewHolder = new ViewHolder();

            viewHolder.title = (TextView) convertView.findViewById(R.id.textview_music_title);
            viewHolder.singer = (TextView) convertView.findViewById(R.id.textview_music_singer);
            viewHolder.more = (LinearLayout) convertView.findViewById(R.id.more);
            viewHolder.scroll = (HorizontalScrollView) convertView.findViewById(R.id.scroll);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.more.setTag(position);//这段代码很重要

        /**
         * 监听写在这里，原因
         */
        viewHolder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImageVisable((int) v.getTag());
            }
        });

        if (position == mLastPosition) {
            viewHolder.scroll.setVisibility(View.VISIBLE);
        } else {
            viewHolder.scroll.setVisibility(View.GONE);

        }

        viewHolder.title.setText(song.getSongname());
        viewHolder.singer.setText(song.getArtistname());

        return convertView;
    }

    static class ViewHolder {

        TextView title;
        TextView singer;
        LinearLayout more;
        HorizontalScrollView scroll;

    }

    public void changeImageVisable(int position) {
        if (position != mLastPosition) {
            mLastPosition = position;
        } else {
            mLastPosition = -1;
        }
        notifyDataSetChanged();
    }
}
