package com.lwx.likestudy.contract;

import com.lwx.likestudy.contract.BasePresenter;
import com.lwx.likestudy.contract.BaseView;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;

import java.util.List;

/**
 * Created by 36249 on 2016/10/28.
 */
public interface RecentContract {

    interface View extends BaseView<Presenter> {

        void showLoading();

        void hideLoading();

        void handleError(Throwable error);

        void onUnFinishedStudyPlansLoaded(List<UnFinishedStudyPlan> unFinishedStudyPlans);

        void onUnFinishedStudyPlanDeleted(UnFinishedStudyPlan unFinishedStudyPlan);

        void onUnFinishedStudyPlanUpdated(UnFinishedStudyPlan unFinishedStudyPlan);
    }

    interface Presenter extends BasePresenter {

        void loadUnFinishedStudyPlans();

        void deleteUnFinishedStudyPlan(UnFinishedStudyPlan unFinishedStudyPlan);

        void updateUnFinishedStudyPlan(UnFinishedStudyPlan unFinishedStudyPlan);

    }
}
