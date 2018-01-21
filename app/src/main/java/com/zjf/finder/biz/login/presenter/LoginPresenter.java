package com.zjf.finder.biz.login.presenter;

import com.zjf.finder.base.mvp.presenter.BasePresenter;
import com.zjf.finder.biz.login.contract.LoginContract;
import com.zjf.finder.biz.login.model.LoginModel;
import com.zjf.finder.biz.login.model.User;

/**
 * Created by Administrator on 2018/1/21.
 */

public class LoginPresenter extends BasePresenter implements LoginContract.OnListener {
    private LoginContract.Model mMdeol;

    public LoginPresenter(LoginContract.UI ui){
        addWeakRefObj(ui);
        mMdeol = new LoginModel();
    }

    public LoginContract.UI getUI(){
        return getUI(LoginContract.UI.class);
    }

    public void login(String appId, String appKey, String unionid){
        if(mMdeol != null){
            mMdeol.login(appId, appKey, unionid, this);
        }
    }

    @Override
    public void onLoginSuccessListener(User user) {
        LoginContract.UI ui = getUI();
        if(ui != null){
            ui.onLoginSuccess(user);
        }
    }

    @Override
    public void onLoginErrorListener(int code, String msg) {
        LoginContract.UI ui = getUI();
        if(ui != null){
            ui.onLoginError(code, msg);
        }
    }
}
