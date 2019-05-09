package com.zgz.playandroid.model;

import com.zgz.playandroid.model.bean.HttpResult;
import com.zgz.playandroid.model.http.HttpHelper;

import java.io.IOException;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 */
public class DataManager implements HttpHelper {
    private HttpHelper httpHelper;

    public DataManager(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public HttpResult getOfficialAccount() throws IOException {
        return httpHelper.getOfficialAccount();
    }

    @Override
    public HttpResult getHomeArticles(int count) throws IOException {
        return httpHelper.getHomeArticles(count);
    }

    @Override
    public HttpResult getOfficialArticle(int id, int count) throws IOException {
        return httpHelper.getOfficialArticle(id, count);
    }
}
