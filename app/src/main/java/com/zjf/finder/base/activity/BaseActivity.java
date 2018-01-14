package com.zjf.finder.base.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.zjf.finder.R;
import com.zjf.finder.base.view.CustomToolBar;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements CustomToolBar.OnToolBarClickListener {
    private CustomToolBar mCustomToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        initCommonView();
    }

    //初始化通用的View
    public void initCommonView(){
        mCustomToolBar = (CustomToolBar) findViewById(R.id.activity_base_tool_bar);
        mCustomToolBar.setToolBarClickListener(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        View contentView = View.inflate(this, layoutResID, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ((LinearLayout) findViewById(R.id.activity_root_layout)).addView(contentView, 0, layoutParams);
        ButterKnife.bind(this);
    }
    protected void setTitleContentText(int textId, int color){
        mCustomToolBar.setVisibility(View.VISIBLE);
        mCustomToolBar.setTitleContentText(textId, color);
    }

    protected void setTitleBackgroundColor(int color){
        mCustomToolBar.setTitleBackgroundColor(color);
    }

    @Override
    public void onClickRight() {
        //TODO:toolbar右侧点击事件响应，子类根据自身情况处理
    }

    @Override
    public void onClickRightLeft() {
        //TODO:toolbar右侧的左侧点击事件响应，子类根据自身情况处理
    }

    @Override
    public void onClickLeft() {
        finish();
        //TODO:toolbar左侧点击事件响应,子类根据自身情况处理
    }

}
