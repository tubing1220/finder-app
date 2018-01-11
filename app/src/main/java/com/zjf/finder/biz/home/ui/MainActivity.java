package com.zjf.finder.biz.home.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Toast;

import com.zjf.finder.R;
import com.zjf.finder.base.activity.BaseActivity;
import com.zjf.finder.base.fragment.BaseFragment;
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

public class MainActivity extends BaseActivity implements NavigatorAdapter.TabListCLickListener, SwipeRefreshLayout.OnRefreshListener, HomeContract.UI {
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


    private CategoryDetailFragment mCategoryDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

//        showHomePageFragment();
//        getData2();
    }

    private void initView(){
        mPresenter = new HomePresenter(this);
        mFragmentPagerAdapter = new TabPageIndicatorAdapter(mTabList, getSupportFragmentManager());
        mViewPager.setAdapter(mFragmentPagerAdapter);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_loadding_first_color, R.color.refresh_loadding_second_color);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mViewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mIndicator = (MagicIndicator) findViewById(R.id.indicator);
        mCommonNavigatorAdapter = new NavigatorAdapter(mTabList);
        mCommonNavigatorAdapter.setTabListCLickListener(this);
        CommonNavigator commonNavigator = new CommonNavigator(getApplicationContext());
        commonNavigator.setAdjustMode(false);
        commonNavigator.setFollowTouch(false);
        commonNavigator.setEnablePivotScroll(true);
        commonNavigator.setBackgroundColor(Color.parseColor("#FFF6F6F6"));
        commonNavigator.setAdapter(mCommonNavigatorAdapter);
        mIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mIndicator, mViewPager);

        initData();
    }

    private void initData(){
        mPresenter.getCategoryList();
    }

    @Override
    public void setCategoryList(List<Category> categoryList) {
        List<Category> categoryListTemp = new ArrayList<>();
        categoryListTemp.addAll(categoryList);
        mFragmentPagerAdapter.setData(categoryListTemp);
        mCommonNavigatorAdapter.setData(categoryListTemp);
        mIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCategoryListError(int code, String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        BaseFragment currentFragment = mFragmentPagerAdapter.getCurrentFragment();
        if(currentFragment != null){
            ((CategoryItem)currentFragment).OnRefresh();
        }
    }

    public void finishRefresh(){
        if(mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    private List<Category> getInitData(){
        int i = 0;
        List<Category> categoryList = new ArrayList<>();
        Category category0 = new Category();
        category0.setId(String.valueOf(i++));
        category0.setName("社会");
        categoryList.add(category0);

        Category category1 = new Category();
        category1.setId(String.valueOf(i++));
        category1.setName("国内");
        categoryList.add(category1);

        Category category2 = new Category();
        category2.setId(String.valueOf(i++));
        category2.setName("国际");
        categoryList.add(category2);

        Category category3 = new Category();
        category3.setId(String.valueOf(i++));
        category3.setName("娱乐");
        categoryList.add(category3);

        Category category4 = new Category();
        category4.setId(String.valueOf(i++));
        category4.setName("体育");
        categoryList.add(category4);

        Category category5 = new Category();
        category5.setId(String.valueOf(i++));
        category5.setName("科技");
        categoryList.add(category5);

        Category category6 = new Category();
        category6.setId(String.valueOf(i++));
        category6.setName("健康");
        categoryList.add(category6);

//    <string name="fragment_news_1">社会</string>
//    <string name="fragment_news_2">国内</string>
//    <string name="fragment_news_3">国际</string>
//    <string name="fragment_news_4">娱乐</string>
//    <string name="fragment_news_5">体育</string>
//    <string name="fragment_news_6">科技</string>
//    <string name="fragment_news_7">健康</string>
        return categoryList;
    }

    @Override
    public void OnTabListCLickListener(int index) {
        mViewPager.setCurrentItem(index);
    }


//    private void getData(){
//        RetrofitHttpClient.getInstance().forRetrofit(Constant.BASE_URL, CategoryDetailService.class)
//                .getNewsData("social", Constant.APIKEY, "10", 1).enqueue(new HttpCallback2<Result2>() {
//            @Override
//            public void onResponse(Result2 result2) {
//                if(result2 != null && result2.getNewslist() != null){
//                    List<News> newsList = result2.getNewslist();
//                }
//            }
//
//            @Override
//            public void onFailure(int code, String msg) {
//                super.onFailure(code, msg);
//                Toast.makeText(getApplicationContext(), String.valueOf(msg), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void getData2(){
//        RetrofitHttpClient.getInstance().forRetrofit(Constant.FINDER_BASE_URL, CategoryDetailService.class)
//                .getCategoryDetailList("Article", "categoryDetail").enqueue(new HttpCallback<Result<CategoryDetailData>>() {
//
//            @Override
//            public void onResponse(Result<CategoryDetailData> categoryDetailDataResult) {
//                if(categoryDetailDataResult != null && categoryDetailDataResult.getData() != null){
//                    List<CategoryDetail> newsList = categoryDetailDataResult.getData().getList();
//                    Toast.makeText(getApplicationContext(), String.valueOf(newsList.toString()), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(int code, String msg) {
//                super.onFailure(code, msg);
//                Toast.makeText(getApplicationContext(), String.valueOf(msg), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    //显示首页Fragment
//    private void showHomePageFragment() {
//        FragmentTransaction transaction = getTransaction();
//        CategoryDetailFragment categoryDetailFragmentGetByTag = (CategoryDetailFragment) getSupportFragmentManager().findFragmentByTag(CategoryDetailFragment.class.getSimpleName());
//        if (categoryDetailFragmentGetByTag == null) {
//            categoryDetailFragmentGetByTag = new CategoryDetailFragment();
//            this.mCategoryDetailFragment = categoryDetailFragmentGetByTag;
//            transaction.add(R.id.tab_change_layout, categoryDetailFragmentGetByTag, categoryDetailFragmentGetByTag.getClass().getSimpleName());
//        } else {
//            this.mCategoryDetailFragment = categoryDetailFragmentGetByTag;
//        }
//        transaction.show(this.mCategoryDetailFragment);
//        transaction.commitAllowingStateLoss();
//        FragmentManager manager = getSupportFragmentManager();
//        manager.executePendingTransactions();
//    }

    private FragmentTransaction getTransaction() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideAllFragmengts(transaction);
        return transaction;
    }


    private void hideAllFragmengts(FragmentTransaction transaction){
        if(mCategoryDetailFragment != null){
            transaction.hide(mCategoryDetailFragment);
        }
    }
}
