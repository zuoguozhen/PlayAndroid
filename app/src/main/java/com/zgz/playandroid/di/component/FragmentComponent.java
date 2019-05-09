package com.zgz.playandroid.di.component;

import android.app.Activity;

import com.zgz.playandroid.di.module.FragmentModule;
import com.zgz.playandroid.di.scope.FragmentScope;
import com.zgz.playandroid.ui.home.HomeFragment;
import com.zgz.playandroid.ui.mine.MineFragment;
import com.zgz.playandroid.ui.official.OfficialFragment;
import com.zgz.playandroid.ui.official.artical.ArticleFragment;

import dagger.Component;

/**
 * @auther ex-zuoguozhen001
 * @date 2018/11/2
 * @description
 */
@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    void inject(HomeFragment fragment);

    void inject(OfficialFragment fragment);

    void inject(MineFragment fragment);

    void inject(ArticleFragment fragment);
}
