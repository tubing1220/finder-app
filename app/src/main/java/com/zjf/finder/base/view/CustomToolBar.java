package com.zjf.finder.base.view;

import android.content.Context;
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
import com.zjf.finder.utils.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/14.
 */

public class CustomToolBar extends Toolbar implements View.OnClickListener {
    @BindView(R.id.title_left_layout)
    RelativeLayout mTitleLeftLayout;
    @BindView(R.id.layout_photo)
    RelativeLayout mLayoutPhoto;
    @BindView(R.id.title_left_photo_img)
    ImageView mTitleLeftPhoto;
    @BindView(R.id.title_left_text)
    TextView mTitleLeftText;
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

    public void setTitleLeftVisiable(boolean visiable){
        mTitleLeftLayout.setVisibility(visiable ? View.VISIBLE : View.GONE);
    }

    public void setTitleLeftImage(int resId){
        setTitleLeftVisiable(true);
        mTitleLeftButton.setImageResource(resId);
    }

    public void setTitleLeftContent(String text){
        setTitleLeftVisiable(true);
        mTitleLeftButton.setImageResource(R.drawable.base_back_gray);
        mTitleLeftText.setText(text);
    }

    public void setTitleLeftPhoto(String headUrl, String text){
        setTitleLeftVisiable(true);
        mTitleLeftText.setVisibility(View.VISIBLE);
        mTitleLeftText.setText(text);
        mTitleContent.setVisibility(View.GONE);
        mTitleLeftButton.setImageResource(R.drawable.base_back_gray);
        ImageLoader.loadCircle(getContext(), headUrl, mTitleLeftPhoto, R.drawable.default_no_sex_circle);
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

    public void setTitleContentText(String text){
        mTitleContent.setVisibility(VISIBLE);
        mTitleContent.setText(text);
    }

    public void setTitleContentText(String text, int color){
        mTitleContent.setVisibility(VISIBLE);
        mTitleContent.setText(text);
        if (color != 0){
            mTitleContent.setTextColor(color);
        }
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
