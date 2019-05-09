package com.zgz.playandroid.di.component;

import android.app.Activity;

import com.zgz.playandroid.di.module.ActivityModule;
import com.zgz.playandroid.di.scope.ActivityScope;

import dagger.Component;

/**
 * @auther ex-zuoguozhen001
 * @date 2018/11/2
 * @description
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

}
