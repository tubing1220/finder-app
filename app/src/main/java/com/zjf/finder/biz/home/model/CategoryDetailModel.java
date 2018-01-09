package com.zjf.finder.biz.home.model;

import com.zjf.finder.R;
import com.zjf.finder.base.BaseApplication;
import com.zjf.finder.base.http.HttpCallback2;
import com.zjf.finder.base.http.Result2;
import com.zjf.finder.base.http.RetrofitHttpClient;
import com.zjf.finder.biz.home.contract.CategoryDetailContract;
import com.zjf.finder.biz.home.service.NewsService;
import com.zjf.finder.constant.Constant;

import java.util.List;

/**
 * Created by Administrator on 2018/1/6.
 */

public class CategoryDetailModel implements CategoryDetailContract.Model {

    @Override
    public void getCategoryDetailList(String category, int page, final CategoryDetailContract.OnListener listener) {
//        RetrofitHttpClient.getInstance().forRetrofit(Constant.BASE_URL, NewsService.class)
//                .getCategoryDetailList("Article", "categoryDetail").enqueue(new HttpCallback<Result<List<CategoryDetail>>>() {
//            @Override
//            public void onResponse(Result<List<CategoryDetail>> listResult) {
//                if(listResult != null && listResult.getData() != null){
//                    List<CategoryDetail> categoryDetails = listResult.getData();
//                    listener.onCategoryDetailSuccessListener(categoryDetails);
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
        RetrofitHttpClient.getInstance().forRetrofit(Constant.BASE_URL, NewsService.class)
                .getNewsData(category, Constant.APIKEY, "10", page).enqueue(new HttpCallback2<Result2>() {
            @Override
            public void onResponse(Result2 result2) {
                if(result2 != null && result2.getNewslist() != null){
                    List<News> newsList = result2.getNewslist();
                    listener.onCategoryDetailSuccessListener(newsList);
                } else{
                    listener.onCategoryDetailErrorListener(HttpCallback2.CODE_FAILURE, BaseApplication.getContext().getString(R.string.net_error_text));
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                listener.onCategoryDetailErrorListener(code, msg);
            }
        });
    }

}
