package com.lwx.likestudy.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.lwx.likestudy.service.NotificationService;

/**
 * Created by 36249 on 2016/11/12.
 */
public class DaemonNotifyServiceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context ,Intent intent){

        Intent intent1 = new Intent(context, NotificationService.class);
        intent1.putExtra("isnotified",true);
        context.startService(intent1);
    }
}
