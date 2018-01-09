package com.zjf.finder.biz.home.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.zjf.finder.base.adapter.FragmentItemIdStatePagerAdapter;
import com.zjf.finder.base.fragment.BaseFragment;
import com.zjf.finder.biz.home.interfaces.CategoryItem;
import com.zjf.finder.biz.home.model.Category;
import com.zjf.finder.biz.home.ui.CategoryDetailFragment;
import com.zjf.finder.constant.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengjunfei on 2018/1/8.
 */

public class TabPageIndicatorAdapter extends FragmentItemIdStatePagerAdapter {
    private List<Category> mTabList = new ArrayList<>();
    private BaseFragment mCurrentFragment;

    public TabPageIndicatorAdapter(List<Category> categoryList, FragmentManager fm) {
        super(fm);
        this.mTabList.addAll(categoryList);
    }

    public void setData(List<Category> categoryList){
        mTabList.clear();
        mTabList.addAll(categoryList);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        CategoryDetailFragment categoryDetailFragment = new CategoryDetailFragment();
        Bundle args = new Bundle();
        Category category = mTabList.get(position);
        args.putParcelable(Constant.CategoryDetailFragment.EXTRA_CATEGORY, category);
        categoryDetailFragment.setArguments(args);
        return categoryDetailFragment;
    }

    @Override
    public int getItemPosition(Object object) {
        BaseFragment itemFragment = (BaseFragment) object;
        int position = -1;
        if (itemFragment instanceof CategoryItem) {
            position = getTabListPosition(((CategoryItem) itemFragment).getCategory());
        }

        Log.i("PagerAdapterPosition : ", String.valueOf(position));

        if (position >= 0) {
            return position;
        } else {
            return PagerAdapter.POSITION_NONE;
        }
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentFragment = (BaseFragment) object;
        super.setPrimaryItem(container, position, object);
    }

    private int getTabListPosition(String category) {
        int tabListSize = mTabList.size();
        for (int i = 0; i < tabListSize; i++) {
            if (category.equals(mTabList.get(i).getName())) {
                return i;
            }
        }
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mTabList == null ? 0 : mTabList.size();
    }
}
