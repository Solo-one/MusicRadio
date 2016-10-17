package com.example.asus.kugoumusic.tmp.tmp_util;

import com.example.asus.kugoumusic.tmp.tmp_entity.CityData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/9/26.
 */
public class getCityList {

    private CharacterParser characterParser;

    public getCityList(CharacterParser characterParser) {
        super();
        this.characterParser = characterParser;
    }
    /**
     * 为ListView填充数据
     *
     * @param date
     * @return
     */
    public List<CityData> filledData(String[] date) {
        List<CityData> mSortList = new ArrayList<CityData>();

        for (int i = 0; i < date.length; i++) {
            CityData cityData = new CityData();
            cityData.setName(date[i]);
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                cityData.setFristA(sortString.toUpperCase());
            } else {
                cityData.setFristA("#");
            }
            mSortList.add(cityData);
        }
        return mSortList;
    }
}

