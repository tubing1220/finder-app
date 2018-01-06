package com.zjf.finder.biz.home.model;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Administrator on 2018/1/6.
 */

public class CategoryDetailData {
    private List<CategoryDetail> list;

    public List<CategoryDetail> getList() {
        return list;
    }

    public void setList(List<CategoryDetail> list) {
        this.list = list;
    }

    public String toString(){
        return new Gson().toJson(this);
    }
}
