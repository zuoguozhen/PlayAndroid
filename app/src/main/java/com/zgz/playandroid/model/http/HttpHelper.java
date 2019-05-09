package com.zgz.playandroid.model.http;

import com.zgz.playandroid.model.bean.HttpResult;

import java.io.IOException;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 */
public interface HttpHelper {
    /**
     * 获取所有的公众号
     * @return
     * @throws IOException
     */
    HttpResult getOfficialAccount() throws IOException;

    /**
     * 获取首页文章
     * @param count
     * @return
     * @throws IOException
     */
    HttpResult getHomeArticles(int count) throws IOException;

    /**
     * 获取某公号历史文章
     * @param id
     * @param count
     * @return
     * @throws IOException
     */
    HttpResult getOfficialArticle(int id, int count) throws IOException;
}
