package com.example.jon.yisoufang2.presenter;

import com.example.jon.yisoufang2.bean.MapMarkerResult;
import com.example.jon.yisoufang2.model.MapMarkerModel;
import com.example.jon.yisoufang2.model.MapMarkerModelImpl;
import com.example.jon.yisoufang2.view.BMapView;

/**
 * Created by Jon on 2016/10/18.
 */
public class BMapPresent extends BasePresenter<BMapView> {
    private MapMarkerModel mapMarkerModel;
    public BMapPresent(BMapView view){
        attachView(view);
        mapMarkerModel = new MapMarkerModelImpl();
    }
    public void requestMarkerInfo(String cityName,int regionTag,String houseTag){
        mapMarkerModel.requestMarkerInfo(cityName, regionTag, houseTag, new MapMarkerModel.MapRentCallback() {

            @Override
            public void requestMarkerSuccess(MapMarkerResult result) {
                mView.updateMarker(result);
            }

            @Override
            public void requestMarkerFail(String failStr) {
                mView.showError(failStr);
            }
        });
    }
}
