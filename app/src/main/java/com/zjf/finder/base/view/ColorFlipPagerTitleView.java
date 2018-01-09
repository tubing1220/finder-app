package com.zjf.finder.base.view;

import android.content.Context;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

/**
 * Created by wangsiyuan1 on 17-5-8.
 */
public class ColorFlipPagerTitleView extends SimplePagerTitleView {
    private float mChangePercent = 0.5f;

    public ColorFlipPagerTitleView(Context context) {
        super(context);
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        if (leavePercent >= mChangePercent) {
            setTextColor(mNormalColor);
        } else {
            setTextColor(mSelectedColor);
        }

        if (onPageTitleListener != null) {
            onPageTitleListener.onLeave(index, totalCount, leavePercent, leftToRight);
        }

    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        if (enterPercent >= mChangePercent) {
            setTextColor(mSelectedColor);
        } else {
            setTextColor(mNormalColor);
        }

        if (onPageTitleListener != null) {
            onPageTitleListener.onEnter(index, totalCount, enterPercent, leftToRight);
        }
    }

    @Override
    public void onSelected(int index, int totalCount) {
    }

    @Override
    public void onDeselected(int index, int totalCount) {
    }

    public float getChangePercent() {
        return mChangePercent;
    }

    public void setChangePercent(float changePercent) {
        mChangePercent = changePercent;
    }


    public void setOnPageTitleListener(OnPageTitleListener onPageTitleListener) {
        this.onPageTitleListener = onPageTitleListener;
    }

    public OnPageTitleListener onPageTitleListener;

    public interface OnPageTitleListener {
        void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight);

        void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight);
    }

}
