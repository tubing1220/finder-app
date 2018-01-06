package com.zjf.finder.base.http;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhengjunfei on 2018/1/3.
 */

public class CommonInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originRequest = chain.request();

        Headers.Builder headerBuilder = originRequest.headers().newBuilder();

        Map<String, String> commonQueryMap = ApiParamsUtils.getCommonParamsMap();
        if (commonQueryMap.size() > 0) {
            Iterator iterator = commonQueryMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String value = (String) entry.getValue();
                value = URLEncoder.encode(value, "UTF-8");
                headerBuilder.add((String) entry.getKey(), value);
            }
        }

        headerBuilder.add("token", "defaultToken");

        originRequest = originRequest.newBuilder().headers(headerBuilder.build()).build();

        return chain.proceed(originRequest);
    }
}
