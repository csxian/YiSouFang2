package com.example.jon.yisoufang2.model;

import android.util.Log;

import com.example.jon.yisoufang2.bean.MapMarkerResult;
import com.example.jon.yisoufang2.bean.RentHouseResult;
import com.example.jon.yisoufang2.bean.SecondHandHouseResult;
import com.example.jon.yisoufang2.biz.MapServiceBiz;
import com.example.jon.yisoufang2.utils.RetrofitUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jon on 2016/10/18.
 */
public class MapMarkerModelImpl implements MapMarkerModel {
    @Override
    public void requestMarkerInfo(String cityName,int regionTag, String houseTag, final MapRentCallback callback) {
        RetrofitUtils.getInstance()
                .getRetrofit()
                .create(MapServiceBiz.class)
                .request4MarkerInfo(cityName,regionTag,houseTag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MapMarkerResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.requestMarkerFail(e.getMessage());
                    }

                    @Override
                    public void onNext(MapMarkerResult result) {
                        Log.d("result",result.toString());
                        callback.requestMarkerSuccess(result);
                    }
                });
    }

    @Override
    public void requestMarkerInfoDetail(String commName, String houseTag, final MapRentDetailCallback
            callback) {
        RetrofitUtils.getInstance()
                .getRetrofit()
                .create(MapServiceBiz.class)
                .request4RentMarkerDetail(commName,houseTag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RentHouseResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.reuqestMarkerDetailFail(e.getMessage());
                    }

                    @Override
                    public void onNext(RentHouseResult result) {
                        callback.requestMarkerDetailSuceess(result);
                    }
                });
    }

    @Override
    public void requestMarkerInfoDetail(String commName, String houseTag, final MapSecondHandDetailCallback callback) {
        RetrofitUtils.getInstance()
                .getRetrofit()
                .create(MapServiceBiz.class)
                .request4MarkerSecondHandDetail(commName,houseTag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SecondHandHouseResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.reuqestMarkerDetailFail(e.getMessage());
                    }

                    @Override
                    public void onNext(SecondHandHouseResult result) {
                        callback.requestMarkerDetailSuceess(result);
                    }
                });
    }
}
