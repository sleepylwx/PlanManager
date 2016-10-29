package com.lwx.likestudy.contract;

/**
 * Created by 36249 on 2016/10/29.
 */
public interface IAdapterView<T> {

    void bind(T item,int position);
}
