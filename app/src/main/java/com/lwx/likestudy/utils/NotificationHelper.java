package com.lwx.likestudy.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import com.lwx.likestudy.LikeStudyApplication;
import com.lwx.likestudy.R;

/**
 * Created by 36249 on 2016/11/9.
 */
public class NotificationHelper {

    private static NotificationManager notificationManager = (NotificationManager)
            LikeStudyApplication.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);

    private static Notification notification;
    private static int index = 1;
    public static void createNotification(){

        notification = new Notification(R.mipmap.ic_launcher,"有未完成的计划喔",System.currentTimeMillis());
        notificationManager.notify(index++,notification);
    }
}
