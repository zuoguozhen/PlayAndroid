package com.zgz.playandroid.ui.splash;

import android.content.Intent;

import com.zgz.playandroid.R;
import com.zgz.playandroid.base.SimpleActivity;
import com.zgz.playandroid.constants.Constant;
import com.zgz.playandroid.ui.main.MainActivity;

import java.util.List;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 */
public class SplashActivity extends SimpleActivity {


    @Override
    protected void initEventAndData() {

    }


    @Override
    protected void onStart() {
        super.onStart();
        requestPermission();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        requestPermission();
    }

    public void requestPermission() {
        requestRuntimePermissions(Constant.REQUEST_PERMISSIONS, new PermissionListener() {
            @Override
            public void onGranted() {
                jumpToMainActivity();
            }

            @Override
            public void onDenied(List<String> permissions) {
                showPermissionDialog("缺少必要权限，程序无法正常运行");
            }
        });
    }

    public void jumpToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.splash_activity;
    }
}
