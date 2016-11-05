package com.lwx.likestudy;

import android.app.Application;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 * Created by 36249 on 2016/10/28.
 */
public class LikeStudyApplication extends Application {

    private static LikeStudyApplication sInstance;

    @Override
    public void onCreate(){
        super.onCreate();
        sInstance = this;

        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5806f8be");
    }

    public static LikeStudyApplication getInstance(){

        return sInstance;
    }
}
