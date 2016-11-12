package com.lwx.likestudy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.lwx.likestudy.LikeStudyApplication;
import com.lwx.likestudy.R;
import com.lwx.likestudy.contract.UnFinishedPlanContract;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.presenter.UnFinishedPlanPresenter;
import com.lwx.likestudy.utils.Time;
import com.lwx.likestudy.utils.VoiceHelper;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 36249 on 2016/10/29.
 */


public class SetPlanActivity extends AppCompatActivity {

    Toolbar toolbar;

    EditText subjectEditText;
    EditText wayEditText;
    TextView endTimeTextView;
    SimpleRatingBar importanceRatingbar;
    EditText contentEditText;

    UnFinishedStudyPlan unFinishedStudyPlan;
    boolean state = false;
    UnFinishedPlanContract.Presenter mPresenter;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    protected void onCreate(Bundle onSavedInstanceState){

        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.activity_set_plan);
        toolbar = (Toolbar)findViewById(R.id.toolbar_set_plan);
        toolbar.setTitle("添加新的计划");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mPresenter = UnFinishedPlanPresenter.getInstance();
        subjectEditText = (EditText)findViewById(R.id.edittext_subject);
        wayEditText = (EditText)findViewById(R.id.edittext_way);
        endTimeTextView = (TextView) findViewById(R.id.textview_endtimeshow);
        importanceRatingbar = (SimpleRatingBar)findViewById(R.id.simple_ratingbar_importance);
        contentEditText = (EditText)findViewById(R.id.edittext_content);


        Intent intent = getIntent();
        unFinishedStudyPlan = ((UnFinishedStudyPlan)intent.getParcelableExtra("source"));


        if(unFinishedStudyPlan == null){
            state = false;

        }
        else{


            subjectEditText.setText(unFinishedStudyPlan.getSubject());
            wayEditText.setText(unFinishedStudyPlan.getWay());
            endTimeTextView.setText(unFinishedStudyPlan.getEndTime());
            importanceRatingbar.setRating(unFinishedStudyPlan.getImportance());

            contentEditText.setText(unFinishedStudyPlan.getContent());
            state = true;
        }

        long tenYears = 50L * 365 * 1000 * 60 * 60 * 24L;
        final TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {

                        String text = getDateToString(millseconds);
                        endTimeTextView.setText(text);
                    }
                })
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("选择截止日期")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis())
                .setMaxMillseconds(System.currentTimeMillis() + tenYears)
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(getResources().getColor(R.color.color_timepicker_title))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(12)
                .build();

        endTimeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDialogAll.show(getSupportFragmentManager(),"all");
            }
        });
        if(LikeStudyApplication.isSpeakerOpen()){

            VoiceHelper.inSetPlanActivity(this);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_set_plan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        if(id == R.id.menu_add_plan){


            String subject = subjectEditText.getText().toString();
            String way = wayEditText.getText().toString();
            String endTime = endTimeTextView.getText().toString();
            int importance = Integer.valueOf((int)importanceRatingbar.getRating());
            String content = contentEditText.getText().toString();
            if(endTime == ""){
                Toast.makeText(SetPlanActivity.this,"请选择截止日期",Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
            }
            if(state == false){

                unFinishedStudyPlan = new UnFinishedStudyPlan(Time.getCurrentTimeString()
                        ,subject,way,content,endTime,importance);
                mPresenter.createUnFinishedStudyPlan(unFinishedStudyPlan);
            }
            else{
                unFinishedStudyPlan.setSubject(subject);
                unFinishedStudyPlan.setWay(way);
                unFinishedStudyPlan.setEndTime(endTime);
                unFinishedStudyPlan.setImportance(importance);
                unFinishedStudyPlan.setContent(content);
                unFinishedStudyPlan.setCreatedTime(Time.getCurrentTimeString());
                mPresenter.updateUnFinishedStudyPlan(unFinishedStudyPlan);
            }

            LikeStudyApplication.setPlanNum(LikeStudyApplication.getPlanNum()+1);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public String getDateToString(long time) {
        Date d = new Date(time);
        return sf.format(d);
    }
}
