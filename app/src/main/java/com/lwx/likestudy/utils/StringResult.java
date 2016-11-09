package com.lwx.likestudy.utils;

import com.lwx.likestudy.data.model.UnFinishedStudyPlan;

import java.util.List;

/**
 * Created by 36249 on 2016/11/5.
 */
public class StringResult {


    private static String[] greetStrings = {"好久不见，你还好吗？",
                                            "我们又见面啦"};
    public static String getRecentPlanString(){

        List<UnFinishedStudyPlan> datas =  Data.getDatasInOrderOfEndTimeWithNoIndex();
        int num = 1;
        for(int i = 0; i < datas.size() - 1;++i){

            if(datas.get(i).getEndTime().equals(datas.get(i+1).getEndTime())){

                ++num;
                continue;
            }
            break;
        }
        String curTime = Time.getCurrentTimeString();
        String curYear = curTime.substring(0,4);
        String datasYear = datas.get(0).getEndTime().substring(0,4);
        String monthAndDay = datas.get(0).getEndTime().substring(5,10);
        String string;
        if(curYear.equals(datasYear)){

            string = monthAndDay;
        }
        else{


            string = datasYear + "年" + monthAndDay;
        }


        return "您在" + string + "有" + num + "个计划需要完成,赶快去完成吧";
    }

    public static String getGreetString(){

            return null;
    }
}
