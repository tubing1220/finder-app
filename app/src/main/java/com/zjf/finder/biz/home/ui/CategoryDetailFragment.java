package com.zjf.finder.biz.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zjf.finder.R;
import com.zjf.finder.base.fragment.BaseFragment;
import com.zjf.finder.base.view.CommonWebViewActivity;
import com.zjf.finder.biz.home.adapter.CategoryDetailAdapter;
import com.zjf.finder.biz.home.contract.CategoryDetailContract;
import com.zjf.finder.biz.home.interfaces.CategoryItem;
import com.zjf.finder.biz.home.model.Category;
import com.zjf.finder.biz.home.model.News;
import com.zjf.finder.biz.home.presenter.CategoryDetailPresenter;
import com.zjf.finder.constant.Constant;
import com.zjf.finder.utils.CollectionUtils;
import com.zjf.finder.utils.TypeUrlUtils;

import java.util.List;

/**
 * Created by zhengjunfei on 2018/1/6.
 */

public class CategoryDetailFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener, CategoryDetailContract.UI, CategoryItem {
    private RecyclerView mRecyclerView;
    private CategoryDetailPresenter mPresenter;
    private CategoryDetailAdapter mAdapter;
    private Category mExtraCategory;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    protected int getContentView() {
        return R.layout.category_detail_layout;
    }

    private void initView(View rootView){
        mPresenter = new CategoryDetailPresenter(this);
        mRecyclerView = rootView.findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        getIntentData();
        initAdapter();
        initData();
        initListener();
    }

    private void getIntentData(){
        mExtraCategory = (Category) getArguments().get(Constant.CategoryDetailFragment.EXTRA_CATEGORY);
    }

    private void initAdapter(){
        mAdapter = new CategoryDetailAdapter(null);
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.setAutoLoadMoreSize(Constant.DEF_AUTO_LOAD_MORE_SIZE);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData(){
        mPresenter.getCategoryDetailList(false, TypeUrlUtils.getTypeUrl(Integer.parseInt(mExtraCategory.getId())));
    }

    private void initListener(){
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(mAdapter != null && CollectionUtils.isValid(mAdapter.getData(), position)){
                    News news = mAdapter.getData().get(position);
                    String url = news.getUrl();
                    String title = news.getTitle();
                    startWebView(url, title);
                }
            }
        });
    }

    private void startWebView(String url, String title){
        Intent intent  = new Intent(getActivity(), CommonWebViewActivity.class);
        intent.putExtra(Constant.CommonWebActivity.EXTRA_URL, url);
        intent.putExtra(Constant.CommonWebActivity.EXTRA_TITLE, title);
        startActivity(intent);
    }

    @Override
    public void OnRefresh() {
        mPresenter.getCategoryDetailList(true, TypeUrlUtils.getTypeUrl(Integer.parseInt(mExtraCategory.getId())));
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

    private void finishRefresh(){
        if(!isAdded() || getActivity() == null){
            return;
        }
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.finishRefresh();
    }

    @Override
    public void onCategoryDetailError(int code, String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
        mAdapter.loadMoreEnd();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getCategoryDetailList(false, TypeUrlUtils.getTypeUrl(Integer.parseInt(mExtraCategory.getId())));
    }

    @Override
    public String getCategory() {
        return mExtraCategory == null ? "" : mExtraCategory.getName();
    }

    @Override
    public void setCategory(Category category) {
        this.mExtraCategory = category;
    }

}
