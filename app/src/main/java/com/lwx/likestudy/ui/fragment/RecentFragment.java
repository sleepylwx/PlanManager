package com.lwx.likestudy.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lwx.likestudy.R;
import com.lwx.likestudy.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 36249 on 2016/10/27.
 */
public class RecentFragment extends Fragment {



    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    List<String> datas;
    RecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_recent,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

        super.onViewCreated(view,savedInstanceState);
        ButterKnife.bind(this,view);
        getData();//implement
        adapter = new RecyclerViewAdapter(getActivity(),datas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.addItemDecoration();
    }

    private void getData(){
        datas = new ArrayList<>();
        for(int i = 0; i < 30; ++i){

            datas.add("" + i);
        }
    }
}


