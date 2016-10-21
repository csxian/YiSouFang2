package com.example.jon.yisoufang2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jon.yisoufang2.R;
import com.example.jon.yisoufang2.activity.RentDetail;
import com.example.jon.yisoufang2.activity.SecondHandDetail;
import com.example.jon.yisoufang2.bean.SecondHandHouseResult;
import com.example.jon.yisoufang2.utils.RetrofitUtils;

import java.util.List;

/**
 * Created by Jon on 2016/10/17.
 */
public class SecondHandHouseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<SecondHandHouseResult.HouseSecondInfo> mData;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private int status = 0;
    private static final int TYPE_FOOTER = -1;
    public static final int LOAD_MORE = 1;
    public static final int LOAD_NONE = 2;
    public static final int LOAD_END = 3;
    public static final int LOAD_FAIL = 4;


    public SecondHandHouseAdapter(Context mContext,List<SecondHandHouseResult.HouseSecondInfo> mData){
        this.mContext = mContext;
        this.mData = mData;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if(position + 1 == getItemCount()){
            return TYPE_FOOTER;
        }else{
            return position;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_FOOTER){
            return new FooterViewHolder(mLayoutInflater.inflate(R.layout.activity_view_footer,parent,false));
        }else {
            return new HouseItemHolder(mLayoutInflater.inflate(R.layout.secondhand_house_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FooterViewHolder){
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            footerViewHolder.bindItem();
        }else {
            SecondHandHouseResult.HouseSecondInfo secondHandInfo = mData.get(position);
            if (secondHandInfo == null)
                return;
            HouseItemHolder viewHolder = (HouseItemHolder) holder;
            viewHolder.houseTitle.setText(secondHandInfo.getHouseTitle());

            String houseContent = secondHandInfo.getHouseType() + " / " + secondHandInfo.getHouseArea() + "平方米"
                    + " / " + secondHandInfo.getHouseOrientation();

            viewHolder.houseContent.setText(houseContent);

            String housePrice = secondHandInfo.getHouseSellPrice()/10000 + "万";
            viewHolder.housePrice.setText(housePrice);
            String houseUniPrice = secondHandInfo.getHouseAvgPrice()+"元/米";
            viewHolder.houseUniPrice.setText(houseUniPrice);

            String imageUrl = RetrofitUtils.baseUrl + secondHandInfo.getPictureUrl();
            Glide.with(mContext).load(imageUrl).into(viewHolder.houseImage);

            viewHolder.houseNum = secondHandInfo.getHouseNum();
        }
    }

    @Override
    public int getItemCount() {
        return mData.size()+1;
    }

    public class HouseItemHolder extends RecyclerView.ViewHolder{

        //        ImageView houseImage;
        TextView houseTitle;
        TextView houseContent;
        TextView housePrice;
        TextView houseUniPrice;
        ImageView houseImage;
        String houseNum;


        public HouseItemHolder(final View itemView) {
            super(itemView);

            houseTitle = (TextView) itemView.findViewById(R.id.houseTitle);
            houseContent = (TextView) itemView.findViewById(R.id.houseContent);
            housePrice = (TextView) itemView.findViewById(R.id.housePrice);
            houseUniPrice = (TextView) itemView.findViewById(R.id.houseUnitPrice);
            houseImage = (ImageView) itemView.findViewById(R.id.houseImage);


            itemView.findViewById(R.id.house_item_container).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext,SecondHandDetail.class);
                    intent.putExtra("houseNum",houseNum);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder{

        ProgressBar progress;
        TextView tvLoad;

        public FooterViewHolder(View itemView) {
            super(itemView);
            progress = (ProgressBar) itemView.findViewById(R.id.progress);
            tvLoad = (TextView) itemView.findViewById(R.id.tv_load);
            LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            itemView.setLayoutParams(params);
        }
        public void bindItem(){
            switch (status){
                case LOAD_MORE:
                    progress.setVisibility(ProgressBar.VISIBLE);
                    tvLoad.setText("正在加载中...");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_NONE:
                    progress.setVisibility(View.GONE);
                    tvLoad.setText("已无更多加载");
                    break;
                case LOAD_FAIL:
                    progress.setVisibility(View.GONE);
                    tvLoad.setText("服务器发生异常");
                    break;
                case LOAD_END:
                    itemView.setVisibility(View.GONE);
                    break;
            }
        }
    }

    public void updateLoadStatus(int status){
        this.status = status;
        notifyDataSetChanged();
    }
}
