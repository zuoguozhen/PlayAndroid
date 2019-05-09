package com.zgz.playandroid.model.http;

import com.alibaba.fastjson.JSON;
import com.zgz.playandroid.constants.HttpConstant;
import com.zgz.playandroid.model.bean.HttpResult;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Response;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 */
public class HttpHelperImpl implements HttpHelper {

    @Inject
    public HttpHelperImpl() {
    }

    @Override
    public HttpResult getOfficialAccount() throws IOException {
        Response response = OkHttpUtils.get()
                .url(HttpConstant.GET_OFFICIAL_ACCOUNT)
                .build().execute();
        if (response.isSuccessful()) {
            String string = response.body().string();
            return JSON.parseObject(string, HttpResult.class);
        } else {
            response.close();
        }
        return null;
    }

    @Override
    public HttpResult getHomeArticles(int count) throws IOException {
        Response response = OkHttpUtils.get()
                .url(String.format(HttpConstant.GET_HOME_ARTICLES, count))
                .build().execute();
        if (response.isSuccessful()) {
            String string = response.body().string();
            return JSON.parseObject(string, HttpResult.class);
        } else {
            response.close();
        }
        return null;
    }

    @Override
    public HttpResult getOfficialArticle(int id, int count) throws IOException {
        Response response = OkHttpUtils.get()
                .url(String.format(HttpConstant.GET_OFFICIAL_ARTICLE, id, count))
                .build().execute();
        if (response.isSuccessful()) {
            String string = response.body().string();
            return JSON.parseObject(string, HttpResult.class);
        } else {
            response.close();
        }
        return null;
    }
}
