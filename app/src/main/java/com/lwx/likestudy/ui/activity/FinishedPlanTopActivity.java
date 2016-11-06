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
import com.lwx.likestudy.adapter.FinishedPlanAdapter;
import com.lwx.likestudy.data.model.FinishedStudyPlan;
import com.lwx.likestudy.data.source.db.LiteOrmHelper;
import com.lwx.likestudy.utils.Data;
import com.lwx.likestudy.utils.VoiceHelper;

/**
 * Created by 36249 on 2016/11/3.
 */
public class FinishedPlanTopActivity extends AppCompatActivity{

    Toolbar toolbar;
    TextView textView;
    ListView listView;
    FinishedPlanAdapter adapter;

    private static final int DELETE_DATA = 9;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_plan_top);

        toolbar = (Toolbar)findViewById(R.id.toolbar_finished_plan_top);
        textView = (TextView)findViewById(R.id.textview_finished_plan_top);
        listView = (ListView)findViewById(R.id.listview_finished_plan);
        toolbar.setTitle("完成记录");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        adapter = new FinishedPlanAdapter(this);

        listView.setAdapter(adapter);

        if(adapter.getCount() == 0){

            textView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }


        this.registerForContextMenu(listView);

        if(LikeStudyApplication.isSpeakerOpen()){

            VoiceHelper.inFinishedPlanTopActivity(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_finished_plan_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_clear_finished_plan) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(FinishedPlanTopActivity.this);
            dialog.setTitle("清空记录");
            dialog.setMessage("是否清空全部记录？");
            dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {


                    Data.deleteAllFinishedPlan();
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

                VoiceHelper.selectDeleteAllFinishedPlan(FinishedPlanTopActivity.this);
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

        if(item.getItemId() != 9 ){
            return false;
        }


        final AdapterView.AdapterContextMenuInfo  menuInfo =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        if(item.getItemId() == DELETE_DATA){



            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("删除该项计划");
            dialog.setMessage("确定要删除该项计划？");
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Data.deleteFinishedPlan(adapter.getDatas().get(menuInfo.position));
                    adapter.itemChanged();

                }
            });
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.show();
            if(LikeStudyApplication.isSpeakerOpen()){

                VoiceHelper.selectDeleteRecord(FinishedPlanTopActivity.this);
            }
        }


        super.onContextItemSelected(item);

        return true;
    }







}
