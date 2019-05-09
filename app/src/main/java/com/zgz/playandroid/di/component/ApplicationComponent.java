package com.zgz.playandroid.di.component;

import com.zgz.playandroid.AppApplication;
import com.zgz.playandroid.di.module.ApplicationModule;
import com.zgz.playandroid.model.DataManager;

import java.util.concurrent.ExecutorService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @auther ex-zuoguozhen001
 * @date 2018/11/2
 * @description
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    AppApplication getApplication();

    ExecutorService getExecutorService();

    DataManager getDataManager();
}
