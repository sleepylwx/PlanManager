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

import com.lwx.likestudy.R;
import com.lwx.likestudy.utils.FormatTime;
import com.lwx.likestudy.utils.TimeTickerThread;

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
    private static  boolean IS_GO_ON = false;
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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toStoreDialog();
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

                }
                else{


                    IS_GO_ON = false;
                    thread = null;
                    button.setText("开始");

                }
            }
        });

    }

    @Override
    public void onBackPressed(){

        toStoreDialog();
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
        String hour = FormatTime.formatTime(this.hour);
        String minute = FormatTime.formatTime(this.minute);
        String second = FormatTime.formatTime(this.second);
        textView.setText(hour + " : " + minute + " : " + second);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        IS_GO_ON = false;
    }

    public void toStoreDialog(){
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
}
