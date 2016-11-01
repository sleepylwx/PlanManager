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

    public TimeTickerThread(Handler handler){

        this.handler = handler;

    }

    @Override
    public void run(){



        long curTime = System.currentTimeMillis();
        long tempTime = curTime;

        while(SelfLearingActivity.isGoOn()){

            curTime = System.currentTimeMillis();

            if(curTime - tempTime >= 1000){

                Message message = new Message();
                handler.sendMessage(message);
                tempTime = curTime;
            }



        }

    }


}
