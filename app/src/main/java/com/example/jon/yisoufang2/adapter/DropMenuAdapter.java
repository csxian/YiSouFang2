package com.example.jon.yisoufang2.adapter;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baiiu.filter.adapter.MenuAdapter;
import com.baiiu.filter.adapter.SimpleTextAdapter;
import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.baiiu.filter.interfaces.OnFilterItemClickListener;
import com.baiiu.filter.typeview.SingleListView;
import com.baiiu.filter.util.UIUtil;
import com.baiiu.filter.view.FilterCheckedTextView;
import com.example.jon.yisoufang2.db.RegionDAO;
import com.example.jon.yisoufang2.entity.FilterUrl;
import com.example.jon.yisoufang2.widget.betterDoubleGrid.BetterDoubleGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * author: baiiu
 * date: on 16/1/17 21:14
 * description:
 */
public class DropMenuAdapter implements MenuAdapter {
    private final Context mContext;
    private OnFilterDoneListener onFilterDoneListener;
    private String[] titles;
    private TextView tv_citySelect;
    public static  SingleListView<String> singleListView;

    public DropMenuAdapter(Context context, String[] titles, OnFilterDoneListener onFilterDoneListener,TextView tv_citySelect) {
        this.mContext = context;
        this.titles = titles;
        this.onFilterDoneListener = onFilterDoneListener;
        this.tv_citySelect = tv_citySelect;

    }

    @Override
    public int getMenuCount() {
        return titles.length;
    }

    @Override
    public String getMenuTitle(int position) {
        return titles[position];
    }

    @Override
    public int getBottomMargin(int position) {
        if (position == 3) {
            return 0;
        }

        return UIUtil.dp(mContext, 140);
    }

    @Override
    public View getView(int position, FrameLayout parentContainer) {
        View view = parentContainer.getChildAt(position);

        switch (position) {
            case 0:
                String loc = tv_citySelect.getText().toString();
                int parent = RegionDAO.getParentIdByName(loc);
                List<String> list = RegionDAO.getCitybyParent(parent);
                view = createRegionSingleListView(list,0);
                singleListView = (SingleListView<String>) view;

                break;
            case 1:
//                view = createDoubleListView();
                List<String> priceList = new ArrayList<>();
                priceList.add("不限");
                priceList.add("100万以下");
                priceList.add("100-120万");
                priceList.add("120-150万");
                priceList.add("200-300万");
                priceList.add("300-500万");
                priceList.add("500万以上");
                view = createPriceSingleListView(priceList,1);
                break;
            case 2:
//                view = createSingleGridView();
                List<String> HouseTypeList = new ArrayList<>();
                HouseTypeList.add("不限");
                HouseTypeList.add("1室");
                HouseTypeList.add("2室");
                HouseTypeList.add("3室");
                HouseTypeList.add("4室");
                HouseTypeList.add("5室");
                HouseTypeList.add("5室以上");

                view = createHouseTypeSingleListView(HouseTypeList,2);
                break;
            case 3:
                // view = createDoubleGrid();
                view = createBetterDoubleGrid();
                break;
        }

        return view;
    }

    private View createRegionSingleListView(List<String> list,final int pos) {
        SingleListView<String> singleListView = new SingleListView<String>(mContext)
                .adapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String string) {
                        return string;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item) {
                        FilterUrl.instance().secondHandRegion = item;

                        FilterUrl.instance().position = pos;
                        FilterUrl.instance().positionTitle = item;
                        onFilterDone();
                    }
                });

        singleListView.setList(list, -1);

        return singleListView;
    }

    private View createPriceSingleListView(List<String> list,final int pos) {
        SingleListView<String> singleListView = new SingleListView<String>(mContext)
                .adapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String string) {
                        return string;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item) {
                        FilterUrl.instance().secondHandPrice = item;
                        FilterUrl.instance().position = pos;
                        FilterUrl.instance().positionTitle = item;
                        onFilterDone();
                    }
                });

        singleListView.setList(list, -1);

        return singleListView;
    }

    private View createHouseTypeSingleListView(List<String> list,final int pos) {
        SingleListView<String> singleListView = new SingleListView<String>(mContext)
                .adapter(new SimpleTextAdapter<String>(null, mContext) {
                    @Override
                    public String provideText(String string) {
                        return string;
                    }

                    @Override
                    protected void initCheckedTextView(FilterCheckedTextView checkedTextView) {
                        int dp = UIUtil.dp(mContext, 15);
                        checkedTextView.setPadding(dp, dp, 0, dp);
                    }
                })
                .onItemClick(new OnFilterItemClickListener<String>() {
                    @Override
                    public void onItemClick(String item) {
                        FilterUrl.instance().secondHouseType = item;

                        FilterUrl.instance().position = pos;
                        FilterUrl.instance().positionTitle = item;

                        onFilterDone();
                    }
                });

        singleListView.setList(list, -1);

        return singleListView;
    }



    private View createBetterDoubleGrid() {

        List<String> orientation = new ArrayList<>();
        orientation.add("朝东");
        orientation.add("朝南");
        orientation.add("朝西");
        orientation.add("朝北");
        orientation.add("朝南北");


        List<String> areas = new ArrayList<>();
        areas.add("50平米以下");
        areas.add("50-70平米");
        areas.add("70-90平米");
        areas.add("90-110平米");
        areas.add("110-130平米");
        areas.add("130-150平米");
        areas.add("200平米以上");

        List<String> age = new ArrayList<>();
        age.add("5年以内");
        age.add("10年以内");
        age.add("15年以内");
        age.add("20年以内");
        age.add("20年以上");

        List<String> floor = new ArrayList<>();
        floor.add("低楼层");
        floor.add("中楼层");
        floor.add("高楼层");

        List<String> fitment = new ArrayList<>();
        fitment.add("精装修");
        fitment.add("普通装修");
        fitment.add("毛坯房");



//        for (int i = 0; i < 10; ++i) {
//            areas.add("3bottom" + i);
//        }


        return new BetterDoubleGridView(mContext)
                .setmOrientationList(orientation)
                .setmAreasList(areas)
                .setmAgeList(age)
                .setmFloorList(floor)
                .setmFitmentList(fitment)
                .setOnFilterDoneListener(onFilterDoneListener)
                .build();
    }





    private void onFilterDone() {
        if (onFilterDoneListener != null) {
            onFilterDoneListener.onFilterDone(0, "", "");
        }
    }

}
