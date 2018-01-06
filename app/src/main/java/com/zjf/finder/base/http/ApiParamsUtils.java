package com.zjf.finder.base.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengjunfei on 2018/1/3.
 */

public class ApiParamsUtils {

    public static Map<String, String> getCommonParamsMap(){
        Map<String, String> map = new HashMap();
        map.put("osn", "android");
        return map;
    }




}
