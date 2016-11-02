package com.lwx.likestudy.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_study_time_top, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_clear_study_time) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(SelfLearningTopActivity.this);
            dialog.setTitle("清空记录");
            dialog.setMessage("是否清空全部记录？");
            dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    LiteOrmHelper.getsInstance().delete(StudyTime.class);
                    adapter.clearDatas();
                    adapter.notifyDataSetChanged();
                }
            });
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialog.show();

        }

        return super.onOptionsItemSelected(item);
    }
}
