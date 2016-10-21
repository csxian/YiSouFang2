package com.example.jon.yisoufang2.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jon.yisoufang2.R;
import com.example.jon.yisoufang2.bean.SecondHandDetailResult;
import com.example.jon.yisoufang2.presenter.SecondHandDetailPresent;
import com.example.jon.yisoufang2.view.SecondHandDetailView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Jon on 2016/10/17.
 */
public class SecondHandDetail extends AppCompatActivity implements SecondHandDetailView{

    private TextView tvSecondTitle;
    private TextView tvSecondPrice;
    private TextView tvSecondPriceAvg;
    private TextView tvSecondArea;
    private TextView tvSecondHouseType;
    private TextView tvSecondStorey;
    private TextView tvSecondOrientation;
    private TextView tvSecondDeco;
    private TextView tvSecondDecade;
    private TextView tvFloorType;
    private TextView tvHouseNum;
    private TextView tvCommName;
    private TextView tvSubwayInfo;
    private TextView tvEvaluaion;
    private TextView tvAgentName;
    private TextView tvAgentPhone;
    private TextView tvOnlineTime;
    private TextView tvSecondHandMainTitle;
    private SecondHandDetailPresent presenter;
    private Banner mBanner;
    private LinearLayout llSecondHandShare;
    private LinearLayout llSecondHandPhoneCall;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondhand_detail);
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
        presenter = new SecondHandDetailPresent(this);
        presenter.requestSecondHandDetail(houseNum);



    }


    private void initView(){
        tvSecondTitle = (TextView) findViewById(R.id.secondhand_house_title);
        tvSecondPrice = (TextView) findViewById(R.id.secondhand_house_price);
        tvSecondHouseType = (TextView) findViewById(R.id.secondhand_house_type);
        tvSecondArea = (TextView) findViewById(R.id.secondhand_house_area);
        tvSecondPriceAvg = (TextView) findViewById(R.id.secondhand_price_avg);
        tvSecondStorey = (TextView) findViewById(R.id.secondhand_hosue_storey);
        tvSecondOrientation = (TextView) findViewById(R.id.secondhand_house_orientation);
        tvSecondDeco = (TextView) findViewById(R.id.secondhand_house_deco);
        //楼型
        tvFloorType = (TextView) findViewById(R.id.secondhand_floor_type);

        tvSecondDecade = (TextView) findViewById(R.id.secondhand_house_decade);
        tvSubwayInfo = (TextView) findViewById(R.id.secondhand_subway_info);
        tvHouseNum = (TextView) findViewById(R.id.secondhand_house_num);

        tvCommName = (TextView) findViewById(R.id.secondhand_comm_name);
        tvAgentName = (TextView) findViewById(R.id.secondhand_agent_name);
        tvAgentPhone = (TextView) findViewById(R.id.secondhand_agent_phone);
        tvEvaluaion = (TextView) findViewById(R.id.secondhand_evaluation);
        tvSecondHandMainTitle = (TextView) findViewById(R.id.secondhand_main_title);
        tvOnlineTime = (TextView) findViewById(R.id.secondhand_online_time);

        mBanner = (Banner) findViewById(R.id.banner);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(3000);
        mBanner.setIndicatorGravity(BannerConfig.CENTER);

        llSecondHandShare = (LinearLayout) findViewById(R.id.ll_secondhand_share);
        llSecondHandPhoneCall = (LinearLayout) findViewById(R.id.ll_secondhand_phonecall);

        llSecondHandPhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+tvAgentPhone.getText()));
                startActivity(intent);
            }
        });

        llSecondHandShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
                String content = "来自易搜房： "+tvSecondTitle.getText()+"售价为:"+tvSecondPrice.getText()
                        +",面积："+tvSecondArea.getText()+","+tvSecondHouseType.getText()+"。";
                intent.putExtra(Intent.EXTRA_TEXT, content);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, getTitle()));

            }
        });

    }

    @Override
    public void updateData(SecondHandDetailResult result) {

        tvSecondTitle.setText(result.getHouseTitle());
        String price = result.getHouseSellPrice()/10000 + "万";
        tvSecondPrice.setText(price);
        tvSecondHouseType.setText(result.getHouseType());
        String area = result.getHouseArea()+"平米";
        tvSecondArea.setText(area);
        String priceAvg = result.getHouseAvgPrice()+"元/平";
        tvSecondPriceAvg.setText(priceAvg);
        tvSecondStorey.setText(result.getHouseStorey());
        tvSecondOrientation.setText(result.getHouseOrientation());
        tvSecondDeco.setText(result.getHouseDeco());
        //楼型
        tvFloorType.setText(result.getFloorType());

        tvOnlineTime.setText(result.getOnlineTime());
        tvSecondDecade.setText(result.getHouseDecade());
        tvSubwayInfo.setText(result.getSubwayInfo());
        tvHouseNum.setText(result.getHouseNum());

        tvCommName.setText(result.getCommName());
        tvAgentName.setText(result.getAgentName());

        tvAgentPhone.setText(result.getAgentPhone());
        tvEvaluaion.setText(result.getEvaluation());

        String mainTitle = result.getCommName()+result.getHouseType().substring(0,2);
        tvSecondHandMainTitle.setText(mainTitle);

        mBanner.setImages(result.getPictures());
        mBanner.start();
    }

    @Override
    public void errorToast(String msg) {
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
