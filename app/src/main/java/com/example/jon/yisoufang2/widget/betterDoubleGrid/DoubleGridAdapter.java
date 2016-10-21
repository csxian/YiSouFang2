package com.example.jon.yisoufang2.widget.betterDoubleGrid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import com.example.jon.yisoufang2.widget.betterDoubleGrid.holder.ItemViewHolder;
import com.example.jon.yisoufang2.widget.betterDoubleGrid.holder.TitleViewHolder;

import java.util.List;

/**
 * auther: baiiu
 * time: 16/6/5 05 23:28
 * description:
 */
public class DoubleGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_TITLE = 0;
    private static final int TYPE_ITEM = 1;

    private Context mContext;
    private List<String> orientationGridData;
    private List<String> areasGridData;

    private List<String> ageGridData;
    private List<String> floorGridData;
    private List<String> fitmentGridData;

    private View.OnClickListener mListener;

    public DoubleGridAdapter(Context context, List<String> orientationGridData, List<String> areasGridData,
                             List<String> ageGridData, List<String> floorGridData,
                             List<String> fitmentGridData, View.OnClickListener listener) {
        this.mContext = context;
        this.orientationGridData = orientationGridData;
        this.areasGridData = areasGridData;
        this.ageGridData = ageGridData;
        this.floorGridData = floorGridData;
        this.fitmentGridData = fitmentGridData;
        this.mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == orientationGridData.size() + 1
                || position == orientationGridData.size()+areasGridData.size()+2
                || position == orientationGridData.size()+areasGridData.size()+ageGridData.size()+3
                || position == orientationGridData.size()+areasGridData.size()+ageGridData.size()+floorGridData.size()+4) {
            return TYPE_TITLE;
        }

        return TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;

        switch (viewType) {
            case TYPE_TITLE:
                holder = new TitleViewHolder(mContext, parent);
                break;
            case TYPE_ITEM:
                holder = new ItemViewHolder(mContext, parent, mListener);
                break;
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TYPE_TITLE:
                TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
                if (position == 0) {
                    titleViewHolder.bind("朝向");
                } else if(position == orientationGridData.size() + 1){
                    titleViewHolder.bind("面积");
                }else if(position == orientationGridData.size()+areasGridData.size()+2){
                    titleViewHolder.bind("楼龄");
                }else if(position == orientationGridData.size()+areasGridData.size()+ageGridData.size()+3){
                    titleViewHolder.bind("楼层");
                }else{
                    titleViewHolder.bind("装修");
                }
                break;
            case TYPE_ITEM:
                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                if (position < orientationGridData.size() + 1) {

                    itemViewHolder.bind(orientationGridData.get(position - 1));

                } else if(position > orientationGridData.size() + 1 && position < orientationGridData.size()+areasGridData.size()+2) {

                    itemViewHolder.bind(areasGridData.get(position - orientationGridData.size() - 2));

                } else if(position > orientationGridData.size()+areasGridData.size()+2 &&
                        position < orientationGridData.size()+areasGridData.size()+ageGridData.size()+3){

                    itemViewHolder.bind(ageGridData.get(position - orientationGridData.size()-areasGridData.size() - 3));

                } else if(position > orientationGridData.size()+areasGridData.size()+ageGridData.size()+3
                        && position < orientationGridData.size()+areasGridData.size()+ageGridData.size()+floorGridData.size()+4){

                    itemViewHolder.bind(floorGridData.get(position - orientationGridData.size()-areasGridData.size()-ageGridData.size()-4));

                } else {

                    itemViewHolder.bind(fitmentGridData.get(position - orientationGridData.size()-areasGridData.size()-ageGridData.size()-floorGridData.size()-5));
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return  orientationGridData.size()+areasGridData.size()+ageGridData.size()+floorGridData.size()+fitmentGridData.size() + 5;
    }
}
