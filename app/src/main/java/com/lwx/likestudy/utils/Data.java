package com.lwx.likestudy.utils;

import com.lwx.likestudy.data.model.FinishedStudyPlan;
import com.lwx.likestudy.data.model.StudyTime;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.data.source.db.LiteOrmHelper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 36249 on 2016/11/4.
 */
public class Data {

    public static List<UnFinishedStudyPlan> getUnFinishedPlanDatas(){

        return LiteOrmHelper.getsInstance().query(UnFinishedStudyPlan.class);
    }

    public static List<FinishedStudyPlan> getFinishedPlanDatas(){

        return LiteOrmHelper.getsInstance().query(FinishedStudyPlan.class);
    }

    public static List<StudyTime> getStudyTimeDatas(){

        return LiteOrmHelper.getsInstance().query(StudyTime.class);
    }

    public static  List<UnFinishedStudyPlan> getDatasInOrderOfCreatedTime(){

        List<UnFinishedStudyPlan> unFinishedStudyPlans = getUnFinishedPlanDatas();
        Collections.sort(unFinishedStudyPlans, new Comparator<UnFinishedStudyPlan>() {
            @Override
            public int compare(UnFinishedStudyPlan studyTime, UnFinishedStudyPlan t1) {

                int temp = studyTime.getCreatedTime().compareTo(t1.getCreatedTime());
                if(temp > 0){

                    return -1;
                }
                else if(temp == 0){

                    return 0;
                }
                else{
                    return  1;
                }

            }
        });
        for(int i = 0; i < unFinishedStudyPlans.size(); ++i){

            unFinishedStudyPlans.get(i).setIndex(i+1);
        }
        return unFinishedStudyPlans;
    }
}
