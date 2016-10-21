package com.example.jon.yisoufang2.model;

import com.example.jon.yisoufang2.bean.MapMarkerResult;
import com.example.jon.yisoufang2.bean.RentHouseResult;
import com.example.jon.yisoufang2.bean.SecondHandHouseResult;

/**
 * Created by Jon on 2016/10/18.
 */
public interface MapMarkerModel {
    void requestMarkerInfo(String cityName,int regionType,String houseTag,MapRentCallback callback);

    void requestMarkerInfoDetail(String commName,String houseTag,MapRentDetailCallback callback);

    void requestMarkerInfoDetail(String commName,String houseTag,MapSecondHandDetailCallback callback);

    interface MapRentCallback{
        void requestMarkerSuccess(MapMarkerResult result);
        void requestMarkerFail(String failStr);
    }

    interface MapRentDetailCallback{
        void requestMarkerDetailSuceess(RentHouseResult result);
        void reuqestMarkerDetailFail(String failStr);
    }

    interface MapSecondHandDetailCallback{
        void requestMarkerDetailSuceess(SecondHandHouseResult result);
        void reuqestMarkerDetailFail(String failStr);
    }
}
