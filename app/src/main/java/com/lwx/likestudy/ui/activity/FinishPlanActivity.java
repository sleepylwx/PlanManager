package com.lwx.likestudy.ui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lwx.likestudy.LikeStudyApplication;
import com.lwx.likestudy.R;
import com.lwx.likestudy.data.model.FinishedStudyPlan;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.data.source.db.LiteOrmHelper;
import com.lwx.likestudy.presenter.MainPresenter;
import com.lwx.likestudy.utils.Time;

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
    UnFinishedStudyPlan unFinishedStudyPlan;
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

                Intent intent = new Intent(FinishPlanActivity.this,SelfLearingActivity.class);
                intent.putExtra("index",1);
                startActivityForResult(intent,1);
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
        if(datas.size() == 0){


            Toast.makeText(LikeStudyApplication.getInstance(),"没有未完成的记录",Toast.LENGTH_SHORT).show();

            finish();
            return;
        }
        Random random = new Random();
        int randomNum;
        while((randomNum = random.nextInt(datas.size())) == num){

        }
        num = randomNum;

        unFinishedStudyPlan = datas.get(randomNum);
        textView.setText("\n\n"
                + "科目： " + unFinishedStudyPlan.getSubject() + ".\n" + "方式： "+unFinishedStudyPlan.getWay()
                +".\n" + "重要性： " + unFinishedStudyPlan.getImportance() + ".\n"
                + "截止时间： "+ unFinishedStudyPlan.getEndTime() + ".\n" + "创建时间： " +unFinishedStudyPlan.getCreatedTime()
                + "." + "\n" + "内容： " + unFinishedStudyPlan.getContent() +".");
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){

        if(requestCode == 1 ){

            if(resultCode == RESULT_OK){

                final String durateTime = data.getStringExtra("duratetime");
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("保存记录");
                dialog.setMessage("是否将这一计划保存为已完成计划？");
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FinishedStudyPlan finishedStudyPlan =
                                new FinishedStudyPlan(durateTime,Time.getCurrentTimeString()
                                ,5,unFinishedStudyPlan);
                        long res = LiteOrmHelper.getsInstance().save(finishedStudyPlan);
                        if(res <= 0){
                            Toast.makeText(FinishPlanActivity.this,"存储数据失败",Toast.LENGTH_SHORT).show();
                        }
                        if(res > 0){

                            MainPresenter.getInstance().deleteUnFinishedStudyPlan(unFinishedStudyPlan);
                        }
                        finish();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                dialog.show();

            }
        }
    }


}
