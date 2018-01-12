package com.zjf.finder.biz.home.service;

import com.zjf.finder.base.http.Result;
import com.zjf.finder.biz.home.model.CategoryDetailData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zhengjunfei on 2018/1/3.
 */

public interface CategoryDetailService {
//
//    @GET("{type}/")
//    Call<Result2> getNewsData(@Path("type") String type, @Query("key") String key, @Query("num") String num, @Query("page") int page);

    @GET("index.php")
    Call<Result<CategoryDetailData>> getCategoryDetailList(@Query("access") String access, @Query("submit") String submit, @Query("id") String id, @Query("rankIndex") String rankIndex);


//    @GET("")
//    Call<Result<List<CategoryDetail>>> getCategoryDetailList(@Query("access") String access, @Query("categoryDetail") String categoryDetail);

}
