package com.lwx.likestudy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lwx.likestudy.R;
import com.lwx.likestudy.data.model.FinishedStudyPlan;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.utils.Data;

import java.util.List;

/**
 * Created by 36249 on 2016/11/5.
 */
public class FinishedPlanAdapter  extends ArrayAdapter<FinishedStudyPlan> {

    Context context;
    int resouceId;

    public FinishedPlanAdapter(Context context){

        super(context, R.layout.listview_finished_plan_item, Data.getFinishedPlanInOrderOfFinshedTime());
        this.context = context;
        resouceId = R.layout.listview_finished_plan_item;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        FinishedStudyPlan finishedStudyPlan = getItem(position);
        ViewHolder viewHolder = null;
        if(convertView == null){

            convertView = LayoutInflater.from(context).inflate(resouceId,null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView)convertView.findViewById(R.id.listview_finished_plan_item);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(finishedStudyPlan.getIndex()+"\n"
                + "科目： "+finishedStudyPlan.getSubject() +"\n"
                +"方式： " + finishedStudyPlan.getWay() + "\n"
                + "完成日期： " + finishedStudyPlan.getFinishedTime() + "\n"
                + "创建日期： " + finishedStudyPlan.getCreatedTime() + "\n"
                + "截止日期： " + finishedStudyPlan.getEndTime() + "\n"
                + "持续时间： " + finishedStudyPlan.getDurateTime() + "\n"
                + "满意度： " + finishedStudyPlan.getSatisfaction() + "\n"
                + "内容： " + finishedStudyPlan.getContent());

        return convertView;
    }


    private class ViewHolder{

        TextView textView;
    }


    public void itemChanged(){

        clear();
        addAll(Data.getFinishedPlanInOrderOfFinshedTime());
        notifyDataSetChanged();
    }

    public List<FinishedStudyPlan> getDatas(){

        return Data.getFinishedPlanInOrderOfFinshedTime();
    }
}
