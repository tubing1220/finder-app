package com.zjf.finder.biz.home.presenter;

import com.zjf.finder.base.mvp.presenter.BasePresenter;
import com.zjf.finder.biz.home.contract.HomeContract;
import com.zjf.finder.biz.home.model.Category;
import com.zjf.finder.biz.home.model.HomeModel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public class HomePresenter extends BasePresenter implements HomeContract.OnListener {
    private HomeContract.Model mModel;

    public HomePresenter(HomeContract.UI ui){
        addWeakRefObj(ui);
        mModel = new HomeModel();
    }

    private HomeContract.UI getUI(){
        return getUI(HomeContract.UI.class);
    }

    public void getCategoryList(){
        if(mModel != null){
            mModel.getCategoryList(this);
        }
    }

    @Override
    public void onCategoryListSuccessListener(List<Category> categorylList) {
        HomeContract.UI ui = getUI();
        if(ui != null){
            ui.setCategoryList(categorylList);
        }
    }

    @Override
    public void onCategoryListErrorListener(int code, String msg) {
        HomeContract.UI ui = getUI();
        if(ui != null){
            ui.onCategoryListError(code, msg);
        }
    }
}
