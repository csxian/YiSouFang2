package com.example.jon.yisoufang2.presenter;

import com.example.jon.yisoufang2.bean.SecondHandDetailResult;
import com.example.jon.yisoufang2.model.SecondHandSearchModel;
import com.example.jon.yisoufang2.model.SecondHandSearchModelImpl;
import com.example.jon.yisoufang2.utils.RetrofitUtils;
import com.example.jon.yisoufang2.view.SecondHandDetailView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jon on 2016/10/17.
 */
public class SecondHandDetailPresent extends BasePresenter<SecondHandDetailView>{
    private SecondHandSearchModel secondHandSearchModel;
    public SecondHandDetailPresent(SecondHandDetailView view){
        attachView(view);
        secondHandSearchModel = new SecondHandSearchModelImpl();
    }

    public void requestSecondHandDetail(String houseNum){
        secondHandSearchModel.requestSecondHandDetail(houseNum, new SecondHandSearchModel
                .SecondHandDetailCallback() {
            @Override
            public void requestSecondHandDetailSuccess(SecondHandDetailResult result) {
                if(result != null){
                    List<String> pictures = new ArrayList<>();
                    pictures.addAll(result.getPictures());
                    result.getPictures().clear();
                    for(String picture : pictures){
                        String imageUrl = RetrofitUtils.baseUrl + picture;
                        result.getPictures().add(imageUrl);
                    }
                    mView.updateData(result);
                }

            }

            @Override
            public void requestSecondHandDetailFail(String failStr) {
                mView.errorToast(failStr);
            }
        });
    }

}
