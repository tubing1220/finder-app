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
import com.zjf.finder.base.view.CustomizeLoadMoreView;
import com.zjf.finder.base.view.StateView;
import com.zjf.finder.biz.home.adapter.CategoryDetailAdapter;
import com.zjf.finder.biz.home.contract.CategoryDetailContract;
import com.zjf.finder.biz.home.interfaces.CategoryItem;
import com.zjf.finder.biz.home.model.Category;
import com.zjf.finder.biz.home.model.CategoryDetail;
import com.zjf.finder.biz.home.presenter.CategoryDetailPresenter;
import com.zjf.finder.constant.Constant;
import com.zjf.finder.utils.CollectionUtils;
import com.zjf.finder.utils.NetworkUtils;

import java.util.List;

/**
 * Created by zhengjunfei on 2018/1/6.
 */

public class CategoryDetailFragment extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener, CategoryDetailContract.UI, CategoryItem {
    public interface Callback{
        void finishRefresh();
    }

    private RecyclerView mRecyclerView;
    private CategoryDetailPresenter mPresenter;
    private CategoryDetailAdapter mAdapter;
    private Category mExtraCategory;
    private Callback mCallback;

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
        setState(StateView.STATE_LOADDING);
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
        mAdapter.setLoadMoreView(new CustomizeLoadMoreView());
        mAdapter.setAutoLoadMoreSize(Constant.DEF_AUTO_LOAD_MORE_SIZE);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData(){
        mPresenter.getCategoryDetailList(false,  mExtraCategory.getId());
    }

    private void initListener(){
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(mAdapter != null && CollectionUtils.isValid(mAdapter.getData(), position)){
                    CategoryDetail categoryDetailItem = mAdapter.getData().get(position);
                    String url = categoryDetailItem.getOriginalUrl();
                    String title = categoryDetailItem.getTitle();
                    startWebView(url, title);
                }
            }
        });
    }

    public void setCallback(Callback callback){
        this.mCallback = callback;
    }

    private void startWebView(String url, String title){
        Intent intent  = new Intent(getActivity(), CommonWebViewActivity.class);
        intent.putExtra(Constant.CommonWebActivity.EXTRA_URL, url);
        intent.putExtra(Constant.CommonWebActivity.EXTRA_TITLE, title);
        startActivity(intent);
    }

    @Override
    public void OnRefresh() {
        mPresenter.getCategoryDetailList(true, mExtraCategory.getId());
    }

    @Override
    public void setCategoryDetailList(List<CategoryDetail> categoryDetailList, boolean isRefresh, boolean isEnd) {
        setState(StateView.STATE_OK);
        if(isRefresh){
            finishRefresh();
            contrastIsLastData(categoryDetailList);
            mAdapter.setNewData(categoryDetailList);
        } else{
            mAdapter.addData(categoryDetailList);
        }
        mAdapter.setEnableLoadMore(true);
        if(isEnd){
            mAdapter.loadMoreEnd();
        }else{
            mAdapter.loadMoreComplete();
        }
    }

    private void contrastIsLastData(List<CategoryDetail> categoryDetailList){
        if(mAdapter == null || !CollectionUtils.isValid(mAdapter.getData(), 0) || !CollectionUtils.isValid(categoryDetailList, 0)){
           return;
        }
        CategoryDetail nowFirst = mAdapter.getData().get(0);
        CategoryDetail newFirst = categoryDetailList.get(0);
        if(nowFirst.getId().equals(newFirst.getId())){
            Toast.makeText(getContext(), "已是最新内容", Toast.LENGTH_SHORT).show();
        }
    }

    private void finishRefresh(){
        if(mCallback != null){
            mCallback.finishRefresh();
        }
    }

    @Override
    public void onCategoryDetailError(int code, String msg) {
        if(CollectionUtils.isEmpty(mAdapter.getData())){
            if(NetworkUtils.isConnected(getContext())){
                setState(StateView.STATE_EMPTY);
            } else{
                setState(StateView.STATE_ERROR);
            }
        } else{
            Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
        }
        mAdapter.loadMoreEnd();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getCategoryDetailList(false, mExtraCategory.getId());
    }

    @Override
    public String getCategoryId() {
        return mExtraCategory == null ? "" : mExtraCategory.getId();
    }

    @Override
    public void setCategory(Category category) {
        this.mExtraCategory = category;
    }

}
