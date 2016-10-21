package com.example.jon.yisoufang2.presenter;

import com.baidu.platform.comapi.map.B;
import com.example.jon.yisoufang2.bean.RentHouseResult;
import com.example.jon.yisoufang2.model.MapMarkerModel;
import com.example.jon.yisoufang2.model.MapMarkerModelImpl;
import com.example.jon.yisoufang2.view.RentView;

/**
 * Created by Jon on 2016/10/18.
 */
public class BMapRentDetailPresenter extends BasePresenter<RentView> {
    private MapMarkerModel mapMarkerModel;
    public BMapRentDetailPresenter(RentView view){
        attachView(view);
        mapMarkerModel = new MapMarkerModelImpl();
    }
    public void requestBMapRentDetail(String commName,String houseTag){
        mapMarkerModel.requestMarkerInfoDetail(commName, houseTag, new MapMarkerModel.MapRentDetailCallback() {

            @Override
            public void requestMarkerDetailSuceess(RentHouseResult result) {
                mView.updateListUI(result);
            }

            @Override
            public void reuqestMarkerDetailFail(String failStr) {
                mView.errorToast(failStr);
            }
        });
    }
}
