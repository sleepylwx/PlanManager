package com.lwx.likestudy.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lwx.likestudy.R;
import com.lwx.likestudy.utils.TimeTickerThread;

/**
 * Created by 36249 on 2016/10/27.
 */
public class SelfLearingActivity extends AppCompatActivity {


    TextView textView;
    Toolbar toolbar;
    Button button;
    boolean isStart = false;
    long time = 0;
    TimeTickerThread thread;
    private static  boolean IS_GO_ON = true;
    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message message){

            textView.setText((String)message.obj);
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
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isStart == false){

                    IS_GO_ON = true;
                    thread = new TimeTickerThread(handler,time);
                    new Thread(thread).start();
                    button.setText("暂停");
                    isStart = true;
                }
                else{
                    IS_GO_ON = false;
                    time = thread.getStartTime();
                    thread = null;
                    button.setText("开始");
                    isStart = false;
                }
            }
        });

    }

    public static boolean isGoOn(){

        return IS_GO_ON;
    }
}
