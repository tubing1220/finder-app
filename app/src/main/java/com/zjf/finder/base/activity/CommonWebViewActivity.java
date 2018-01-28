package com.zjf.finder.base.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.zjf.finder.R;
import com.zjf.finder.constant.Constant;
import com.zjf.finder.utils.UriParamQueryUtils;

import butterknife.BindView;

/**
 * Created by zhengjunfei on 2018/1/8.
 */

public class CommonWebViewActivity extends BaseActivity {
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.webview)
    WebView mWebview;

    private String mUrl;
    private String mHeaderUrl;
    private String mTitle;

    public static void start(Context context, String url, String headerUrl, String title){
        Intent intent  = new Intent(context, CommonWebViewActivity.class);
        intent.putExtra(Constant.CommonWebActivity.EXTRA_URL, url);
        intent.putExtra(Constant.CommonWebActivity.EXTRA_HEADER_URL, headerUrl);
        intent.putExtra(Constant.CommonWebActivity.EXTRA_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_common_webview);
        getIntentData();
        initTitle();
        initView();
    }

    private void getIntentData() {
        mUrl = getIntent().getStringExtra(Constant.CommonWebActivity.EXTRA_URL);
        mHeaderUrl = getIntent().getStringExtra(Constant.CommonWebActivity.EXTRA_HEADER_URL);
        mTitle = getIntent().getStringExtra(Constant.CommonWebActivity.EXTRA_TITLE);
        Uri uri = getIntent().getData();
        if (TextUtils.isEmpty(mUrl) && uri != null) {
            mUrl = UriParamQueryUtils.getStringQueryParameter(uri, Constant.CommonWebActivity.Web_Url);
        }
        if (TextUtils.isEmpty(mTitle) && uri != null) {
            mTitle = UriParamQueryUtils.getStringQueryParameter(uri, Constant.CommonWebActivity.Web_Title);
        }
    }

    private void initTitle(){
        getCustomToolBar().setTitleLeftPhoto(mHeaderUrl, mTitle);
    }

    private void initView() {
        initSetting();
        mWebview.loadUrl(mUrl);
    }

    private void initSetting() {
        mWebview.setWebViewClient(OnWebViewClient);
        mWebview.setWebChromeClient(OnClientListener);
        mWebview.setHorizontalScrollBarEnabled(false);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setDatabaseEnabled(true);
        mWebview.getSettings().setDomStorageEnabled(true);
        mWebview.getSettings().setGeolocationEnabled(true);
    }

    private WebViewClient OnWebViewClient = new WebViewClient() {

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            handler.proceed();//接受证书,这样可以打开https的网页
        }
    };

    private WebChromeClient OnClientListener = new WebChromeClient() {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mProgressBar.setProgress(newProgress);
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.GONE);
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && mWebview != null && mWebview.canGoBack()){
            mWebview.goBack();
        } else{
            CommonWebViewActivity.this.finish();
        }
        return true;
    }
}
