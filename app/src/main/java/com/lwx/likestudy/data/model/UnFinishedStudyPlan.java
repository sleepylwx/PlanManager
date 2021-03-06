package com.lwx.likestudy.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.litesuits.orm.db.annotation.Ignore;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by 36249 on 2016/10/28.
 */

@Table("unfinishedstudyplan")
public class UnFinishedStudyPlan implements Parcelable {


    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    private String createdTime;

    private String subject;

    private String way;

    private String content;

    private String endTime;



    private int importance = 0;

    @Ignore
    private int index;
    public UnFinishedStudyPlan(){

    }

    public UnFinishedStudyPlan(String createdTime, String subject, String way, String content,
                               String endTime, int importance) {

        this.createdTime = createdTime;
        this.subject = subject;
        this.way = way;
        this.content = content;
        this.endTime = endTime;
        this.importance = importance;
    }

    public UnFinishedStudyPlan(Parcel in){

        readFromParcel(in);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        dest.writeString(this.createdTime);
        dest.writeString(this.subject);
        dest.writeString(this.way);
        dest.writeString(this.content);
        dest.writeString(this.endTime);
        dest.writeInt(this.importance);
    }

    public static final Parcelable.Creator<UnFinishedStudyPlan>CREATOR = new Parcelable.Creator<UnFinishedStudyPlan>(){

        @Override
        public UnFinishedStudyPlan createFromParcel(Parcel source){
            return new UnFinishedStudyPlan(source);
        }

        @Override
        public UnFinishedStudyPlan[] newArray(int size){

            return new UnFinishedStudyPlan[size];
        }
    };
}
