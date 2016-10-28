package com.lwx.likestudy.data.db;

import com.litesuits.orm.LiteOrm;
import com.lwx.likestudy.BuildConfig;
import com.lwx.likestudy.LikeStudyApplication;

/**
 * Created by 36249 on 2016/10/28.
 */
public class LiteOrmHelper {

    private static final String DATABASENAME = "likestudy.db";
    private static volatile LiteOrm sInstance;

    private LiteOrmHelper(){

    }

    public static LiteOrm getsInstance(){

        if(sInstance == null){

            synchronized (LiteOrmHelper.class){

                if(sInstance == null){

                    sInstance = LiteOrm.newCascadeInstance(LikeStudyApplication.getInstance(),DATABASENAME);
                    sInstance.setDebugged(BuildConfig.DEBUG);
                }
            }

            return sInstance;
        }

        return sInstance;
    }
}
