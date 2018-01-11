package com.zjf.finder.biz.home.model;

import com.zjf.finder.R;
import com.zjf.finder.base.BaseApplication;
import com.zjf.finder.base.http.HttpCallback;
import com.zjf.finder.base.http.Result;
import com.zjf.finder.base.http.RetrofitHttpClient;
import com.zjf.finder.biz.home.contract.CategoryDetailContract;
import com.zjf.finder.biz.home.service.CategoryDetailService;
import com.zjf.finder.constant.Constant;

/**
 * Created by Administrator on 2018/1/6.
 */

public class CategoryDetailModel implements CategoryDetailContract.Model {

    @Override
    public void getCategoryDetailList(String categoryId, String rankIndex, final CategoryDetailContract.OnListener listener) {
        RetrofitHttpClient.getInstance().forRetrofit(Constant.FINDER_BASE_URL, CategoryDetailService.class)
                .getCategoryDetailList(Constant.Common.ACCESS_ARTICLE, Constant.Common.SUBMIT_CATEGORYDETAIL, categoryId, rankIndex).
                enqueue(new HttpCallback<Result<CategoryDetailData>>() {
            @Override
            public void onResponse(Result<CategoryDetailData> categoryDetailDataResult) {
                if(categoryDetailDataResult != null && categoryDetailDataResult.getData() != null &&
                        categoryDetailDataResult.getData().getList() != null){
                    CategoryDetailData categoryDetailData = categoryDetailDataResult.getData();
                    listener.onCategoryDetailSuccessListener(categoryDetailData);
                } else{
                    listener.onCategoryDetailErrorListener(HttpCallback.CODE_FAILURE, BaseApplication.getContext().getString(R.string.net_error_text));
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                listener.onCategoryDetailErrorListener(code, msg);
            }

        });


//        RetrofitHttpClient.getInstance().forRetrofit(Constant.BASE_URL, CategoryDetailService.class)
//                .getNewsData(category, Constant.APIKEY, "10", page).enqueue(new HttpCallback2<Result2>() {
//            @Override
//            public void onResponse(Result2 result2) {
//                if(result2 != null && result2.getNewslist() != null){
//                    List<News> newsList = result2.getNewslist();
//                    listener.onCategoryDetailSuccessListener(newsList);
//                } else{
//                    listener.onCategoryDetailErrorListener(HttpCallback2.CODE_FAILURE, BaseApplication.getContext().getString(R.string.net_error_text));
//                }
//            }
//
//            @Override
//            public void onFailure(int code, String msg) {
//                listener.onCategoryDetailErrorListener(code, msg);
//            }
//        });
    }

}
