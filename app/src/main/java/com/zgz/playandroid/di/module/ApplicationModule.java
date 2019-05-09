package com.zgz.playandroid.di.module;


import com.zgz.playandroid.AppApplication;
import com.zgz.playandroid.model.DataManager;
import com.zgz.playandroid.model.http.HttpHelper;
import com.zgz.playandroid.model.http.HttpHelperImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @auther ex-zuoguozhen001
 * @date 2018/11/2
 * @description
 */
@Module
public class ApplicationModule {
    public final AppApplication application;

    public ApplicationModule(AppApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    AppApplication provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    ExecutorService provideExecutorService() {
        return Executors.newCachedThreadPool();
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(HttpHelperImpl httpHelper){
        return httpHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper) {
        return new DataManager(httpHelper);
    }
}
