package com.zjf.finder.biz.home.contract;

import com.zjf.finder.base.mvp.ui.BaseUI;
import com.zjf.finder.biz.home.model.CategoryDetail;
import com.zjf.finder.biz.home.model.CategoryDetailData;

import java.util.List;

/**
 * Created by Administrator on 2018/1/6.
 */

public class CategoryDetailContract {

    public interface UI extends BaseUI{
        void setCategoryDetailList(List<CategoryDetail> categoryDetailList, boolean isRefresh);
        void onCategoryDetailError(int code, String msg);
    }

    public interface Model{
        void getCategoryDetailList(String categoryId, String rankIndex, OnListener listener);
    }

    public interface OnListener{
        void onCategoryDetailSuccessListener(CategoryDetailData categoryDetailData);
        void onCategoryDetailErrorListener(int code, String msg);
    }

}
