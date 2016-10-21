package com.example.jon.yisoufang2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.jon.yisoufang2.R;
import com.example.jon.yisoufang2.adapter.MapRentDetailAdapter;
import com.example.jon.yisoufang2.adapter.MapSHDetailAdapter;
import com.example.jon.yisoufang2.bean.RentHouseResult;
import com.example.jon.yisoufang2.bean.SecondHandHouseResult;

import java.util.List;

/**
 * Created by Jon on 2016/10/18.
 */
public class MapDetail extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<RentHouseResult.RentInfoListBean> rentDataList;
    private List<SecondHandHouseResult.HouseSecondInfo> secondHandList;
    private MapRentDetailAdapter rentAdapter;
    private MapSHDetailAdapter secondHandAdapter;
    private TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpler_msg);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        title = (TextView) findViewById(R.id.msg_title);

        recyclerView = (RecyclerView) findViewById(R.id.rv_msg);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        Intent intent = this.getIntent();

        rentDataList = (List<RentHouseResult.RentInfoListBean>) intent.getSerializableExtra("rentList");
        secondHandList = (List<SecondHandHouseResult.HouseSecondInfo>) intent.getSerializableExtra("secondHandList");

        if(rentDataList != null){
            rentAdapter = new MapRentDetailAdapter(this,rentDataList);
            title.setText(rentDataList.get(0).getCommName());
            recyclerView.setAdapter(rentAdapter);
        }else {

            secondHandAdapter = new MapSHDetailAdapter(this, secondHandList);
            title.setText(secondHandList.get(0).getCommName());
            recyclerView.setAdapter(secondHandAdapter);
        }
    }
}
