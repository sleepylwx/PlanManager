package com.lwx.likestudy.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.lwx.likestudy.LikeStudyApplication;
import com.lwx.likestudy.R;
import com.lwx.likestudy.utils.Data;
import com.lwx.likestudy.utils.PreferenceHelper;
import com.lwx.likestudy.utils.VoiceHelper;
import com.zcw.togglebutton.ToggleButton;

/**
 * Created by 36249 on 2016/11/6.
 */
public class SettingActivity extends AppCompatActivity {

    Toolbar toolbar;
    //ToggleButton toggleButton;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        toolbar = (Toolbar)findViewById(R.id.toolbar_setting);
        //toggleButton = (ToggleButton)findViewById(R.id.togglebutton_setting_voice);
        button = (Button)findViewById(R.id.button_activity_setting_clear_data);
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

//        toggleButton.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
//            @Override
//            public void onToggle(boolean on) {
//
//                if(on){
//
//                    LikeStudyApplication.setSpeakerOpen(true);
//                    VoiceHelper.onVoiceHelperToggleSelected(SettingActivity.this);
//                    PreferenceHelper.storeSpeakerOpen();
//                }
//                else{
//                    LikeStudyApplication.setSpeakerOpen(false);
//                    PreferenceHelper.storeSpeakerOpen();
//                }
//            }
//        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(SettingActivity.this);
                dialog.setTitle("清空所有数据");
                dialog.setMessage("确定清空所有数据");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Data.deleteAllData();
                        LikeStudyApplication.setPlanNum(0);
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
//        if(LikeStudyApplication.isSpeakerOpen()){
//
//            toggleButton.setToggleOn();
//        }
//        else{
//
//            toggleButton.setToggleOff();
//        }


    }
}
