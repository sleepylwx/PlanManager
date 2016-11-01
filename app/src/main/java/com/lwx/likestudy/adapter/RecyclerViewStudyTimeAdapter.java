package com.lwx.likestudy.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lwx.likestudy.R;
import com.lwx.likestudy.data.model.StudyTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 36249 on 2016/11/1.
 */
public class RecyclerViewStudyTimeAdapter extends RecyclerView.Adapter<RecyclerViewStudyTimeAdapter.MyViewHolder> {

    Context context;
    List<StudyTime> datas;

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

            holder.bodyTextView.setText(studyTime.getId()+" "
                    + "自习持续时间： "+studyTime.getDurateTime()+"\n"
            +"满意度： " + studyTime.getSatisfaction()
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

        this.datas = new ArrayList<>();
        this.datas.addAll(datas);
        Log.e("adapter",String.valueOf(datas.size()));
    }
}
