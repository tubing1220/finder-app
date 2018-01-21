package com.zjf.finder.biz.login.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2018/1/21.
 */

public class User implements Parcelable {
    private String id;
    private String user_name;
    private String header_url;
    private int create_article_num;
    private int save_article_num;
    private int like_article_num;
    private int follow_author_num;
    private int fans_num;
    private int classify_follow_num;
    private int History_num;
    private String company_info;
    private int position;
    private String introduce;
    private String blog_address;
    private long create_time;
    private long update_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getHeaderUrl() {
        return header_url;
    }

    public void setHeaderUrl(String header_url) {
        this.header_url = header_url;
    }

    public int getCreateArticleNum() {
        return create_article_num;
    }

    public void setCreateArticleNum(int create_article_num) {
        this.create_article_num = create_article_num;
    }

    public int getSaveArticlNum() {
        return save_article_num;
    }

    public void setSaveArticleNum(int save_article_num) {
        this.save_article_num = save_article_num;
    }

    public int getLikeArticleNum() {
        return like_article_num;
    }

    public void setLikeArticleNum(int like_article_num) {
        this.like_article_num = like_article_num;
    }

    public int getFollowAuthorNum() {
        return follow_author_num;
    }

    public void setFollowAuthorNum(int follow_author_num) {
        this.follow_author_num = follow_author_num;
    }

    public int getFansNum() {
        return fans_num;
    }

    public void setFansNum(int fans_num) {
        this.fans_num = fans_num;
    }

    public int getClassifyFollowNum() {
        return classify_follow_num;
    }

    public void setClassifyFollowNum(int classify_follow_num) {
        this.classify_follow_num = classify_follow_num;
    }

    public int getHistoryNum() {
        return History_num;
    }

    public void setHistoryNum(int history_num) {
        History_num = history_num;
    }

    public String getCompanyInfo() {
        return company_info;
    }

    public void setCompanyInfo(String company_info) {
        this.company_info = company_info;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getBlogAddress() {
        return blog_address;
    }

    public void setBlogAddress(String blog_address) {
        this.blog_address = blog_address;
    }

    public long getCreateTime() {
        return create_time;
    }

    public void setCreateTime(long create_time) {
        this.create_time = create_time;
    }

    public long getUpdateTime() {
        return update_time;
    }

    public void setUpdateTime(long update_time) {
        this.update_time = update_time;
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
        dest.writeString(this.user_name);
        dest.writeString(this.header_url);
        dest.writeInt(this.create_article_num);
        dest.writeInt(this.save_article_num);
        dest.writeInt(this.like_article_num);
        dest.writeInt(this.follow_author_num);
        dest.writeInt(this.fans_num);
        dest.writeInt(this.classify_follow_num);
        dest.writeInt(this.History_num);
        dest.writeString(this.company_info);
        dest.writeInt(this.position);
        dest.writeString(this.introduce);
        dest.writeString(this.blog_address);
        dest.writeLong(this.create_time);
        dest.writeLong(this.update_time);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.user_name = in.readString();
        this.header_url = in.readString();
        this.create_article_num = in.readInt();
        this.save_article_num = in.readInt();
        this.like_article_num = in.readInt();
        this.follow_author_num = in.readInt();
        this.fans_num = in.readInt();
        this.classify_follow_num = in.readInt();
        this.History_num = in.readInt();
        this.company_info = in.readString();
        this.position = in.readInt();
        this.introduce = in.readString();
        this.blog_address = in.readString();
        this.create_time = in.readLong();
        this.update_time = in.readLong();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
