package com.lwx.likestudy;

import android.app.Application;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 * Created by 36249 on 2016/10/28.
 */
public class LikeStudyApplication extends Application {

    private static LikeStudyApplication sInstance;

    private static boolean speakerOpen = true;
    @Override
    public void onCreate(){
        super.onCreate();
        sInstance = this;

        SpeechUtility.createUtility(this, "appid" +"=581ed01c");
        
    }

    public static LikeStudyApplication getInstance(){

        return sInstance;
    }

    public static boolean isSpeakerOpen() {
        return speakerOpen;
    }

    public static void setSpeakerOpen(boolean speakerOpen) {
        LikeStudyApplication.speakerOpen = speakerOpen;
    }
}
