package com.example.jon.yisoufang2.model;

import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.example.jon.yisoufang2.bean.RentDetailResult;
import com.example.jon.yisoufang2.bean.RentHouseResult;
import com.example.jon.yisoufang2.bean.RentHouseSearchCon;
import com.example.jon.yisoufang2.biz.RentServiceBiz;
import com.example.jon.yisoufang2.utils.RetrofitUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jon on 2016/10/10.
 */
public class RentSearchModelImpl implements RentSearchModel {


    @Override
    public void requestRentInfo(RentHouseSearchCon rentCondition, final RentSearchCallback callback) {
        RetrofitUtils.getInstance()
                .getRetrofit()
                .create(RentServiceBiz.class)
                .request4Rent(rentCondition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RentHouseResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.requestRentInfoFail(e.getMessage());
                    }

                    @Override
                    public void onNext(RentHouseResult result) {
                        callback.requestRentInfoSuccess(result);
                    }
                });
    }

    @Override
    public void rquestRentDetail(String houseNum, final RentDetailCallback callback) {
        RetrofitUtils.getInstance()
                .getRetrofit()
                .create(RentServiceBiz.class)
                .request4RentDetail(houseNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RentDetailResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.requestRentInfoFail(e.getMessage());
                    }

                    @Override
                    public void onNext(RentDetailResult rentDetailResult) {
                        callback.requestRentDetailSuccess(rentDetailResult);
                    }
                });
    }
}
