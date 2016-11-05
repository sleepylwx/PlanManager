package com.lwx.likestudy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lwx.likestudy.R;
import com.lwx.likestudy.data.model.StudyTime;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.utils.Data;

import java.util.List;

/**
 * Created by 36249 on 2016/11/5.
 */
public class StudyTimeAdapter extends ArrayAdapter<StudyTime> {

    Context context;
    int resouceId;

    public StudyTimeAdapter(Context context){

        super(context, R.layout.listview_study_time_item, Data.getStudyTimeInOrderOfDurateTime());
        this.context = context;
        this.resouceId = R.layout.listview_study_time_item;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        StudyTime studyTime = getItem(position);
        ViewHolder viewHolder = null;
        if(convertView == null){

            convertView = LayoutInflater.from(context).inflate(resouceId,null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView)convertView.findViewById(R.id.listview_study_time_item);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(studyTime.getIndex()+"\n"
                + "持续时间： "+studyTime.getDurateTime()+"\n"
                +"随手一写： " + studyTime.getSatisfaction() + "\n"
                + "日期： " + studyTime.getFinishedTime());
        return convertView;

    }


    private class ViewHolder{


        TextView textView;
    }


    public void itemChanged(){

        clear();
        addAll(Data.getStudyTimeInOrderOfDurateTime());
        notifyDataSetChanged();
    }

    public List<StudyTime> getDatas(){

        return Data.getStudyTimeInOrderOfDurateTime();
    }
}
