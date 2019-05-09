package com.zgz.playandroid.utils;


import com.zgz.playandroid.AppApplication;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @auther ex-zuoguozhen001
 * @date 2018/11/2
 * @description Rx统一线程处理
 */
public class RxUtil {
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers
                        .from(AppApplication.getApplicationComponent().getExecutorService()))
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
