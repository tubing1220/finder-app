package com.zjf.finder.base.http;

import com.zjf.finder.base.BaseApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhengjunfei on 2018/1/3.
 */

public class RetrofitHttpClient {
    private static RetrofitHttpClient mInstance;
    private static OkHttpClient mClient;

    public static RetrofitHttpClient getInstance(){
        if(mInstance == null){
            synchronized(RetrofitHttpClient.class){
                if(mInstance == null){
                    mInstance = new RetrofitHttpClient();
                }
            }
        }
        return mInstance;
    }

    public <T> T forRetrofit(String baseUrl, Class<T> service){

        //添加一个log拦截器，打印所有的log
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //可以设置请求过滤的水平，body，basic，headers
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        File cacheFile = new File(BaseApplication.getContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024*1024*50);

        mClient = new OkHttpClient
                .Builder()
                .addInterceptor(new CommonInterceptor()) //添加参数
                .cache(cache)//添加缓存
                .connectTimeout(60l, TimeUnit.SECONDS)
                .readTimeout(60l, TimeUnit.SECONDS)
                .writeTimeout(60l, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mClient)
                .build();

        return retrofit.create(service);
    }





}
