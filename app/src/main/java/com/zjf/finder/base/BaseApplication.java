package com.zjf.finder.base;

import android.app.Application;
import android.content.Context;

import com.zjf.finder.utils.ImageLoader;

/**
 * Created by zhengjunfei on 2018/1/3.
 */

public class BaseApplication extends Application {
    private static Context mBaseApplicationContext;

    public static BaseApplication getInstance() {
        return (BaseApplication) getContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        synchronized (BaseApplication.this) {
            mBaseApplicationContext = getApplicationContext();
        }
    }

    public synchronized static Context getContext() {
        return mBaseApplicationContext;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImageLoader.clearImageMemoryCache(mBaseApplicationContext);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        ImageLoader.trimMemory(mBaseApplicationContext, level);
    }
}
