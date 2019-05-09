package com.zgz.playandroid.model.bean;

/**
 * @auther ex-zuoguozhen001
 * @date 2018/11/2
 * @description
 */
public class RxBean<T> {
    private int action;
    private T data;

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
