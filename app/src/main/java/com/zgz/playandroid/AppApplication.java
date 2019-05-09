package com.zgz.playandroid;

import android.app.Application;

import com.zgz.playandroid.di.component.ApplicationComponent;
import com.zgz.playandroid.di.component.DaggerApplicationComponent;
import com.zgz.playandroid.di.module.ApplicationModule;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 */
public class AppApplication extends Application {
    private static AppApplication appApplication;
    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appApplication = this;
    }

    public static AppApplication getInstance() {
        return appApplication;
    }

    public static ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(appApplication))
                    .build();
        }
        return applicationComponent;
    }
}
