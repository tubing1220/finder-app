package com.zjf.finder.biz.home.presenter;

import android.text.TextUtils;

import com.zjf.finder.R;
import com.zjf.finder.base.BaseApplication;
import com.zjf.finder.base.http.HttpCallback;
import com.zjf.finder.base.mvp.presenter.BasePresenter;
import com.zjf.finder.biz.home.contract.CategoryDetailContract;
import com.zjf.finder.biz.home.model.Category;
import com.zjf.finder.biz.home.model.CategoryDetail;
import com.zjf.finder.biz.home.model.CategoryDetailData;
import com.zjf.finder.biz.home.model.CategoryDetailModel;
import com.zjf.finder.biz.home.model.News;

import java.util.List;

/**
 * Created by Administrator on 2018/1/6.
 */

public class CategoryDetailPresenter extends BasePresenter implements CategoryDetailContract.OnListener {
    private String mRankIndex;
    private CategoryDetailContract.Model mModel;
    private String mAfter;

    public CategoryDetailPresenter(CategoryDetailContract.UI ui){
        addWeakRefObj(ui);
        mRankIndex = "0";
        mModel = new CategoryDetailModel();
    }

    private CategoryDetailContract.UI getUI(){
        return getUI(CategoryDetailContract.UI.class);
    }

    public void getCategoryDetailList(boolean isRefresh, String categoryId){
        if(mModel != null){
            mRankIndex = isRefresh ? "0" : mRankIndex;
            mModel.getCategoryDetailList(categoryId, mRankIndex, this);
        }
    }

    @Override
    public void onCategoryDetailSuccessListener(CategoryDetailData categoryDetailData) {
        CategoryDetailContract.UI ui = getUI();
        if(ui == null){
            return;
        }
        mAfter = categoryDetailData.getAfter();
        mRankIndex = String.valueOf(categoryDetailData.getRankIndex());
        ui.setCategoryDetailList(categoryDetailData.getList(), TextUtils.isEmpty(mAfter));
    }

    @Override
    public void onCategoryDetailErrorListener(int code, String msg) {
        CategoryDetailContract.UI ui = getUI();
        if(ui != null){
            ui.onCategoryDetailError(code, msg);
        }
    }
}
