package com.zjf.finder.biz.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

/**
 * Created by zhengjunfei on 2018/1/8.
 */

public class Category implements Parcelable {

    /**
     * id : 10000
     * name : 前端
     * link : /welcome/frontend
     * true_category_id : 5562b415e4b00c57d9b94ac8
     */

    private String id;
    private String name;
    private String link;
    private String true_category_id;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTrue_category_id() {
        return true_category_id;
    }

    public void setTrue_category_id(String true_category_id) {
        this.true_category_id = true_category_id;
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
        dest.writeString(this.link);
        dest.writeString(this.true_category_id);
    }

    public Category() {
    }

    protected Category(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.link = in.readString();
        this.true_category_id = in.readString();
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
