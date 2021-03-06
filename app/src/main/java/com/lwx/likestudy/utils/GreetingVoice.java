package com.lwx.likestudy.utils;

import com.lwx.likestudy.data.model.UnFinishedStudyPlan;

import java.util.List;

/**
 * Created by 36249 on 2016/11/5.
 */
public class GreetingVoice {


    private static String[] greetStrings = {"好久不见，最近好吗？",
                                            "我们又见面啦",};
    public static String getRecentPlanString(){

        List<UnFinishedStudyPlan> datas =  Data.getDatasInOrderOfEndTimeWithNoIndex();
        int num = datas.size() > 0 ? 1 : 0;
        for(int i = 0; i < datas.size() - 1;++i){

            if(datas.get(i).getEndTime().equals(datas.get(i+1).getEndTime())){

                ++num;
                continue;
            }
            break;
        }
        if(num == 0){

            return "您最近没有添加计划，快去添加一个计划吧";
        }
        String curTime = Time.getCurrentTimeString();
        String curYear = curTime.substring(0,4);
        StringBuffer stringBuffer = new StringBuffer();
        String data = datas.get(0).getEndTime();
        for(int i = 5 ; i < 7; ++i){

            stringBuffer.append(data.charAt(i));
        }
        String month = stringBuffer.toString();
        stringBuffer = new StringBuffer();
        for(int i = 8; i < 10; ++i){
            stringBuffer.append(data.charAt(i));
        }
        String day = stringBuffer.toString();
        String datasYear = datas.get(0).getEndTime().substring(0,4);

        String string;
        if(curYear.equals(datasYear)){

            string = month + "月" + day + "日";
        }
        else{


            string = datasYear + "年" + month + "月" + day + "日";
        }


        return "您在" + string + "有" + num + "个计划需要完成,赶快去完成吧";
    }

    public static String getGreetString(){

            return null;
    }


}
