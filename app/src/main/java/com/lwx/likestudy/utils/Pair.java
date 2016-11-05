package com.lwx.likestudy.utils;

import com.lwx.likestudy.data.model.UnFinishedStudyPlan;

import java.util.List;

/**
 * Created by 36249 on 2016/11/5.
 */
public class Pair<T> {

    private List<String> groupName;
    private List<List<T>> datas;

    public Pair(List<String> groupName, List<List<T>> datas) {
        this.groupName = groupName;
        this.datas = datas;
    }

    public List<String> getGroupName() {
        return groupName;
    }

    public void setGroupName(List<String> groupName) {
        this.groupName = groupName;
    }

    public List<List<T>> getDatas() {
        return datas;
    }

    public void setDatas(List<List<T>> datas) {
        this.datas = datas;
    }
}
