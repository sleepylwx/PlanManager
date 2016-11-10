package com.lwx.likestudy.utils;

import android.content.SharedPreferences;

import com.lwx.likestudy.LikeStudyApplication;

/**
 * Created by 36249 on 2016/11/9.
 */
public class PreferenceHelper {

    public static void storeSpeakerOpen(){

        SharedPreferences preferences = android.preference.PreferenceManager.getDefaultSharedPreferences(LikeStudyApplication.getInstance());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("speakerOpen", LikeStudyApplication.isSpeakerOpen());
        editor.commit();
    }

    public static boolean getSpeakerOpen(){

        SharedPreferences preferences = android.preference.PreferenceManager.getDefaultSharedPreferences(LikeStudyApplication.getInstance());
        return preferences.getBoolean("speakerOpen",false);
    }
}
