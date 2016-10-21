package com.example.jon.yisoufang2.widget.betterDoubleGrid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.jon.yisoufang2.widget.betterDoubleGrid.holder.ItemViewHolder;
import com.example.jon.yisoufang2.widget.betterDoubleGrid.holder.TitleViewHolder;

import java.util.List;

/**
 * Created by Jon on 2016/10/12.
 */
public class RentGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_TITLE = 0;
    private static final int TYPE_ITEM = 1;

    private Context mContext;
    private List<String> orientationGridData;
    private List<String> areasGridData;


    private View.OnClickListener mListener;

    public RentGridAdapter(Context context, List<String> orientationGridData, List<String>
            areasGridData,
                           View.OnClickListener listener) {
        this.mContext = context;
        this.orientationGridData = orientationGridData;
        this.areasGridData = areasGridData;

        this.mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == orientationGridData.size() + 1) {
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
                } else {
                    titleViewHolder.bind("面积");
                }
                break;
            case TYPE_ITEM:
                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                if (position < orientationGridData.size() + 1) {

                    itemViewHolder.bind(orientationGridData.get(position - 1));

                } else if (position > orientationGridData.size() + 1 && position <
                        orientationGridData.size() + areasGridData.size() + 2) {

                    itemViewHolder.bind(areasGridData.get(position - orientationGridData.size() -
                            2));

                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return orientationGridData.size() + areasGridData.size()  + 2 ;
    }
}
