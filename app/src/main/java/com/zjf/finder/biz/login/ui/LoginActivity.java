package com.zjf.finder.biz.login.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.zjf.finder.R;
import com.zjf.finder.base.activity.BaseActivity;
import com.zjf.finder.biz.login.contract.LoginContract;
import com.zjf.finder.biz.login.model.User;
import com.zjf.finder.biz.login.presenter.LoginPresenter;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginContract.UI {
    @BindView(R.id.close_btn)
    ImageView mCloseBtn;
    @BindView(R.id.wechat_login_btn)
    ImageView mWechatLoginBtn;
    private LoginPresenter mPresenter;

    public static void start(Context context){
        Intent intent  = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initTitle();
        initView();
    }

    private void initTitle(){
        setCustomToolBarVisiable(false);
    }

    private void initView(){
        mCloseBtn.setOnClickListener(this);
        mWechatLoginBtn.setOnClickListener(this);
        mPresenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.wechat_login_btn:
                Toast.makeText(getApplicationContext(), "你点击了登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.close_btn:
                finish();
                break;
        }
    }

    @Override
    public void onLoginSuccess(User user) {

    }

    @Override
    public void onLoginError(int code, String msg) {

    }
}
