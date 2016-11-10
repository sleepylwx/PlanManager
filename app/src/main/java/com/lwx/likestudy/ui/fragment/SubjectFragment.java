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
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lwx.likestudy.LikeStudyApplication;
import com.lwx.likestudy.R;
import com.lwx.likestudy.adapter.SubjectPlanAdapter;
import com.lwx.likestudy.contract.UnFinishedPlanContract;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.presenter.UnFinishedPlanPresenter;
import com.lwx.likestudy.ui.activity.FinishPlanActivity;
import com.lwx.likestudy.ui.activity.SetPlanActivity;
import com.lwx.likestudy.utils.ClickRecord;
import com.lwx.likestudy.utils.VoiceHelper;

import java.util.List;

/**
 * Created by 36249 on 2016/10/27.
 */
public class SubjectFragment extends BaseFragment implements UnFinishedPlanContract.View{




    ExpandableListView expandableListView;
    TextView textView;
    SubjectPlanAdapter madapter;
    boolean isEmpty = false;
    UnFinishedPlanContract.Presenter mPresenter;

    private static final int SELECTED_DATA = 3;
    private static final int UPDATE_DATA = 4;
    private static final int DELETE_DATA = 5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_subject,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

        super.onViewCreated(view,savedInstanceState);
        expandableListView = (ExpandableListView) getActivity().findViewById(R.id.expandablelistview_subject);
        textView = (TextView)getActivity().findViewById(R.id.textview_fragment_subject);
        madapter = new SubjectPlanAdapter(getActivity());
        expandableListView.setAdapter(madapter);


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

        UnFinishedPlanContract.Presenter presenter = UnFinishedPlanPresenter.getInstance();
        setPresenter(presenter);
        presenter.addView(this);

        if(madapter.getGroupCount() == 0){

            expandableListView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            isEmpty = true;
        }
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

        if(isEmpty){

            expandableListView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
            isEmpty = false;
        }

    }
    @Override
    public void onUnFinishedStudyPlanUpdated(UnFinishedStudyPlan unFinishedStudyPlan){

        madapter.itemChanged();

    }

    @Override
    public void onUnFinishedStudyPlanDeleted(UnFinishedStudyPlan unFinishedStudyPlan){

        madapter.itemChanged();
        if(madapter.getGroupCount() == 0){

            expandableListView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            isEmpty = true;
        }

    }

    @Override
    public void onUnFinishedStudyPlanAllDeleted(){

        madapter.itemChanged();
        if(madapter.getGroupCount() == 0){

            expandableListView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            isEmpty = true;
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo){

        super.onCreateContextMenu(menu,v,menuInfo);

        ExpandableListView.ExpandableListContextMenuInfo info =
                (ExpandableListView.ExpandableListContextMenuInfo)menuInfo;

        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        if(type == ExpandableListView.PACKED_POSITION_TYPE_CHILD){
            menu.add(0,SELECTED_DATA,Menu.NONE,"选择计划");
            menu.add(0,UPDATE_DATA , Menu.NONE, "更新计划");
            menu.add(0,DELETE_DATA, Menu.NONE, "删除计划");
        }


    }

    @Override
    public boolean onContextItemSelected(MenuItem item){


        int id = item.getItemId();
        if(id < 3 || id > 5){

            return false;
        }
        final ExpandableListView.ExpandableListContextMenuInfo info =
                (ExpandableListView.ExpandableListContextMenuInfo)item.getMenuInfo();
        int group = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int child = ExpandableListView.getPackedPositionChild(info.packedPosition);

        if(id == SELECTED_DATA){

            Intent intent = new Intent(getActivity(), FinishPlanActivity.class);
            intent.putExtra("plan",madapter.getData(group,child));
            startActivity(intent);

        }
        else if(id == UPDATE_DATA){

            UnFinishedStudyPlan unFinishedStudyPlan = madapter.getData(group,child);
            Intent intent = new Intent(getActivity(), SetPlanActivity.class);
            intent.putExtra("source",unFinishedStudyPlan);
            startActivity(intent);
        }
        else if(id == DELETE_DATA){

            final UnFinishedStudyPlan unFinishedStudyPlan = madapter.getData(group,child);
            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            dialog.setTitle("删除该项计划");
            dialog.setMessage("确定要删除该项计划？");
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    UnFinishedPlanPresenter.getInstance().deleteUnFinishedStudyPlan(unFinishedStudyPlan);
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

                VoiceHelper.selectDeleteUnFinishedPlan(getActivity());
            }

        }

        super.onContextItemSelected(item);

        return true;
    }


}


