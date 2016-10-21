package com.example.jon.yisoufang2.presenter;

import com.example.jon.yisoufang2.bean.SecondHandHouseResult;
import com.example.jon.yisoufang2.model.MapMarkerModel;
import com.example.jon.yisoufang2.model.MapMarkerModelImpl;
import com.example.jon.yisoufang2.view.SecondHandView;

/**
 * Created by Jon on 2016/10/19.
 */
public class BMapSHDetailPresenter extends BasePresenter<SecondHandView> {
    private MapMarkerModel mapMarkerModel;
    public BMapSHDetailPresenter(SecondHandView view){
        attachView(view);
        mapMarkerModel = new MapMarkerModelImpl();
    }
    public void requestSHMarkerDetail(String commName,String houseTag){
        mapMarkerModel.requestMarkerInfoDetail(commName, houseTag, new MapMarkerModel.MapSecondHandDetailCallback() {

            @Override
            public void requestMarkerDetailSuceess(SecondHandHouseResult result) {
                mView.updateListUI(result);
            }

            @Override
            public void reuqestMarkerDetailFail(String failStr) {
                mView.errorToast(failStr);
            }
        });
    }
}
