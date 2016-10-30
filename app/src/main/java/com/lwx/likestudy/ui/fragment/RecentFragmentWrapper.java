package com.lwx.likestudy.ui.fragment;

import com.lwx.likestudy.ui.fragment.RecentFragment;

/**
 * Created by 36249 on 2016/10/30.
 */
public class RecentFragmentWrapper {

    public static volatile RecentFragment sInstance;

    public static RecentFragment getInstance(){

        return sInstance;
    }
}
