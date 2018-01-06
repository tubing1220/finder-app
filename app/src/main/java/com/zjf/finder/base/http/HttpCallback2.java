package com.zjf.finder.base.http;

import com.zjf.finder.R;
import com.zjf.finder.base.BaseApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhengjunfei on 2018/1/3.
 */

public abstract  class HttpCallback2<T extends Result2> implements Callback<T> {
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_FAILURE = -101;


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.isSuccessful()){
            int code = response.body().getCode();
            if(code == CODE_SUCCESS){
                onResponse(response.body());
            } else{
                onFailure(code, response.body().getMsg());
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailure(CODE_FAILURE, BaseApplication.getContext().getString(R.string.net_error_text));
    }


    public abstract void onResponse(T t);


    public void onFailure(int code, String msg){

    }

}
