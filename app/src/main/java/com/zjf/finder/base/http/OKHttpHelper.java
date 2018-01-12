package com.zjf.finder.base.http;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by zhengjunfei on 2018/1/12.
 */

public class OKHttpHelper {

    public static final String TAG = "XMOKHttpHelper";
    private static final int DEFAULT_CONNECT_TIMEOUT = 20;
    private static final int DEFAULT_READ_TIMEOUT = 15;

    public static OkHttpClient createForImage() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(new HttpLogInterceptor(TAG + "_IMG"));
        return builder.build();
    }

    public static class HttpLogInterceptor implements Interceptor {
        private static final int MAX_LOG_LENGTH = 400;
        private final HttpLoggingInterceptor mHttpLoggingInterceptor;

        private final String mTag;

        public HttpLogInterceptor() {
            this(TAG);
        }

        public HttpLogInterceptor(String tag) {
            mTag = tag;
            mHttpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    final int logLevel = Log.VERBOSE;

                    // Split by line, then ensure each line can fit into Log's maximum length.
                    for (int i = 0, length = message.length(); i < length; i++) {
                        int newline = message.indexOf('\n', i);
                        newline = newline != -1 ? newline : length;
                        do {
                            int end = Math.min(newline, i + MAX_LOG_LENGTH);
                            Log.println(logLevel, mTag, message.substring(i, end));
                            i = end;
                        } while (i < newline);
                    }
                }
            });
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            mHttpLoggingInterceptor.setLevel(Log.isLoggable(mTag, Log.VERBOSE) ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
            return mHttpLoggingInterceptor.intercept(chain);
        }
    }


}
