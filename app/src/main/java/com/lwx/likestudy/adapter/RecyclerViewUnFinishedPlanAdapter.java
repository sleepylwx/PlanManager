package com.lwx.likestudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lwx.likestudy.R;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 36249 on 2016/10/27.
 */
public class RecyclerViewUnFinishedPlanAdapter extends RecyclerView.Adapter<RecyclerViewUnFinishedPlanAdapter.MyViewHolder> {


    Context context;
    List<UnFinishedStudyPlan> datas;

    public RecyclerViewUnFinishedPlanAdapter(Context context){

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
        if(holder.headTextView != null)

        holder.headTextView.setText(unFinishedStudyPlan.getId()+"\n"
            + "科目： " + unFinishedStudyPlan.getSubject() + ".\n" + "方式： "+unFinishedStudyPlan.getWay()
             +".\n" + "重要性： " + unFinishedStudyPlan.getImportance() + ".\n"
                + "截止时间： "+ unFinishedStudyPlan.getEndTime() + ".\n" + "创建时间： " +unFinishedStudyPlan.getCreatedTime()
                + ".");
        if(holder.bodyTextView != null)
        holder.bodyTextView.setText("内容： " + unFinishedStudyPlan.getContent() +".");
    }

    @Override
    public int getItemCount(){
        if(datas == null)
            return 0;
        return datas.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView headTextView;
        TextView bodyTextView;
        public MyViewHolder(View view){

            super(view);
            headTextView = (TextView) view.findViewById(R.id.recyclerview_item_textview_header);
            bodyTextView = (TextView) view.findViewById(R.id.recyclerview_item_textview_body);

        }
    }

    public void setDatas(List<UnFinishedStudyPlan> unFinishedStudyPlans){

        datas = new ArrayList<>();
        datas.addAll(unFinishedStudyPlans);

    }

    public List<UnFinishedStudyPlan> getDatas(){

        return this.datas;
    }

    public void addData(UnFinishedStudyPlan unFinishedStudyPlan){

        this.datas.add(unFinishedStudyPlan);
    }
}
