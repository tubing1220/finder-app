package com.zjf.finder.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by zhengjunfei on 2018/1/6.
 */

public class ImageLoader {

    public static void load(Context context, String url, ImageView iv, int placeholder) {
        if (isDestroyed(context)) {
            return;
        }
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//让Glide既缓存全尺寸图片，下次在任何ImageView中加载图片的时候，全尺寸的图片将从缓存中取出，重新调整大小，然后缓存
                .crossFade()
                .placeholder(placeholder)
                .into(iv);
    }

    public static void load(Context context, String url, ImageView iv, ColorDrawable placeholder) {
        if (isDestroyed(context)) {
            return;
        }
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//让Glide既缓存全尺寸图片，下次在任何ImageView中加载图片的时候，全尺寸的图片将从缓存中取出，重新调整大小，然后缓存
                .crossFade()
                .placeholder(placeholder)
                .into(iv);
    }

    public static void clearImageMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(context.getApplicationContext()).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void trimMemory(Context context, int level) {
        Glide.get(context).trimMemory(level);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isDestroyed(Context context) {
        if (context instanceof Activity && ((Activity) context).isDestroyed()) {
            return true;
        }
        return false;
    }


}
