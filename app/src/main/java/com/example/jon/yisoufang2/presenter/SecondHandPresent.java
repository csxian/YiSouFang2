package com.example.jon.yisoufang2.presenter;

import android.view.View;

import com.example.jon.yisoufang2.bean.SecondHandHouseCon;
import com.example.jon.yisoufang2.bean.SecondHandHouseResult;
import com.example.jon.yisoufang2.model.SecondHandSearchModel;
import com.example.jon.yisoufang2.model.SecondHandSearchModelImpl;
import com.example.jon.yisoufang2.view.SecondHandView;

/**
 * Created by Jon on 2016/10/17.
 */
public class SecondHandPresent extends BasePresenter<SecondHandView> {
    private SecondHandSearchModel secondHandSearchModel;

    public SecondHandPresent(SecondHandView view){
        attachView(view);
        secondHandSearchModel = new SecondHandSearchModelImpl();
    }

    public void requestSecondHandData(SecondHandHouseCon condition){
        if(secondHandSearchModel == null || mView == null){
            return;
        }

        secondHandSearchModel.requestSecondHandInfo(condition, new SecondHandSearchModel.SecondHouseSearchCallback() {

            @Override
            public void requestSecondHandInfoSuccess(SecondHandHouseResult result) {
                if(result != null){
                    mView.updateListUI(result);
                    if(result.getStatus().equals("none")){
                        mView.errorToast("没有相关数据");
                    }
                }
            }

            @Override
            public void requestSecondHandInfoFail(String failStr) {
                mView.errorToast(failStr);
            }
        });
    }
}
