package com.example.jon.yisoufang2.view;

import com.example.jon.yisoufang2.bean.RentHouseResult;

/**
 * Created by Jon on 2016/10/10.
 */
public interface RentView {
    void updateListUI(RentHouseResult result);
    void errorToast(String msg);
}
