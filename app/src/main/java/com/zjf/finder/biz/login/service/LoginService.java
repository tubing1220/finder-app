package com.zjf.finder.biz.login.service;

import com.zjf.finder.base.http.Result;
import com.zjf.finder.biz.home.model.CategoryData;
import com.zjf.finder.biz.login.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/1/21.
 */

public interface LoginService {


    @GET("index.php")
    Call<Result<User>> login(@Query("appId") String appId, @Query("appKey") String appKey, @Query("unionid") String unionid);

}
