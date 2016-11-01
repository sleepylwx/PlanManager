package com.lwx.likestudy.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by 36249 on 2016/10/28.
 */
@Table("studytime")
public class StudyTime implements Parcelable {


    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    private String durateTime;

    private String finishedTime;

    private int satisfaction;

    public StudyTime() {

    }



    public StudyTime(String durateTime,String finishedTime, int satisfaction) {

        this.durateTime = durateTime;

        this.finishedTime = finishedTime;
        this.satisfaction = satisfaction;
    }

    public StudyTime(Parcel in){

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

    private void readFromParcel(Parcel in){

        this.id = in.readInt();
        this.durateTime = in.readString();
        this.finishedTime = in.readString();
        this.satisfaction = in.readInt();
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
    }

    public static final Parcelable.Creator<StudyTime>CREATOR = new Parcelable.Creator<StudyTime>(){

        @Override
        public StudyTime createFromParcel(Parcel source){
            return new StudyTime(source);
        }

        @Override
        public StudyTime[] newArray(int size){

            return new StudyTime[size];
        }
    };

}
