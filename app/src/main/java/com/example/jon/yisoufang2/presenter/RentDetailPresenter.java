package com.example.jon.yisoufang2.presenter;

import android.util.Log;

import com.example.jon.yisoufang2.R;
import com.example.jon.yisoufang2.activity.RentDetail;
import com.example.jon.yisoufang2.bean.RentDetailResult;
import com.example.jon.yisoufang2.model.RentSearchModel;
import com.example.jon.yisoufang2.model.RentSearchModelImpl;
import com.example.jon.yisoufang2.utils.RetrofitUtils;
import com.example.jon.yisoufang2.view.RentDetailView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

/**
 * Created by Jon on 2016/10/16.
 */
public class RentDetailPresenter extends BasePresenter<RentDetailView> {

    private RentSearchModel rentSearchModel;

    public RentDetailPresenter(RentDetailView view){
        attachView(view);
        rentSearchModel = new RentSearchModelImpl();
    }

    public void requestRentDetail(String houseNum){
        if(rentSearchModel == null || mView == null){
            return;
        }
        rentSearchModel.rquestRentDetail(houseNum,new RentSearchModel.RentDetailCallback(){

            @Override
            public void requestRentDetailSuccess(RentDetailResult rentDetailInfo) {

                if(rentDetailInfo != null){
                    List<String> pictures = new ArrayList<>();
                    pictures.addAll(rentDetailInfo.getPictures());
                    rentDetailInfo.getPictures().clear();
                    for(String picture : pictures){
                        String imageUrl = RetrofitUtils.baseUrl + picture;
                        rentDetailInfo.getPictures().add(imageUrl);
                    }
                    Log.d("rentDetailInfo","info==>"+rentDetailInfo);

                    mView.updateData(rentDetailInfo);
                }
            }

            @Override
            public void requestRentInfoFail(String failStr) {
                mView.errorToast(failStr);
            }
        });
    }
}
