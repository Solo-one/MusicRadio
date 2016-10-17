package com.example.asus.kugoumusic.tmp.flowLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.asus.kugoumusic.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlowLayoutActivity extends AppCompatActivity {

    @BindView(R.id.id_flowLayout)
    FlowLatout mFlowLayout;

    private String[] mVals = new String[]{
            "BIGBANG", "安静的音乐", "薛之谦", "最新歌榜前十首歌", "纯音乐前十首", "随便听听", "忧伤的情歌",
            "周杰伦", "中国新歌声最热曲目", "超女令人难忘的歌曲", "欢快的铃声", "微微一笑影视原声"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
        ButterKnife.bind(this);
        initData();

    }

    public void initData() {
        LayoutInflater mInflater = LayoutInflater.from(this);
        for (int i = 0; i < mVals.length; i++) {
            TextView tv = (TextView) mInflater.inflate(R.layout.mytextview, mFlowLayout, false);//第二个参数是放在哪里
            tv.setText(mVals[i]);
            mFlowLayout.addView(tv);
        }

    }
}
