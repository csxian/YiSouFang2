package com.example.jon.yisoufang2.view;

import com.example.jon.yisoufang2.bean.RentDetailResult;

/**
 * Created by Jon on 2016/10/16.
 */
public interface RentDetailView {
    void updateData(RentDetailResult result);
    void errorToast(String msg);
}
