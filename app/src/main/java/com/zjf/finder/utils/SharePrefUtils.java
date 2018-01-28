package com.zjf.finder.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

public class SharePrefUtils {

    public static boolean getBoolean(Context context, String prefName, String prefKey,
                                     boolean defaultValue) {
        return getSharedPreferences(context, prefName).getBoolean(prefKey, defaultValue);
    }

    public static float getFloat(Context context, String prefName, String prefKey,
                                 float defaultValue) {
        return getSharedPreferences(context, prefName).getFloat(prefKey, defaultValue);
    }

    public static int getInt(Context context, String prefName, String prefKey, int defaultValue) {
        return getSharedPreferences(context, prefName).getInt(prefKey, defaultValue);
    }

    public static long getLong(Context context, String prefName, String prefKey, long defaultValue) {
        return getSharedPreferences(context, prefName).getLong(prefKey, defaultValue);
    }

    public static String getString(Context context, String prefName, String prefKey,
                                   String defaultValue) {
        return getSharedPreferences(context, prefName).getString(prefKey, defaultValue);
    }

    public static void putBoolean(Context context, String prefName, String prefKey, boolean value) {
        getSharedPreferences(context, prefName).edit().putBoolean(prefKey, value).commit();
    }

    public static void putFloat(Context context, String prefName, String prefKey, float value) {
        getSharedPreferences(context, prefName).edit().putFloat(prefKey, value).commit();
    }

    public static void putInt(Context context, String prefName, String prefKey, int value) {
        getSharedPreferences(context, prefName).edit().putInt(prefKey, value).commit();
    }

    public static void putLong(Context context, String prefName, String prefKey, long value) {
        getSharedPreferences(context, prefName).edit().putLong(prefKey, value).commit();
    }

    public static void putString(Context context, String prefName, String prefKey, String value) {
        getSharedPreferences(context, prefName).edit().putString(prefKey, value).commit();
    }

    public static void putStringSet(Context context, String prefName, String prefKey, Set<String> value) {
        getSharedPreferences(context, prefName).edit().clear().putStringSet(prefKey, value).commit();
    }

    public static Set<String> getStringSet(Context context, String prefName, String prefKey,
                                           Set<String> defaultValue) {
        return getSharedPreferences(context, prefName).getStringSet(prefKey, defaultValue);
    }

    public static void remove(Context context, String prefName, String prefKey) {
        getSharedPreferences(context, prefName).edit().remove(prefKey).commit();
    }

    public static boolean getBoolean(Context context, String prefKey, boolean defaultValue) {
        return getSharedPreferences(context).getBoolean(prefKey, defaultValue);
    }

    public static float getFloat(Context context, String prefKey, float defaultValue) {
        return getSharedPreferences(context).getFloat(prefKey, defaultValue);
    }

    public static int getInt(Context context, String prefKey, int defaultValue) {
        return getSharedPreferences(context).getInt(prefKey, defaultValue);
    }

    public static long getLong(Context context, String prefKey, long defaultValue) {
        return getSharedPreferences(context).getLong(prefKey, defaultValue);
    }

    public static String getString(Context context, String prefKey, String defaultValue) {
        return getSharedPreferences(context).getString(prefKey, defaultValue);
    }

    public static void putBoolean(Context context, String prefKey, boolean value) {
        getSharedPreferences(context).edit().putBoolean(prefKey, value).commit();
    }

    public static void putFloat(Context context, String prefKey, float value) {
        getSharedPreferences(context).edit().putFloat(prefKey, value).commit();
    }

    public static void putInt(Context context, String prefKey, int value) {
        getSharedPreferences(context).edit().putInt(prefKey, value).commit();
    }

    public static void putLong(Context context, String prefKey, long value) {
        getSharedPreferences(context).edit().putLong(prefKey, value).commit();
    }

    public static void putString(Context context, String prefKey, String value) {
        getSharedPreferences(context).edit().putString(prefKey, value).commit();
    }

    public static void remove(Context context, String prefKey) {
        getSharedPreferences(context).edit().remove(prefKey).commit();
    }

    /**
     * 得到默认SharePreference
     *
     * @param context
     * @return
     */
    public static SharedPreferences getSharedPreferences(Context context) {
        return getSharedPreferences(context, null);
    }

    /**
     * 根据名字得到SharePreference
     *
     * @param context
     * @param prefName
     * @return
     */
    public static SharedPreferences getSharedPreferences(Context context, String prefName) {
        if (prefName == null || prefName.length() == 0) {
            return PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            return context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        }
    }
}
