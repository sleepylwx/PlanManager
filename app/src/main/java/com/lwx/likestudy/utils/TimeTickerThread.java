package com.lwx.likestudy.utils;

import android.os.Handler;
import android.os.Message;
import android.provider.Settings;

import com.lwx.likestudy.ui.activity.SelfLearingActivity;

import java.util.Calendar;

/**
 * Created by 36249 on 2016/10/31.
 */
public class TimeTickerThread implements Runnable {

    Handler handler;
    long time;
    public TimeTickerThread(Handler handler,long time){

        this.handler = handler;
        this.time = time;
    }

    @Override
    public void run(){

        long startTime = time > 0 ? time : System.currentTimeMillis();
        time = startTime;
        long gapTime = startTime;
        while(SelfLearingActivity.isGoOn()){

            long curTime = System.currentTimeMillis();

            if(curTime - gapTime >= 1000){



                long hour =(curTime - startTime)/(1000*60*60);
                long minute = (curTime - startTime)%(1000*60*60)/(1000*60);
                long second = (curTime - startTime)%(1000*60)/1000;
                Message message = new Message();
                String sHour = hour <= 9 ? "0"+ hour: String.valueOf(hour);
                String sMinute = minute <= 9 ? "0" + minute : String.valueOf(minute);
                String sSecond = second <= 9 ? "0" + second :String.valueOf(second);
                message.obj = sHour + " : " + sMinute + " : " + sSecond;
                handler.sendMessage(message);
                gapTime = curTime;
            }
        }

    }

    public long getStartTime(){

        return time;
    }
}
