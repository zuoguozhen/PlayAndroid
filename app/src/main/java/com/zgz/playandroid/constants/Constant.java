package com.zgz.playandroid.constants;

import android.Manifest;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 */
public class Constant {
    /**
     * 程序运行时需要申请的权限
     */
    public static final String[] REQUEST_PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION};
}
