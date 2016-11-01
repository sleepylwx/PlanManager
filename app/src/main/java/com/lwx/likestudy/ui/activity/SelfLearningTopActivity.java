package com.lwx.likestudy.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lwx.likestudy.R;
import com.lwx.likestudy.adapter.RecyclerViewStudyTimeAdapter;
import com.lwx.likestudy.adapter.RecyclerViewUnFinishedPlanAdapter;
import com.lwx.likestudy.data.model.StudyTime;
import com.lwx.likestudy.data.source.db.LiteOrmHelper;

/**
 * Created by 36249 on 2016/11/1.
 */
public class SelfLearningTopActivity extends AppCompatActivity {

    TextView textView;
    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerViewStudyTimeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_learning_top);
        toolbar = (Toolbar)findViewById(R.id.toolbar_self_learning_top);
        textView = (TextView)findViewById(R.id.textview_self_learing_top);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_self_learning_top);
        toolbar.setTitle("自习记录");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        adapter = new RecyclerViewStudyTimeAdapter(this);
        adapter.setDatas(LiteOrmHelper.getsInstance().query(StudyTime.class));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.e("abc" , String.valueOf(adapter.getItemCount()));
        if(adapter.getItemCount() == 0){

            textView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }


    }
}
