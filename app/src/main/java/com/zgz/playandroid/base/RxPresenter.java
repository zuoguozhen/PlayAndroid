package com.zgz.playandroid.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 */
public class RxPresenter<V extends BaseView> implements BasePresenter<V> {
    protected V mView;
    protected CompositeDisposable mCompositeDisposable;

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        clearSubscribe();
    }

    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    protected void clearSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
