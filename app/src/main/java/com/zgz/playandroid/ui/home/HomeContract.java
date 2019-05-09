package com.zgz.playandroid.ui.home;

import com.zgz.playandroid.base.BasePresenter;
import com.zgz.playandroid.base.BaseView;
import com.zgz.playandroid.model.bean.Article;

import java.util.List;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 */
public class HomeContract {
    interface View extends BaseView {

        /**
         * 更新文章列表
         *
         * @param data
         */
        void setHomeArticles(List<Article> data);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 获取首页文章
         *
         * @param count
         */
        void getHomeArticles(int count);
    }
}
