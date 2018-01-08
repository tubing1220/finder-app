package com.zjf.finder.base.view;

import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.zjf.finder.R;
import com.zjf.finder.base.activity.BaseActivity;
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
    private String mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_common_webview);
        initView();
    }

    private void initView() {
        initSetting();
        getIntentData();
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

    private void getIntentData() {
        mUrl = getIntent().getStringExtra(Constant.CommonWebActivity.EXTRA_URL);
        mTitle = getIntent().getStringExtra(Constant.CommonWebActivity.EXTRA_TITLE);
        Uri uri = getIntent().getData();
        if (TextUtils.isEmpty(mUrl) && uri != null) {
            mUrl = UriParamQueryUtils.getStringQueryParameter(uri, Constant.CommonWebActivity.Web_Url);
        }
        if (TextUtils.isEmpty(mTitle) && uri != null) {
            mTitle = UriParamQueryUtils.getStringQueryParameter(uri, Constant.CommonWebActivity.Web_Title);
        }
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
    public void onBackPressed() {
//        Uri uri = getIntent().getData();
//        if (uri != null && TextUtils.equals(UriParamQueryUtils.getStringQueryParameter(uri, ProtocolKey.Push.IS_FROM_PUSH), ProtocolKey.Push.IS_FROM_PUSH)) {
//            Intent intent = new Intent(CommonWebViewActivity.this, MainActivity.class);
//            startActivity(intent);
//        }

        CommonWebViewActivity.this.finish();
    }
}
