package com.zjf.finder.biz.home.interfaces;

import com.zjf.finder.biz.home.model.Category;

/**
 * Created by zhengjunfei on 2018/1/8.
 */

public interface CategoryItem {
    String getCategory();
    void setCategory(Category category);
    void OnRefresh();
}
