package com.lwx.likestudy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lwx.likestudy.LikeStudyApplication;
import com.lwx.likestudy.R;
import com.lwx.likestudy.utils.Time;
import com.lwx.likestudy.utils.TimeTickerThread;
import com.lwx.likestudy.utils.VoiceHelper;

/**
 * Created by 36249 on 2016/10/27.
 */
public class SelfLearingActivity extends AppCompatActivity {


    TextView textView;
    Toolbar toolbar;
    Button button;
    int hour = 0;
    int minute = 0;
    int second = 0;
    TimeTickerThread thread;
    String sumTime;

    private static  boolean IS_GO_ON = false;
    int num;
    int startTimes = 0;
    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message message){

            printClock();
        }
    };
    @Override
    protected void onCreate(Bundle onSavedInstanceState){

        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.activity_self_learing);
        textView = (TextView)findViewById(R.id.textview_timeticker);
        toolbar = (Toolbar)findViewById(R.id.toolbar_self_learning);
        button = (Button)findViewById(R.id.button_self_learing);
        toolbar.setTitle("自习模式");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        num = getIntent().getIntExtra("index",0);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                exit();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(IS_GO_ON == false){

                    IS_GO_ON = true;
                    thread = new TimeTickerThread(handler);
                    new Thread(thread).start();
                    button.setText("暂停");
                    if(startTimes == 0){

                        if(LikeStudyApplication.isSpeakerOpen()){

                            VoiceHelper.selectStudyStart(SelfLearingActivity.this);
                        }
                        ++startTimes;
                    }
                }
                else{


                    IS_GO_ON = false;
                    thread = null;
                    button.setText("开始");

                }
            }
        });
        if(LikeStudyApplication.isSpeakerOpen()){

            VoiceHelper.selectStudy(this);
        }
    }

    @Override
    public void onBackPressed(){

        exit();
    }
    public static boolean isGoOn(){

        return IS_GO_ON;
    }

    private void printClock(){

        second += 1;
        if(second  >= 60){
            minute += 1;
            second = 0;
        }
        if(minute >= 60){
            hour += 1;
            minute = 0;
        }
        if(hour >= 24){
            hour = 0;
        }

        sumTime = Time.formatTimeWithSpace(this.hour,this.minute,this.second);

        textView.setText(sumTime);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        IS_GO_ON = false;
    }

    private void toStoreDialog(){
        Log.e("find",String.valueOf(hour+ "."+minute +"."+ second));
        if(hour == 0 && minute == 0 && second == 0){
            finish();
            return;
        }
        Intent intent = new Intent(this,StoreStudyTimeActivity.class);
        intent.putExtra("hour",hour);
        intent.putExtra("minute",minute);
        intent.putExtra("second",second);
        startActivity(intent);
        finish();
    }

    public void exit() {

        if (num == 0) {

            toStoreDialog();
        } else if (num == 1) {


            Intent intent = new Intent();
            intent.putExtra("duratetime",sumTime);
            setResult(RESULT_OK,intent);
            finish();
        }
    }


}
