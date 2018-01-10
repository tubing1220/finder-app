package com.zjf.finder.biz.home.presenter;

import com.zjf.finder.base.mvp.presenter.BasePresenter;
import com.zjf.finder.biz.home.contract.CategoryDetailContract;
import com.zjf.finder.biz.home.model.CategoryDetailModel;
import com.zjf.finder.biz.home.model.News;

import java.util.List;

/**
 * Created by Administrator on 2018/1/6.
 */

public class CategoryDetailPresenter extends BasePresenter implements CategoryDetailContract.OnListener {
    private int mPage;
    private CategoryDetailContract.Model mModel;

    public CategoryDetailPresenter(CategoryDetailContract.UI ui){
        addWeakRefObj(ui);
        mPage = 0;
        mModel = new CategoryDetailModel();
    }

    private CategoryDetailContract.UI getUI(){
        return getUI(CategoryDetailContract.UI.class);
    }

    public void getCategoryDetailList(boolean isRefresh, String category){
        if(mModel != null){
            mPage = isRefresh ? 0 : mPage;
            mModel.getCategoryDetailList(category, mPage++, this);
        }
    }

    @Override
    public void onCategoryDetailSuccessListener(List<News> categoryDetailList) {
        CategoryDetailContract.UI ui = getUI();
        if(ui != null){
            ui.setCategoryDetailList(categoryDetailList, mPage == 1);
        }
    }

    @Override
    public void onCategoryDetailErrorListener(int code, String msg) {
        CategoryDetailContract.UI ui = getUI();
        if(ui != null){
            ui.onCategoryDetailError(code, msg);
        }
    }
}
