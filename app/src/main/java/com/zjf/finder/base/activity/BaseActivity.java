package com.zjf.finder.base.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.zjf.finder.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
    }

    @Override
    public void setContentView(int layoutResID) {
        View contentView = View.inflate(this, layoutResID, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ((LinearLayout) findViewById(R.id.activity_root_layout)).addView(contentView, 0, layoutParams);
        ButterKnife.bind(this);
    }

}
