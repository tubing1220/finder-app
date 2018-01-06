package com.zjf.finder.biz.home.model;

import com.google.gson.Gson;

/**
 * Created by zhengjunfei on 2018/1/3.
 */

public class News {
    /**
     * ctime : 2018-01-03 00:00
     * title : “雪乡宰客”酒店赵家大院：对方就是想讹钱
     * description : 凤凰社会
     * picUrl : http://d.ifengimg.com/w150_h95/p0.ifengimg.com/fck/2018_01/6ba67ce54e7dd2e_w641_h361.jpg
     * url : http://news.ifeng.com/a/20180103/54779066_0.shtml
     */

    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toString(){
        return new Gson().toJson(this);
    }
}
