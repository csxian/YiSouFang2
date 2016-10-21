package com.example.jon.yisoufang2.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.jon.yisoufang2.R;
import com.example.jon.yisoufang2.bean.RentHouseResult;
import com.example.jon.yisoufang2.bean.RentHouseSearchCon;
import com.example.jon.yisoufang2.bean.SecondHandHouseCon;
import com.example.jon.yisoufang2.bean.SecondHandHouseResult;
import com.example.jon.yisoufang2.presenter.RentPresenter;
import com.example.jon.yisoufang2.presenter.SecondHandPresent;
import com.example.jon.yisoufang2.utils.ClearEditText;
import com.example.jon.yisoufang2.view.RentView;
import com.example.jon.yisoufang2.view.SecondHandView;

import java.io.Serializable;

public class SearchActivity extends AppCompatActivity implements RentView,SecondHandView {

    TextView tvHouseSelect;
    TextView tvHouseSearch;
    ClearEditText content;
    TextView tvErrorMsg;
    private RentPresenter rentPresenter;
    private SecondHandPresent secondHandPresent;
    private RentHouseSearchCon rentCondition;
    private SecondHandHouseCon secondHandCondition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvHouseSelect = (TextView) findViewById(R.id.tv_house_select);
        tvHouseSearch = (TextView) findViewById(R.id.tv_house_search);
        content = (ClearEditText) findViewById(R.id.filter_edit);
        tvErrorMsg = (TextView) findViewById(R.id.tv_error_msg);

        Intent intent = getIntent();
        final String cityName = intent.getStringExtra("cityName");

        secondHandPresent = new SecondHandPresent(this);
        rentPresenter = new RentPresenter(this);

        tvHouseSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });

        tvHouseSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvHouseSelect.getText().toString().equals("租房")){
                    rentCondition = new RentHouseSearchCon();
                    rentCondition.setCityName(cityName);
                    rentCondition.setSearchPageNum(1);
                    rentCondition.setCommName(content.getText().toString());
                    rentPresenter.requestRentData(rentCondition);
                }else{
                    secondHandCondition = new SecondHandHouseCon();
                    secondHandCondition.setCityName(cityName);
                    secondHandCondition.setSearchPageNum(1);
                    secondHandCondition.setCommName(content.getText().toString());
                    secondHandPresent.requestSecondHandData(secondHandCondition);
                }
            }
        });

    }


    private void showPopupWindow(View view){
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_window, null);
        final TextView tvRent = (TextView) contentView.findViewById(R.id.tv_rent);
        final TextView tvSecondHand = (TextView) contentView.findViewById(R.id.tv_secondhand);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);

        popupWindow.setTouchable(true);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.shape_rectangle_white));

        popupWindow.showAsDropDown(view);

        tvRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvRent.setSelected(true);
                tvSecondHand.setSelected(false);
                tvHouseSelect.setText(tvRent.getText().toString());
                popupWindow.dismiss();
            }
        });
        tvSecondHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvRent.setSelected(false);
                tvSecondHand.setSelected(true);
                tvHouseSelect.setText(tvSecondHand.getText().toString());
                popupWindow.dismiss();
            }
        });


    }

    @Override
    public void updateListUI(RentHouseResult result) {
        if(result.getStatus().equals("success")) {
            Intent intent = new Intent(this, MapDetail.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("rentList", (Serializable) result.getRentInfoList());
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    @Override
    public void updateListUI(SecondHandHouseResult result) {
        if(result.getStatus().equals("success")) {
            Intent intent = new Intent(this, MapDetail.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("secondHandList", (Serializable) result.getSecondHandInfoList());
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    @Override
    public void errorToast(String msg) {
        tvErrorMsg.setText("对不起，查找失败  >_<");
        tvErrorMsg.setVisibility(View.VISIBLE);
    }
}
