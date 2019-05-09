package com.zgz.playandroid.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import com.zgz.playandroid.AppApplication;
import com.zgz.playandroid.ui.web.WebActivity;

import java.util.List;

/**
 * @auther ex-zuoguozhen001
 * @date 2019/3/11
 * @description
 */
public class ActivityUtil {
    public static void jumpToWebActivity(Activity activity, String url) {
        Intent intent = new Intent(activity, WebActivity.class);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }

    public static boolean hasAnyAppStore() {
        Intent intent = new Intent();
        intent.setData(Uri.parse("market://details?id=android.browser"));
        List<ResolveInfo> infos = AppApplication.getInstance().getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return infos.size() != 0;
    }
}
