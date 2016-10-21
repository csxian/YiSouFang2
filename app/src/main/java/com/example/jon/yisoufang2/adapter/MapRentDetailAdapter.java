package com.example.jon.yisoufang2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jon.yisoufang2.R;
import com.example.jon.yisoufang2.activity.RentDetail;
import com.example.jon.yisoufang2.bean.RentHouseResult;
import com.example.jon.yisoufang2.utils.RetrofitUtils;

import java.util.List;

/**
 * Created by Jon on 2016/10/18.
 */
public class MapRentDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<RentHouseResult.RentInfoListBean> mData;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public MapRentDetailAdapter(Context mContext,List<RentHouseResult.RentInfoListBean> mData){
        this.mContext = mContext;
        this.mData = mData;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HouseItemHolder(mLayoutInflater.inflate(R.layout.rent_house_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RentHouseResult.RentInfoListBean rentInfo = mData.get(position);
        if (rentInfo == null)
            return;
        HouseItemHolder viewHolder = (HouseItemHolder) holder;
        viewHolder.houseTitle.setText(rentInfo.getHouseTitle());
        String rentHouseContent = rentInfo.getHouseType() + " / " + rentInfo.getHouseArea() + "平方米"

                + " / " + rentInfo.getHouseOrientation();
        viewHolder.houseContent.setText(rentHouseContent);
        String money = rentInfo.getHouseRental() + "元/月";
        viewHolder.housePrice.setText(money);
        String imageUrl = RetrofitUtils.baseUrl + rentInfo.getPictureUrl();
        Glide.with(mContext).load(imageUrl).into(viewHolder.houseImage);
        viewHolder.houseNum = rentInfo.getHouseNum();

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class HouseItemHolder extends RecyclerView.ViewHolder{

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

                    Intent intent = new Intent(mContext,RentDetail.class);
                    intent.putExtra("houseNum",houseNum);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
