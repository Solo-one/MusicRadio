package com.example.asus.kugoumusic.tmp.LeafLoading;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asus.kugoumusic.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeafLoadingActivity extends AppCompatActivity {

    @BindView(R.id.leafloading)
    LeafLoadingView mLeafLoadingView;
    @BindView(R.id.iqiyi)
    IQiYiLoadingView myView;

    private static final int REFRESH_PROGRESS = 0x10;
    private int mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaf_loading);
        ButterKnife.bind(this);
        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS, 2000);

    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REFRESH_PROGRESS:
                    if (mProgress < 40) {
                        mProgress += 1;
                        // 随机800ms以内刷新一次
                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                                new Random().nextInt(800));
                        mLeafLoadingView.setCurrentProgress(mProgress);
                    } else {
                        mProgress += 1;
                        // 随机1200ms以内刷新一次
                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                                new Random().nextInt(1200));
                        mLeafLoadingView.setCurrentProgress(mProgress);

                    }
                    break;

                default:
                    break;
            }

        }
    };
}
