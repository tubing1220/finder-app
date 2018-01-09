package com.zjf.finder.utils;

/**
 * Created by Administrator on 2017/5/2.
 */

public class TypeUrlUtils {

    public static String getTypeUrl(int type){
        switch (type){
            case 0:
                return "social";
            case 1:
                return "guonei";
            case 2:
                return "world";
            case 3:
                return "huabian";
            case 4:
                return "tiyu";
            case 5:
                return "keji";
            case 6:
                return "health";
           default:
               return "social";
        }
    }


}
