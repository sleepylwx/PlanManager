package com.lwx.likestudy.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.lwx.likestudy.LikeStudyApplication;
import com.lwx.likestudy.R;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.ui.activity.RecentNeededPlanActivity;

import java.util.List;

/**
 * Created by 36249 on 2016/11/9.
 */
public class NotificationHelper {

    private static int notifyID = 1;

    public static int getNotifyID() {
        return notifyID;
    }

    public static void setNotifyID(int notifyID) {
        NotificationHelper.notifyID = notifyID;
    }

    public static void createNotification(Context context){

        String curTime = Time.getYearMonthAndDay(Time.getCurrentTimeString());
        List<UnFinishedStudyPlan> list = Data.getDatasInOrderOfEndTime();
        if(list == null){
            return;
        }
        if(list.size() == 0){
            return;
        }
        String planTime = Time.getYearMonthAndDay(list.get(0).getEndTime());

        if(curTime.compareTo(planTime) < 0 ){
            return;
        }

        //int num = Data.getTodayNeededPlanNum(list);
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(
                Context.NOTIFICATION_SERVICE
        );

        Intent intent = new Intent(context, RecentNeededPlanActivity.class);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("未完成的计划")
                .setContentText("您今天有未完成的计划")
                .setTicker("")
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setDefaults( Notification.DEFAULT_SOUND
                            |Notification.DEFAULT_LIGHTS)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT))
        ;


        notificationManager.notify(notifyID,builder.build());
    }
}
