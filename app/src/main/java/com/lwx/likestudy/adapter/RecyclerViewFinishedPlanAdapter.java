package com.lwx.likestudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lwx.likestudy.R;
import com.lwx.likestudy.data.model.FinishedStudyPlan;
import com.lwx.likestudy.data.model.StudyTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 36249 on 2016/11/3.
 */
public class RecyclerViewFinishedPlanAdapter extends RecyclerView.Adapter<RecyclerViewFinishedPlanAdapter.MyViewHolder> {



    Context context;
    List<FinishedStudyPlan> datas;
//    List<Integer> indexes;

    public RecyclerViewFinishedPlanAdapter(Context context){

        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_finished_plan_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,int position){


        FinishedStudyPlan finishedStudyPlan = datas.get(position);
        if(holder.bodyTextView != null){

            holder.bodyTextView.setText(finishedStudyPlan.getId()+"\n"
                    + "科目： "+finishedStudyPlan.getSubject() +"\n"
                    +"方式： " + finishedStudyPlan.getWay() + "\n"
                    + "完成日期： " + finishedStudyPlan.getFinishedTime() + "\n"
                    + "创建日期： " + finishedStudyPlan.getCreatedTime() + "\n"
                    + "截止日期： " + finishedStudyPlan.getEndTime() + "\n"
                    + "持续时间： " + finishedStudyPlan.getDurateTime() + "\n"
                    + "满意度： " + finishedStudyPlan.getSatisfaction() + "\n"
                    + "内容： " + finishedStudyPlan.getContent());
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
            bodyTextView = (TextView) view.findViewById(R.id.recyclerview_finished_plan_item_textview);

        }
    }

    public void setDatas(List<FinishedStudyPlan> datas){

        if(datas == null){
            Toast.makeText(context,"初始化数据失败",Toast.LENGTH_SHORT).show();
            return;
        }
        this.datas = new ArrayList<>();
        //this.indexes = new ArrayList<>();
        this.datas.addAll(datas);

        Collections.sort(this.datas, new Comparator<FinishedStudyPlan>() {
            @Override
            public int compare(FinishedStudyPlan studyTime, FinishedStudyPlan t1) {

                int temp = studyTime.getFinishedTime().compareTo(t1.getFinishedTime());
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
//        for(int i = 0 ; i < this.datas.size(); ++i){
//
//            this.indexes.add(this.datas.get(i).getId());
//        }
        for(int i = 0; i < this.datas.size(); ++i){

            this.datas.get(i).setId(i+1);
        }

        Log.e("adapter",String.valueOf(datas.size()));
    }

    public void clearDatas(){

        this.datas = new ArrayList<>();
        //this.indexes = new ArrayList<>();
    }
}
