package com.zjf.finder.biz.login.contract;

import com.zjf.finder.base.mvp.ui.BaseUI;
import com.zjf.finder.biz.login.model.User;

/**
 * Created by Administrator on 2018/1/21.
 */

public interface LoginContract {
    public interface UI extends BaseUI {
        void onLoginSuccess(User user);
        void onLoginError(int code, String msg);
    }

    public interface Model{
        void login(String appId, String appKey, String unionid, OnListener listener);
    }

    public interface OnListener{
        void onLoginSuccessListener(User user);
        void onLoginErrorListener(int code, String msg);
    }
}
