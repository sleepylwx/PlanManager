package com.lwx.likestudy.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lwx.likestudy.R;
import com.lwx.likestudy.data.model.StudyTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 36249 on 2016/11/1.
 */
public class RecyclerViewStudyTimeAdapter extends RecyclerView.Adapter<RecyclerViewStudyTimeAdapter.MyViewHolder> {

    Context context;
    List<StudyTime> datas;
    List<Integer> indexes;
    public RecyclerViewStudyTimeAdapter(Context context){

        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_study_time_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,int position){

        StudyTime studyTime = datas.get(position);
        if(holder.bodyTextView != null){

            holder.bodyTextView.setText(studyTime.getId()+"\n"
                    + "持续时间： "+studyTime.getDurateTime()+"\n"
            +"随手一写： " + studyTime.getSatisfaction() + "\n"
                    + "日期： " + studyTime.getFinishedTime());
        }


    }

    @Override
    public int getItemCount(){

        Log.e("qwe",String.valueOf(datas == null));
        if(datas == null)
            return 0;
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bodyTextView;

        public MyViewHolder(View view){

            super(view);
            bodyTextView = (TextView) view.findViewById(R.id.recyclerview_study_time_item_textview);

        }
    }

    public void setDatas(List<StudyTime> datas){

        if(datas == null){
            Toast.makeText(context,"初始化数据失败",Toast.LENGTH_SHORT).show();
            return;
        }
        this.datas = new ArrayList<>();
        this.indexes = new ArrayList<>();
        this.datas.addAll(datas);

        Collections.sort(this.datas, new Comparator<StudyTime>() {
            @Override
            public int compare(StudyTime studyTime, StudyTime t1) {

                int temp = studyTime.getDurateTime().compareTo(t1.getDurateTime());
                if(temp > 0){

                    return -1;
                }
                else if(temp == 0){

                    return 0;
                }
                else{
                    return  1;
                }

            }
        });
        for(int i = 0 ; i < this.datas.size(); ++i){

            this.indexes.add(this.datas.get(i).getId());
        }
        for(int i = 0; i < this.datas.size(); ++i){

            this.datas.get(i).setId(i+1);
        }

        Log.e("adapter",String.valueOf(datas.size()));
    }

    public void clearDatas(){

        this.datas = new ArrayList<>();
        this.indexes = new ArrayList<>();
    }
}
