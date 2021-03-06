package com.lwx.likestudy.utils;

import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechSynthesizer;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by 36249 on 2016/11/6.
 */
public class VoiceHelper {

    private static SpeechSynthesizer speechSynthesizer;



    private static String SELECTRECENT = "在最近计划列表中，您将看到按创建时间降序创建的计划卡片，"
            +"长按可以选择、更改或者删除该计划";

    private static String LONGSELECTPLAN = "这里有三个菜单项，其中更新计划，会在更新页面中"
            +"重新填好旧的计划，不用担心重填," + "若选择计划将会进入进行计划界面";

    private static String SELECTEDELETEDUNFINISHEDPLAN = "若选择确定，将永久删除这个计划卡片";
    private static String SELECTEDELETEDRECORD = "若选择确定，将永久删除这个记录卡片";
    private static String SELECTEDELETEALLFINISHEDRECORD = "若选择确定，将永久删除所有已完成的记录卡片";
    private static String SELECTEDELETEALLSTUDYTIME = "若选择确定，将永久删除所有自习记录卡片";
    private static String SELECTEDELETEALLDATA = "若选择确定，将永久删除所有数据";
    private static String INFINISHEDPLANTOPACTIVITY = "在这个页面，您将看到所有按完成时间降序排列的"
            +"已完成的计划卡片,长按可以删除该记录卡片，也可以点击右上角清空所有完成的记录卡片";
    private static String INSELFLEARNITOPACTIVITY = "在这个页面，您将看到所有按自习时间长短降序排序的自习记录卡片"
            +"长按可删除该记录卡片，也可以点击右上角清空所有自习记录卡片";
    private static String SELECTESUBJECT = "在这个页面，您将看到按科目分类的计划抽屉，点击相应科目名称可以"
            +"看到所有该科目抽屉下的计划卡片，长按计划卡片，可以弹出菜单";
    private static String SELECTEWAY = "在这个页面，您将看到按计划方式分类的计划抽屉，点击相应方式名称可以"
            +"看到所有该方式下的计划卡片，长按计划卡片，可以弹出菜单";
    private static String SELECTESTUDY = "在自习模式下，您可以点击下方按钮开始自习，应用会为您统计自习时间,"
            +"完成后还可以评价自习效果";

    private static String SELECTESTUDYSTART="您已点击开始按钮，赶快学习吧";

    private static String SELECTEFLOATINGBUTTON = "这是悬浮按钮，点击第三个按钮，并说出相应关键词，可以语音"
            +"打开相应页面";

    private static String INFINISHPLANACTIVITY = "在进行计划页面，您选择了一个计划卡片，并将完成它"
            +"长按计划卡片可以选择完成或者重新选择";

    private static String INSETPLANACTIVITY = "在这一页面，您可以填写相关信息，并保存为未完成的计划卡片";

    private static String INSTORESTUDYTIMEACTIVITY = "您可以填写完成的满意情况并点击确定保存";

    private static String INRECENTNEEDEDACTIVITY="这是最近需要完成的计划页面，在这里，您将看到最近需要完成的计划列表,"
            + "长按某一个计划卡片可以选择完成，更新或删除这一计划卡片";
    private VoiceHelper(){


    }

    public static void selectRecent(Context context){

        setSpeaker(context);
        speechSynthesizer.startSpeaking(SELECTRECENT,null);
    }


    public static void longSelectPlan(Context context){

        setSpeaker(context);
        speechSynthesizer.startSpeaking(LONGSELECTPLAN,null);
    }

    public static void selectDeleteUnFinishedPlan(Context context){

        setSpeaker(context);
        speechSynthesizer.startSpeaking(SELECTEDELETEDUNFINISHEDPLAN,null);
    }

    public static void selectSubject(Context context){

        setSpeaker(context);
        speechSynthesizer.startSpeaking(SELECTESUBJECT,null);
    }

    public static void selectWay(Context context){

        setSpeaker(context);
        speechSynthesizer.startSpeaking(SELECTEWAY,null);
    }

    public static void selectStudy(Context context){

        setSpeaker(context);
        speechSynthesizer.startSpeaking(SELECTESTUDY,null);
    }

    public static void selectFloatingButton(Context context){

        setSpeaker(context);
        speechSynthesizer.startSpeaking(SELECTEFLOATINGBUTTON,null);
    }

    public static void inFinishPlanAcitivity(Context context){

        setSpeaker(context);
        speechSynthesizer.startSpeaking(INFINISHPLANACTIVITY,null);
    }

    public static void selectStudyStart(Context context){

        setSpeaker(context);
        speechSynthesizer.startSpeaking(SELECTESTUDYSTART,null);
    }

    public static void inSetPlanActivity(Context context){

        setSpeaker(context);
        speechSynthesizer.startSpeaking(INSETPLANACTIVITY,null);
    }

    public static void inFinishedPlanTopActivity(Context context){

        setSpeaker(context);
        speechSynthesizer.startSpeaking(INFINISHEDPLANTOPACTIVITY,null);
    }

    public static void selectDeleteAllFinishedPlan(Context context){

        setSpeaker(context);
        speechSynthesizer.startSpeaking(SELECTEDELETEALLFINISHEDRECORD,null);
    }

    public static void selectDeleteRecord(Context context){

        setSpeaker(context);
        speechSynthesizer.startSpeaking(SELECTEDELETEDRECORD,null);
    }

    public static void inStoreStudyTimeActivity(Context context){

        setSpeaker(context);
        speechSynthesizer.startSpeaking(INSTORESTUDYTIMEACTIVITY,null);
    }

    public static void inSelfLearningTopActivity(Context context){

        setSpeaker(context);
        speechSynthesizer.startSpeaking(INSELFLEARNITOPACTIVITY,null);
    }

    public static void selectDeleteStudyTime(Context context){

        setSpeaker(context);
        speechSynthesizer.startSpeaking(SELECTEDELETEALLSTUDYTIME,null);
    }

    public static void inRecentNeededPlanActivity(Context context){

        setSpeaker(context);
        speechSynthesizer.startSpeaking(INRECENTNEEDEDACTIVITY,null);
    }
    public static void onVoiceHelperToggleSelected(Context context){

        setSpeaker(context);
        speechSynthesizer.startSpeaking(GreetingVoice.getRecentPlanString(),null);
    }
    private static void setSpeaker(Context context){

        speechSynthesizer = SpeechSynthesizer.createSynthesizer(context,null);
        speechSynthesizer.setParameter(SpeechConstant.VOICE_NAME,"yefang");
        speechSynthesizer.setParameter(SpeechConstant.SPEED,"50");
        speechSynthesizer.setParameter(SpeechConstant.VOLUME,"80");
        speechSynthesizer.setParameter(SpeechConstant.ENGINE_TYPE,SpeechConstant.TYPE_CLOUD);

    }




}
