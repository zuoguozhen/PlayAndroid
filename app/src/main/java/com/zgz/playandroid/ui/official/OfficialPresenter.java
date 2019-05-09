package com.zgz.playandroid.ui.official;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.zgz.playandroid.AppApplication;
import com.zgz.playandroid.base.RxPresenter;
import com.zgz.playandroid.model.DataManager;
import com.zgz.playandroid.model.bean.HttpResult;
import com.zgz.playandroid.model.bean.OfficialAccount;
import com.zgz.playandroid.model.bean.RxBean;
import com.zgz.playandroid.utils.RxUtil;
import com.zgz.playandroid.utils.ToastUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Consumer;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 */
public class OfficialPresenter extends RxPresenter<OfficialContract.View> implements OfficialContract.Presenter {
    private final DataManager dataManager;

    @Inject
    public OfficialPresenter() {
        dataManager = AppApplication.getApplicationComponent().getDataManager();
    }

    public void getOfficialAccount() {
        addSubscribe(Flowable.create((FlowableOnSubscribe<RxBean<List<OfficialAccount>>>) emitter -> {
            HttpResult result = dataManager.getOfficialAccount();
            List<OfficialAccount> accounts = JSON.parseArray(result.getData().toString(), OfficialAccount.class);
            RxBean<List<OfficialAccount>> rxBean = new RxBean<>();
            rxBean.setData(accounts);
            emitter.onNext(rxBean);
        }, BackpressureStrategy.BUFFER).compose(RxUtil.rxSchedulerHelper()).subscribe(new Consumer<RxBean<List<OfficialAccount>>>() {
            @Override
            public void accept(RxBean<List<OfficialAccount>> listRxBean) throws Exception {
                mView.setOfficialAccount(listRxBean.getData());
            }
        }, throwable -> ToastUtil.toastShort(Log.getStackTraceString(throwable))));
    }
}
