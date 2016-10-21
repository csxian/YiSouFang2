package com.example.jon.yisoufang2.view;

import com.example.jon.yisoufang2.bean.SecondHandHouseResult;

/**
 * Created by Jon on 2016/10/17.
 */
public interface SecondHandView {
    void updateListUI(SecondHandHouseResult result);
    void errorToast(String msg);
}
