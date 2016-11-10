package com.lwx.likestudy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.lwx.likestudy.LikeStudyApplication;
import com.lwx.likestudy.R;
import com.lwx.likestudy.data.model.FinishedStudyPlan;
import com.lwx.likestudy.data.model.StudyTime;
import com.lwx.likestudy.data.source.db.LiteOrmHelper;
import com.lwx.likestudy.presenter.UnFinishedPlanPresenter;
import com.lwx.likestudy.utils.Time;
import com.lwx.likestudy.utils.VoiceHelper;

/**
 * Created by 36249 on 2016/11/1.
 */
public class StoreFinishedPlanActivity extends AppCompatActivity {

    EditText editText;
    SimpleRatingBar simpleRatingBar;
    Button addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_store_finished_plan);

        editText = (EditText)findViewById(R.id.edittext_edit_saticfaction_finished_plan);
        simpleRatingBar = (SimpleRatingBar)findViewById(R.id.simple_ratingbar_store_finished_plan);
        addButton = (Button) findViewById(R.id.button_add_saticfaction_finished_plan);


        Intent intent = getIntent();
        final FinishedStudyPlan finishedStudyPlan = (FinishedStudyPlan) intent.getParcelableExtra("finishedplan");
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String satisfaction = editText.getText().toString();

                int starNum =(int) simpleRatingBar.getRating();
                finishedStudyPlan.setSatisfaction(satisfaction);
                finishedStudyPlan.setStarNum(starNum);

                long res = LiteOrmHelper.getsInstance().save(finishedStudyPlan);
                if(res <= 0){
                    Toast.makeText(StoreFinishedPlanActivity.this,"存储数据失败",Toast.LENGTH_SHORT).show();
                }
                if(res > 0){

                    Log.e("qwe","OK");
                }
                finish();
            }
        });



        if(LikeStudyApplication.isSpeakerOpen()){

            VoiceHelper.inStoreStudyTimeActivity(this);
        }
    }

    @Override
    public void onBackPressed(){

        ;
    }
}
