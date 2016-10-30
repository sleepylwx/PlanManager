package com.lwx.likestudy.presenter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lwx.likestudy.contract.RecentContract;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.data.source.DataRepository;

import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by 36249 on 2016/10/28.
 */
public class RecentPresenter implements RecentContract.Presenter{

    private RecentContract.View mView;
    private DataRepository mRepository;
    private CompositeSubscription mSubscriptions;
    public RecentPresenter(RecentContract.View view){

        this.mView = view;
        mRepository = DataRepository.getsIntance();
        mSubscriptions = new CompositeSubscription();
        this.mView.setPresenter(this);
    }


    @Override
    public void subscribe(){

        loadUnFinishedStudyPlans();
    }

    @Override
    public void unSubscribe(){

        mView = null;
        //mSubscriptions.clear();
    }

    @Override
    public void loadUnFinishedStudyPlans(){

        Subscription subscription = mRepository.getUnFinishedStudyPlans()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<UnFinishedStudyPlan>>() {

                    @Override
                    public void onStart(){

                        mView.showLoading();
                    }
                    @Override
                    public void onCompleted() {

                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.hideLoading();
                        mView.handleError(e);
                    }

                    @Override
                    public void onNext(List<UnFinishedStudyPlan> unFinishedStudyPlans) {

                        mView.onUnFinishedStudyPlansLoaded(unFinishedStudyPlans);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void createUnFinishedStudyPlan(UnFinishedStudyPlan unFinishedStudyPlan){

        Subscription subscription = mRepository.create(unFinishedStudyPlan)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UnFinishedStudyPlan>() {

                    @Override
                    public void onStart(){

                        mView.showLoading();
                    }
                    @Override
                    public void onCompleted() {

                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.hideLoading();
                        mView.handleError(e);
                    }

                    @Override
                    public void onNext(UnFinishedStudyPlan unFinishedStudyPlan) {

                        mView.onUnFinishedStudyPlanCreated(unFinishedStudyPlan);
                    }
                });
    }
    @Override
    public void deleteUnFinishedStudyPlan(UnFinishedStudyPlan unFinishedStudyPlan){

        Subscription subscription = mRepository.delete(unFinishedStudyPlan)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UnFinishedStudyPlan>() {

                    @Override
                    public void onStart(){

                        mView.showLoading();
                    }
                    @Override
                    public void onCompleted() {

                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.hideLoading();
                        mView.handleError(e);
                    }

                    @Override
                    public void onNext(UnFinishedStudyPlan unFinishedStudyPlan) {

                        mView.onUnFinishedStudyPlanDeleted(unFinishedStudyPlan);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void updateUnFinishedStudyPlan(UnFinishedStudyPlan unFinishedStudyPlan){

        Subscription subscription = mRepository.update(unFinishedStudyPlan)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UnFinishedStudyPlan>() {

                    @Override
                    public void onStart(){

                        mView.showLoading();
                    }
                    @Override
                    public void onCompleted() {

                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.hideLoading();
                        mView.handleError(e);
                    }

                    @Override
                    public void onNext(UnFinishedStudyPlan unFinishedStudyPlan) {

                        mView.onUnFinishedStudyPlanUpdated(unFinishedStudyPlan);
                    }
                });
        mSubscriptions.add(subscription);
    }

}
