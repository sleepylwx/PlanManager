package com.lwx.likestudy.data.source;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.lwx.likestudy.contract.DataBaseContract;
import com.lwx.likestudy.data.model.FinishedStudyPlan;
import com.lwx.likestudy.data.model.StudyTime;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by 36249 on 2016/10/28.
 */
class LocalDataSouce implements DataBaseContract {

    private static final String TAG = "LocalDataSouce";

    private Context mContext;
    private LiteOrm mLiteOrm;

    public LocalDataSouce(Context mContext, LiteOrm mLiteOrm) {
        this.mContext = mContext;
        this.mLiteOrm = mLiteOrm;
    }

    @Override
    public Observable<List<UnFinishedStudyPlan>> getUnFinishedStudyPlans(){

        return Observable.create(new Observable.OnSubscribe<List<UnFinishedStudyPlan>>() {
            @Override
            public void call(Subscriber<? super List<UnFinishedStudyPlan>> subscriber) {

                List<UnFinishedStudyPlan> unFinishedStudyPlanList
                        = mLiteOrm.query(UnFinishedStudyPlan.class);

                subscriber.onNext(unFinishedStudyPlanList);
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<UnFinishedStudyPlan> create(final UnFinishedStudyPlan unFinishedStudyPlan){

        return Observable.create(new Observable.OnSubscribe<UnFinishedStudyPlan>() {
            @Override
            public void call(Subscriber<? super UnFinishedStudyPlan> subscriber) {

                long result = mLiteOrm.save(unFinishedStudyPlan);
                if(result > 0){
                    subscriber.onNext(unFinishedStudyPlan);
                }
                else{
                    subscriber.onError(new Exception("创建计划失败"));
                }
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<UnFinishedStudyPlan> update(final UnFinishedStudyPlan unFinishedStudyPlan){

        return Observable.create(new Observable.OnSubscribe<UnFinishedStudyPlan>() {
            @Override
            public void call(Subscriber<? super UnFinishedStudyPlan> subscriber) {

                long result = mLiteOrm.update(unFinishedStudyPlan);
                if(result > 0){
                    subscriber.onNext(unFinishedStudyPlan);
                }
                else{
                    subscriber.onError(new Exception("更新计划失败"));
                }

                subscriber.onCompleted();
            }
        });
    }
    @Override
    public Observable<UnFinishedStudyPlan> delete(final UnFinishedStudyPlan unFinishedStudyPlan){

        return Observable.create(new Observable.OnSubscribe<UnFinishedStudyPlan>() {
            @Override
            public void call(Subscriber<? super UnFinishedStudyPlan> subscriber) {

                long result = mLiteOrm.delete(unFinishedStudyPlan);
                if(result > 0){

                    subscriber.onNext(unFinishedStudyPlan);
                }
                else{

                    subscriber.onError(new Exception("删除计划失败"));
                }
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<List<FinishedStudyPlan>> getFinishedStudyPlans(){

        return Observable.create(new Observable.OnSubscribe<List<FinishedStudyPlan>>() {
            @Override
            public void call(Subscriber<? super List<FinishedStudyPlan>> subscriber) {

                List<FinishedStudyPlan> finishedStudyPlanList
                        = mLiteOrm.query(FinishedStudyPlan.class);

                subscriber.onNext(finishedStudyPlanList);
                subscriber.onCompleted();
            }
        });

    }
    @Override
    public Observable<FinishedStudyPlan> create(final FinishedStudyPlan finishedStudyPlan){

        return Observable.create(new Observable.OnSubscribe<FinishedStudyPlan>() {
            @Override
            public void call(Subscriber<? super FinishedStudyPlan> subscriber) {

                long result = mLiteOrm.save(finishedStudyPlan);

                if(result > 0){
                    subscriber.onNext(finishedStudyPlan);
                }
                else{
                    subscriber.onError(new Exception("完成计划失败"));
                }

                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<FinishedStudyPlan> delete(final FinishedStudyPlan finishedStudyPlan){

        return Observable.create(new Observable.OnSubscribe<FinishedStudyPlan>() {
            @Override
            public void call(Subscriber<? super FinishedStudyPlan> subscriber) {

                long result = mLiteOrm.delete(finishedStudyPlan);

                if(result > 0){

                    subscriber.onNext(finishedStudyPlan);
                }
                else{

                    subscriber.onError(new Exception("删除失败"));
                }

                subscriber.onCompleted();
            }
        });

    }

    @Override
    public Observable<List<StudyTime>> getStudyTimes(){

        return Observable.create(new Observable.OnSubscribe<List<StudyTime>>() {
            @Override
            public void call(Subscriber<? super List<StudyTime>> subscriber) {

                List<StudyTime> studyTimeList = mLiteOrm.query(StudyTime.class);

                subscriber.onNext(studyTimeList);
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<StudyTime> create(final StudyTime studyTime){

        return Observable.create(new Observable.OnSubscribe<StudyTime>() {
            @Override
            public void call(Subscriber<? super StudyTime> subscriber) {

                long result = mLiteOrm.save(studyTime);

                if(result > 0){

                    subscriber.onNext(studyTime);
                }
                else{

                    subscriber.onError(new Exception("完成自习失败"));
                }

                subscriber.onCompleted();
            }
        });
    }

    @Override
    public Observable<StudyTime> delete(final StudyTime studyTime){

        return Observable.create(new Observable.OnSubscribe<StudyTime>() {
            @Override
            public void call(Subscriber<? super StudyTime> subscriber) {

                long result = mLiteOrm.delete(studyTime);

                if(result > 0){

                    subscriber.onNext(studyTime);
                }
                else{
                    subscriber.onError(new Exception("删除失败"));
                }
            }
        });
    }


    @Override
    public Observable<List<UnFinishedStudyPlan>> deleteAll(){

        return Observable.create(new Observable.OnSubscribe<List<UnFinishedStudyPlan>>() {
            @Override
            public void call(Subscriber<? super List<UnFinishedStudyPlan>> subscriber) {

                long result = mLiteOrm.deleteAll(UnFinishedStudyPlan.class);

                if(result > 0){

                    subscriber.onNext(null);
                }
                else{

                    subscriber.onError(new Exception("删除失败"));
                }
            }
        });


    }

}
