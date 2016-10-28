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

    private long durateTime;

    private long finishedTime;

    private int satisfaction;

    UnFinishedStudyPlan plan;

    public FinishedStudyPlan() {

    }

    public FinishedStudyPlan(int id, long durateTime,long finishedTime, int satisfaction, UnFinishedStudyPlan plan) {
        this.id = id;
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

    public long getDurateTime() {
        return durateTime;
    }

    public void setDurateTime(long durateTime) {
        this.durateTime = durateTime;
    }

    public long getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(long finishedTime) {
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
        this.durateTime = in.readLong();
        this.finishedTime = in.readLong();
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
        dest.writeLong(this.durateTime);
        dest.writeLong(this.finishedTime);
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
