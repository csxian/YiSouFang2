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
import com.example.jon.yisoufang2.activity.SecondHandDetail;
import com.example.jon.yisoufang2.bean.SecondHandHouseResult;
import com.example.jon.yisoufang2.utils.RetrofitUtils;

import java.util.List;

/**
 * Created by Jon on 2016/10/19.
 */
public class MapSHDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<SecondHandHouseResult.HouseSecondInfo> mData;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public MapSHDetailAdapter(Context mContext,List<SecondHandHouseResult.HouseSecondInfo> mData){
        this.mContext = mContext;
        this.mData = mData;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HouseItemHolder(mLayoutInflater.inflate(R.layout.secondhand_house_item, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
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

    @Override
    public int getItemCount() {
        return mData.size();
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
}
