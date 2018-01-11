package com.zjf.finder.constant;

/**
 * Created by zhengjunfei on 2018/1/3.
 */

public class Constant {

    public static String BASE_URL="http://api.tianapi.com/";
    public static String APIKEY="60a7c25077f08a8f653df86da738877c";

    public static String FINDER_BASE_URL="http://finder123.applinzi.com/";

    public static class Common{
        public static final String ACCESS_ARTICLE = "Article";
        public static final String SUBMIT_CATEGORYLIST = "categoryList";
        public static final String SUBMIT_CATEGORYDETAIL = "categoryDetail";
    }


    public static final int DEF_AUTO_LOAD_MORE_SIZE = 3;

    public static class CommonWebActivity {
        public static final String EXTRA_URL = "url";
        public static final String EXTRA_TITLE = "title";
        public static final String Web_Url = "webUrl";
        public static final String Web_Title = "webTitle";
    }

    public class CategoryDetailFragment{
        public static final String EXTRA_CATEGORY = "extra_category";
    }

}
