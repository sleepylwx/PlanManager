package com.lwx.likestudy.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

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

    UnFinishedStudyPlan plan;

    public FinishedStudyPlan() {

    }

    public FinishedStudyPlan( String durateTime,String finishedTime, int satisfaction, UnFinishedStudyPlan plan) {

        this.durateTime = durateTime;
        this.finishedTime = finishedTime;
        this.satisfaction = satisfaction;
        this.plan = plan;
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

    public UnFinishedStudyPlan getPlan() {
        return plan;
    }

    public void setPlan(UnFinishedStudyPlan plan) {
        this.plan = plan;
    }

    private void readFromParcel(Parcel in){

        this.id = in.readInt();
        this.durateTime = in.readString();
        this.finishedTime = in.readString();
        this.satisfaction = in.readInt();
        this.plan = in.readParcelable(UnFinishedStudyPlan.class.getClassLoader());

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
        dest.writeParcelable(this.plan,flags);
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
