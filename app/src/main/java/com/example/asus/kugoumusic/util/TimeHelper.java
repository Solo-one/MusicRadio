package com.example.asus.kugoumusic.util;

import java.util.Locale;

/**
 * Created by asus on 2016/9/8.
 */
public class TimeHelper {
    /**
     * 将给定的毫秒数转换成00:00:00样式的字符串
     *
     * @param milliseconds
     *            待转换的毫秒数
     * */
    public static String milliSecondsToFormatTimeString(long milliseconds) {
        String finalTimerString = "";
        int hours, minutes, seconds;

        hours = (int) (milliseconds / (1000 * 60 * 60));
        minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        if (hours > 0) {
            finalTimerString = String.format(Locale.getDefault(),
                    "%02d%02d:%02d", hours, minutes, seconds);
        } else {
            finalTimerString = String.format(Locale.getDefault(), "%02d:%02d",
                    minutes, seconds);
        }
        // Log.d(TAG, "milliseconds=" + milliseconds + "\t finalTimerString=" +
        // finalTimerString);
        return finalTimerString;
    }
}
