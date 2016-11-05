package com.lwx.likestudy.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.lwx.likestudy.R;
import com.lwx.likestudy.adapter.RecyclerViewUnFinishedPlanAdapter;
import com.lwx.likestudy.adapter.SubjectPlanAdapter;
import com.lwx.likestudy.contract.PlanContract;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.presenter.MainPresenter;
import com.lwx.likestudy.ui.activity.SetPlanActivity;
import com.lwx.likestudy.utils.ClickRecord;

import java.util.List;

/**
 * Created by 36249 on 2016/10/27.
 */
public class SubjectFragment extends BaseFragment implements PlanContract.View{




    ExpandableListView expandableListView;

    SubjectPlanAdapter madapter;

    PlanContract.Presenter mPresenter;

    private static final int UPDATE_DATE = 2;
    private static final int DELETE_DATE = 3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_subject,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

        super.onViewCreated(view,savedInstanceState);
        expandableListView = (ExpandableListView) getActivity().findViewById(R.id.expandablelistview_subject);

        madapter = new SubjectPlanAdapter(getActivity());
        expandableListView.setAdapter(madapter);
        //

        expandableListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                ClickRecord clickRecord = (ClickRecord) view.getTag();
                if(clickRecord.getChildPosition() == -1){

                    return false;
                }
                else{


                    return false;
                }

            }
        });

        this.registerForContextMenu(expandableListView);

        PlanContract.Presenter presenter = MainPresenter.getInstance();
        setPresenter(presenter);
        presenter.addView(this);
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

        madapter.itemChanged();
//        madapter.setDatas(unFinishedStudyPlans);
//        Log.e("Subjectload",String.valueOf(madapter.getDatas().size()));
//        madapter.notifyDataSetChanged();
    }

    @Override
    public void onUnFinishedStudyPlanCreated(UnFinishedStudyPlan unFinishedStudyPlan){

        madapter.itemChanged();
//        madapter.addData(unFinishedStudyPlan);
//        Log.e("subjectcreate",String.valueOf(madapter.getDatas().size()));
//        madapter.notifyDataSetChanged();
    }
    @Override
    public void onUnFinishedStudyPlanUpdated(UnFinishedStudyPlan unFinishedStudyPlan){
//
        madapter.itemChanged();
//        madapter.getDatas().set(mUpdateIndex,unFinishedStudyPlan);
//        madapter.notifyItemChanged(mUpdateIndex);
    }

    @Override
    public void onUnFinishedStudyPlanDeleted(UnFinishedStudyPlan unFinishedStudyPlan){

        madapter.itemChanged();
//        madapter.getDatas().remove(mDeleteIndex);
//        madapter.notifyItemRemoved(mDeleteIndex);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo){

        super.onCreateContextMenu(menu,v,menuInfo);

        ExpandableListView.ExpandableListContextMenuInfo info =
                (ExpandableListView.ExpandableListContextMenuInfo)menuInfo;

        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        if(type == ExpandableListView.PACKED_POSITION_TYPE_CHILD){

            menu.add(0,UPDATE_DATE , Menu.NONE, "更新计划");
            menu.add(0,DELETE_DATE, Menu.NONE, "删除计划");
        }


    }

    @Override
    public boolean onContextItemSelected(MenuItem item){


        int id = item.getItemId();
        if(id < 2 || id > 3){

            return false;
        }
        final ExpandableListView.ExpandableListContextMenuInfo info =
                (ExpandableListView.ExpandableListContextMenuInfo)item.getMenuInfo();
        int group = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int child = ExpandableListView.getPackedPositionChild(info.packedPosition);
        if(id == UPDATE_DATE){

            UnFinishedStudyPlan unFinishedStudyPlan = madapter.getData(group,child);
            Intent intent = new Intent(getActivity(), SetPlanActivity.class);
            intent.putExtra("source",unFinishedStudyPlan);
            startActivity(intent);
        }
        else if(id == DELETE_DATE){

            final UnFinishedStudyPlan unFinishedStudyPlan = madapter.getData(group,child);
            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            dialog.setTitle("删除该项计划");
            dialog.setMessage("确定要删除该项计划？");
            dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    MainPresenter.getInstance().deleteUnFinishedStudyPlan(unFinishedStudyPlan);

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


