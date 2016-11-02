package com.lwx.likestudy.utils;

/**
 * Created by 36249 on 2016/11/2.
 */
public class FormatTime {

    public static String formatTime(int time){

        String sTime = time <= 9? "0" + time : String.valueOf(time);
        return sTime;
    }
}
