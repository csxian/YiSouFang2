package com.example.jon.yisoufang2.model;

import com.example.jon.yisoufang2.bean.RentHouseResult;
import com.example.jon.yisoufang2.bean.SecondHandDetailResult;
import com.example.jon.yisoufang2.bean.SecondHandHouseCon;
import com.example.jon.yisoufang2.bean.SecondHandHouseResult;

/**
 * Created by Jon on 2016/10/17.
 */
public interface SecondHandSearchModel {
    void requestSecondHandInfo(SecondHandHouseCon condition,SecondHouseSearchCallback callback);

    void requestSecondHandDetail(String houseNum, SecondHandDetailCallback callback);


    interface SecondHouseSearchCallback{
        void requestSecondHandInfoSuccess(SecondHandHouseResult result);
        void requestSecondHandInfoFail(String failStr);
    }

    interface SecondHandDetailCallback{
        void requestSecondHandDetailSuccess(SecondHandDetailResult result);
        void requestSecondHandDetailFail(String failStr);
    }

}
