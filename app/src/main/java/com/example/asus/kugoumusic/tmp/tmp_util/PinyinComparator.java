package com.example.asus.kugoumusic.tmp.tmp_util;

import com.example.asus.kugoumusic.tmp.tmp_entity.CityData;

import java.util.Comparator;

/**
 * Created by asus on 2016/9/26.
 * 拼音比较器
 */
public class PinyinComparator implements Comparator<CityData> {

    @Override
    public int compare(CityData o1, CityData o2) {
        if (o1.getFristA().equals("@")||o2.getFristA().equals("#")) {
            return -1;
        } else if (o1.getFristA().equals("#")||o2.getFristA().equals("@")){
            return 1;
        } else {
            return o1.getFristA().compareTo(o2.getFristA());
        }
    }
}
