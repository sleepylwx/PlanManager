package com.lwx.likestudy.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lwx.likestudy.R;
import com.lwx.likestudy.adapter.RecyclerViewAdapter;
import com.lwx.likestudy.contract.RecentContract;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.data.source.DataRepository;
import com.lwx.likestudy.data.source.db.LiteOrmHelper;
import com.lwx.likestudy.presenter.RecentPresenter;
import com.lwx.likestudy.ui.activity.MainActivity;
import com.lwx.likestudy.ui.activity.SelfLearingActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 36249 on 2016/10/27.
 */
public class RecentFragment extends BaseFragment implements RecentContract.View{




    RecyclerView recyclerView;

    RecyclerViewAdapter madapter;

    RecentContract.Presenter mPresenter;

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
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerview);
        //implement
        madapter = new RecyclerViewAdapter(getActivity());
        //

        LiteOrmHelper.getsInstance().save(new UnFinishedStudyPlan(1,1024,"math","write","content",2048,5));
        LiteOrmHelper.getsInstance().save(new UnFinishedStudyPlan(2,1024,"math","write","contentsadasd"+
                "asdasdsadsdsdsvdcvcvssdfdcscxcsdfsdcsfdfefdsafsfsdfsdvsdvcdsssssssssssssssssssssssssssssssssssssssssss"+
                "在网上找了一下实现方法，有的说在adapter里添加回调接口来实现点击事件与长按事件。 \n" +
                "不过经过测试，有时候会产生这样的现象：点击item跳转到下一个页面，再返回回来时， \n" +
                "item的点击事件会失效。后来在Stack Overflow找到了新的方法。 \n" +
                "原文链接如下：RecyclerView onClick。这里 贴上代码：",2048,5));
        LiteOrmHelper.getsInstance().save(new UnFinishedStudyPlan(3,1024,"math","write","content",2048,5));


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
        new RecentPresenter(this).subscribe();
    }

    @Override
    public void onDestroyView(){

        super.onDestroyView();
        mPresenter.unSubscribe();
    }



    @Override
    public void setPresenter(RecentContract.Presenter presenter){

        mPresenter = presenter;
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


