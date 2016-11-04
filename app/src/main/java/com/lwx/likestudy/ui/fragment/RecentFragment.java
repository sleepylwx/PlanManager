package com.lwx.likestudy.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lwx.likestudy.R;
import com.lwx.likestudy.adapter.RecentPlanAdapter;
import com.lwx.likestudy.contract.PlanContract;

import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.presenter.MainPresenter;
import com.lwx.likestudy.ui.activity.SetPlanActivity;


import java.util.List;

/**
 * Created by 36249 on 2016/10/27.
 */
public class RecentFragment extends BaseFragment implements PlanContract.View{




    ListView listView;

    RecentPlanAdapter madapter;

    PlanContract.Presenter mPresenter;

    int mDeleteIndex;
    int mUpdateIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_recent,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

        super.onViewCreated(view,savedInstanceState);

//        for(int i = 0; i < 2000; ++i){
//            LiteOrmHelper.getsInstance().save(new UnFinishedStudyPlan(Time.getCurrentTimeString()
//                    +"",i+"",i+"",i+"",i+"",i));
//        }
        listView = (ListView) getActivity().findViewById(R.id.listview_recent);
        //implement
        madapter = new RecentPlanAdapter(getActivity());
        //

        listView.setAdapter(madapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Toast.makeText(getActivity(),"long press" + l + " " ,Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        this.registerForContextMenu(listView);
        PlanContract.Presenter presenter = MainPresenter.getInstance();
        setPresenter(presenter);
        presenter.addView(this);
        presenter.loadUnFinishedStudyPlans();

    }

    @Override
    public void onDestroyView(){

        super.onDestroyView();
        mPresenter.unSubscribe();
    }



    @Override
    public void setPresenter(PlanContract.Presenter presenter){

        mPresenter = presenter;
    }

    @Override
    public PlanContract.Presenter getPresenter(){

        return mPresenter;
    }
    @Override
    public void showLoading(){

    }

    @Override
    public void hideLoading(){

    }

    @Override
    public void handleError(Throwable error){

        Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUnFinishedStudyPlansLoaded(List<UnFinishedStudyPlan> unFinishedStudyPlans){

       // madapter.setDatas(unFinishedStudyPlans);
//        Log.e("recentload",String.valueOf(madapter.getDatas().size()));
//        madapter.notifyChanged();
       // madapter.notifyDataSetChanged();
        madapter.itemChanged();
    }

    @Override
    public void onUnFinishedStudyPlanCreated(UnFinishedStudyPlan unFinishedStudyPlan){

        //madapter.addData(unFinishedStudyPlan);
//        Log.e("recentcreate",String.valueOf(madapter.getDatas().size()));
//        madapter.notifyChanged();
       // madapter.notifyDataSetChanged();
        madapter.itemChanged();
    }
    @Override
    public void onUnFinishedStudyPlanUpdated(UnFinishedStudyPlan unFinishedStudyPlan){

//        madapter.getDatas().set(mUpdateIndex,unFinishedStudyPlan);
//        //madapter.notifyChanged();
//        madapter.notifyItemChanged(mUpdateIndex);
        madapter.itemChanged();
    }

    @Override
    public void onUnFinishedStudyPlanDeleted(UnFinishedStudyPlan unFinishedStudyPlan){
//
//        madapter.getDatas().remove(unFinishedStudyPlan.getIndex());
//        madapter.notifyChanged();
        //madapter.notifyChanged();
        //madapter.notifyItemRemoved(mDeleteIndex);
        madapter.itemChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo){

        super.onCreateContextMenu(menu,v,menuInfo);
        menu.add(0, 0, Menu.NONE, "更新计划");
        menu.add(0, 1, Menu.NONE, "删除计划");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        final AdapterView.AdapterContextMenuInfo  menuInfo =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        if(item.getItemId() == 0){

            UnFinishedStudyPlan unFinishedStudyPlan = madapter.getDatas().get(menuInfo.position);
            Intent intent = new Intent(getActivity(), SetPlanActivity.class);
            intent.putExtra("source",unFinishedStudyPlan);
            startActivity(intent);
        }
        else if(item.getItemId() == 1){


            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            dialog.setTitle("删除该项计划");
            dialog.setMessage("确定要删除该项计划？");
            dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    MainPresenter.getInstance().deleteUnFinishedStudyPlan(
                            madapter.getDatas().get(menuInfo.position));
                    madapter.itemChanged();
                }
            });
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.show();
        }


        super.onContextItemSelected(item);

        return true;
    }

}


