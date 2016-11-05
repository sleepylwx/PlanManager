package com.lwx.likestudy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.lwx.likestudy.R;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.utils.ClickRecord;
import com.lwx.likestudy.utils.Data;
import com.lwx.likestudy.utils.Pair;

import org.w3c.dom.Text;

import java.security.acl.Group;
import java.util.List;

/**
 * Created by 36249 on 2016/11/5.
 */
public class WayPlanAdapter extends BaseExpandableListAdapter {

    Context context;

    List<String> groupName;
    List<List<UnFinishedStudyPlan>> datasByWay;

    public WayPlanAdapter(Context context){

        this.context = context;
        Pair<UnFinishedStudyPlan> pair = Data.getDatasInOrderOfWay();
        groupName = pair.getGroupName();
        datasByWay = pair.getDatas();
    }

    @Override
    public int getGroupCount(){

        if(datasByWay == null){

            return 0;
        }
        else{

            return datasByWay.size();
        }
    }

    @Override
    public int getChildrenCount(int groupPosition){

        if(datasByWay == null){
            return 0;
        }

        return datasByWay.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition){

        return datasByWay.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition,int childPosition){

        return datasByWay.get(groupPosition).get(childPosition);
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

            convertView = LayoutInflater.from(context).inflate(R.layout.expandable_way_parent,null);
            groupHolder = new GroupHolder();
            groupHolder.textView = (TextView)convertView.findViewById(R.id.textview_way_parent);
            groupHolder.setGroupPosition(groupPosition);
            groupHolder.setChildPosition(-1);
            convertView.setTag(groupHolder);
        }
        else{

            groupHolder = (GroupHolder) convertView.getTag();

        }

        groupHolder.textView.setText(groupName.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPostion,int childPostion,
                             boolean isLastChild,View convertView,ViewGroup parent){

        ChildHolder childHolder = null;
        if(convertView == null){

            convertView = LayoutInflater.from(context).inflate(R.layout.expandable_way_child,null);
            childHolder = new ChildHolder();
            childHolder.textView = (TextView)convertView.findViewById(R.id.textview_way_child);
            childHolder.setGroupPosition(groupPostion);
            childHolder.setChildPosition(childPostion);
            convertView.setTag(childHolder);
        }
        else{

            childHolder = (ChildHolder)convertView.getTag();
        }

        UnFinishedStudyPlan unFinishedStudyPlan = datasByWay.get(groupPostion)
                                                            .get(childPostion);
        childHolder.textView.setText(unFinishedStudyPlan.getIndex()+"\n"
                + "科目： " + unFinishedStudyPlan.getSubject() + "\n" + "方式： "+unFinishedStudyPlan.getWay()
                +"\n" + "重要性： " + unFinishedStudyPlan.getImportance() + "\n"
                + "截止时间： "+ unFinishedStudyPlan.getEndTime() + "\n" + "创建时间： " +unFinishedStudyPlan.getCreatedTime()
                + "\n" + "内容： " + unFinishedStudyPlan.getContent());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition,int childPosition){

        return true;
    }

    private class GroupHolder extends ClickRecord {

        TextView textView;
    }

    private class ChildHolder extends ClickRecord{

        TextView textView;
    }

    public void itemChanged(){

        Pair<UnFinishedStudyPlan> pair = Data.getDatasInOrderOfWay();
        groupName = pair.getGroupName();
        datasByWay = pair.getDatas();
        notifyDataSetChanged();
    }

    public UnFinishedStudyPlan getData(int group,int child){

        return datasByWay.get(group).get(child);

    }
}
