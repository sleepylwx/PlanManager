package com.lwx.likestudy.ui.activity;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.lwx.likestudy.adapter.RecentNeededPlanAdapter;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.presenter.UnFinishedPlanPresenter;
import com.lwx.likestudy.utils.NotificationHelper;
import com.lwx.likestudy.utils.VoiceHelper;

import org.w3c.dom.Text;

/**
 * Created by 36249 on 2016/11/9.
 */
public class RecentNeededPlanActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    TextView textView;
    RecentNeededPlanAdapter adapter;
    private static final int SELECTED_DATA = 13;
    private static final int UPDATE_DATA = 14;
    private static final int DELETE_DATA = 15;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_needed_plan);

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(NotificationHelper.getNotifyID());
        toolbar = (Toolbar)findViewById(R.id.toolbar_recent_needed_plan);
        listView = (ListView)findViewById(R.id.listview_recent_needed_plan);
        textView = (TextView)findViewById(R.id.textview_recent_needed_plan);
        toolbar.setTitle("近期需要完成的计划");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        adapter = new RecentNeededPlanAdapter(this);
        listView.setAdapter(adapter);

        if(adapter.getCount() == 0){
            listView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }

        this.registerForContextMenu(listView);

        if(LikeStudyApplication.isSpeakerOpen()){

            VoiceHelper.inRecentNeededPlanActivity(this);
        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo){

        super.onCreateContextMenu(menu,v,menuInfo);
        menu.add(0,SELECTED_DATA, Menu.NONE,"选择计划");
        menu.add(0, UPDATE_DATA, Menu.NONE, "更新计划");
        menu.add(0, DELETE_DATA, Menu.NONE, "删除计划");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        if(item.getItemId() > 15 || item.getItemId() < 13){

            return false;
        }


        final AdapterView.AdapterContextMenuInfo  menuInfo =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        if(item.getItemId() == SELECTED_DATA){

            Intent intent = new Intent(this, FinishPlanActivity.class);
            intent.putExtra("plan",adapter.getDatas().get(menuInfo.position));
            startActivity(intent);

        }
        else if(item.getItemId() == UPDATE_DATA){

            UnFinishedStudyPlan unFinishedStudyPlan = adapter.getDatas().get(menuInfo.position);
            Intent intent = new Intent(this, SetPlanActivity.class);
            intent.putExtra("source",unFinishedStudyPlan);
            startActivity(intent);
        }
        else if(item.getItemId() == DELETE_DATA){


            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("删除该项计划");
            dialog.setMessage("确定要删除该项计划？");
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    UnFinishedPlanPresenter.getInstance().deleteUnFinishedStudyPlan(
                            adapter.getDatas().get(menuInfo.position));
                    adapter.itemChanged();

                    if(adapter.getCount() == 0){

                        listView.setVisibility(View.GONE);
                        textView.setVisibility(View.VISIBLE);
                    }
                    LikeStudyApplication.setPlanNum(LikeStudyApplication.getPlanNum() -1);
                }
            });
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.show();

            if(LikeStudyApplication.isSpeakerOpen()){

                VoiceHelper.selectDeleteUnFinishedPlan(this);
            }
        }


        super.onContextItemSelected(item);

        return true;
    }

}
