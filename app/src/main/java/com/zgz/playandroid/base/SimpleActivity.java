package com.zgz.playandroid.base;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.zgz.playandroid.constants.RequestCode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author zuoguozhen
 * @date 2019/4/10
 */
public abstract class SimpleActivity extends SupportActivity {

    private Unbinder mBinder;
    private PermissionListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mBinder = ButterKnife.bind(this);
        onViewCreated();
        initEventAndData();
    }

    /**
     * 请求权限
     *
     * @param permissions
     * @param listener
     */
    public void requestRuntimePermissions(String[] permissions, PermissionListener listener) {
        //待请求的权限集合
        this.listener = listener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (permissionList.size() != 0) {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[0]), RequestCode.REQUEST_PERMISSION);
        } else {
            listener.onGranted();
        }
    }

    public void showPermissionDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage(msg);
        builder.setPositiveButton("去授权", (dialog, which) -> jumpToAppSettings());
        builder.setNegativeButton("我拒绝", (dialog, which) -> finish());
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    /**
     * 打开应用信息页面以便用户打开权限
     */
    public void jumpToAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case RequestCode.REQUEST_PERMISSION:
                //有权限被拒绝
                if (grantResults.length > 0) {
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    //全部授权成功
                    if (deniedPermissions.isEmpty()) {
                        listener.onGranted();
                    } else {
                        listener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }

    protected void onViewCreated() {
    }

    protected abstract void initEventAndData();


    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBinder != null) {
            mBinder.unbind();
        }
    }

    public interface PermissionListener {
        /**
         * 权限允许
         */
        void onGranted();

        /**
         * 权限拒绝
         *
         * @param permissions
         */
        void onDenied(List<String> permissions);
    }

}
