package com.zgz.playandroid.ui.official.artical;

import com.zgz.playandroid.base.BasePresenter;
import com.zgz.playandroid.base.BaseView;
import com.zgz.playandroid.model.bean.Article;

import java.util.List;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 */
public class ArticleContract {
    interface View extends BaseView {

        /**
         * 更新文章列表
         * @param data
         */
        void setOfficialArticle(List<Article> data);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * 获取某公号历史文章
         * @param id
         * @param count
         */
        void getOfficialArticle(int id, int count);
    }
}
