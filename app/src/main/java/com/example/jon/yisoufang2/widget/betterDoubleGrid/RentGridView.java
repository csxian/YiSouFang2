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
 * Created by Jon on 2016/10/12.
 */
public class RentGridView extends LinearLayout implements View.OnClickListener {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<String> mOrientationData;
    private List<String> mAreasList;

    private OnFilterDoneListener mOnFilterDoneListener;


    public RentGridView(Context context) {
        this(context, null);
    }

    public RentGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RentGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RentGridView(Context context, AttributeSet attrs, int defStyleAttr,
                                int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        setBackgroundColor(Color.WHITE);
        inflate(context, R.layout.merge_filter_double_grid, this);
        ButterKnife.bind(this, this);
    }


    public RentGridView setmOrientationData(List<String> mOrientationData) {
        this.mOrientationData = mOrientationData;
        return this;
    }

    public RentGridView setmAreasList(List<String> mAreasList) {
        this.mAreasList = mAreasList;
        return this;
    }




    public RentGridView build() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0 || position == mOrientationData.size() + 1) {
                    return 4;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new RentGridAdapter(getContext(), mOrientationData, mAreasList, this));

        return this;
    }

    private TextView orientation_tv;
    private TextView areas_tv;


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
        }else if (mOrientationData.contains(text)) {
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
        }
    }


    public RentGridView setOnFilterDoneListener(OnFilterDoneListener listener) {
        mOnFilterDoneListener = listener;
        return this;
    }

    @OnClick(R.id.bt_confirm)
    public void clickDone() {

//        FilterUrl.instance().doubleGridTop = mTopSelectedTextView == null ? "" : (String) mTopSelectedTextView.getTag();
//        FilterUrl.instance().doubleGridBottom = mBottomSelectedTextView == null ? "" : (String) mBottomSelectedTextView.getTag();


        FilterUrl.instance().rentOrientation = orientation_tv == null ? "" : (String)orientation_tv.getTag();
        FilterUrl.instance().rentAreas = areas_tv == null ? "" : (String)areas_tv.getTag();


        if (mOnFilterDoneListener != null) {
            mOnFilterDoneListener.onFilterDone(3, "", "");
        }
    }


}
