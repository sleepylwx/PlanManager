package com.lwx.likestudy.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.lwx.likestudy.LikeStudyApplication;
import com.lwx.likestudy.R;
import com.lwx.likestudy.data.model.FinishedStudyPlan;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.data.source.db.LiteOrmHelper;
import com.lwx.likestudy.presenter.UnFinishedPlanPresenter;
import com.lwx.likestudy.utils.Time;
import com.lwx.likestudy.utils.VoiceHelper;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

/**
 * Created by 36249 on 2016/11/1.
 */
public class FinishPlanActivity extends AppCompatActivity {

    Toolbar toolbar;
    ScrollView scrollView;
    LinearLayout linearLayout;
    TextView headerTextView;
    SimpleRatingBar simpleRatingBar;
    TextView bodyTextView;
    TextView timeTextView;
    UnFinishedStudyPlan unFinishedStudyPlan;
    int num = -1;
    boolean speak = true;

    private static final int SELECT_PLAN = 11;
    private static final int RESELECT = 12;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_plan);
        toolbar = (Toolbar)findViewById(R.id.toolbar_finish_plan);
        scrollView = (ScrollView)findViewById(R.id.scrollview_finish_plan);
        linearLayout = (LinearLayout)findViewById(R.id.linearlayout_finish_plan);
        headerTextView = (TextView)findViewById(R.id.textview_selected_plan_header);
        simpleRatingBar = (SimpleRatingBar)findViewById(R.id.simple_ratingbar_selected_plan);
        bodyTextView = (TextView)findViewById(R.id.textview_selected_plan_body);
        timeTextView = (TextView)findViewById(R.id.textview_selected_plan_time);
        toolbar.setTitle("进行计划");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        unFinishedStudyPlan = ((UnFinishedStudyPlan)intent.getParcelableExtra("plan"));
        if(unFinishedStudyPlan == null){

            getPlan();
        }
        else{

            setView();


        }



        this.registerForContextMenu(linearLayout);
        if(LikeStudyApplication.isSpeakerOpen() && speak == true){

            VoiceHelper.inFinishPlanAcitivity(this);
        }
    }

    public void getPlan(){

        List<UnFinishedStudyPlan> datas =
                LiteOrmHelper.getsInstance().query(UnFinishedStudyPlan.class);
        if(datas.size() == 0){

            speak = false;
            Toast.makeText(LikeStudyApplication.getInstance(),"没有未完成的记录",Toast.LENGTH_SHORT).show();

            finish();
            return;
        }
        Random random = new Random();
        int randomNum;

        while((randomNum = random.nextInt(datas.size())) == num){
            if(datas.size() == 1){

                break;
            }
        }
        num = randomNum;

        unFinishedStudyPlan = datas.get(randomNum);
        setView();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo){

        super.onCreateContextMenu(menu,v,menuInfo);
        menu.add(0,SELECT_PLAN, Menu.NONE,"选择计划");
        menu.add(0,RESELECT, Menu.NONE, "重新选择");


    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        if(item.getItemId() < 11 || item.getItemId() > 12){

            return false;
        }

        if(item.getItemId() == 11){

            Intent intent = new Intent(FinishPlanActivity.this,SelfLearingActivity.class);
            intent.putExtra("index",1);
            startActivityForResult(intent,1);
        }
        else if(item.getItemId() == 12){

            getPlan();
        }

        super.onContextItemSelected(item);
        return true;
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


                        Intent intent = new Intent(FinishPlanActivity.this,StoreFinishedPlanActivity.class);


                        FinishedStudyPlan finishedStudyPlan =
                                new FinishedStudyPlan(durateTime,Time.getCurrentTimeString()
                                ,unFinishedStudyPlan);
                        intent.putExtra("finishedplan",finishedStudyPlan);
                        startActivity(intent);
                        UnFinishedPlanPresenter.getInstance().deleteUnFinishedStudyPlan(unFinishedStudyPlan);
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

    private void setView(){

        headerTextView.setText(
                 unFinishedStudyPlan.getEndTime() + "    "
                        + unFinishedStudyPlan.getSubject() + " " +unFinishedStudyPlan.getWay());

        simpleRatingBar.setRating((int)unFinishedStudyPlan.getImportance());

        bodyTextView.setText(unFinishedStudyPlan.getContent()+"\n");
        timeTextView.setText(unFinishedStudyPlan.getCreatedTime());
    }


}
