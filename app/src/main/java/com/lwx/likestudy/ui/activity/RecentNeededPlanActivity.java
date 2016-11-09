package com.lwx.likestudy.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lwx.likestudy.R;

/**
 * Created by 36249 on 2016/11/9.
 */
public class RecentNeededPlanActivity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_needed_plan);
        toolbar = (Toolbar)findViewById(R.id.toolbar_recent_needed_plan);
        toolbar.setTitle("近期需要完成的计划");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }


}
