package com.example.jon.yisoufang2.view;

import com.example.jon.yisoufang2.bean.MapMarkerResult;

/**
 * Created by Jon on 2016/10/18.
 */
public interface BMapView {
    void updateMarker(MapMarkerResult result);
    void showError(String failStr);
}
