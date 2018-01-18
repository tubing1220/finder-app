package com.zjf.finder.biz.home.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Toast;

import com.zjf.finder.R;
import com.zjf.finder.base.fragment.BaseFragment;
import com.zjf.finder.base.view.StateView;
import com.zjf.finder.biz.home.adapter.NavigatorAdapter;
import com.zjf.finder.biz.home.adapter.TabPageIndicatorAdapter;
import com.zjf.finder.biz.home.contract.HomeContract;
import com.zjf.finder.biz.home.interfaces.CategoryItem;
import com.zjf.finder.biz.home.model.Category;
import com.zjf.finder.biz.home.presenter.HomePresenter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/1/12.
 */

public class HomePageFragment extends BaseFragment implements NavigatorAdapter.TabListCLickListener, SwipeRefreshLayout.OnRefreshListener, HomeContract.UI, TabPageIndicatorAdapter.Callback {
    @BindView(R.id.indicator)
    MagicIndicator mIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.activity_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private TabPageIndicatorAdapter mFragmentPagerAdapter;
    private NavigatorAdapter mCommonNavigatorAdapter;
    private HomePresenter mPresenter;
    private List<Category> mTabList = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        mPresenter = new HomePresenter(this);
        mFragmentPagerAdapter = new TabPageIndicatorAdapter(mTabList, getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mFragmentPagerAdapter.setCallback(this);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_loadding_first_color, R.color.refresh_loadding_second_color);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mViewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mCommonNavigatorAdapter = new NavigatorAdapter(mTabList);
        mCommonNavigatorAdapter.setTabListCLickListener(this);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(false);
        commonNavigator.setFollowTouch(false);
        commonNavigator.setEnablePivotScroll(true);
        commonNavigator.setBackgroundColor(Color.parseColor("#FFF6F6F6"));
        commonNavigator.setAdapter(mCommonNavigatorAdapter);
        mIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mIndicator, mViewPager);

        setState(StateView.STATE_LOADDING);
        initData();
    }

    private void initData(){
        mPresenter.getCategoryList();
    }

    @Override
    public void onRefresh() {
        BaseFragment currentFragment = mFragmentPagerAdapter.getCurrentFragment();
        if(currentFragment != null){
            ((CategoryItem)currentFragment).OnRefresh();
        }
    }

    @Override
    public void setCategoryList(List<Category> categoryList) {
        setState(StateView.STATE_OK);
        List<Category> categoryListTemp = new ArrayList<>();
        categoryListTemp.addAll(categoryList);
        mFragmentPagerAdapter.setData(categoryListTemp);
        mCommonNavigatorAdapter.setData(categoryListTemp);
        mIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCategoryListError(int code, String msg) {
        setState(StateView.STATE_ERROR);
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnTabListCLickListener(int index) {
        mViewPager.setCurrentItem(index);
    }

    @Override
    public void finishRefresh() {
        if(mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onRetry() {
        if(mFragmentPagerAdapter != null){
            initData();
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_page;
    }
}
