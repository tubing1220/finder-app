package com.zjf.finder.biz.home.service;

import com.zjf.finder.base.http.Result;
import com.zjf.finder.base.http.Result2;
import com.zjf.finder.biz.home.model.CategoryDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zhengjunfei on 2018/1/3.
 */

public interface NewsService {

    @GET("{type}/")
    Call<Result2> getNewsData(@Path("type") String type, @Query("key") String key, @Query("num") String num, @Query("page") int page);

//    @GET("")
//    Call<Result<List<CategoryDetail>>> getCategoryDetailList(@Query("access") String access, @Query("categoryDetail") String categoryDetail);


//    @GET("")
//    Call<Result<List<CategoryDetail>>> getCategoryDetailList(@Query("access") String access, @Query("categoryDetail") String categoryDetail);

}
