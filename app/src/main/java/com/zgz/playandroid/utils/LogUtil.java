package com.zgz.playandroid.utils;

import android.util.Log;

/**
 * @auther ex-zuoguozhen001
 * @date 2018/11/5
 * @description
 */
public class LogUtil {
    private static final boolean IS_SHOW_LOG = true;

    public static void e(String tag, String msg) {
        if (IS_SHOW_LOG) {
            Log.e(tag, msg);
        }
    }
}
