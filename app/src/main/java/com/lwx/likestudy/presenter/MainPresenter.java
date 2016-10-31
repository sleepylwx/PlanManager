package com.lwx.likestudy.presenter;

import com.lwx.likestudy.contract.PlanContract;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;
import com.lwx.likestudy.data.source.DataRepository;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 36249 on 2016/10/31.
 */
public class MainPresenter implements PlanContract.Presenter{


    private List<PlanContract.View> mViews;
    private DataRepository mRepository;
    private CompositeSubscription mSubscriptions;
    private static MainPresenter sInstance;

    //public static List<RecentContract.View> mViews;

    private MainPresenter(){

        mViews = new ArrayList<>();
        mRepository = DataRepository.getsIntance();
        mSubscriptions = new CompositeSubscription();
        //view.setPresenter(this);
    }


    public static MainPresenter getInstance(){

        if(sInstance == null){

            synchronized (MainPresenter.class){

                if(sInstance == null){

                    sInstance = new MainPresenter();
                }
            }
        }


        return sInstance;
    }
    @Override
    public void subscribe(){

        loadUnFinishedStudyPlans();
    }

    @Override
    public void unSubscribe(){

        mViews.clear();
        mSubscriptions.clear();
    }

    @Override
    public void loadUnFinishedStudyPlans(){

        Subscription subscription = mRepository.getUnFinishedStudyPlans()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<UnFinishedStudyPlan>>() {

                    @Override
                    public void onStart(){

                        for(int i = 0; i < mViews.size(); ++i){

                            mViews.get(i).showLoading();
                        }
                    }
                    @Override
                    public void onCompleted() {

                        for(int i = 0; i < mViews.size(); ++i){

                            mViews.get(i).hideLoading();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        for(int i = 0; i < mViews.size(); ++i){

                            mViews.get(i).hideLoading();
                            mViews.get(i).handleError(e);
                        }
                    }

                    @Override
                    public void onNext(List<UnFinishedStudyPlan> unFinishedStudyPlans) {

                        for(int i = 0; i < mViews.size(); ++i){

                            mViews.get(i).onUnFinishedStudyPlansLoaded(unFinishedStudyPlans);
                        }

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

                        for(int i = 0; i < mViews.size(); ++i){

                            mViews.get(i).showLoading();
                        }
                    }
                    @Override
                    public void onCompleted() {

                        for(int i = 0; i < mViews.size(); ++i){

                            mViews.get(i).hideLoading();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        for(int i = 0; i < mViews.size(); ++i){

                            mViews.get(i).hideLoading();
                            mViews.get(i).handleError(e);
                        }
                    }

                    @Override
                    public void onNext(UnFinishedStudyPlan unFinishedStudyPlan) {

                        for(int i = 0; i < mViews.size(); ++i){

                            mViews.get(i).onUnFinishedStudyPlanCreated(unFinishedStudyPlan);
                        }

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

                        for(int i = 0; i < mViews.size(); ++i){

                            mViews.get(i).showLoading();
                        }
                    }
                    @Override
                    public void onCompleted() {

                        for(int i = 0; i < mViews.size(); ++i){

                            mViews.get(i).hideLoading();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        for(int i = 0; i < mViews.size(); ++i){

                            mViews.get(i).hideLoading();
                            mViews.get(i).handleError(e);
                        }
                    }

                    @Override
                    public void onNext(UnFinishedStudyPlan unFinishedStudyPlan) {

                        for(int i = 0; i < mViews.size(); ++i){

                            mViews.get(i).onUnFinishedStudyPlanDeleted(unFinishedStudyPlan);
                        }
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

                        for(int i = 0; i < mViews.size(); ++i){

                            mViews.get(i).showLoading();
                        }
                    }
                    @Override
                    public void onCompleted() {

                        for(int i = 0; i < mViews.size(); ++i){

                            mViews.get(i).hideLoading();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        for(int i = 0; i < mViews.size(); ++i){

                            mViews.get(i).hideLoading();
                            mViews.get(i).handleError(e);
                        }
                    }

                    @Override
                    public void onNext(UnFinishedStudyPlan unFinishedStudyPlan) {

                        for(int i = 0; i < mViews.size(); ++i){

                            mViews.get(i).onUnFinishedStudyPlanUpdated(unFinishedStudyPlan);
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void addView(PlanContract.View view){

        mViews.add(view);
    }
}
