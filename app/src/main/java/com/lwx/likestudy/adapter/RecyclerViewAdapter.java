package com.lwx.likestudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lwx.likestudy.R;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;

import java.util.List;

/**
 * Created by 36249 on 2016/10/27.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    Context context;
    List<UnFinishedStudyPlan> datas;

    public RecyclerViewAdapter(Context context){

        this.context = context;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,int position){

        UnFinishedStudyPlan unFinishedStudyPlan = datas.get(position);
        holder.textView.setText(unFinishedStudyPlan.getId() + " " + unFinishedStudyPlan.getCreatedTime()
        +" " + unFinishedStudyPlan.getSubject() + " " + unFinishedStudyPlan.getWay() + "\n" +
        unFinishedStudyPlan.getContent() + "\n" + unFinishedStudyPlan.getEndTime() + " " +
        unFinishedStudyPlan.getImportance());
    }

    @Override
    public int getItemCount(){
        if(datas == null)
            return 0;
        return datas.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public MyViewHolder(View view){

            super(view);
            textView = (TextView) view.findViewById(R.id.recyclerview_item_textview);

        }
    }

    public void setDatas(List<UnFinishedStudyPlan> unFinishedStudyPlans){

        this.datas = unFinishedStudyPlans;
    }

    public List<UnFinishedStudyPlan> getDatas(){

        return this.datas;
    }
}
