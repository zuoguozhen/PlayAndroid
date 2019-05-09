package com.zgz.playandroid.ui.web;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zgz.playandroid.R;
import com.zgz.playandroid.base.SimpleActivity;
import com.zgz.playandroid.utils.LogUtil;

import butterknife.BindView;

/**
 * @author ex-zuoguozhen001
 * @date 2018/11/8
 * @description web页面（产品详情，碎屏险，投保等h5页面）
 */
public class WebActivity extends SimpleActivity {

    private static final String TAG = WebActivity.class.getName();

    @BindView(R.id.v_common_title)
    View vCommonTitle;
    @BindView(R.id.web_detail)
    WebView webView;

    private String oriUrl;

    @BindView(R.id.v_title_top)
    TextView vName;

    @BindView(R.id.v_back)
    View vBack;

    @BindView(R.id.v_progress)
    ProgressBar vProgress;

    private WebViewClient myWebViewClient = new WebViewClient() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            LogUtil.e("onPageFinished", "Url = " + url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            LogUtil.e("shouldOverrideUrlLoading", "Url = " + url);
            //处理支付url
            if (!url.contains("http")) {
                //防止未安装客户端时崩溃
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }

            if (url.endsWith(".pdf")) {
                view.loadUrl("file:///android_asset/index.html?" + url);
            }

            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            LogUtil.e("onReceivedError", "errorCode = " + errorCode + ", description=" + description);
            view.loadUrl("file:///android_asset/html/404.html");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            CookieManager cookieManager = CookieManager.getInstance();
            String CookieStr = cookieManager.getCookie(url);
            LogUtil.e("Cookies", "Cookies = " + CookieStr);
            LogUtil.e("onPageFinished", "Url = " + url);
            super.onPageFinished(view, url);
        }

    };

    private View.OnClickListener onClickListener = v -> {
        switch (v.getId()) {
            case R.id.v_back:
//                    finish();
                onBackPressed();
                break;
            default:
                break;
        }
    };
    private WebChromeClient myWebChromeClient = new WebChromeClient() {

        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, true);
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            // 跳转 - 返回 - 跳转 会空指针
            if (vProgress == null) {
                return;
            }
            if (newProgress >= 100) {
                vProgress.setVisibility(View.GONE);
            } else {
                vProgress.setVisibility(View.VISIBLE);
                vProgress.setProgress(newProgress);
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (vName == null) {
                return;
            }
            //可能是网址
            if (!TextUtils.isEmpty(title) && !title.contains(".")) {
                vName.setText(title);
            }
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
            LogUtil.e(TAG, "onJsAlert:" + message);
            if (message.equals("error")) {
                result.confirm();
                return true;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(WebActivity.this);
            builder.setTitle("温馨提示");
            builder.setMessage(message);
            builder.setPositiveButton("确定", (dialog, which) -> result.confirm());
            builder.setOnCancelListener(dialog -> result.cancel());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return true;
        }

    };

    @Override
    protected void initEventAndData() {
        oriUrl = getIntent().getStringExtra("url");

        vBack.setOnClickListener(onClickListener);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //支持定位
        settings.setGeolocationEnabled(true);
        settings.setDatabaseEnabled(true);
        String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        settings.setGeolocationDatabasePath(dir);
        //开启DOM storage API功能(重定向问题)
        settings.setDomStorageEnabled(true);
        webView.setWebViewClient(myWebViewClient);
        webView.setWebChromeClient(myWebChromeClient);
        webView.setBackgroundColor(Color.parseColor("#F8F8F8"));
        //缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        //不显示webview缩放按钮
        settings.setDisplayZoomControls(false);
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        //访问文件
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        //可浏览器调试
        WebView.setWebContentsDebuggingEnabled(true);

        webView.loadUrl(oriUrl);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.web_activity;
    }

}
