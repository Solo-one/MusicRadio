package com.example.asus.kugoumusic.tmp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.asus.kugoumusic.R;
import com.example.asus.kugoumusic.tmp.tmp_entity.CityData;
import com.example.asus.kugoumusic.tmp.tmp_util.CharacterParser;
import com.example.asus.kugoumusic.tmp.tmp_util.MyCityAdapter;
import com.example.asus.kugoumusic.tmp.tmp_util.PinyinComparator;
import com.example.asus.kugoumusic.tmp.tmp_util.getCityList;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityFirstActivity extends AppCompatActivity {

    @BindView(R.id.lv)
    ListView lv;

    CharacterParser characterParser;
    PinyinComparator pinyinComparator;
    List<CityData> cityDatas;
    MyCityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_first);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        getCity();
    }

    private void getCity() {
        getCityList cityList = new getCityList(characterParser);

        cityDatas = cityList.filledData(getResources().getStringArray(R.array.date));

        Collections.sort(cityDatas, pinyinComparator);

        adapter = new MyCityAdapter(this,cityDatas);
        lv.setAdapter(adapter);

    }
}
