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

        if(temp.size() == 0){

            return new Pair<UnFinishedStudyPlan>();
        }
        if(temp.size() == 1){

            List<List<UnFinishedStudyPlan>> single = new ArrayList<>();
            single.add(temp);
            List<String> title = new ArrayList<>();
            temp.get(0).setIndex(1);
            title.add(temp.get(0).getSubject());
            return new Pair<UnFinishedStudyPlan>(title,single);
        }

        for(int i = 1; i < temp.size(); ++i){

            temp.get(i-1).setIndex(++num);
            child.add(temp.get(i-1));




            if(!temp.get(i).getSubject().equals(temp.get(i-1).getSubject())){

                name.add(temp.get(i-1).getSubject());
                datas.add(child);
                Log.e("data",child.size()+"");
                child = new ArrayList<>();
                num = 0;
            }

            if(i == (temp.size() -1)){

                temp.get(i).setIndex(++num);
                child.add(temp.get(i));
                name.add(temp.get(i).getSubject());
                datas.add(child);
                Log.e("datainordsubject",child.size()+"");

            }
        }

        Pair<UnFinishedStudyPlan> pair = new Pair<>(name,datas);
        return pair;
    }



    public static Pair<UnFinishedStudyPlan> getDatasInOrderOfWay(){


        List<UnFinishedStudyPlan> temp = getUnFinishedPlanDatas();
        Collections.sort(temp, new Comparator<UnFinishedStudyPlan>() {
            @Override
            public int compare(UnFinishedStudyPlan o1, UnFinishedStudyPlan o2) {

                return o1.getWay().compareTo(o2.getWay());
            }
        });

        List<String> name = new ArrayList<>();
        List<List<UnFinishedStudyPlan>> datas = new ArrayList<>();
        List<UnFinishedStudyPlan> child = new ArrayList<>();
        int num = 0;

        if(temp.size() == 0){
            return new Pair<UnFinishedStudyPlan>();
        }
        if(temp.size() == 1){

            List<List<UnFinishedStudyPlan>> single = new ArrayList<>();
            single.add(temp);
            List<String> title = new ArrayList<>();
            temp.get(0).setIndex(1);
            title.add(temp.get(0).getWay());
            return new Pair<UnFinishedStudyPlan>(title,single);
        }
        for(int i = 1; i < temp.size(); ++i){


            temp.get(i-1).setIndex(++num);
            child.add(temp.get(i-1));

            if(!temp.get(i).getWay().equals(temp.get(i-1).getWay())){

                name.add(temp.get(i-1).getWay());
                datas.add(child);
                child = new ArrayList<>();
                num = 0;
            }

            if(i == temp.size() - 1){

                temp.get(i).setIndex(++num);
                child.add(temp.get(i));
                name.add(temp.get(i).getWay());
                datas.add(child);
            }
        }

        Pair<UnFinishedStudyPlan> pair = new Pair<>(name,datas);
        return pair;
    }

    public static List<UnFinishedStudyPlan> getDatasInOrderOfEndTimeWithNoIndex(){

        List<UnFinishedStudyPlan> datas = getUnFinishedPlanDatas();

        Collections.sort(datas, new Comparator<UnFinishedStudyPlan>() {
            @Override
            public int compare(UnFinishedStudyPlan unFinishedStudyPlan, UnFinishedStudyPlan t1) {

                return unFinishedStudyPlan.getEndTime().compareTo(t1.getEndTime());
            }
        });

        return datas;
    }


    public static List<StudyTime> getStudyTimeInOrderOfDurateTime(){

        List<StudyTime> datas = getStudyTimeDatas();
        Collections.sort(datas, new Comparator<StudyTime>() {
            @Override
            public int compare(StudyTime studyTime, StudyTime t1) {

                return -studyTime.getDurateTime().compareTo(t1.getDurateTime());
            }
        });

        for(int i = 0 ; i < datas.size(); ++i){

            datas.get(i).setIndex(i+1);
        }
        return datas;
    }

    public static List<FinishedStudyPlan> getFinishedPlanInOrderOfFinshedTime(){


        List<FinishedStudyPlan> datas = getFinishedPlanDatas();
        Collections.sort(datas, new Comparator<FinishedStudyPlan>() {
            @Override
            public int compare(FinishedStudyPlan finishedStudyPlan, FinishedStudyPlan t1) {

                return -finishedStudyPlan.getFinishedTime().compareTo(t1.getFinishedTime());
            }
        });

        for(int i = 0; i < datas.size(); ++i){

            datas.get(i).setIndex(i+1);
        }

        return datas;
    }

    public static long deleteFinishedPlan(FinishedStudyPlan finishedStudyPlan){

        return LiteOrmHelper.getsInstance().delete(finishedStudyPlan);
    }

    public static long deleteStudyTime(StudyTime studyTime){

        return LiteOrmHelper.getsInstance().delete(studyTime);
    }

    public static long deleteAllStudyTime(){

        return LiteOrmHelper.getsInstance().delete(StudyTime.class);
    }
    public static long deleteAllFinishedPlan(){

        return LiteOrmHelper.getsInstance().delete(FinishedStudyPlan.class);
    }

    public static long deleteAllUnFinishedPlan(){

        return 0;
    }
}
