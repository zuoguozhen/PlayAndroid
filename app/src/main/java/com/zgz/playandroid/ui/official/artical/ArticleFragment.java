package com.zgz.playandroid.ui.official.artical;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ajguan.library.EasyRefreshLayout;
import com.zgz.playandroid.R;
import com.zgz.playandroid.base.BaseFragment;
import com.zgz.playandroid.model.bean.Article;
import com.zgz.playandroid.model.bean.OfficialAccount;
import com.zgz.playandroid.utils.ActivityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 */
public class ArticleFragment extends BaseFragment<ArticlePresenter> implements ArticleContract.View {

    @BindView(R.id.v_article_refresh)
    EasyRefreshLayout vArticleRefresh;
    @BindView(R.id.v_article_recycler)
    RecyclerView vArticleRecycler;

    ArticleAdapter articleAdapter;
    private List<Article> articles = new ArrayList<>();
    private int count = 0;

    public static ArticleFragment newInstance(OfficialAccount account) {

        Bundle args = new Bundle();
        args.putSerializable("account", account);
        ArticleFragment fragment = new ArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        if (getArguments() == null) {
            return;
        }
        OfficialAccount account = (OfficialAccount) getArguments().getSerializable("account");
        if (account == null) {
            return;
        }

        articleAdapter = new ArticleAdapter(articles);
        articleAdapter.setOnItemClickListener((adapter, view, position) -> ActivityUtil.jumpToWebActivity(getActivity(), articles.get(position).getLink()));
        vArticleRecycler.setAdapter(articleAdapter);
        vArticleRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        vArticleRefresh.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                mPresenter.getOfficialArticle(account.getId(), count);
            }

            @Override
            public void onRefreshing() {
                count = 0;
                mPresenter.getOfficialArticle(account.getId(), count);
            }
        });
        mPresenter.getOfficialArticle(account.getId(), count);
    }

    @Override
    public void setOfficialArticle(List<Article> data) {
        if (count == 0) {
            articles.clear();
        }
        articles.addAll(data);
        count++;
        articleAdapter.notifyDataSetChanged();
        if (vArticleRefresh.isRefreshing()) {
            vArticleRefresh.refreshComplete();
        }
        if (vArticleRefresh.isLoading()) {
            vArticleRefresh.loadMoreComplete();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.article_fragment;
    }
}
