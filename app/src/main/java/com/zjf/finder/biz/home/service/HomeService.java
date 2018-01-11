package com.zjf.finder.biz.home.service;

import com.zjf.finder.base.http.Result;
import com.zjf.finder.biz.home.model.CategoryData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/1/11.
 */

public interface HomeService {

    @GET("index.php")
    Call<Result<CategoryData>> getCategoryList(@Query("access") String access, @Query("submit") String submit);


}
