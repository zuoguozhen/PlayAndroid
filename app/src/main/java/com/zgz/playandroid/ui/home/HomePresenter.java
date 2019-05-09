package com.zgz.playandroid.ui.home;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.zgz.playandroid.AppApplication;
import com.zgz.playandroid.base.RxPresenter;
import com.zgz.playandroid.model.DataManager;
import com.zgz.playandroid.model.bean.Article;
import com.zgz.playandroid.model.bean.ArticleGroup;
import com.zgz.playandroid.model.bean.HttpResult;
import com.zgz.playandroid.model.bean.RxBean;
import com.zgz.playandroid.utils.RxUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 */
public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public HomePresenter() {
        dataManager = AppApplication.getApplicationComponent().getDataManager();
    }


    @Override
    public void getHomeArticles(int count) {
        addSubscribe(Flowable.create((FlowableOnSubscribe<RxBean<List<Article>>>) emitter -> {
            HttpResult result = dataManager.getHomeArticles(count);
            ArticleGroup group = JSON.parseObject(result.getData().get(0).toString(), ArticleGroup.class);
            RxBean<List<Article>> rxBean = new RxBean<>();
            rxBean.setData(group.getDatas());
            emitter.onNext(rxBean);
        }, BackpressureStrategy.BUFFER).compose(RxUtil.rxSchedulerHelper()).subscribe(listRxBean ->
                mView.setHomeArticles(listRxBean.getData()), throwable ->
                Log.e(HomePresenter.class.getName(), Log.getStackTraceString(throwable))));
    }
}
