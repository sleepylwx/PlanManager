package com.lwx.likestudy.utils;

import android.util.Log;

import com.lwx.likestudy.data.model.FinishedStudyPlan;
import com.lwx.likestudy.data.model.StudyTime;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.data.source.db.LiteOrmHelper;

import java.util.ArrayList;
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

                return -studyTime.getCreatedTime().compareTo(t1.getCreatedTime());

            }
        });

        for(int i = 0; i < unFinishedStudyPlans.size(); ++i){

            unFinishedStudyPlans.get(i).setIndex(i+1);
        }

        return unFinishedStudyPlans;
    }



    public static Pair<UnFinishedStudyPlan>  getDatasInOrderOfSubject(){

        List<UnFinishedStudyPlan> temp = getUnFinishedPlanDatas();

        Collections.sort(temp, new Comparator<UnFinishedStudyPlan>() {
            @Override
            public int compare(UnFinishedStudyPlan o1, UnFinishedStudyPlan o2) {

                return o1.getSubject().compareTo(o2.getSubject());

            }
        });
        List<String> name = new ArrayList<>();
        List<List<UnFinishedStudyPlan>> datas = new ArrayList<>();
        List<UnFinishedStudyPlan> child = new ArrayList<>();
        int num = 0;
        for(int i = 1; i < temp.size(); ++i){

            temp.get(i-1).setIndex(++num);
            child.add(temp.get(i-1));




            if(!temp.get(i).getSubject().equals(temp.get(i-1).getSubject())){

                name.add(temp.get(i-1).getSubject());
                datas.add(child);
                Log.e("abc",child.size()+"");
                child = new ArrayList<>();
                num = 0;
            }

            if(i == (temp.size() -1)){

                temp.get(i).setIndex(++num);
                child.add(temp.get(i));
                name.add(temp.get(i).getSubject());
                datas.add(child);
                Log.e("qwe",child.size()+"");

            }
        }

        Pair<UnFinishedStudyPlan> pair = new Pair<>(name,datas);
        return pair;
    }
}
