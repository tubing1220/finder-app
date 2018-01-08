package com.zjf.finder.biz.home.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.zjf.finder.R;
import com.zjf.finder.base.activity.BaseActivity;
import com.zjf.finder.base.http.HttpCallback;
import com.zjf.finder.base.http.HttpCallback2;
import com.zjf.finder.base.http.Result;
import com.zjf.finder.base.http.Result2;
import com.zjf.finder.base.http.RetrofitHttpClient;
import com.zjf.finder.biz.home.model.CategoryDetail;
import com.zjf.finder.biz.home.model.CategoryDetailData;
import com.zjf.finder.biz.home.model.News;
import com.zjf.finder.biz.home.service.NewsService;
import com.zjf.finder.constant.Constant;

import java.util.List;

public class MainActivity extends BaseActivity {
    private CategoryDetailFragment mCategoryDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showHomePageFragment();
        getData2();
    }

    private void getData(){
        RetrofitHttpClient.getInstance().forRetrofit(Constant.BASE_URL, NewsService.class)
                .getNewsData("social", Constant.APIKEY, "10", 1).enqueue(new HttpCallback2<Result2>() {
            @Override
            public void onResponse(Result2 result2) {
                if(result2 != null && result2.getNewslist() != null){
                    List<News> newsList = result2.getNewslist();
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                super.onFailure(code, msg);
                Toast.makeText(getApplicationContext(), String.valueOf(msg), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData2(){
        RetrofitHttpClient.getInstance().forRetrofit(Constant.FINDER_BASE_URL, NewsService.class)
                .getCategoryDetailList("Article", "categoryDetail").enqueue(new HttpCallback<Result<CategoryDetailData>>() {

            @Override
            public void onResponse(Result<CategoryDetailData> categoryDetailDataResult) {
                if(categoryDetailDataResult != null && categoryDetailDataResult.getData() != null){
                    List<CategoryDetail> newsList = categoryDetailDataResult.getData().getList();
                    Toast.makeText(getApplicationContext(), String.valueOf(newsList.toString()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                super.onFailure(code, msg);
                Toast.makeText(getApplicationContext(), String.valueOf(msg), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //显示首页Fragment
    private void showHomePageFragment() {
        FragmentTransaction transaction = getTransaction();
        CategoryDetailFragment categoryDetailFragmentGetByTag = (CategoryDetailFragment) getSupportFragmentManager().findFragmentByTag(CategoryDetailFragment.class.getSimpleName());
        if (categoryDetailFragmentGetByTag == null) {
            categoryDetailFragmentGetByTag = new CategoryDetailFragment();
            this.mCategoryDetailFragment = categoryDetailFragmentGetByTag;
            transaction.add(R.id.tab_change_layout, categoryDetailFragmentGetByTag, categoryDetailFragmentGetByTag.getClass().getSimpleName());
        } else {
            this.mCategoryDetailFragment = categoryDetailFragmentGetByTag;
        }
        transaction.show(this.mCategoryDetailFragment);
        transaction.commitAllowingStateLoss();
        FragmentManager manager = getSupportFragmentManager();
        manager.executePendingTransactions();
    }

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
