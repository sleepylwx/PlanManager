package com.lwx.likestudy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.lwx.likestudy.R;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.utils.Data;

import java.util.List;

/**
 * Created by 36249 on 2016/11/4.
 */
public class RecentNeededPlanAdapter extends ArrayAdapter<UnFinishedStudyPlan> {

    Context context;
    int resourceId;


    public RecentNeededPlanAdapter(Context context){

        super(context, R.layout.listview_recent_needed_plan_item, Data.getRecentNeededPlan());

        this.context = context;
        this.resourceId = R.layout.listview_recent_needed_plan_item;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        UnFinishedStudyPlan unFinishedStudyPlan = getItem(position);
        ViewHolder  viewHolder;
        View view;

        if(convertView == null){

            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.headTextView = (TextView)view.findViewById(R.id.listview_needed_item_textview_header);
            viewHolder.bodyTextView = (TextView)view.findViewById(R.id.listview_needed_item_textview_body);
            viewHolder.simpleRatingBar = (SimpleRatingBar)view.findViewById(R.id.simple_ratingbar_needed_item_body);
            viewHolder.timeTextView = (TextView)view.findViewById(R.id.listview_needed_item_textview_time);
            view.setTag(viewHolder);
        }
        else{

            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.headTextView.setText(
                unFinishedStudyPlan.getIndex()+"   " + unFinishedStudyPlan.getEndTime() + "    "
                 + unFinishedStudyPlan.getSubject() + " " +unFinishedStudyPlan.getWay()
                 );
        viewHolder.simpleRatingBar.setRating(unFinishedStudyPlan.getImportance());
        viewHolder.bodyTextView.setText(unFinishedStudyPlan.getContent()+"\n");
        viewHolder.timeTextView.setText(unFinishedStudyPlan.getCreatedTime());

        return view;
    }

    private class ViewHolder{

        TextView headTextView;
        TextView bodyTextView;
        SimpleRatingBar simpleRatingBar;
        TextView timeTextView;
    }


    public void setDatas(List<UnFinishedStudyPlan> unFinishedStudyPlans){

        clear();
        addAll(unFinishedStudyPlans);

    }

    public void itemChanged(){

        clear();
        addAll(Data.getRecentNeededPlan());
        notifyDataSetChanged();
    }

    public List<UnFinishedStudyPlan> getDatas(){

        return Data.getRecentNeededPlan();
    }


}
