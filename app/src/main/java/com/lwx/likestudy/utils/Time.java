package com.lwx.likestudy.utils;

import java.util.Calendar;

/**
 * Created by 36249 on 2016/11/2.
 */
public class Time {

    public static String formatTime(int time){

        String sTime = time <= 9? "0" + time : String.valueOf(time);
        return sTime;
    }

    public static String formatTime(int hour,int minute,int second){

        return formatTime(hour) + ":" + formatTime(minute) + ":"
                + formatTime(second);
    }

    public static String formatTimeWithSpace(int hour,int minute,int second){
        return formatTime(hour) +" : " + formatTime(minute) + " : "
                + formatTime(second);
    }

    public static String getCurrentTimeString(){

        Calendar calendar = Calendar.getInstance();

        String createTime = calendar.get(Calendar.YEAR) + "年"
                + (calendar.get(Calendar.MONTH) +  1) + "月"
                + calendar.get(Calendar.DATE) + "日" +" "
                + Time.formatTime(calendar.get(Calendar.HOUR_OF_DAY)
                ,calendar.get(Calendar.MINUTE)
                ,calendar.get(Calendar.SECOND));

        return createTime;
    }



}
