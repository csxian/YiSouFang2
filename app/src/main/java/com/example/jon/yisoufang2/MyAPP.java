package com.example.jon.yisoufang2;

import android.app.Application;

import com.example.jon.yisoufang2.db.DBManager;
import com.example.jon.yisoufang2.utils.RetrofitUtils;


/**
 * 作者：chs on 2015/12/26 17:35
 * 邮箱：657083984@qq.com
 */
public class MyAPP extends Application {
    private DBManager dbHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        //导入数据库
        dbHelper = new DBManager(this);
        dbHelper.openDatabase();
//        dbHelper.closeDatabase();

        RetrofitUtils.getInstance().initOkHttp(this);

    }
}
