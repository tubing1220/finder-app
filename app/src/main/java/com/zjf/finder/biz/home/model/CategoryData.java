package com.zjf.finder.biz.home.model;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public class CategoryData {
    private List<Category> list;

    public List<Category> getList() {
        return list;
    }

    public void setList(List<Category> categoryList) {
        this.list = categoryList;
    }

    public String toString(){
        return new Gson().toJson(this);
    }

}
