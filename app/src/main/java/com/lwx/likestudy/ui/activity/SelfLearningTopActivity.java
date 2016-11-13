package com.lwx.likestudy.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lwx.likestudy.LikeStudyApplication;
import com.lwx.likestudy.R;
import com.lwx.likestudy.adapter.StudyTimeAdapter;
import com.lwx.likestudy.data.model.StudyTime;
import com.lwx.likestudy.data.source.db.LiteOrmHelper;
import com.lwx.likestudy.utils.Data;
import com.lwx.likestudy.utils.VoiceHelper;

/**
 * Created by 36249 on 2016/11/1.
 */
public class SelfLearningTopActivity extends AppCompatActivity {

    TextView textView;
    Toolbar toolbar;
    ListView listView;
    StudyTimeAdapter adapter;
    private static final int DELETE_DATA = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_learning_top);
        toolbar = (Toolbar)findViewById(R.id.toolbar_self_learning_top);
        textView = (TextView)findViewById(R.id.textview_self_learing_top);
        listView = (ListView)findViewById(R.id.listview_study_time);
        toolbar.setTitle("自习记录");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        adapter = new StudyTimeAdapter(this);

        listView.setAdapter(adapter);


        if(adapter.getCount() == 0){

            textView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }

        this.registerForContextMenu(listView);

        if(LikeStudyApplication.isSpeakerOpen()){

            VoiceHelper.inSelfLearningTopActivity(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_study_time_top, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();


        if (id == R.id.menu_clear_study_time) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(SelfLearningTopActivity.this);
            dialog.setTitle("清空记录");
            dialog.setMessage("是否清空全部记录？");
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Data.deleteAllStudyTime();
                    adapter.itemChanged();
                    textView.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                }
            });
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialog.show();

            if(LikeStudyApplication.isSpeakerOpen()){

                VoiceHelper.selectDeleteStudyTime(SelfLearningTopActivity.this);
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo){

        super.onCreateContextMenu(menu,v,menuInfo);

        menu.add(0, DELETE_DATA, Menu.NONE, "删除计划");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        if(item.getItemId() != 10 ){
            return false;
        }


        final AdapterView.AdapterContextMenuInfo  menuInfo =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        if(item.getItemId() == DELETE_DATA){


            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("删除该项计划");
            dialog.setMessage("确定要删除该项计划？");
            dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Data.deleteStudyTime(adapter.getDatas().get(menuInfo.position));
                    adapter.itemChanged();
                    if(adapter.getCount() == 0){

                        textView.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                    }

                }
            });
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.show();

            if(LikeStudyApplication.isSpeakerOpen()){

                VoiceHelper.selectDeleteRecord(SelfLearningTopActivity.this);
            }
        }


        super.onContextItemSelected(item);

        return true;
    }
}
