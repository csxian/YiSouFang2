package com.example.jon.yisoufang2.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
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
import com.example.jon.yisoufang2.adapter.DropMenuAdapter;
import com.example.jon.yisoufang2.adapter.SecondHandHouseAdapter;
import com.example.jon.yisoufang2.bean.SecondHandHouseCon;
import com.example.jon.yisoufang2.bean.SecondHandHouseResult;
import com.example.jon.yisoufang2.entity.FilterUrl;
import com.example.jon.yisoufang2.presenter.SecondHandPresent;
import com.example.jon.yisoufang2.view.SecondHandView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jon on 2016/10/13.
 */
public class SecondHandHouseFragment extends Fragment
        implements OnFilterDoneListener,SecondHandView{

    DropDownMenu shDropDownMenu;
    RecyclerView mFilterContentView;
    TextView tv_citySelect;
    private int lastVisibleItem;
    private LinearLayoutManager layoutManager;
    private List<SecondHandHouseResult.HouseSecondInfo> secondHandDataList;
    private SecondHandHouseAdapter mAdapter;
    private static int currentPage;
    private SecondHandHouseCon condition = new SecondHandHouseCon();
    private boolean isLoadMore = false;
    private SecondHandPresent secondHandPresent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_secondhand_house,container,false);
        initView(view);
        initData(condition);
        return view;
    }

    private void initView(View view) {
        currentPage = 0;
        shDropDownMenu = (DropDownMenu) view.findViewById(R.id.sh_dropDownMenu);
        mFilterContentView = (RecyclerView) view.findViewById(R.id.mFilterContentView);
        tv_citySelect = (TextView) getActivity().findViewById(R.id.tv_citySelect);

        initFilterDropDownView();

        layoutManager = new LinearLayoutManager(getActivity());
        mFilterContentView.setLayoutManager(layoutManager);
        secondHandDataList = new ArrayList<>();
        mAdapter = new SecondHandHouseAdapter(getActivity(),secondHandDataList);
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

    private void initData(SecondHandHouseCon condition){
        condition.setCityName(tv_citySelect.getText().toString());
        currentPage = currentPage + 1;
        condition.setSearchPageNum(currentPage);
        secondHandPresent = new SecondHandPresent(this);
        secondHandPresent.requestSecondHandData(condition);
    }

    private void requestRentData(SecondHandHouseCon condition){
        currentPage = 1;
        condition.setSearchPageNum(currentPage);
        secondHandPresent = new SecondHandPresent(this);
        secondHandPresent.requestSecondHandData(condition);
    }

    private void initFilterDropDownView() {
        String[] titleList = new String[]{"区域", "价格", "房型", "更多"};
        shDropDownMenu.setMenuAdapter(new DropMenuAdapter(getActivity(),titleList,this,tv_citySelect));
    }

    @Override
    public void onFilterDone(int position, String positionTitle, String urlValue) {
        if (position != 3) {
            shDropDownMenu.setPositionIndicatorText(FilterUrl.instance().position, FilterUrl.instance().positionTitle);
        }

        shDropDownMenu.close();
        secondHandDataList.clear();

        condition.setCityName(tv_citySelect.getText().toString());
        condition.setSearchPageNum(currentPage);
        condition.setRegionName(FilterUrl.instance().secondHandRegion);
        condition.setHouseSellPrice(FilterUrl.instance().secondHandPrice);
        condition.setHouseType(FilterUrl.instance().secondHouseType);
        condition.setHouseOrientation(FilterUrl.instance().mOrientation);
        condition.setHouseArea(FilterUrl.instance().mAreas);
        condition.setHouseDecade(FilterUrl.instance().mAge);
        condition.setHouseStorey(FilterUrl.instance().mFloor);
        condition.setHouseDeco(FilterUrl.instance().mFitment);

        requestRentData(condition);
    }

    @Override
    public void updateListUI(SecondHandHouseResult result) {
        if(result.getStatus().equals("none")){
            Log.d("statusDetail","none");
            mAdapter.updateLoadStatus(mAdapter.LOAD_NONE);
        }else if(result.getStatus().equals("fail")){
            Log.d("statusDetail","fail");
            mAdapter.updateLoadStatus(mAdapter.LOAD_FAIL);
        }else if(result.getStatus().equals("success")) {
            Log.d("statusDetail","success");
            secondHandDataList.addAll(result.getSecondHandInfoList());
            mAdapter.updateLoadStatus(mAdapter.LOAD_END);
        }
    }

    @Override
    public void errorToast(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        if(secondHandPresent != null){
            secondHandPresent.detach();
        }
        super.onDestroy();
        FilterUrl.instance().clear();
    }
}
