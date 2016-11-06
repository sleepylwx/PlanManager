package com.lwx.likestudy.data.source;

import com.lwx.likestudy.LikeStudyApplication;
import com.lwx.likestudy.contract.DataBaseContract;
import com.lwx.likestudy.data.source.db.LiteOrmHelper;
import com.lwx.likestudy.data.model.FinishedStudyPlan;
import com.lwx.likestudy.data.model.StudyTime;
import com.lwx.likestudy.data.model.UnFinishedStudyPlan;

import java.util.List;

import rx.Observable;

/**
 * Created by 36249 on 2016/10/28.
 */
public class DataRepository implements DataBaseContract {

    private static volatile DataRepository sIntance;

    private LocalDataSouce mLocalDataSouce;

   // private List<UnFinishedStudyPlan> mCachedUnFinishedStudy;

    private DataRepository(){

        mLocalDataSouce = new LocalDataSouce(LikeStudyApplication.getInstance(),
                LiteOrmHelper.getsInstance());
    }

    public static DataRepository getsIntance(){

        if(sIntance == null){

            synchronized (DataRepository.class){

                if(sIntance == null){

                    sIntance = new DataRepository();
                }
            }
        }
        return sIntance;
    }

    @Override
    public Observable<List<UnFinishedStudyPlan>> getUnFinishedStudyPlans(){

        return mLocalDataSouce.getUnFinishedStudyPlans();
    }

    @Override
    public Observable<UnFinishedStudyPlan> create(UnFinishedStudyPlan unFinishedStudyPlan){

        return mLocalDataSouce.create(unFinishedStudyPlan);
    }

    @Override
    public Observable<UnFinishedStudyPlan> update(UnFinishedStudyPlan unFinishedStudyPlan){

        return mLocalDataSouce.update(unFinishedStudyPlan);
    }

    @Override
    public Observable<UnFinishedStudyPlan> delete(UnFinishedStudyPlan unFinishedStudyPlan){

        return mLocalDataSouce.delete(unFinishedStudyPlan);
    }

    @Override
    public Observable<List<FinishedStudyPlan>> getFinishedStudyPlans(){

        return mLocalDataSouce.getFinishedStudyPlans();
    }

    @Override
    public Observable<FinishedStudyPlan> create(FinishedStudyPlan finishedStudyPlan){

        return mLocalDataSouce.create(finishedStudyPlan);
    }

    @Override
    public Observable<FinishedStudyPlan> delete(FinishedStudyPlan finishedStudyPlan){

        return mLocalDataSouce.delete(finishedStudyPlan);
    }

    @Override
    public Observable<List<StudyTime>> getStudyTimes(){

        return mLocalDataSouce.getStudyTimes();
    }

    @Override
    public Observable<StudyTime> create(StudyTime studyTime){

        return mLocalDataSouce.create(studyTime);
    }

    @Override
    public Observable<StudyTime> delete(StudyTime studyTime){

        return mLocalDataSouce.delete(studyTime);
    }

    @Override
    public Observable<List<UnFinishedStudyPlan>> deleteAll(){

        return mLocalDataSouce.deleteAll();
    }
}
