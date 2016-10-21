package com.example.jon.yisoufang2.activity;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jon.yisoufang2.R;
import com.example.jon.yisoufang2.bean.RentDetailResult;
import com.example.jon.yisoufang2.bean.RentHouseResult;
import com.example.jon.yisoufang2.presenter.RentDetailPresenter;
import com.example.jon.yisoufang2.view.RentDetailView;
import com.example.jon.yisoufang2.view.RentView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Jon on 2016/10/15.
 */
public class RentDetail extends AppCompatActivity implements RentDetailView{

    private TextView tvRentTitle;
    private TextView tvRentRental;
    private TextView tvRentArea;
    private TextView tvRentType;
    private TextView tvRentStorey;
    private TextView tvRentOrientation;
    private TextView tvRentDeco;
    private TextView tvRentDecade;
    private TextView tvFloorType;
    private TextView tvRentWay;
    private TextView tvHouseNum;
    private TextView tvCommName;
    private TextView tvSubwayInfo;
    private TextView tvEvaluaion;
    private TextView tvAgentName;
    private TextView tvAgentPhone;
    private TextView tvRentMainTitle;
    private Banner mBanner;
    private RentDetailPresenter presenter;
    private LinearLayout llPhoneCall;
    private LinearLayout llRentShare;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initView();
        Intent intent = getIntent();
        String houseNum = intent.getStringExtra("houseNum");
        presenter = new RentDetailPresenter(this);
        presenter.requestRentDetail(houseNum);



    }

    private void initView() {
        tvRentTitle = (TextView) findViewById(R.id.rent_house_title);
        tvRentRental = (TextView) findViewById(R.id.rent_house_rental);
        tvRentArea = (TextView) findViewById(R.id.rent_house_area);
        tvRentType = (TextView) findViewById(R.id.rent_house_type);
        tvRentStorey = (TextView) findViewById(R.id.rent_hosue_storey);
        tvRentOrientation = (TextView) findViewById(R.id.rent_house_orientation);
        tvRentDeco = (TextView) findViewById(R.id.rent_house_deco);
        tvRentDecade = (TextView) findViewById(R.id.rent_house_decade);
        tvFloorType = (TextView) findViewById(R.id.rent_floor_type);
        tvRentWay = (TextView) findViewById(R.id.rent_way);
        tvHouseNum = (TextView) findViewById(R.id.rent_house_num);
        tvCommName = (TextView) findViewById(R.id.rent_comm_name);
        tvSubwayInfo = (TextView) findViewById(R.id.rent_subway_info);
        tvEvaluaion = (TextView) findViewById(R.id.rent_evaluation);
        tvAgentName = (TextView) findViewById(R.id.rent_agent_name);
        tvAgentPhone = (TextView) findViewById(R.id.rent_agent_phone);
        tvRentMainTitle = (TextView) findViewById(R.id.rent_main_title);
        llPhoneCall = (LinearLayout) findViewById(R.id.ll_phone_call);
        llRentShare = (LinearLayout) findViewById(R.id.ll_rent_share);

        mBanner = (Banner) findViewById(R.id.banner);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(3000);
        mBanner.setIndicatorGravity(BannerConfig.CENTER);

        llPhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+tvAgentPhone.getText()));
                startActivity(intent);
            }
        });

        llRentShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
                String content = "来自易搜房： "+tvRentTitle.getText()+"租金为:"+tvRentRental.getText()
                        +",面积："+tvRentArea.getText()+","+tvRentType.getText()+"。";
                intent.putExtra(Intent.EXTRA_TEXT, content);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, getTitle()));

            }
        });

    }

    @Override
    public void updateData(RentDetailResult result) {

        tvRentTitle.setText(result.getHouseTitle());
        String rental = String.valueOf(result.getHouseRental())+"元/月";
        tvRentRental.setText(rental);
        String area = String.valueOf(result.getHouseArea())+"平米";
        tvRentArea.setText(area);
        tvRentType.setText(result.getHouseType());
        tvRentStorey.setText(result.getHouseStorey());
        tvRentOrientation.setText(result.getHouseOrientation());
        tvRentDeco.setText(result.getHouseDeco());
        tvRentDecade.setText(result.getHouseDecade());
        tvFloorType.setText(result.getFloorType());
        tvRentWay.setText(result.getRentWay());
        tvHouseNum.setText(result.getHouseNum());
        tvCommName.setText(result.getCommName());
        tvSubwayInfo.setText(result.getSubwayInfo());
        tvEvaluaion.setText(result.getEvaluation());
        tvAgentName.setText(result.getAgentName());
        tvAgentPhone.setText(result.getAgentPhone());

        String mainTitle = result.getCommName()+result.getHouseType().substring(0,2);
        tvRentMainTitle.setText(mainTitle);

        mBanner.setImages(result.getPictures());
        mBanner.start();

    }

    @Override
    public void errorToast(String msg) {
//        finish();
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        if(presenter != null){
            presenter.detach();
        }
        super.onDestroy();
    }

    public class GlideImageLoader implements ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
