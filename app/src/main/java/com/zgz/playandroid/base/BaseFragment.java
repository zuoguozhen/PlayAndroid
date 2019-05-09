package com.zgz.playandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.zgz.playandroid.AppApplication;
import com.zgz.playandroid.di.component.DaggerFragmentComponent;
import com.zgz.playandroid.di.component.FragmentComponent;
import com.zgz.playandroid.di.module.FragmentModule;

import javax.inject.Inject;

/**
 * @auther ex-zuoguozhen001
 * @date 2018/11/2
 * @description
 */
public abstract class BaseFragment<P extends BasePresenter> extends SimpleFragment implements BaseView {
    @Inject
    protected P mPresenter;

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .applicationComponent(AppApplication.getApplicationComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract void initInject();

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

}
