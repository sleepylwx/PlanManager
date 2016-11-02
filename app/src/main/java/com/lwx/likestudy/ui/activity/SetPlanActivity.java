package com.lwx.likestudy.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lwx.likestudy.R;
import com.lwx.likestudy.contract.PlanContract;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.presenter.MainPresenter;

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
        addContentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String subject = subjectEditText.getText().toString();
                String way = wayEditText.getText().toString();
                long endTime = 1024;
                int importance = Integer.valueOf(importanceEditText.getText().toString());
                String content = contentEditText.getText().toString();
//                LiteOrmHelper.getsInstance().save(new UnFinishedStudyPlan(0,subject,way,
//                        content,endTime,importance));
//                DataRepository.getsIntance().create(new UnFinishedStudyPlan(0,subject,way,
//                        content,endTime,importance));
                mPresenter.createUnFinishedStudyPlan(new UnFinishedStudyPlan(0,subject,way,content,endTime,importance));
                finish();
            }
        });

    }
}
