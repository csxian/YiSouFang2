package com.example.jon.yisoufang2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.example.jon.yisoufang2.R;

/**
 * Created by Jon on 2016/10/19.
 */
public class SplashActivity extends Activity {

    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    boolean isFirstLoc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_splash);

        mLocClient = new LocationClient(getApplicationContext());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        option.setIsNeedAddress(true);
        mLocClient.setLocOption(option);
        mLocClient.start();


    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null ) {
                return;
            }

            if (isFirstLoc) {
                isFirstLoc = false;
                String city = location.getCity();
                String newCity = null;
                if(city.contains("市")){
                    newCity = city.substring(0,city.indexOf("市"));
                }
                Toast.makeText(getApplicationContext(), newCity, Toast.LENGTH_LONG).show();

                prepareStart(newCity);

            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    public void prepareStart(final String cityName){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                intent.putExtra("cityName",cityName);
                startActivity(intent);
                finish();
            }
        },1000*2);

    }

    @Override
    public void onDestroy() {
        mLocClient.stop();
        super.onDestroy();
    }
}
