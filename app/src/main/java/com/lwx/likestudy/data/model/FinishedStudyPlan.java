package com.lwx.likestudy.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.litesuits.orm.db.annotation.Ignore;
import com.litesuits.orm.db.annotation.Mapping;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;
import com.litesuits.orm.db.enums.Relation;

/**
 * Created by 36249 on 2016/10/28.
 */
@Table("FinishedStudyPlan")
public class FinishedStudyPlan  implements Parcelable {


    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    private String durateTime;

    private String finishedTime;

    private int satisfaction;


    private String createdTime;

    private String subject;

    private String way;
    private String content;
    private String endTime;
    private int importance;
    @Ignore
    private int index;

    public FinishedStudyPlan() {

    }

    public FinishedStudyPlan( String durateTime,String finishedTime, int satisfaction, UnFinishedStudyPlan plan) {

        this.durateTime = durateTime;
        this.finishedTime = finishedTime;
        this.satisfaction = satisfaction;
        this.createdTime = plan.getCreatedTime();
        this.subject = plan.getSubject();
        this.way = plan.getWay();
        this.content = plan.getContent();
        this.endTime = plan.getEndTime();
        this.importance = plan.getImportance();
//        this.plan = new UnFinishedStudyPlan(plan.getCreatedTime(),plan.getSubject(),
//                plan.getWay(),plan.getContent(),plan.getEndTime(),plan.getImportance());
    }

    public FinishedStudyPlan(Parcel in){

        readFromParcel(in);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDurateTime() {
        return durateTime;
    }

    public void setDurateTime(String durateTime) {
        this.durateTime = durateTime;
    }

    public String getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(String finishedTime) {
        this.finishedTime = finishedTime;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }


    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private void readFromParcel(Parcel in){

        this.id = in.readInt();
        this.durateTime = in.readString();
        this.finishedTime = in.readString();
        this.satisfaction = in.readInt();
        this.createdTime = in.readString();
        this.subject = in.readString();
        this.way = in.readString();
        this.content = in.readString();
        this.endTime = in.readString();
        this.importance = in.readInt();

    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest,int flags){

        dest.writeInt(this.id);
        dest.writeString(this.durateTime);
        dest.writeString(this.finishedTime);
        dest.writeInt(this.satisfaction);
        dest.writeString(this.createdTime);
        dest.writeString(this.subject);
        dest.writeString(this.way);
        dest.writeString(this.content);
        dest.writeString(this.endTime);
        dest.writeInt(this.importance);
    }

    public static final Parcelable.Creator<FinishedStudyPlan>CREATOR = new Parcelable.Creator<FinishedStudyPlan>(){

        @Override
        public FinishedStudyPlan createFromParcel(Parcel source){
            return new FinishedStudyPlan(source);
        }

        @Override
        public FinishedStudyPlan[] newArray(int size){

            return new FinishedStudyPlan[size];
        }
    };


}
