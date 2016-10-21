package com.example.jon.yisoufang2.presenter;


import com.example.jon.yisoufang2.R;
import com.example.jon.yisoufang2.bean.RentDetailResult;
import com.example.jon.yisoufang2.bean.RentHouseResult;
import com.example.jon.yisoufang2.bean.RentHouseSearchCon;
import com.example.jon.yisoufang2.model.RentSearchModel;
import com.example.jon.yisoufang2.model.RentSearchModelImpl;
import com.example.jon.yisoufang2.view.RentView;

/**
 * Created by Jon on 2016/10/10.
 */
public class RentPresenter extends BasePresenter<RentView> {
    private RentSearchModel rentSearchModel;

    public RentPresenter(RentView rentView){
        attachView(rentView);
        rentSearchModel = new RentSearchModelImpl();
    }

    public void requestRentData(RentHouseSearchCon rentCondition){
        if(rentSearchModel == null || mView == null){
            return;
        }
//        mView.showProgressDialog();
        rentSearchModel.requestRentInfo(rentCondition, new RentSearchModel.RentSearchCallback() {
            @Override
            public void requestRentInfoSuccess(RentHouseResult result) {
//                mView.hideProgressDialog();
                if(result != null){
                    mView.updateListUI(result);
                    if(result.getStatus().equals("none")){
                        mView.errorToast("没有相关数据");
                    }
                }
            }

            @Override
            public void requestRentInfoFail(String failStr) {
//                mView.showProgressDialog();
                mView.errorToast(failStr);
            }
        });

    }
    


}
