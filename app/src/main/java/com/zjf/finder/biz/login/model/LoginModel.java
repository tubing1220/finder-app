package com.zjf.finder.biz.login.model;

import com.zjf.finder.R;
import com.zjf.finder.base.BaseApplication;
import com.zjf.finder.base.http.HttpCallback;
import com.zjf.finder.base.http.Result;
import com.zjf.finder.base.http.RetrofitHttpClient;
import com.zjf.finder.biz.login.contract.LoginContract;
import com.zjf.finder.biz.login.service.LoginService;
import com.zjf.finder.constant.Constant;

/**
 * Created by Administrator on 2018/1/21.
 */

public class LoginModel implements LoginContract.Model {

    @Override
    public void login(String appId, String appKey, String unionid, final LoginContract.OnListener listener) {
        RetrofitHttpClient.getInstance().forRetrofit(Constant.FINDER_BASE_URL, LoginService.class).
                login(appId, appKey, unionid).enqueue(new HttpCallback<Result<User>>() {
            @Override
            public void onResponse(Result<User> userResult) {
                if(userResult != null && userResult.getData() != null){
                    listener.onLoginSuccessListener(userResult.getData());
                } else{
                    listener.onLoginErrorListener(HttpCallback.CODE_FAILURE, BaseApplication.getContext().getString(R.string.net_error_text));
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                super.onFailure(code, msg);
                listener.onLoginErrorListener(code, msg);
            }
        });
    }

}
