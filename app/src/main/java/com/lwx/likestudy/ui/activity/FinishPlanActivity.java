package com.lwx.likestudy.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lwx.likestudy.R;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.data.source.db.LiteOrmHelper;

import java.util.List;
import java.util.Random;

/**
 * Created by 36249 on 2016/11/1.
 */
public class FinishPlanActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView textView;
    Button confirmButton;
    Button nextIndexButton;
    int num = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_plan);
        toolbar = (Toolbar)findViewById(R.id.toolbar_finish_plan);
        textView = (TextView)findViewById(R.id.textview_selected_plan);
        confirmButton = (Button)findViewById(R.id.button_confirm_continue);
        nextIndexButton = (Button)findViewById(R.id.button_next_index);
        toolbar.setTitle("进行计划");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        getPlan();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        nextIndexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getPlan();
            }
        });
    }

    public void getPlan(){

        List<UnFinishedStudyPlan> datas =
                LiteOrmHelper.getsInstance().query(UnFinishedStudyPlan.class);

        Random random = new Random();
        int randomNum;
        while((randomNum = random.nextInt(datas.size())) == num){

        }
        num = randomNum;

        UnFinishedStudyPlan unFinishedStudyPlan = datas.get(randomNum);
        textView.setText("\n\n"
                + "科目： " + unFinishedStudyPlan.getSubject() + ".\n" + "方式： "+unFinishedStudyPlan.getWay()
                +".\n" + "重要性： " + unFinishedStudyPlan.getImportance() + ".\n"
                + "截止时间： "+ unFinishedStudyPlan.getEndTime() + ".\n" + "创建时间： " +unFinishedStudyPlan.getCreatedTime()
                + "." + "\n" + "内容： " + unFinishedStudyPlan.getContent() +".");
    }
}
