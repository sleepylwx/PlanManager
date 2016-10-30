package com.lwx.likestudy.data.model;

import android.os.Parcel;
import android.os.Parcelable;

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

    private long createdTime;

    private String subject;

    private String way;

    private String content;

    private long endTime;



    private int importance = 0;
    public UnFinishedStudyPlan(){

    }

    public UnFinishedStudyPlan(long createdTime, String subject, String way, String content,
                               long endTime, int importance) {

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

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
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

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    private void readFromParcel(Parcel in){

        this.id = in.readInt();
        this.createdTime = in.readLong();
        this.subject = in.readString();
        this.way = in.readString();
        this.content = in.readString();
        this.endTime = in.readLong();
        this.importance = in.readInt();
    }

    @Override
    public int describeContents(){
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest,int flags){

        dest.writeInt(this.id);
        dest.writeLong(this.createdTime);
        dest.writeString(this.subject);
        dest.writeString(this.way);
        dest.writeString(this.content);
        dest.writeLong(this.endTime);
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
