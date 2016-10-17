package com.example.asus.kugoumusic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.entity.Track;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by asus on 2016/9/15.
 */
public class SingerAdapter extends BaseAdapter {
    private Context mContext;
    private List<Track> mData;
    private int mLastPosition = -1;
    private int mLastPosition1 = -1;

    public SingerAdapter(Context mContext, List<Track> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Track getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.list_item_track, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.more.setTag(position);//这段代码很重要

        if (position == mLastPosition) {
            holder.scroll.setVisibility(View.VISIBLE);
        } else {
            holder.scroll.setVisibility(View.GONE);

        }

        if (position == mLastPosition1) {
            holder.view.setVisibility(View.VISIBLE);
            holder.title.setTextColor(mContext.getResources().getColor(R.color.mycolor3));
            holder.artist.setTextColor(mContext.getResources().getColor(R.color.mycolor4));
        } else {
            holder.view.setVisibility(View.GONE);
            holder.title.setTextColor(mContext.getResources().getColor(R.color.mycolor1));
            holder.artist.setTextColor(mContext.getResources().getColor(R.color.mycolor2));
        }

        Track track = mData.get(position);
        holder.title.setText(track.getTitle());

        if (track.getArtist().equals("<unknown>")) {
            holder.artist.setText(mContext.getResources().getString(
                    R.string.unknown_artist));
        } else {
            holder.artist.setText(track.getArtist());
        }

        /**
         * 监听写在这里，原因
         */
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImageVisable((int) v.getTag());
            }
        });

        return convertView;
    }

    public static class ViewHolder {
        @BindView(R.id.textview_music_title)
        TextView title;
        @BindView(R.id.textview_music_singer)
        TextView artist;
        @BindView(R.id.scroll)
        HorizontalScrollView scroll;
        @BindView(R.id.view)
        View view;
        @BindView(R.id.more)
        LinearLayout more;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }

    public void changeImageVisable(int position) {
        if (position != mLastPosition) {
            mLastPosition = position;
        } else {
            mLastPosition = -1;
        }
        notifyDataSetChanged();
    }

    public void changeVisable(int position) {
//        if(position != mLastPosition1) {
//            mLastPosition1 = position;
//        } else {
//            mLastPosition1 = -1;
//        }
//        notifyDataSetChanged();

        //点击原处不会改变状态
        if (position != mLastPosition1) {
            mLastPosition1 = position;
            notifyDataSetChanged();//刷新适配器
        }
    }


}
