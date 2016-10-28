package com.lwx.likestudy;

import android.app.Application;

/**
 * Created by 36249 on 2016/10/28.
 */
public class LikeStudyApplication extends Application {

    private static LikeStudyApplication sInstance;

    @Override
    public void onCreate(){
        super.onCreate();
        sInstance = this;
    }

    public static LikeStudyApplication getInstance(){

        return sInstance;
    }
}
