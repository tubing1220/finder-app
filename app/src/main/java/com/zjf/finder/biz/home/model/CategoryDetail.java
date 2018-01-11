package com.zjf.finder.biz.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

/**
 * Created by zhengjunfei on 2018/1/6.
 */

public class CategoryDetail implements Parcelable {

    /**
     * id : 5a559dd36fb9a01c9e45d896
     * name : zoumiaojiang
     * headerUrl : https://avatars5.githubusercontent.com/u/3365978?v=4
     * originalUrl : https://zoumiaojiang.com/article/common-web-security/
     * classifyName : 前端
     * title : 常见 Web 安全攻防总结
     * desc : Web 安全的对于 Web 从业人员来说是一个非常重要的课题，所以在这里总结一下 Web 相关的安全攻防知识，希望以后不要再踩雷，也希望对看到这篇文章的同学有所帮助。今天这边文章主要的内容就是分析几种常见的攻击的类型以及防御的方法。 也许你对所有的安全问题都有一定的认识，但最主…
     * createtime : 1515679752
     */

    private String id;
    private String name;
    private String headerUrl;
    private String originalUrl;
    private String classifyName;
    private String title;
    private String desc;
    private int createtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCreatetime() {
        return createtime;
    }

    public void setCreatetime(int createtime) {
        this.createtime = createtime;
    }

    public String toString(){
        return new Gson().toJson(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.headerUrl);
        dest.writeString(this.originalUrl);
        dest.writeString(this.classifyName);
        dest.writeString(this.title);
        dest.writeString(this.desc);
        dest.writeInt(this.createtime);
    }

    public CategoryDetail() {
    }

    protected CategoryDetail(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.headerUrl = in.readString();
        this.originalUrl = in.readString();
        this.classifyName = in.readString();
        this.title = in.readString();
        this.desc = in.readString();
        this.createtime = in.readInt();
    }

    public static final Parcelable.Creator<CategoryDetail> CREATOR = new Parcelable.Creator<CategoryDetail>() {
        @Override
        public CategoryDetail createFromParcel(Parcel source) {
            return new CategoryDetail(source);
        }

        @Override
        public CategoryDetail[] newArray(int size) {
            return new CategoryDetail[size];
        }
    };
}
