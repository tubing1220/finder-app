package com.zjf.finder.biz.home.adapter;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zjf.finder.R;
import com.zjf.finder.base.BaseApplication;
import com.zjf.finder.biz.home.model.CategoryDetail;
import com.zjf.finder.utils.ImageLoader;

import java.util.List;

/**
 * Created by zhengjunfei on 2018/1/6.
 */

public class CategoryDetailAdapter extends BaseQuickAdapter<CategoryDetail, BaseViewHolder> {

    public CategoryDetailAdapter(List<CategoryDetail> data) {
        super(R.layout.category_detail_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, CategoryDetail item) {
        ImageView photoImageView = holder.getView(R.id.photo);
        ImageLoader.load(BaseApplication.getContext(), item.getHeaderUrl(), photoImageView, new ColorDrawable(Color.parseColor("#eeeff2")));
        holder.setText(R.id.name, String.valueOf(item.getName()));
        holder.setText(R.id.category_name, String.valueOf(item.getClassifyName()));
        holder.setText(R.id.title, String.valueOf(item.getTitle()));
        holder.setText(R.id.desc, String.valueOf(item.getDesc()));

    }
}
