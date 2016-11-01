package com.lwx.likestudy.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lwx.likestudy.R;
import com.lwx.likestudy.adapter.RecyclerViewUnFinishedPlanAdapter;
import com.lwx.likestudy.contract.PlanContract;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.presenter.MainPresenter;

import java.util.List;

/**
 * Created by 36249 on 2016/10/27.
 */
public class RecentFragment extends BaseFragment implements PlanContract.View{




    RecyclerView recyclerView;

    RecyclerViewUnFinishedPlanAdapter madapter;

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
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerview_recent);
        //implement
        madapter = new RecyclerViewUnFinishedPlanAdapter(getActivity());
        //

//        LiteOrmHelper.getsInstance().save(new UnFinishedStudyPlan(0,"数学","fasd",
//                        "asdasdsa",1024,5));


        recyclerView.setAdapter(madapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(getActivity(),recyclerView,
                new RecyclerViewItemClickListener.OnItemClickListener(){

                    @Override
                    public void onItemClick(View view,int position){


                    }

                    @Override
                    public void onItemLongClick(View view,int position){

                    }
                }));
        recyclerView.addItemDecoration(new DividerItemDecoration());

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

        madapter.setDatas(unFinishedStudyPlans);
        Log.e("recentload",String.valueOf(madapter.getDatas().size()));
        madapter.notifyDataSetChanged();
    }

    @Override
    public void onUnFinishedStudyPlanCreated(UnFinishedStudyPlan unFinishedStudyPlan){

        madapter.addData(unFinishedStudyPlan);
        Log.e("recentcreate",String.valueOf(madapter.getDatas().size()));
        madapter.notifyDataSetChanged();
    }
    @Override
    public void onUnFinishedStudyPlanUpdated(UnFinishedStudyPlan unFinishedStudyPlan){

        madapter.getDatas().set(mUpdateIndex,unFinishedStudyPlan);
        madapter.notifyItemChanged(mUpdateIndex);
    }

    @Override
    public void onUnFinishedStudyPlanDeleted(UnFinishedStudyPlan unFinishedStudyPlan){

        madapter.getDatas().remove(mDeleteIndex);
        madapter.notifyItemRemoved(mDeleteIndex);
    }

}


