package com.zgz.playandroid.di.module;

import android.app.Activity;

import com.zgz.playandroid.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * @auther ex-zuoguozhen001
 * @date 2018/11/2
 * @description
 */
@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity(){
        return activity;
    }
}
