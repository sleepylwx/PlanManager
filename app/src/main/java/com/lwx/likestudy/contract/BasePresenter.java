package com.lwx.likestudy.contract;

/**
 * Created by 36249 on 2016/10/28.
 */
interface BasePresenter  {

    void addView(PlanContract.View view);

    void subscribe();

    void unSubscribe();
}
