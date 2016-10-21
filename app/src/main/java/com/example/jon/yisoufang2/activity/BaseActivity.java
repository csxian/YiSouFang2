package com.example.jon.yisoufang2.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jon.yisoufang2.view.BaseView;

/**
 * Created by Jon on 2016/10/10.
 */
public class BaseActivity extends AppCompatActivity implements BaseView{

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("正在查询...");
    }

    @Override
    public void showProgressDialog() {
//        if(progressDialog!=null){
//            progressDialog.show();
//        }
    }

    @Override
    public void hideProgressDialog() {
//        if(progressDialog!=null && progressDialog.isShowing()){
//            progressDialog.dismiss();
//        }
    }
}
