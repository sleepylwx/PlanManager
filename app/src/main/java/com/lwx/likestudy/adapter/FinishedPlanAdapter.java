package com.lwx.likestudy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;
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
            viewHolder.contentTextView = (TextView)convertView.findViewById(R.id.textview_finished_plan_item_content);
            viewHolder.contentTimeTextView = (TextView)convertView.findViewById(R.id.textview_finished_plan_item_content_time);
            viewHolder.simpleRatingBar = (SimpleRatingBar)convertView.findViewById(R.id.simple_ratingbar_finished_plan);
            viewHolder.durateTextView = (TextView)convertView.findViewById(R.id.textview_finished_plan_item_durate);
            viewHolder.bodyTextView = (TextView)convertView.findViewById(R.id.textview_finished_plan_item_body);
            viewHolder.timeTextView = (TextView)convertView.findViewById(R.id.textview_finished_plan_item_time);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(finishedStudyPlan.getIndex() + "   "
                + finishedStudyPlan.getEndTime() +"    " + finishedStudyPlan.getSubject()
                + " " +  finishedStudyPlan.getWay());
        viewHolder.contentTextView.setText(finishedStudyPlan.getContent() + "\n");
        viewHolder.contentTimeTextView.setText(finishedStudyPlan.getCreatedTime());
        viewHolder.simpleRatingBar.setRating((int)finishedStudyPlan.getStarNum());
       // Log.e("Log",finishedStudyPlan.getDurateTime());
        viewHolder.durateTextView.setText(finishedStudyPlan.getDurateTime());
        viewHolder.bodyTextView.setText(finishedStudyPlan.getSatisfaction()+"\n");
        viewHolder.timeTextView.setText(finishedStudyPlan.getFinishedTime());
        return convertView;
    }


    private class ViewHolder{

        TextView textView;
        TextView contentTextView;
        TextView contentTimeTextView;
        SimpleRatingBar simpleRatingBar;
        TextView durateTextView;
        TextView bodyTextView;
        TextView timeTextView;
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
