package com.lwx.likestudy.contract;

import com.lwx.likestudy.data.model.FinishedStudyPlan;
import com.lwx.likestudy.data.model.StudyTime;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;

import java.util.List;

import rx.Observable;


/**
 * Created by 36249 on 2016/10/28.
 */
public interface DataBaseContract {
    //
    Observable<List<UnFinishedStudyPlan>> getUnFinishedStudyPlans();

    Observable<UnFinishedStudyPlan> create(UnFinishedStudyPlan unFinishedStudyPlan);

    Observable<UnFinishedStudyPlan> delete(UnFinishedStudyPlan unFinishedStudyPlan);

    Observable<UnFinishedStudyPlan> update(UnFinishedStudyPlan unFinishedStudyPlan);


    //
    Observable<List<FinishedStudyPlan>> getFinishedStudyPlans();

    Observable<FinishedStudyPlan> create(FinishedStudyPlan finishedStudyPlan);

    Observable<FinishedStudyPlan> delete(FinishedStudyPlan finishedStudyPlan);


    //

    Observable<List<StudyTime>> getStudyTimes();

    Observable<StudyTime> create(StudyTime studyTime);

    Observable<StudyTime> delete(StudyTime studyTime);

}
