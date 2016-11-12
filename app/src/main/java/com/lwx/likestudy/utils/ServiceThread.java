package com.lwx.likestudy.utils;

import android.content.Context;
import android.util.Log;

/**
 * Created by 36249 on 2016/11/11.
 */
public class ServiceThread implements Runnable {


    Context context;
    boolean isNotified;
    boolean isOnTimeNotified = false;
    public ServiceThread(Context context,boolean isNotified){

        this.context = context;
        this.isNotified = isNotified;
    }
    @Override
    public void run(){

        if(!isNotified){

            NotificationHelper.createNotification(context);
        }


        while(true){

            if((Time.getCurrentHour() == 8 || Time.getCurrentHour() == 14) &&
                                        !isOnTimeNotified){

                Log.e("qbc",Time.getCurrentHour()+"");
                NotificationHelper.createNotification(context);

                isOnTimeNotified = true;
            }

            if(Time.getCurrentHour() != 8 && Time.getCurrentHour() != 14){

                isOnTimeNotified = false;
            }

        }
    }
}
