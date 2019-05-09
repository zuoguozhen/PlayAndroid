package com.zgz.playandroid.utils;

import android.widget.Toast;

import com.zgz.playandroid.AppApplication;

/**
 * @auther ex-zuoguozhen001
 * @date 2018/11/2
 * @description
 */
public class ToastUtil {
    private static Toast toast;

    public static void toastShort(String msg) {
        if (toast == null) {
            toast = Toast.makeText(AppApplication.getInstance(), "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }
}
