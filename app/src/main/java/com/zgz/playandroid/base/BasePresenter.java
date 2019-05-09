package com.zgz.playandroid.base;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 */
public interface BasePresenter<V extends BaseView> {
    /**
     * 绑定View
     * @param view
     */
    void attachView(V view);

    /**
     * 解绑View
     */
    void detachView();
}
