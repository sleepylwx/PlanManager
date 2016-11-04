package com.lwx.likestudy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lwx.likestudy.R;
import com.lwx.likestudy.contract.PlanContract;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.presenter.MainPresenter;
import com.lwx.likestudy.utils.Time;

/**
 * Created by 36249 on 2016/10/29.
 */
public class SetPlanActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button addContentbutton;
    EditText subjectEditText;
    EditText wayEditText;
    EditText endTimeEditText;
    EditText importanceEditText;
    EditText contentEditText;

    UnFinishedStudyPlan unFinishedStudyPlan;
    boolean state = false;
    PlanContract.Presenter mPresenter;
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

        mPresenter = MainPresenter.getInstance();
        subjectEditText = (EditText)findViewById(R.id.edittext_subject);
        wayEditText = (EditText)findViewById(R.id.edittext_way);
        endTimeEditText = (EditText)findViewById(R.id.edittext_endtime);
        importanceEditText = (EditText)findViewById(R.id.edittext_importance);
        contentEditText = (EditText)findViewById(R.id.edittext_content);

        addContentbutton = (Button)findViewById(R.id.button_add_plan);
        Intent intent = getIntent();
        unFinishedStudyPlan = ((UnFinishedStudyPlan)intent.getParcelableExtra("source"));


        if(unFinishedStudyPlan == null){
            state = false;

        }
        else{


            subjectEditText.setText(unFinishedStudyPlan.getSubject());
            wayEditText.setText(unFinishedStudyPlan.getWay());
            endTimeEditText.setText(unFinishedStudyPlan.getEndTime());
            importanceEditText.setText(unFinishedStudyPlan.getImportance()+"");
            contentEditText.setText(unFinishedStudyPlan.getContent());
            state = true;
        }


        addContentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String subject = subjectEditText.getText().toString();
                String way = wayEditText.getText().toString();
                String endTime = endTimeEditText.getText().toString();
                int importance = Integer.valueOf(importanceEditText.getText().toString());
                String content = contentEditText.getText().toString();
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

                finish();
            }
        });

    }
}
