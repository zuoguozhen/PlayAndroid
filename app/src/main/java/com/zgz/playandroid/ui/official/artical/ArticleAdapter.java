package com.zgz.playandroid.ui.official.artical;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zgz.playandroid.R;
import com.zgz.playandroid.model.bean.Article;

import java.util.List;

/**
 * @author zuoguozhen
 * @date 2019/4/11
 */
public class ArticleAdapter extends BaseQuickAdapter<Article, BaseViewHolder> {
    public ArticleAdapter(@Nullable List<Article> data) {
        super(R.layout.article_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Article item) {
        helper.setText(R.id.v_article_title, item.getTitle());
        helper.setText(R.id.v_article_author, item.getAuthor());
        helper.setText(R.id.v_article_date, item.getNiceDate());
    }
}
