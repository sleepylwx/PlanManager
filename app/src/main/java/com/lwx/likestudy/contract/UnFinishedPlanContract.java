package com.lwx.likestudy.contract;

import com.lwx.likestudy.contract.BasePresenter;
import com.lwx.likestudy.contract.BaseView;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;

import java.util.List;

/**
 * Created by 36249 on 2016/10/28.
 */
public interface UnFinishedPlanContract {


    interface View extends BaseView<Presenter> {

        void showLoading();

        void hideLoading();

        void handleError(Throwable error);


        void onUnFinishedStudyPlansLoaded(List<UnFinishedStudyPlan> unFinishedStudyPlans);

        void onUnFinishedStudyPlanCreated(UnFinishedStudyPlan unFinishedStudyPlan);

        void onUnFinishedStudyPlanDeleted(UnFinishedStudyPlan unFinishedStudyPlan);

        void onUnFinishedStudyPlanUpdated(UnFinishedStudyPlan unFinishedStudyPlan);

        void onUnFinishedStudyPlanAllDeleted();
    }

    interface Presenter extends BasePresenter<View> {

        void loadUnFinishedStudyPlans();

        void createUnFinishedStudyPlan(UnFinishedStudyPlan unFinishedStudyPlan);

        void deleteUnFinishedStudyPlan(UnFinishedStudyPlan unFinishedStudyPlan);

        void updateUnFinishedStudyPlan(UnFinishedStudyPlan unFinishedStudyPlan);

        void deleteAllUnFinishedPlan();

    }
}
