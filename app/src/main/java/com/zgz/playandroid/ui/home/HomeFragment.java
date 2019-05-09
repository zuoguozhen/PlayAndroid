package com.zgz.playandroid.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ajguan.library.EasyRefreshLayout;
import com.zgz.playandroid.R;
import com.zgz.playandroid.base.BaseFragment;
import com.zgz.playandroid.model.bean.Article;
import com.zgz.playandroid.utils.ActivityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 * 首页
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {
    @BindView(R.id.v_home_refresh)
    EasyRefreshLayout vHomeRefresh;
    @BindView(R.id.v_home_list)
    RecyclerView vHomeList;
    private HomeAdapter homeAdapter;
    private List<Article> articles = new ArrayList<>();
    private int count = 0;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        homeAdapter = new HomeAdapter(articles);
        homeAdapter.setOnItemClickListener((adapter, view, position) -> ActivityUtil.jumpToWebActivity(getActivity(), articles.get(position).getLink()));
        vHomeList.setAdapter(homeAdapter);
        vHomeList.setLayoutManager(new LinearLayoutManager(getContext()));
        vHomeRefresh.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                mPresenter.getHomeArticles(count);
            }

            @Override
            public void onRefreshing() {
                count = 0;
                mPresenter.getHomeArticles(count);
            }
        });

        mPresenter.getHomeArticles(count);
    }

    @Override
    public void setHomeArticles(List<Article> data) {
        if (count == 0) {
            articles.clear();
        }
        articles.addAll(data);
        count++;
        homeAdapter.notifyDataSetChanged();
        if (vHomeRefresh.isRefreshing()) {
            vHomeRefresh.refreshComplete();
        }
        if (vHomeRefresh.isLoading()) {
            vHomeRefresh.loadMoreComplete();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }
}
