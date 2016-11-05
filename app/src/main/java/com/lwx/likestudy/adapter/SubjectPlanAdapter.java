package com.lwx.likestudy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.lwx.likestudy.R;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.utils.ClickRecord;
import com.lwx.likestudy.utils.Data;
import com.lwx.likestudy.utils.Pair;

import java.util.List;

import rx.Observable;

/**
 * Created by 36249 on 2016/11/4.
 */
public class SubjectPlanAdapter extends BaseExpandableListAdapter {

    Context context;

    List<String> groupName;
    List<List<UnFinishedStudyPlan>> datasBySubject;

    public SubjectPlanAdapter(Context context){

        this.context = context;
        Pair<UnFinishedStudyPlan> pair = Data.getDatasInOrderOfSubject();
        groupName = pair.getGroupName();
        datasBySubject = pair.getDatas();
//        for(int i = 0; i < groupName.size() ;++i){
//            Log.e("groupname",groupName.get(i));
//        }
//
//        for(int i = 0; i < datasBySubject.size();++i){
//            for(int j = 0;j < datasBySubject.get(i).size();++j){
//
//                Log.e(i+"",datasBySubject.get(i).get(j).getSubject());
//            }
//        }
    }

    @Override
    public int getGroupCount(){
        if(datasBySubject == null){
            return 0;
        }
        else{
            return datasBySubject.size();
        }
    }

    @Override
    public int getChildrenCount(int groupPosition){

        if(datasBySubject == null){
            return 0;
        }
        return datasBySubject.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPostion){

        return datasBySubject.get(groupPostion);
    }

    @Override
    public  Object getChild(int groupPosition,int childPosition){

        return datasBySubject.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition){

        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition,int childPosition){

        return childPosition;
    }

    @Override
    public boolean hasStableIds(){
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent){

        GroupHolder groupHolder = null;
        if(convertView == null){

            convertView = LayoutInflater.from(context).inflate(R.layout.expandable_subject_parent,null);
            groupHolder = new GroupHolder();
            groupHolder.textView = (TextView)convertView.findViewById(R.id.textview_subject_parent);
            groupHolder.setGroupPosition(groupPosition);
            groupHolder.setChildPosition(-1);
            convertView.setTag(groupHolder);

        }
        else{

            groupHolder = (GroupHolder)convertView.getTag();
        }

        groupHolder.textView.setText(groupName.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition,int childPosition,
                                boolean isLastChild,View convertView,ViewGroup parent){

        ChildHolder childHolder = null;
        if(convertView == null){

            convertView = LayoutInflater.from(context).inflate(R.layout.expandable_subject_child,null);
            childHolder = new ChildHolder();
            childHolder.textView = (TextView)convertView.findViewById(R.id.textview_subject_child);
            childHolder.setGroupPosition(groupPosition);
            childHolder.setChildPosition(childPosition);
            convertView.setTag(childHolder);
        }
        else{

            childHolder = (ChildHolder)convertView.getTag();
        }

        UnFinishedStudyPlan unFinishedStudyPlan = datasBySubject
                .get(groupPosition)
                .get(childPosition);
        childHolder.textView.setText(unFinishedStudyPlan.getIndex()+"\n"
                + "科目： " + unFinishedStudyPlan.getSubject() + "\n" + "方式： "+unFinishedStudyPlan.getWay()
                +"\n" + "重要性： " + unFinishedStudyPlan.getImportance() + "\n"
                + "截止时间： "+ unFinishedStudyPlan.getEndTime() + "\n" + "创建时间： " +unFinishedStudyPlan.getCreatedTime()
                + "\n"+ "内容： " + unFinishedStudyPlan.getContent());

        return convertView;


    }

    @Override
    public boolean isChildSelectable(int groupPosition,int childPosition){

        return true;
    }
    private class GroupHolder extends ClickRecord{

        TextView textView;
    }

    private class ChildHolder extends ClickRecord{

        TextView textView;
    }


    public void itemChanged(){

        Pair<UnFinishedStudyPlan> pair = Data.getDatasInOrderOfSubject();
        groupName = pair.getGroupName();
        datasBySubject = pair.getDatas();
        notifyDataSetChanged();
    }

    public UnFinishedStudyPlan getData(int group,int child){

        return datasBySubject.get(group).get(child);
    }
}
