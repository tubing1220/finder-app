package com.zjf.finder.biz.home.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.zjf.finder.R;
import com.zjf.finder.base.activity.BaseActivity;

public class MainActivity extends BaseActivity {
    private HomePageFragment mHomePageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showHomePageFragment();
    }

    //显示首页Fragment
    private void showHomePageFragment() {
        FragmentTransaction transaction = getTransaction();
        HomePageFragment homePageFragmentGetByTag = (HomePageFragment) getSupportFragmentManager().findFragmentByTag(HomePageFragment.class.getSimpleName());
        if (homePageFragmentGetByTag == null) {
            homePageFragmentGetByTag = new HomePageFragment();
            this.mHomePageFragment = homePageFragmentGetByTag;
            transaction.add(R.id.tab_change_layout, homePageFragmentGetByTag, homePageFragmentGetByTag.getClass().getSimpleName());
        } else {
            this.mHomePageFragment = homePageFragmentGetByTag;
        }
        transaction.show(this.mHomePageFragment);
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
        if(mHomePageFragment != null){
            transaction.hide(mHomePageFragment);
        }
    }
}
