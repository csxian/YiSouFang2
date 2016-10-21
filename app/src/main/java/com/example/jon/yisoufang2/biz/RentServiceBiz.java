package com.example.jon.yisoufang2.biz;

import com.example.jon.yisoufang2.bean.RentDetailResult;
import com.example.jon.yisoufang2.bean.RentHouseResult;
import com.example.jon.yisoufang2.bean.RentHouseSearchCon;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Jon on 2016/10/10.
 */
public interface RentServiceBiz {

    @POST("RentHouse.do")
    Observable<RentHouseResult> request4Rent(@Body RentHouseSearchCon rentCondition);

    @POST("RentHouseDetail.do")
    Observable<RentDetailResult> request4RentDetail(@Query("housenum") String houseNum);
}
