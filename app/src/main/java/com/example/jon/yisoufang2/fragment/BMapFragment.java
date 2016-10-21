package com.example.jon.yisoufang2.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.example.jon.yisoufang2.R;
import com.example.jon.yisoufang2.activity.MapDetail;
import com.example.jon.yisoufang2.bean.MapMarkerResult;
import com.example.jon.yisoufang2.bean.RentHouseResult;
import com.example.jon.yisoufang2.bean.SecondHandHouseResult;
import com.example.jon.yisoufang2.presenter.BMapPresent;
import com.example.jon.yisoufang2.presenter.BMapRentDetailPresenter;
import com.example.jon.yisoufang2.presenter.BMapSHDetailPresenter;
import com.example.jon.yisoufang2.view.BMapView;
import com.example.jon.yisoufang2.view.MainToBmap;
import com.example.jon.yisoufang2.view.RentView;
import com.example.jon.yisoufang2.view.SecondHandView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jon on 2016/10/18.
 */
public class BMapFragment extends Fragment
        implements OnGetGeoCoderResultListener,BMapView,RentView,SecondHandView,MainToBmap {

    MapView mMapView = null;
    LocationClient mLocClient;
    GeoCoder mSearch = null;
    private View markView;
    private View popMarkView;
    public MyLocationListenner myListener = new MyLocationListenner();
    BaiduMap mBaiduMap;
    boolean isFirstLoc = true; // 是否首次定位
    private BMapPresent present;
    private BMapRentDetailPresenter rentDetailPresenter;
    private BMapSHDetailPresenter shDetailPresenter;
    TextView tv_citySelect;
    Button btBmapRent;
    Button btBmapSecondhand;
    private float firstZoom;
    private LatLng firstLatlng;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        SDKInitializer.initialize(getActivity().getApplicationContext());
        View rootView = inflater.inflate(R.layout.fragment_bmap,container,false);
        mMapView = (MapView) rootView.findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);


        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(getActivity());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        option.setIsNeedAddress(true);
        mLocClient.setLocOption(option);
        mLocClient.start();

        firstZoom = mBaiduMap.getMapStatus().zoom;//定位后的地图级别
        tv_citySelect = (TextView) getActivity().findViewById(R.id.tv_citySelect);
        btBmapRent = (Button) rootView.findViewById(R.id.bt_bmap_rent);
        btBmapSecondhand = (Button) rootView.findViewById(R.id.bt_bmap_secondhand);
        present = new BMapPresent(this);

        rentDetailPresenter = new BMapRentDetailPresenter(this);
        shDetailPresenter = new BMapSHDetailPresenter(this);

        btBmapRent.setSelected(true);

        btBmapRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!btBmapRent.isSelected()){
                    btBmapRent.setSelected(true);
                    MapStatus.Builder builder = new MapStatus.Builder();
                    builder.target(firstLatlng).zoom(firstZoom); //zoom == 14.0
                    mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder
                            .build()));
                    mBaiduMap.clear();
                    present.requestMarkerInfo(tv_citySelect.getText().toString(),1,"租房");
                }
                btBmapSecondhand.setSelected(false);


            }
        });

        btBmapSecondhand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!btBmapSecondhand.isSelected()){
                    btBmapSecondhand.setSelected(true);
                    MapStatus.Builder builder = new MapStatus.Builder();
                    builder.target(firstLatlng).zoom(firstZoom); //zoom == 14.0
                    mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder
                            .build()));
                    mBaiduMap.clear();
                    present.requestMarkerInfo(tv_citySelect.getText().toString(),1,"二手房");
                }
                btBmapRent.setSelected(false);
            }
        });


        //设置mark监听
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                LatLng position = marker.getPosition();
                Bundle extraInfo = marker.getExtraInfo();
                String regionName = extraInfo.getString("regionName");
                int type = extraInfo.getInt("type");
                float zoom = mBaiduMap.getMapStatus().zoom;
                MapStatus.Builder builder = new MapStatus.Builder();

                switch (type) {
                    case 1:
                        builder.target(position).zoom(zoom + 2); //zoom == 14.0
                        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder
                                .build()));
                        mBaiduMap.clear();
                        //加载数据
                        if(btBmapSecondhand.isSelected()){
                            present.requestMarkerInfo(tv_citySelect.getText().toString(),2,"二手房");
                        }else {
                            present.requestMarkerInfo(tv_citySelect.getText().toString(), 2, "租房");
                        }
                        break;
                    case 2:
                        builder.target(position).zoom(zoom + 2); //zoom == 14.0
                        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder
                                .build()));
                        mBaiduMap.clear();
//                        initMark(initMarkData3(), 3);
                        if(btBmapSecondhand.isSelected()){
                            present.requestMarkerInfo(tv_citySelect.getText().toString(),3,"二手房");
                        }else {
                            present.requestMarkerInfo(tv_citySelect.getText().toString(),3,"租房");
                        }

                        break;
                    case 3:
                        if(btBmapSecondhand.isSelected()){
                            Bundle bundle = marker.getExtraInfo();
                            String commName = bundle.getString("regionName");
                            shDetailPresenter.requestSHMarkerDetail(commName,"二手房");
                        }else {
                            Bundle bundle = marker.getExtraInfo();
                            String commName = bundle.getString("regionName");
                            rentDetailPresenter.requestBMapRentDetail(commName,"租房");
                        }


                }

                return false;
            }
        });

        //设置地图状态改变监听
        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                float currentZoom = mapStatus.zoom;
                if (currentZoom > 0 && currentZoom < 14) {
                    mBaiduMap.clear();
//                    initMark(initMarkData(), 1);

                    if(btBmapSecondhand.isSelected()){
                        present.requestMarkerInfo(tv_citySelect.getText().toString(),1,"二手房");
                    }else {
                        present.requestMarkerInfo(tv_citySelect.getText().toString(),1,"租房");
                    }
                }
                if (currentZoom >= 14 && currentZoom<16) {
                    mBaiduMap.clear();
//                    initMark(initMarkData2(), 2);
                    if(btBmapSecondhand.isSelected()){
                        present.requestMarkerInfo(tv_citySelect.getText().toString(),2,"二手房");
                    }else {
                        present.requestMarkerInfo(tv_citySelect.getText().toString(), 2, "租房");
                    }
                }
                if(currentZoom >= 16){
                    mBaiduMap.clear();
//                    initMark(initMarkData3(), 3);
                    if(btBmapSecondhand.isSelected()){
                        present.requestMarkerInfo(tv_citySelect.getText().toString(),3,"二手房");
                    }else {
                        present.requestMarkerInfo(tv_citySelect.getText().toString(),3,"租房");
                    }
                }

            }
        });



        return rootView;

    }


//    private List<RegionBean> initMarkData() {
//        List<RegionBean> rbList = new ArrayList<>();
//        RegionBean rb = new RegionBean();
//        rb.setLon("113.33384126612643");
//        rb.setLat("23.08762922878852");
//        rb.setCount(120);
//        rb.setRegionName("海珠");
//        rbList.add(rb);
//
//        RegionBean rb1 = new RegionBean();
//        rb1.setLon("113.276542");
//        rb1.setLat("23.164284");
//        rb1.setCount(320);
//        rb1.setRegionName("白云");
//        rbList.add(rb1);
//
//        RegionBean rb2 = new RegionBean();
//        rb2.setLon("113.3856428913265");
//        rb2.setLat("23.166129265424514");
//        rb2.setCount(230);
//        rb2.setRegionName("天河");
//        rbList.add(rb2);
//
//        return rbList;
//
//    }
//
//    private List<RegionBean> initMarkData2() {
//
//        List<RegionBean> rbList = new ArrayList<>();
//
//        RegionBean rb = new RegionBean();
//        rb.setLon("113.275242");
//        rb.setLat("23.209616");
//        rb.setCount(2);
//        rb.setRegionName("黄石路");
//        rbList.add(rb);
//
//        RegionBean rb1 = new RegionBean();
//        rb1.setLon("113.247732");
//        rb1.setLat("23.186646");
//        rb1.setCount(2);
//        rb1.setRegionName("同德围");
//        rbList.add(rb1);
//
//
//        return rbList;
//
//    }
//
//    private List<RegionBean> initMarkData3() {
//
//        List<RegionBean> rbList = new ArrayList<>();
//
//        RegionBean rb = new RegionBean();
//        rb.setLon("113.273146");
//        rb.setLat("23.210634");
//        rb.setCount(12);
//        rb.setRegionName("黄石花园");
//        rbList.add(rb);
//
//        RegionBean rb1 = new RegionBean();
//        rb1.setLon("113.275582");
//        rb1.setLat("23.209874");
//        rb1.setCount(22);
//        rb1.setRegionName("新苑大厦");
//        rbList.add(rb1);
//
//        RegionBean rb2 = new RegionBean();
//        rb2.setLon("113.239433");
//        rb2.setLat("23.171692");
//        rb2.setCount(20);
//        rb2.setRegionName("同德花园");
//        rbList.add(rb2);
//
//        RegionBean rb3 = new RegionBean();
//        rb3.setLon("113.244327");
//        rb3.setLat("23.182252");
//        rb3.setCount(3);
//        rb3.setRegionName("泽德花园");
//        rbList.add(rb3);
//
//
//        return rbList;
//
//    }


    private void initMark(List<MapMarkerResult.MapMarkerInfo> mapMarkerInfos, int type) {

        for (MapMarkerResult.MapMarkerInfo markerInfo : mapMarkerInfos) {
            LatLng point = new LatLng(Double.valueOf(markerInfo.getLat()), Double.valueOf(markerInfo.getLon()));
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(
                    getMarkView(markerInfo.getRegionName(), markerInfo.getCount()));
            if (type == 3) {
                bitmap = BitmapDescriptorFactory.fromView(
                        getPopMarkView(markerInfo.getRegionName(), markerInfo.getCount()));
            }
            Bundle bundle = new Bundle();
            bundle.putString("regionName", markerInfo.getRegionName());
            bundle.putInt("type", type);
            MarkerOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap)
                    .alpha(0.7f)
                    .extraInfo(bundle);
            mBaiduMap.addOverlay(option);
        }
//
//        LatLng point = new LatLng(23.08762922878852, 113.33384126612643);
//
//        BitmapDescriptor bitmap = BitmapDescriptorFactory
//                .fromView(View.inflate(MainActivity.this,R.layout.cmark_layout,null));
//
//
//        MarkerOptions option = new MarkerOptions()
//                .position(point)
//                .icon(bitmap);
//        mBaiduMap.addOverlay(option);

    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(getActivity(), "抱歉，未能找到结果", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        firstLatlng = result.getLocation();
//        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(firstLatlng));

        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(firstLatlng).zoom(firstZoom); //zoom == 14.0
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder
                .build()));

//       初始化数据
        present.requestMarkerInfo(tv_citySelect.getText().toString(),1,"租房");
    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

    }

    @Override
    public void updateMarker(MapMarkerResult result) {
        initMark(result.getMapMarkerInfoList(),result.getRegionTag());
    }

    @Override
    public void showError(String failStr) {
        Toast.makeText(getActivity(),failStr,Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateListUI(RentHouseResult result) {
        Intent intent = new Intent(getActivity(), MapDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("rentList", (Serializable) result.getRentInfoList());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void updateListUI(SecondHandHouseResult result) {
        Intent intent = new Intent(getActivity(), MapDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("secondHandList", (Serializable) result.getSecondHandInfoList());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void errorToast(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }


    //城市改变后的回调函数
    @Override
    public void updateDataCallback(String cityName) {
        mSearch.geocode(new GeoCodeOption().city(cityName).address(cityName));
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
//            if (isFirstLoc) {
//                isFirstLoc = false;
//                LatLng ll = new LatLng(location.getLatitude(),
//                        location.getLongitude());
//                MapStatus.Builder builder = new MapStatus.Builder();
//                builder.target(ll).zoom(18.0f);
//                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
//            }
            if (isFirstLoc) {
                isFirstLoc = false;
                String city = location.getCity();
                String newCity = null;
                if(city.contains("市")){
                   newCity = city.substring(0,city.indexOf("市"));
                }
                Toast.makeText(getActivity(), newCity, Toast.LENGTH_LONG).show();

                mSearch.geocode(new GeoCodeOption().city(newCity).address(newCity));

            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    //圆形标志
    private View getMarkView(String regionName, int count) {
        MarkViewHolder viewHolder;
        if (markView == null) {
            markView = View.inflate(getActivity(), R.layout.cmark_layout, null);
            viewHolder = new MarkViewHolder();
            viewHolder.regionName = (TextView) markView.findViewById(R.id.mark_name);
            viewHolder.count = (TextView) markView.findViewById(R.id.mark_count);
            markView.setTag(viewHolder);
        } else {
            viewHolder = (MarkViewHolder) markView.getTag();
        }
        viewHolder.regionName.setText(regionName);
        viewHolder.count.setText(String.valueOf(count));
        return markView;
    }

    private static class MarkViewHolder {
        public TextView regionName;
        public TextView count;
    }


    //显示小区的气泡标志
    private View getPopMarkView(String regionName, int count) {
        PopViewHolder viewHolder;
        if (popMarkView == null) {
            popMarkView = View.inflate(getActivity(), R.layout.overlay_pop, null);
            viewHolder = new PopViewHolder();
            viewHolder.content = (TextView) popMarkView.findViewById(R.id.content_tv);

            popMarkView.setTag(viewHolder);
        } else {
            viewHolder = (PopViewHolder) popMarkView.getTag();
        }
        String content = regionName + "(" + String.valueOf(count) + ")";
        viewHolder.content.setText(content);

        return popMarkView;
    }

    private static class PopViewHolder {
        public TextView content;
    }


    @Override
    public void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        present.detach();
        rentDetailPresenter.detach();
        shDetailPresenter.detach();
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }
}
