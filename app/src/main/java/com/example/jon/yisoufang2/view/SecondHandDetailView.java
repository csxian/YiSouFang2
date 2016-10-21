package com.example.jon.yisoufang2.view;

import com.example.jon.yisoufang2.bean.SecondHandDetailResult;

/**
 * Created by Jon on 2016/10/17.
 */
public interface SecondHandDetailView {
    void updateData(SecondHandDetailResult result);
    void errorToast(String msg);
}
