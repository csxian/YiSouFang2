package com.example.jon.yisoufang2.presenter;

/**
 * Created by Jon on 2016/10/10.
 */
public abstract class BasePresenter<T> {
    public T mView;

    public void attachView(T view){
        this.mView = view;
    }

    public void detach(){
        mView = null;
    }
}
