package com.example.jon.yisoufang2.model;

import com.example.jon.yisoufang2.bean.RentDetailResult;
import com.example.jon.yisoufang2.bean.RentHouseResult;
import com.example.jon.yisoufang2.bean.RentHouseSearchCon;

/**
 * Created by Jon on 2016/10/10.
 */
public interface RentSearchModel {
    void requestRentInfo(RentHouseSearchCon rentCondition , RentSearchCallback callback);

    void rquestRentDetail(String houseNum,RentDetailCallback callback);


    interface RentSearchCallback{
        void requestRentInfoSuccess(RentHouseResult rentInfo);
        void requestRentInfoFail(String failStr);
    }

    interface RentDetailCallback{
        void requestRentDetailSuccess(RentDetailResult rentDetailInfo);
        void requestRentInfoFail(String failStr);
    }
}
