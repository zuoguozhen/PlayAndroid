package com.zgz.playandroid.constants;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 */
public class HttpConstant {


    private static final String BASE_URL = "https://wanandroid.com";

    /**
     * 所有的公众号
     */
    public static final String GET_OFFICIAL_ACCOUNT = BASE_URL + "/wxarticle/chapters/json";

    /**
     * 首页的文章
     */
    public static final String GET_HOME_ARTICLES = BASE_URL + "/article/list/%s/json";
    /**
     * 公号历史文章
     */
    public static final String GET_OFFICIAL_ARTICLE = BASE_URL + "/wxarticle/list/%s/%s/json";
}
