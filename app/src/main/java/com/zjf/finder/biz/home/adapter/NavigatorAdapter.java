package com.zjf.finder.biz.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.zjf.finder.base.view.ColorFlipPagerTitleView;
import com.zjf.finder.biz.home.model.Category;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.List;

/**
 * Created by zhengjunfei on 2018/1/9.
 */

public class NavigatorAdapter extends CommonNavigatorAdapter {
    private List<Category> mCategoryList;
    private TabListCLickListener mListener;
    private float leaveCurrentPercent;
    private float enterCurrentPercent;

    public NavigatorAdapter(List<Category> categoryList){
        this.mCategoryList = categoryList;
    }

    public void setData(List<Category> categoryList){
        if(categoryList != null){
            this.mCategoryList.clear();
            this.mCategoryList.addAll(categoryList);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mCategoryList == null ? 0 : mCategoryList.size();
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int index) {
        SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
        simplePagerTitleView.setNormalColor(Color.parseColor("#383838"));
        simplePagerTitleView.setSelectedColor(Color.parseColor("#3F51B5"));
        simplePagerTitleView.setText(mCategoryList.get(index).getName());
        simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    mListener.OnTabListCLickListener(index);
                }
            }
        });
        return simplePagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator indicator = new LinePagerIndicator(context);
        indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
        indicator.setLineHeight(UIUtil.dip2px(context, 3));
        indicator.setLineWidth(UIUtil.dip2px(context, 18));
        indicator.setRoundRadius(UIUtil.dip2px(context, 3));
        indicator.setStartInterpolator(new AccelerateInterpolator());
        indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
        indicator.setColors(Color.parseColor("#3F51B5"));
        return indicator;
    }

    public void setTabListCLickListener(TabListCLickListener listener) {
        this.mListener = listener;
    }

    public interface TabListCLickListener {
        void OnTabListCLickListener(int index);
    }
}
