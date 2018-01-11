package com.zjf.finder.biz.home.contract;

import com.zjf.finder.base.mvp.ui.BaseUI;
import com.zjf.finder.biz.home.model.Category;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public class HomeContract {

    public interface UI extends BaseUI {
        void setCategoryList(List<Category> categoryList);
        void onCategoryListError(int code, String msg);
    }

    public interface Model{
        void getCategoryList(HomeContract.OnListener listener);
    }

    public interface OnListener{
        void onCategoryListSuccessListener(List<Category> categorylList);
        void onCategoryListErrorListener(int code, String msg);
    }
}
