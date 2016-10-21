package com.example.jon.yisoufang2.biz;

import com.example.jon.yisoufang2.bean.MapMarkerResult;
import com.example.jon.yisoufang2.bean.RentHouseResult;
import com.example.jon.yisoufang2.bean.SecondHandHouseResult;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Jon on 2016/10/18.
 */
public interface MapServiceBiz {

    @POST("MapMarker.do")
    Observable<MapMarkerResult> request4MarkerInfo(@Query("cityName") String cityName, @Query("regionTag") int regionTag,@Query("houseTag") String houseTag);

    @POST("MapMarkerDetail.do")
    Observable<RentHouseResult> request4RentMarkerDetail(@Query("commName") String commName,@Query("houseTag") String houseTag);

    @POST("MapMarkerDetail.do")
    Observable<SecondHandHouseResult> request4MarkerSecondHandDetail(@Query("commName") String commName, @Query("houseTag") String houseTag);

}
