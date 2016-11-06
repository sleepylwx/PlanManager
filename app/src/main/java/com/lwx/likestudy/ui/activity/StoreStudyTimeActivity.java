package com.lwx.likestudy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lwx.likestudy.LikeStudyApplication;
import com.lwx.likestudy.R;
import com.lwx.likestudy.data.model.StudyTime;
import com.lwx.likestudy.data.source.db.LiteOrmHelper;
import com.lwx.likestudy.utils.Time;
import com.lwx.likestudy.utils.VoiceHelper;

/**
 * Created by 36249 on 2016/11/1.
 */
public class StoreStudyTimeActivity extends AppCompatActivity {

    EditText editText;
    Button addButton;
    Button cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_store_study_time);

        editText = (EditText)findViewById(R.id.edittext_edit_saticfaction);
        addButton = (Button) findViewById(R.id.button_add_saticfaction);
        cancelButton = (Button)findViewById(R.id.button_cancel_add_saticfaction);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                int hour = intent.getIntExtra("hour",-1);
                int minute = intent.getIntExtra("minute",-1);
                int second = intent.getIntExtra("second",-1);
                Log.e("StoreStudyTimeActivity",String.valueOf(hour+minute+second));
                if(hour == -1 || minute == -1 || second == -1){
                    Toast.makeText(StoreStudyTimeActivity.this,"存储数据失败",Toast.LENGTH_SHORT).show();
                    finish();
                }

                String satisfaction = editText.getText().toString();
                String durateTime = Time.formatTime(hour,minute,second);


                String createTime = Time.getCurrentTimeString();
                long result = LiteOrmHelper.getsInstance().save(new StudyTime(durateTime,createTime,satisfaction));
                if(result <= 0){
                    Toast.makeText(StoreStudyTimeActivity.this,"存储数据失败",Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        if(LikeStudyApplication.isSpeakerOpen()){

            VoiceHelper.inStoreStudyTimeActivity(this);
        }
    }
}
