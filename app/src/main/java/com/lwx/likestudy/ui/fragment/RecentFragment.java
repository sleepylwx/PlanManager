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
import com.lwx.likestudy.contract.UnFinishedPlanContract;

import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.presenter.UnFinishedPlanPresenter;
import com.lwx.likestudy.ui.activity.FinishPlanActivity;
import com.lwx.likestudy.ui.activity.SetPlanActivity;


import java.util.List;

/**
 * Created by 36249 on 2016/10/27.
 */
public class RecentFragment extends BaseFragment implements UnFinishedPlanContract.View{




    ListView listView;

    RecentPlanAdapter madapter;

    UnFinishedPlanContract.Presenter mPresenter;


    private static final int SELECTED_DATA = 0;
    private static final int UPDATE_DATA = 1;
    private static final int DELETE_DATA = 2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_recent,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

        super.onViewCreated(view,savedInstanceState);


        listView = (ListView) getActivity().findViewById(R.id.listview_recent);

        madapter = new RecentPlanAdapter(getActivity());


        listView.setAdapter(madapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {


                return false;
            }
        });

        this.registerForContextMenu(listView);

        UnFinishedPlanContract.Presenter presenter = UnFinishedPlanPresenter.getInstance();
        setPresenter(presenter);
        presenter.addView(this);
        //presenter.loadUnFinishedStudyPlans();

    }

    @Override
    public void onDestroyView(){

        super.onDestroyView();
        mPresenter.unSubscribe();
    }



    @Override
    public void setPresenter(UnFinishedPlanContract.Presenter presenter){

        mPresenter = presenter;
    }

    @Override
    public UnFinishedPlanContract.Presenter getPresenter(){

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


        madapter.itemChanged();
    }

    @Override
    public void onUnFinishedStudyPlanCreated(UnFinishedStudyPlan unFinishedStudyPlan){


        madapter.itemChanged();
    }
    @Override
    public void onUnFinishedStudyPlanUpdated(UnFinishedStudyPlan unFinishedStudyPlan){


        madapter.itemChanged();
    }

    @Override
    public void onUnFinishedStudyPlanDeleted(UnFinishedStudyPlan unFinishedStudyPlan){


        madapter.itemChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo){

        super.onCreateContextMenu(menu,v,menuInfo);
        menu.add(0,SELECTED_DATA,Menu.NONE,"选择计划");
        menu.add(0, UPDATE_DATA, Menu.NONE, "更新计划");
        menu.add(0, DELETE_DATA, Menu.NONE, "删除计划");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        if(item.getItemId() > 2){
            return false;
        }


        final AdapterView.AdapterContextMenuInfo  menuInfo =
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        if(item.getItemId() == SELECTED_DATA){

            Intent intent = new Intent(getActivity(), FinishPlanActivity.class);
            intent.putExtra("plan",madapter.getDatas().get(menuInfo.position));
            startActivity(intent);

        }
        else if(item.getItemId() == UPDATE_DATA){

            UnFinishedStudyPlan unFinishedStudyPlan = madapter.getDatas().get(menuInfo.position);
            Intent intent = new Intent(getActivity(), SetPlanActivity.class);
            intent.putExtra("source",unFinishedStudyPlan);
            startActivity(intent);
        }
        else if(item.getItemId() == DELETE_DATA){


            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            dialog.setTitle("删除该项计划");
            dialog.setMessage("确定要删除该项计划？");
            dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    UnFinishedPlanPresenter.getInstance().deleteUnFinishedStudyPlan(
                            madapter.getDatas().get(menuInfo.position));

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


