package com.zgz.playandroid.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.zgz.playandroid.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * @auther ex-zuoguozhen001
 * @date 2018/11/2
 * @description
 */
@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity(){
        return fragment.getActivity();
    }
}
