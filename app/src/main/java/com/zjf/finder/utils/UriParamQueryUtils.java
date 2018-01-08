package com.zjf.finder.utils;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.regex.Pattern;

public final class UriParamQueryUtils {
    private UriParamQueryUtils() {
    }

    public static long getLongQueryParameter(@NonNull Uri uri, @NonNull String key) {
        return getLongQueryParameter(uri, key, 0);
    }

    @SuppressWarnings("PrimitiveParseDetector")
    public static long getLongQueryParameter(@NonNull Uri uri, @NonNull String key, long defaultValue) {
        String value = uri.getQueryParameter(key);
        if (!TextUtils.isEmpty(value) && isNumeric(value)) {
            return Long.parseLong(value);
        }
        return defaultValue;
    }

    public static String getStringQueryParameter(@NonNull Uri uri, @NonNull String key) {
        return getStringQueryParameter(uri, key, "");
    }

    public static String getStringQueryParameter(@NonNull Uri uri, @NonNull String key, String defaultValue) {
        String value = uri.getQueryParameter(key);
        if (!TextUtils.isEmpty(value)) {
            return value;
        }
        return defaultValue;
    }


    /***
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */

    public static boolean isNumeric(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}