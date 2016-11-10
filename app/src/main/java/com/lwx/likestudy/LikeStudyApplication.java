package com.lwx.likestudy;

import android.app.Application;

import com.iflytek.cloud.SpeechUtility;
import com.lwx.likestudy.utils.PreferenceHelper;

/**
 * Created by 36249 on 2016/10/28.
 */
public class LikeStudyApplication extends Application {

    private static LikeStudyApplication sInstance;

    private static boolean speakerOpen;

    private static int planNum;
    @Override
    public void onCreate(){
        super.onCreate();
        sInstance = this;
        SpeechUtility.createUtility(this, "appid" +"=581ed01c");
        speakerOpen = PreferenceHelper.getSpeakerOpen();
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

    public static int getPlanNum() {
        return planNum;
    }

    public static void setPlanNum(int planNum) {
        LikeStudyApplication.planNum = planNum;
    }
}
