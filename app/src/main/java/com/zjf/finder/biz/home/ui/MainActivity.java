package com.zjf.finder.biz.home.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.zjf.finder.R;
import com.zjf.finder.base.http.HttpCallback2;
import com.zjf.finder.base.http.Result2;
import com.zjf.finder.base.http.RetrofitHttpClient;
import com.zjf.finder.biz.home.model.News;
import com.zjf.finder.biz.home.service.NewsService;
import com.zjf.finder.constant.Constant;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CategoryDetailFragment mCategoryDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showHomePageFragment();


//        getData();
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
