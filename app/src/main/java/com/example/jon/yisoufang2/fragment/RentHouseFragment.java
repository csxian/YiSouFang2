package com.example.jon.yisoufang2.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baiiu.filter.DropDownMenu;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.example.jon.yisoufang2.R;
import com.example.jon.yisoufang2.adapter.HouseAdapter;
import com.example.jon.yisoufang2.adapter.RentDropMenuAdapter;
import com.example.jon.yisoufang2.bean.RentHouseResult;
import com.example.jon.yisoufang2.bean.RentHouseSearchCon;
import com.example.jon.yisoufang2.entity.FilterUrl;
import com.example.jon.yisoufang2.presenter.RentPresenter;
import com.example.jon.yisoufang2.view.RentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jon on 2016/10/13.
 */
public class RentHouseFragment extends Fragment implements OnFilterDoneListener, RentView {

    DropDownMenu dropDownMenu;
    RecyclerView mFilterContentView;
    TextView tv_citySelect;

    private RentPresenter rentPresenter;
    private HouseAdapter mAdapter;
    private int lastVisibleItem;
    private LinearLayoutManager layoutManager;
    private boolean isLoadMore = false;
    public static int currentPage;
    private RentHouseSearchCon condition = new RentHouseSearchCon();
    private List<RentHouseResult.RentInfoListBean> rentDataList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rent_house, container, false);
        initView(view);
        initData(condition);

        return view;
    }

    private void initView(View view) {
        currentPage = 0;
        dropDownMenu = (DropDownMenu) view.findViewById(R.id.dropDownMenu);
        mFilterContentView = (RecyclerView) view.findViewById(R.id.mFilterContentView);
        tv_citySelect = (TextView) getActivity().findViewById(R.id.tv_citySelect);


        initFilterDropDownView();//初始化dropDownMenu


        layoutManager = new LinearLayoutManager(getActivity());
        mFilterContentView.setLayoutManager(layoutManager);
        rentDataList = new ArrayList<>();
        mAdapter = new HouseAdapter(getActivity(), rentDataList);
        mFilterContentView.setAdapter(mAdapter);

        mFilterContentView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                    if (layoutManager.getItemCount() == 1) {
                        mAdapter.updateLoadStatus(mAdapter.LOAD_NONE);
                        return;
                    }
                    if (lastVisibleItem + 1 == layoutManager.getItemCount()) {
                        isLoadMore = true;
                        mAdapter.updateLoadStatus(mAdapter.LOAD_MORE);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                initData(condition);
                            }
                        }, 1000);

                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });

    }

    private void initFilterDropDownView() {
        String[] titleList = new String[]{"区域", "价格", "房型", "更多"};
        dropDownMenu.setMenuAdapter(new RentDropMenuAdapter(getActivity(), titleList, this,
                tv_citySelect));
    }

    private void initData(RentHouseSearchCon condition){
        condition.setCityName(tv_citySelect.getText().toString());
        currentPage = currentPage + 1;
        condition.setSearchPageNum(currentPage);
        rentPresenter = new RentPresenter(this);
        rentPresenter.requestRentData(condition);
    }

    private void requestRentData(RentHouseSearchCon condition){
        currentPage = 1;
        condition.setSearchPageNum(currentPage);
        rentPresenter = new RentPresenter(this);
        rentPresenter.requestRentData(condition);
    }

    @Override
    public void onFilterDone(int position, String positionTitle, String urlValue) {
        if (position != 3) {
            dropDownMenu.setPositionIndicatorText(FilterUrl.instance().position, FilterUrl.instance().positionTitle);
        }

        dropDownMenu.close();
        rentDataList.clear();

        condition.setCityName(tv_citySelect.getText().toString());
        condition.setSearchPageNum(currentPage);
        condition.setRegionName(FilterUrl.instance().rentRegion);
        condition.setHouseRental(FilterUrl.instance().rentPrice);
        condition.setHouseType(FilterUrl.instance().rentHouseType);
        condition.setHouseOrientation(FilterUrl.instance().rentOrientation);
        condition.setHouseArea(FilterUrl.instance().rentAreas);
        requestRentData(condition);
    }

    @Override
    public void updateListUI(RentHouseResult result) {
        Log.d("status",result.getStatus());
        if(result.getStatus().equals("none")){
            Log.d("statusDetail","none");
            mAdapter.updateLoadStatus(mAdapter.LOAD_NONE);
        }else if(result.getStatus().equals("fail")){
            Log.d("statusDetail","fail");
            mAdapter.updateLoadStatus(mAdapter.LOAD_FAIL);
        }else if(result.getStatus().equals("success")) {
            Log.d("statusDetail","success");
            rentDataList.addAll(result.getRentInfoList());
            mAdapter.updateLoadStatus(mAdapter.LOAD_END);
        }
    }

    @Override
    public void errorToast(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        if(rentPresenter != null){
            rentPresenter.detach();
        }
            super.onDestroy();
        FilterUrl.instance().clear();
    }
}
