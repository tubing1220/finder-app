package com.zjf.finder.base.mvp.presenter;

import android.support.v4.app.Fragment;

import java.util.Set;
import java.util.WeakHashMap;

/**
 * Created by zhengjunfei on 18-1-6.
 */
public class BasePresenter implements BasePresenterInterface {

    private WeakHashMap<Object, Object> mWeakRefs = new WeakHashMap<>();

    @Override
    public void unBind() {

    }

    @Override
    public void start() {

    }

    @Override
    public void bind() {

    }

    public final <T> void addWeakRefObj(T t) {
        if (t == null) {
            return;
        }
        if (!mWeakRefs.containsKey(t)) {
            mWeakRefs.put(t, null);
        }
    }

    public final <T> T getWeakRefObj(Class<T> tClass) {
        if (tClass == null) {
            return null;
        }

        Set<Object> objectSet = mWeakRefs.keySet();
        for (Object o : objectSet) {
            if (tClass.isInstance(o)) {
                return (T) o;
            }
        }
        return null;
    }

    public final <UI> UI getUI(Class<UI> uiClass) {
        UI ui = getWeakRefObj(uiClass);
        if (ui instanceof Fragment) {
            Fragment f = (Fragment) ui;
            if (!f.isAdded()) {
                return null;
            }
        }

        if (ui instanceof android.app.Fragment) {
            android.app.Fragment f = (android.app.Fragment) ui;
            if (!f.isAdded()) {
                return null;
            }
        }
        return ui;
    }
}
