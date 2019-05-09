package com.zgz.playandroid.base;

import com.zgz.playandroid.AppApplication;
import com.zgz.playandroid.di.component.ActivityComponent;
import com.zgz.playandroid.di.component.DaggerActivityComponent;
import com.zgz.playandroid.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * @auther ex-zuoguozhen001
 * @date 2018/11/2
 * @description mvp模式的activity基类
 */
public abstract class BaseActivity<P extends BasePresenter> extends SimpleActivity implements BaseView {
    @Inject
    protected P mPresenter;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .applicationComponent(AppApplication.getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    @Override
    protected void onViewCreated() {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }


    protected abstract void initInject();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
