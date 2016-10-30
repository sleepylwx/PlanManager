package com.lwx.likestudy.contract;

/**
 * Created by 36249 on 2016/10/28.
 */
interface BaseView<T> {

    void setPresenter(T presenter);

    T getPresenter();
}
