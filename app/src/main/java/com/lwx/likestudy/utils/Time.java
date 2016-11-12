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

        String createTime = calendar.get(Calendar.YEAR) + "-"
                + Time.formatTime(calendar.get(Calendar.MONTH) +  1) + "-"
                + Time.formatTime(calendar.get(Calendar.DATE))  +" "
                + Time.formatTime(calendar.get(Calendar.HOUR_OF_DAY)
                ,calendar.get(Calendar.MINUTE)
                ,calendar.get(Calendar.SECOND));

        return createTime;
    }

    public static String getYearMonthAndDay(String str){

        String time = str.substring(0,10);
        return time;
    }

    public static int getCurrentHour(){

        Calendar calendar = Calendar.getInstance();
        return Integer.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
    }
}
