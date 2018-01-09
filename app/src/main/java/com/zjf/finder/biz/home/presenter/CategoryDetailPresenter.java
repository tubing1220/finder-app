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
        mPage = 1;
        mModel = new CategoryDetailModel();
    }

    private CategoryDetailContract.UI getUI(){
        return getUI(CategoryDetailContract.UI.class);
    }

    public void getCategoryDetailList(String category){
        if(mModel != null){
            mModel.getCategoryDetailList(category, mPage++, this);
        }
    }

    @Override
    public void onCategoryDetailSuccessListener(List<News> categoryDetailList) {
        CategoryDetailContract.UI ui = getUI();
        if(ui != null){
            ui.setCategoryDetailList(categoryDetailList, mPage == 2);
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
