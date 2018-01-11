package com.zjf.finder.biz.home.model;

import com.zjf.finder.R;
import com.zjf.finder.base.BaseApplication;
import com.zjf.finder.base.http.HttpCallback;
import com.zjf.finder.base.http.Result;
import com.zjf.finder.base.http.RetrofitHttpClient;
import com.zjf.finder.biz.home.contract.HomeContract;
import com.zjf.finder.biz.home.service.HomeService;
import com.zjf.finder.constant.Constant;

/**
 * Created by Administrator on 2018/1/11.
 */

public class HomeModel implements HomeContract.Model {

    @Override
    public void getCategoryList(final HomeContract.OnListener listener) {
        RetrofitHttpClient.getInstance().forRetrofit(Constant.FINDER_BASE_URL, HomeService.class).
                getCategoryList(Constant.Common.ACCESS_ARTICLE, Constant.Common.SUBMIT_CATEGORYLIST).
                enqueue(new HttpCallback<Result<CategoryData>>() {
                    @Override
                    public void onResponse(Result<CategoryData> categoryDataResult) {
                        if(categoryDataResult != null && categoryDataResult.getData() != null &&
                                categoryDataResult.getData().getList() != null){
                            listener.onCategoryListSuccessListener(categoryDataResult.getData().getList());
                        } else{
                            listener.onCategoryListErrorListener(HttpCallback.CODE_FAILURE, BaseApplication.getContext().getString(R.string.net_error_text));
                        }
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        super.onFailure(code, msg);
                        listener.onCategoryListErrorListener(code, msg);
                    }
                });
    }

}
