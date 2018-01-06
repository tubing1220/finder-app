package com.zjf.finder.biz.home.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zjf.finder.R;
import com.zjf.finder.biz.home.adapter.CategoryDetailAdapter;
import com.zjf.finder.biz.home.contract.CategoryDetailContract;
import com.zjf.finder.biz.home.model.CategoryDetail;
import com.zjf.finder.biz.home.model.News;
import com.zjf.finder.biz.home.presenter.CategoryDetailPresenter;
import com.zjf.finder.constant.Constant;

import java.util.List;

/**
 * Created by zhengjunfei on 2018/1/6.
 */

public class CategoryDetailFragment extends Fragment implements BaseQuickAdapter.RequestLoadMoreListener, CategoryDetailContract.UI {
    private RecyclerView mRecyclerView;
    private CategoryDetailPresenter mPresenter;
    private CategoryDetailAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.category_detail_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View rootView){
        mPresenter = new CategoryDetailPresenter(this);
        mRecyclerView = rootView.findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        initAdapter();
        initData();
    }

    private void initAdapter(){
        mAdapter = new CategoryDetailAdapter(null);
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.setAutoLoadMoreSize(Constant.DEF_AUTO_LOAD_MORE_SIZE);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData(){
        mPresenter.getCategoryDetailList();
    }

    @Override
    public void setCategoryDetailList(List<News> categoryDetailList, boolean isRefresh) {
        if(isRefresh){
            mAdapter.setNewData(categoryDetailList);
        } else{
            mAdapter.addData(categoryDetailList);
        }
        mAdapter.setEnableLoadMore(true);
        mAdapter.loadMoreComplete();
//        Toast.makeText(getContext(), String.valueOf(categoryDetailList.size()), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCategoryDetailError(int code, String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
        mAdapter.loadMoreEnd();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getCategoryDetailList();
    }
}
