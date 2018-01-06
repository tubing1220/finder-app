package com.zjf.finder.base.mvp.presenter;

/**
 * Created by zhengjunfei on 18-1-6.
 */

public interface BasePresenterInterface {

    /**
     * unBind:释放资源. <br/>
     *
     * @author adison
     */
    void unBind();

    /**
     * 逻辑开始
     */
    void start();

    /**
     * 注册资源
     */
    void bind();


}
