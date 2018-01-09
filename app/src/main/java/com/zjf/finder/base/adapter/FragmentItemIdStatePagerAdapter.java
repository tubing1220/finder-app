/*
 * Copyright (C) 2011 The Android Open Source Project
 * Copyright (C) 2015 Yuya Tanaka
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zjf.finder.base.adapter;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.LongSparseArray;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


// This file come from below URL:
// https://github.com/android/platform_frameworks_support/blob/62cf5e32ad0d24fffde4c0d0425aa12cd2b054a6/v4/java/android/support/v4/app/FragmentStatePagerAdapter.java
// Contains this patch: https://code.google.com/p/android/issues/detail?id=77285
//
// Modified to keep fragment state by item id instead of position
// - Refer https://code.google.com/p/android/issues/detail?id=37990
// - Also refer FragmentPagerAdapter.java

/**
 * Implementation of {@link android.support.v4.view.PagerAdapter} that
 * uses a {@link Fragment} to manage each page. This class also handles
 * saving and restoring of fragment's state.
 * <p>
 * <p>This version of the pager is more useful when there are a large number
 * of pages, working more like a list view.  When pages are not visible to
 * the user, their entire fragment may be destroyed, only keeping the saved
 * state of that fragment.  This allows the pager to hold on to much less
 * memory associated with each visited page as compared to
 * {@link FragmentPagerAdapter} at the cost of potentially more overhead when
 * switching between pages.
 * <p>
 * <p>When using FragmentPagerAdapter the host ViewPager must have a
 * valid ID set.</p>
 * <p>
 * <p>Subclasses only need to implement {@link #getItem(int)}
 * and {@link #getCount()} to have a working adapter.
 * <p>
 * <p>Here is an example implementation of a pager containing fragments of
 * lists:
 * <p>
 * {@sample development/samples/Support13Demos/src/com/example/android/supportv13/app/FragmentStatePagerSupport.java
 * complete}
 * <p>
 * <p>The <code>R.layout.fragment_pager</code> resource of the top-level fragment is:
 * <p>
 * {@sample development/samples/Support13Demos/res/layout/fragment_pager.xml
 * complete}
 * <p>
 * <p>The <code>R.layout.fragment_pager_list</code> resource containing each
 * individual fragment's layout is:
 * <p>
 * {@sample development/samples/Support13Demos/res/layout/fragment_pager_list.xml
 * complete}
 */
public abstract class FragmentItemIdStatePagerAdapter extends PagerAdapter {
    private static final String TAG = "FragmentItemIdAdapter";
    private static final String KEY_FRAGMENT = "fragment";
    private static final boolean DEBUG = false;

    private final FragmentManager mFragmentManager;
    private FragmentTransaction mCurTransaction = null;

    private LongSparseArray<Fragment.SavedState> mSavedState = new LongSparseArray<Fragment.SavedState>();
    private Map<Fragment, Long> mFragmentToItemIdMap = new HashMap<Fragment, Long>();
    private Map<Long, Fragment> mItemIdToFragmentMap = new HashMap<Long, Fragment>();
    private Set<Fragment> mUnusedRestoredFragments = new HashSet<Fragment>();
    private Fragment mCurrentPrimaryItem = null;

    public FragmentItemIdStatePagerAdapter(FragmentManager fm) {
        mFragmentManager = fm;
    }

    /**
     * Return the Fragment associated with a specified position.
     */
    public abstract Fragment getItem(int position);

    /**
     * Return a unique identifier for the item at the given position.
     * <p>
     * <p>The default implementation returns the given position.
     * Subclasses should override this method if the positions of items can change.</p>
     *
     * @param position Position within this adapter
     * @return Unique identifier for the item at position
     */
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void startUpdate(ViewGroup container) {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Long itemId = getItemId(position);

        // If we already have this item instantiated, there is nothing
        // to do.  This can happen when we are restoring the entire pager
        // from its saved state, where the fragment manager has already
        // taken care of restoring the fragments we previously had instantiated.
        Fragment f = mItemIdToFragmentMap.get(itemId);
        if (f != null) {
            if (DEBUG) Log.v(TAG, "Returning cached fragment: #" + itemId + ": f=" + f);
            mUnusedRestoredFragments.remove(f);
            return f;
        }

        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }

        Fragment fragment = getItem(position);
        if (DEBUG) Log.v(TAG, "Adding item #" + itemId + ": f=" + fragment);
        mFragmentToItemIdMap.put(fragment, itemId);
        mItemIdToFragmentMap.put(itemId, fragment);
        Fragment.SavedState fss = mSavedState.get(itemId);
        if (fss != null) {
            fragment.setInitialSavedState(fss);
        }
        fragment.setMenuVisibility(false);
        fragment.setUserVisibleHint(false);
        mCurTransaction.add(container.getId(), fragment);

        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = (Fragment) object;
        destroyFragment(fragment);
    }

    private void destroyFragment(Fragment fragment) {
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }
        Long itemId = mFragmentToItemIdMap.remove(fragment);
        mItemIdToFragmentMap.remove(itemId);
        if (DEBUG) Log.v(TAG, "Removing item #" + itemId + ": f=" + fragment
                + " v=" + fragment.getView());

        // XXX: Workaround for NullPointerException, but I don't know why ViewPager passes fragment
        // which is not owned by pager adapter (i.e. mFragmentToItemIdMap does not contain it).
        if (itemId != null) mSavedState.put(itemId, mFragmentManager.saveFragmentInstanceState(fragment));

        mCurTransaction.remove(fragment);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        Fragment fragment = (Fragment) object;
        if (fragment != mCurrentPrimaryItem) {
            if (mCurrentPrimaryItem != null) {
                mCurrentPrimaryItem.setMenuVisibility(false);
                mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            if (fragment != null) {
                fragment.setMenuVisibility(true);
                fragment.setUserVisibleHint(true);
            }
            mCurrentPrimaryItem = fragment;
        }
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        if (!mUnusedRestoredFragments.isEmpty()) {
            // Remove fragments which are restored but unused after first finishUpdate.
            for (Fragment fragment : mUnusedRestoredFragments) {
                destroyFragment(fragment);
            }
            mUnusedRestoredFragments.clear();
        }
        if (mCurTransaction != null) {
            mCurTransaction.commitAllowingStateLoss();
            mCurTransaction = null;
            mFragmentManager.executePendingTransactions();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return ((Fragment) object).getView() == view;
    }

    @Override
    public Parcelable saveState() {
        Bundle state = null;
        if (mSavedState.size() > 0) {
            state = new Bundle();
            long[] itemIdsForState = new long[mSavedState.size()];
            Fragment.SavedState[] fss = new Fragment.SavedState[mSavedState.size()];
            for (int i = 0; i < mSavedState.size(); i++) {
                itemIdsForState[i] = mSavedState.keyAt(i);
                fss[i] = mSavedState.valueAt(i);
            }
            state.putLongArray("itemIdsForState", itemIdsForState);
            state.putParcelableArray("states", fss);
        }
        for (Map.Entry<Fragment, Long> fragmentToIdEntry : mFragmentToItemIdMap.entrySet()) {
            Fragment f = fragmentToIdEntry.getKey();
            if (f != null && f.isAdded()) {
                if (state == null) {
                    state = new Bundle();
                }
                Long itemId = fragmentToIdEntry.getValue();
                String bundleKey = KEY_FRAGMENT + itemId;
                mFragmentManager.putFragment(state, bundleKey, f);
            }
        }
        return state;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        if (state != null) {
            Bundle bundle = (Bundle) state;
            bundle.setClassLoader(loader);
            long[] itemIdsForState = bundle.getLongArray("itemIdsForState");
            Parcelable[] fss = bundle.getParcelableArray("states");
            mFragmentToItemIdMap.clear();
            mItemIdToFragmentMap.clear();
            mUnusedRestoredFragments.clear();
            mSavedState.clear();
            if (fss != null) {
                for (int i = 0; i < fss.length; i++) {
                    mSavedState.put(itemIdsForState[i], (Fragment.SavedState) fss[i]);
                }
            }
            Iterable<String> keys = bundle.keySet();
            for (String key : keys) {
                if (key.startsWith(KEY_FRAGMENT)) {
                    Long itemId = Long.parseLong(key.substring(KEY_FRAGMENT.length()));
                    Fragment f = mFragmentManager.getFragment(bundle, key);
                    if (f != null) {
                        f.setMenuVisibility(false);
                        mFragmentToItemIdMap.put(f, itemId);
                        mItemIdToFragmentMap.put(itemId, f);
                    } else {
                        Log.w(TAG, "Bad fragment at key " + key);
                    }
                }
            }
            mUnusedRestoredFragments.addAll(mFragmentToItemIdMap.keySet());
        }
    }
}