package com.zgz.playandroid.ui.official.artical;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.zgz.playandroid.AppApplication;
import com.zgz.playandroid.base.RxPresenter;
import com.zgz.playandroid.model.DataManager;
import com.zgz.playandroid.model.bean.Article;
import com.zgz.playandroid.model.bean.ArticleGroup;
import com.zgz.playandroid.model.bean.HttpResult;
import com.zgz.playandroid.model.bean.RxBean;
import com.zgz.playandroid.utils.LogUtil;
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
public class ArticlePresenter extends RxPresenter<ArticleContract.View> implements ArticleContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public ArticlePresenter() {
        dataManager = AppApplication.getApplicationComponent().getDataManager();
    }

    @Override
    public void getOfficialArticle(int id, int count) {
        addSubscribe(Flowable.create((FlowableOnSubscribe<RxBean<List<Article>>>) emitter -> {
            HttpResult httpResult = dataManager.getOfficialArticle(id, count);
            ArticleGroup group = JSON.parseObject(httpResult.getData().get(0).toString(), ArticleGroup.class);
            RxBean<List<Article>> rxBean = new RxBean<>();
            rxBean.setData(group.getDatas());
            emitter.onNext(rxBean);
        }, BackpressureStrategy.BUFFER).compose(RxUtil.rxSchedulerHelper()).subscribe(listRxBean ->
                mView.setOfficialArticle(listRxBean.getData()), throwable ->
                LogUtil.e(ArticlePresenter.class.getName(), Log.getStackTraceString(throwable))));
    }
}
