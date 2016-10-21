package com.example.jon.yisoufang2.utils;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jon on 2016/9/22.
 */
public class RetrofitUtils {
    private Retrofit retrofit;

    //服务器地址
    public final static String baseUrl = "http://192.168.199.214:8080/YiSouFang/";
//    public final static String baseUrl = "http://192.168.43.216:8080/YiSouFang/";

    private static class SingleLoader{
        public static final RetrofitUtils INSTANCE = new RetrofitUtils();
    }

    public static RetrofitUtils getInstance(){
        return SingleLoader.INSTANCE;
    }

    public void initOkHttp(Context context){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L,TimeUnit.MILLISECONDS)
                .writeTimeout(10000L,TimeUnit.MILLISECONDS)
                .cache(new Cache(context.getCacheDir(),10*1024*1024))
                .addInterceptor(interceptor)
                .build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

}
