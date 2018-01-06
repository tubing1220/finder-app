package com.zjf.finder.biz.home.model;

import com.google.gson.Gson;

/**
 * Created by zhengjunfei on 2018/1/6.
 */

public class CategoryDetail {


    /**
     * id : 1100
     * name : 骑摩托马斯
     * headerUrl : https: //dn-mhke0kuv.qbox.me/4aca6eae6cb95ca86c1d.jpeg?imageView2/1/w/100/h/100/q/85/format/webp/interlace/1
     * classifyName : Android
     * title : 如何获得微信小游戏跳一跳源码
     * desc : 昨天V2EX上的一篇通过抓包来获取微信跳一跳源码的文章走红，文章连接点击这里我也在通过文章中的方式进行了抓包，但是并未探测到小游戏的下载连接，可能微信对此已经进行了修复。而且上文中提供的下载连接也显示为404的状态码。虽然抓包未果，但是依然可以从本地将源码抽离出来，下面将介绍如何从Android手机里面找到微信下载的小游戏源码作者：骑摩托马斯链接：https: //juejin.im/post/5a4b0fc7f265da431956a2b7来源：掘金著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * createtime : 1400786745
     */

    private String id;
    private String name;
    private String headerUrl;
    private String classifyName;
    private String title;
    private String desc;
    private String createtime;

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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String toString(){
        return new Gson().toJson(this);
    }
}
