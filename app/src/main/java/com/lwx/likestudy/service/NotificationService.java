package com.lwx.likestudy.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.lwx.likestudy.LikeStudyApplication;
import com.lwx.likestudy.utils.NotificationHelper;
import com.lwx.likestudy.utils.ServiceThread;

/**
 * Created by 36249 on 2016/11/11.
 */
public class NotificationService extends Service {


    @Override
    public IBinder onBind(Intent intent){

        return null;
    }

    @Override
    public void onCreate(){

        super.onCreate();


    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){

        new Thread(new ServiceThread(LikeStudyApplication.getInstance(),
        intent.getBooleanExtra("isnotified",false))).start();
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy(){

        Intent intent = new Intent("com.lwx.NOTIFYSERVICEEND");
        sendBroadcast(intent);
        super.onDestroy();
    }
}
