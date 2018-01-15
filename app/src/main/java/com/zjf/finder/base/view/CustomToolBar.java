package com.zjf.finder.base.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjf.finder.R;
import com.zjf.finder.base.BaseApplication;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/14.
 */

public class CustomToolBar extends Toolbar implements View.OnClickListener {
    @BindView(R.id.title_left_layout)
    RelativeLayout mTitleLeftLayout;
    @BindView(R.id.title_right_layout)
    RelativeLayout mTitleRightLayout;
    @BindView(R.id.toolbar_container)
    RelativeLayout mToolbarContainer;
    @BindView(R.id.title_left_button)
    ImageView mTitleLeftButton;
    @BindView(R.id.title_right_button)
    TextView mTitleRightButton;
    @BindView(R.id.title_right_left_button)
    TextView mTitleRightLeftButton;
    @BindView(R.id.title_content)
    TextView mTitleContent;
    @BindView(R.id.title_left_text)
    TextView mTitleLeftText;
    @BindView(R.id.toolbar)
    ViewGroup container;
    @BindView(R.id.toolbar_divider)
    View divider;

    public CustomToolBar(Context context) {
        super(context);
        initView();
    }

    public CustomToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        inflate(getContext(), R.layout.layout_base_title, this);
        ButterKnife.bind(this);
        mTitleLeftLayout.setOnClickListener(this);
        mTitleRightButton.setOnClickListener(this);
        mTitleRightLeftButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (mOnToolBarClickListener == null) {
            return;
        }
        if (view.getId() == R.id.title_left_layout) {
            mOnToolBarClickListener.onClickLeft();
        } else if (view.getId() == R.id.title_right_button) {
            mOnToolBarClickListener.onClickRight();
        } else if (view.getId() == R.id.title_right_left_button) {
            mOnToolBarClickListener.onClickRightLeft();
        }
    }

    public void setTitleBackgroundColor(int color){
        container.setBackgroundColor(ContextCompat.getColor(BaseApplication.getContext(), color));
    }

    public void setTitleLeftText(int resId) {
        mTitleLeftLayout.setVisibility(VISIBLE);
        mTitleLeftText.setVisibility(VISIBLE);
        mTitleLeftText.setText(resId);
    }

    public void setTitleLeftText(String text) {
        mTitleLeftLayout.setVisibility(VISIBLE);
        mTitleLeftText.setVisibility(VISIBLE);
        mTitleLeftText.setText(text);
    }

    public void setTitleRightLeftText(String text) {
        mTitleLeftLayout.setVisibility(VISIBLE);
        mTitleRightLeftButton.setVisibility(View.VISIBLE);
        mTitleRightLeftButton.setText(text);
    }

    public void setTitleRightLeftDrawable(int res) {
        mTitleLeftLayout.setVisibility(VISIBLE);
        mTitleRightLeftButton.setVisibility(View.VISIBLE);
        Drawable drawable = ContextCompat.getDrawable(getContext(), res);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTitleRightLeftButton.setCompoundDrawables(drawable, null, null, null);
    }

    public void setTitleRightLeftText(int resId) {
        mTitleLeftLayout.setVisibility(VISIBLE);
        mTitleRightLeftButton.setVisibility(View.VISIBLE);
        mTitleRightLeftButton.setText(resId);
    }

    public void setTitleContentText(int textId, int color){
        mTitleContent.setVisibility(VISIBLE);
        mTitleContent.setText(textId);
        if (color != 0){
            mTitleContent.setTextColor(color);
        }
    }

    public OnToolBarClickListener mOnToolBarClickListener;

    public void setToolBarClickListener(OnToolBarClickListener mOnToolBarClickListener) {
        this.mOnToolBarClickListener = mOnToolBarClickListener;
    }

    public interface OnToolBarClickListener {
        void onClickLeft();

        void onClickRightLeft();

        void onClickRight();
    }
}
