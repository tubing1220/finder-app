package com.zjf.finder.biz.home.contract;

import com.zjf.finder.base.mvp.ui.BaseUI;
import com.zjf.finder.biz.home.model.News;

import java.util.List;

/**
 * Created by Administrator on 2018/1/6.
 */

public class CategoryDetailContract {

    public interface UI extends BaseUI{
        void setCategoryDetailList(List<News> categoryDetailList, boolean isRefresh);
        void onCategoryDetailError(int code, String msg);
    }

    public interface Model{
        void getCategoryDetailList(int page, OnListener listener);
    }

    public interface OnListener{
        void onCategoryDetailSuccessListener(List<News> categoryDetailList);
        void onCategoryDetailErrorListener(int code, String msg);
    }

}
