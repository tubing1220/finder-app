package com.zjf.finder.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zjf.finder.R;
import com.zjf.finder.base.view.StateView;

import butterknife.ButterKnife;

/**
 * Created by zhengjunfei on 2018/1/8.
 */

public abstract class BaseFragment extends Fragment implements StateView.Callback {
    private View mRootView;
    private LinearLayout mRootContinerView;
    private StateView mStateView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_base, container, false);
        View contentView = View.inflate(container.getContext(), getContentView(), null);
        mRootContinerView = mRootView.findViewById(R.id.fragment_root_layout);
        mStateView = mRootView.findViewById(R.id.state_view);
        mStateView.setCallback(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mRootContinerView.addView(contentView, 0, layoutParams);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public int getState(){
        if(mStateView != null){
            return mStateView.getState();
        }
        return StateView.STATE_DEFAULT;
    }
    
    /**
     * 包含三种状态,loadding, error, ok
     * @author zhengjunfei
     * @data 2018/1/8
     *
     */
    public void setState(int state){
        mStateView.setState(state, mRootContinerView);
    }

    @Override
    public void onRetryListener() {
        onRetry();
    }

    public abstract void onRetry();

    /***
     * 获取Fragment中设置layout的id
     *
     * @return layout的id
     */
    protected abstract int getContentView();

}
