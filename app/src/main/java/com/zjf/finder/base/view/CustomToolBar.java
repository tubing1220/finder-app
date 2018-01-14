package com.zjf.finder.base.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjf.finder.R;

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
    }

    public CustomToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
