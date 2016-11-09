package com.lwx.likestudy.utils;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.lwx.likestudy.ui.activity.FinishPlanActivity;
import com.lwx.likestudy.ui.activity.FinishedPlanTopActivity;
import com.lwx.likestudy.ui.activity.SelfLearingActivity;
import com.lwx.likestudy.ui.activity.SelfLearningTopActivity;
import com.lwx.likestudy.ui.activity.SetPlanActivity;

/**
 * Created by 36249 on 2016/11/6.
 */
public class VoiceRecognizer {


    private static RecognizerDialog recognizerDialog;


    private static final String STUDY="自习";
    private static final String STUDYRECOD="自习记录";
    private static final String FINISHPLAN="完成计划";
    private static final String FINISHPLAN1="完成一个计划";
    private static final String FINISHEDPLAN="完成的计划";
    private static final String FINISHEDRECORD="完成记录";
    private static final String FINISHEDRECORD1="完成的记录";
    private static final String ADDPLAN="添加计划";
    private static final String ADDPLAN1="添加一个计划";
    private static final String ADDPLAN2 = "制定计划";
    private static final String ADDPLAN3="制定一个计划";

    public static void getVoice(Context context){

        setRecognizer(context);
    }

    private static void setRecognizer(final Context context){


        recognizerDialog = new RecognizerDialog(context, null);

        recognizerDialog.setParameter(SpeechConstant.DOMAIN,"iat");
        recognizerDialog.setParameter(SpeechConstant.LANGUAGE,"zh_cn");
        recognizerDialog.setParameter(SpeechConstant.ACCENT,"mandarin");


        recognizerDialog.setListener(new RecognizerDialogListener() {
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {

                String string = getResult(recognizerResult.getResultString());

                parseResult(string,context);
            }

            @Override
            public void onError(SpeechError speechError) {

            }
        });
        recognizerDialog.show();
    }


    private static String getResult(String string){

        StringBuffer stringBuffer = new StringBuffer();
        int index = 0;
        int index1 = 0;
        for(int i = 0; i < string.length(); ++i){

            index = string.indexOf("\"w\"",i);
            index1 = string.indexOf("{",index);

            for(int j = index + 5; j < string.length() ; ++j){
                if(string.charAt(j) == '\"')
                    break;
                stringBuffer.append(string.charAt(j));
            }

            i = index1;

            if(i == -1){
                break;
            }
        }

        return stringBuffer.toString();
    }

    public static void parseResult(String string,Context context){


        if(string.indexOf(STUDYRECOD ) >= 1){

            Intent intent = new Intent(context, SelfLearningTopActivity.class);
            context.startActivity(intent);
        }
        else if(string.indexOf(STUDY) >= 0 && !string.equals(STUDYRECOD)){

            Intent intent = new Intent(context,SelfLearingActivity.class);
            context.startActivity(intent);
        }
        else if(string.indexOf(FINISHPLAN) >= 0 || string.indexOf(FINISHPLAN1) >= 0){

            Intent intent = new Intent(context, FinishPlanActivity.class);
            context.startActivity(intent);
        }
        else if(string.indexOf(FINISHEDPLAN) >= 1 || string.indexOf(FINISHEDRECORD) >= 1
                || string.indexOf(FINISHEDRECORD1) >= 1){

            Intent intent = new Intent(context, FinishedPlanTopActivity.class);
            context.startActivity(intent);
        }
        else if(string.indexOf(ADDPLAN) >= 0 || string.indexOf(ADDPLAN1) >= 0
                ||string.indexOf(ADDPLAN2) >= 0 || string.indexOf(ADDPLAN3) >= 0){

            Intent intent = new Intent(context, SetPlanActivity.class);
            context.startActivity(intent);
        }
    }
}
