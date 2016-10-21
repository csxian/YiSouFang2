package com.example.jon.yisoufang2.biz;

import com.example.jon.yisoufang2.bean.RentHouseResult;
import com.example.jon.yisoufang2.bean.RentHouseSearchCon;
import com.example.jon.yisoufang2.bean.SecondHandDetailResult;
import com.example.jon.yisoufang2.bean.SecondHandHouseCon;
import com.example.jon.yisoufang2.bean.SecondHandHouseResult;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Jon on 2016/10/17.
 */
public interface SecondHandServiceBiz {

    @POST("SecondHandHouse.do")
    Observable<SecondHandHouseResult> request4SecondHand(@Body SecondHandHouseCon condition);

    @POST("SecondHandHouseDetail.do")
    Observable<SecondHandDetailResult> request4SecondHandDetail(@Query("housenum") String houseNum);
}
