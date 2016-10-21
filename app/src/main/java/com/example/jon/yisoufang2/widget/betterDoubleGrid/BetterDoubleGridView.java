package com.example.jon.yisoufang2.widget.betterDoubleGrid;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.baiiu.filter.interfaces.OnFilterDoneListener;
import com.example.jon.yisoufang2.R;
import com.example.jon.yisoufang2.entity.FilterUrl;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * auther: baiiu
 * time: 16/6/5 05 23:03
 * description:
 */
public class BetterDoubleGridView extends LinearLayout implements View.OnClickListener {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<String> mOrientationData;
    private List<String> mAreasList;
    private List<String> mAgeList;
    private List<String> mFloorList;
    private List<String> mFitmentList;
    private OnFilterDoneListener mOnFilterDoneListener;


    public BetterDoubleGridView(Context context) {
        this(context, null);
    }

    public BetterDoubleGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BetterDoubleGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BetterDoubleGridView(Context context, AttributeSet attrs, int defStyleAttr,
                                int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        setBackgroundColor(Color.WHITE);
        inflate(context, R.layout.merge_filter_double_grid, this);
        ButterKnife.bind(this, this);
    }


    public BetterDoubleGridView setmOrientationList(List<String> mOrientationData) {
        this.mOrientationData = mOrientationData;
        return this;
    }

    public BetterDoubleGridView setmAreasList(List<String> mAreasList) {
        this.mAreasList = mAreasList;
        return this;
    }

    public BetterDoubleGridView setmAgeList(List<String> mAgeList) {
        this.mAgeList = mAgeList;
        return this;
    }

    public BetterDoubleGridView setmFloorList(List<String> mFloorList) {
        this.mFloorList = mFloorList;
        return this;
    }

    public BetterDoubleGridView setmFitmentList(List<String> mFitmentList) {
        this.mFitmentList = mFitmentList;
        return this;
    }



    public BetterDoubleGridView build() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0 || position == mOrientationData.size() + 1
                        || position == mOrientationData.size()+mAreasList.size()+2
                        || position == mOrientationData.size()+mAreasList.size()+mAgeList.size()+3
                        || position == mOrientationData.size()+mAreasList.size()+mAgeList.size()+mFloorList.size()+4) {
                    return 4;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new DoubleGridAdapter(getContext(), mOrientationData, mAreasList,mAgeList,mFloorList,mFitmentList, this));

        return this;
    }

    private TextView orientation_tv;
    private TextView areas_tv;
    private TextView age_tv;
    private TextView floor_tv;
    private TextView fitment_tv;

    @Override
    public void onClick(View v) {

        TextView textView = (TextView) v;
        String text = (String) textView.getTag();

        if (textView == orientation_tv) {
            orientation_tv = null;
            textView.setSelected(false);
        } else if (textView == areas_tv) {
            areas_tv = null;
            textView.setSelected(false);
        } else if(textView == age_tv){
            age_tv = null;
            textView.setSelected(false);
        }else if(textView == floor_tv){
            floor_tv = null;
            textView.setSelected(false);
        }else if(textView == fitment_tv){
            fitment_tv = null;
            textView.setSelected(false);
        } else if (mOrientationData.contains(text)) {
            if (orientation_tv != null) {
                orientation_tv.setSelected(false);
            }
            orientation_tv = textView;
            textView.setSelected(true);
        } else if(mAreasList.contains(text)){
            if (areas_tv != null) {
                areas_tv.setSelected(false);
            }
            areas_tv = textView;
            textView.setSelected(true);
        }else if(mAgeList.contains(text)){
            if (age_tv != null) {
                age_tv.setSelected(false);
            }
            age_tv = textView;
            textView.setSelected(true);
        }else if(mFloorList.contains(text)){
            if (floor_tv != null) {
                floor_tv.setSelected(false);
            }
            floor_tv = textView;
            textView.setSelected(true);
        }else{
            if (fitment_tv != null) {
                fitment_tv.setSelected(false);
            }
            fitment_tv = textView;
            textView.setSelected(true);
        }
    }


    public BetterDoubleGridView setOnFilterDoneListener(OnFilterDoneListener listener) {
        mOnFilterDoneListener = listener;
        return this;
    }

    @OnClick(R.id.bt_confirm)
    public void clickDone() {

//        FilterUrl.instance().doubleGridTop = mTopSelectedTextView == null ? "" : (String) mTopSelectedTextView.getTag();
//        FilterUrl.instance().doubleGridBottom = mBottomSelectedTextView == null ? "" : (String) mBottomSelectedTextView.getTag();

        FilterUrl.instance().mOrientation = orientation_tv == null ? "" : (String)orientation_tv.getTag();
        FilterUrl.instance().mAreas = areas_tv == null ? "" : (String)areas_tv.getTag();
        FilterUrl.instance().mAge = age_tv == null ? "" : (String)age_tv.getTag();
        FilterUrl.instance().mFloor = floor_tv == null ? "" : (String)floor_tv.getTag();
        FilterUrl.instance().mFitment = fitment_tv == null ? "" : (String)fitment_tv.getTag();




        if (mOnFilterDoneListener != null) {
            mOnFilterDoneListener.onFilterDone(3, "", "");
        }
    }


}
