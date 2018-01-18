package com.zjf.finder.base.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.zjf.finder.R;

/**
 * Created by zhengjunfei on 2018/1/8.
 */

public class StateView extends LinearLayout implements View.OnClickListener {

    public interface Callback{
        void onRetryListener();
    }

    public static final int STATE_DEFAULT = 0;
    public static final int STATE_EMPTY = 1;
    public static final int STATE_LOADDING = 2;
    public static final int STATE_ERROR = 3;
    public static final int STATE_OK = 4;

    private Callback mCallback;

    public void setCallback(Callback callback){
        this.mCallback = callback;
    }

    public StateView(Context context) {
        super(context);
    }

    public StateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StateView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setState(int state, View continerView){
        removeAllViews();
        switch (state){
            case STATE_EMPTY:
                setVisibility(View.VISIBLE);
                continerView.setVisibility(View.GONE);
                LayoutInflater.from(getContext()).inflate(R.layout.layout_state_empty, this, true);
                break;
            case STATE_LOADDING:
                setVisibility(View.VISIBLE);
                continerView.setVisibility(View.GONE);
                LayoutInflater.from(getContext()).inflate(R.layout.layout_state_loadding, this, true);
                break;
            case STATE_ERROR:
                setVisibility(View.VISIBLE);
                continerView.setVisibility(View.GONE);
                View errorView = LayoutInflater.from(getContext()).inflate(R.layout.layout_state_error, this, true);
                errorView.findViewById(R.id.try_again_btn).setOnClickListener(this);
                break;
            case STATE_OK:
                setVisibility(View.GONE);
                continerView.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.try_again_btn:
                if(mCallback != null){
                    mCallback.onRetryListener();
                }
                break;
        }
    }

}
