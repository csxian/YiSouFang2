package com.example.jon.yisoufang2.model;

import com.example.jon.yisoufang2.bean.SecondHandDetailResult;
import com.example.jon.yisoufang2.bean.SecondHandHouseCon;
import com.example.jon.yisoufang2.bean.SecondHandHouseResult;
import com.example.jon.yisoufang2.biz.SecondHandServiceBiz;
import com.example.jon.yisoufang2.utils.RetrofitUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jon on 2016/10/17.
 */
public class SecondHandSearchModelImpl implements SecondHandSearchModel {
    @Override
    public void requestSecondHandInfo(SecondHandHouseCon condition, final SecondHouseSearchCallback
            callback) {
        RetrofitUtils.getInstance()
                .getRetrofit()
                .create(SecondHandServiceBiz.class)
                .request4SecondHand(condition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SecondHandHouseResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.requestSecondHandInfoFail(e.getMessage());
                    }

                    @Override
                    public void onNext(SecondHandHouseResult result) {
                        callback.requestSecondHandInfoSuccess(result);
                    }
                });
    }

    @Override
    public void requestSecondHandDetail(String houseNum, final SecondHandDetailCallback callback) {
        RetrofitUtils.getInstance()
                .getRetrofit()
                .create(SecondHandServiceBiz.class)
                .request4SecondHandDetail(houseNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SecondHandDetailResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.requestSecondHandDetailFail(e.getMessage());
                    }

                    @Override
                    public void onNext(SecondHandDetailResult result) {
                        callback.requestSecondHandDetailSuccess(result);
                    }
                });
    }
}
