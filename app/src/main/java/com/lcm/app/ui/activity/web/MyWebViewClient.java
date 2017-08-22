package com.lcm.app.ui.activity.web;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/12 下午8:13
 * Desc:
 * *****************************************************************
 */

public class MyWebViewClient extends WebViewClient {
    private Context mContext;
    private ProgressBar mProgressBar;

    public MyWebViewClient(Context mContext, ProgressBar mProgressBar) {
        this.mContext = mContext;
        this.mProgressBar = mProgressBar;
    }

    /**
     * 多页面在同一个WebView中打开，就是不新建activity或者调用系统浏览器打开
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.startsWith("tel:")) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(url));
            mContext.startActivity(intent);
            return true;
        }
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();
    }

    // 页面开始加载
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if(mProgressBar!=null)mProgressBar.setVisibility(View.VISIBLE);
        super.onPageStarted(view, url, favicon);
    }

    // 页面加载完成
    @Override
    public void onPageFinished(WebView view, String url) {
        if(mProgressBar!=null)mProgressBar.setVisibility(View.GONE);
        super.onPageFinished(view, url);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode,
                                String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        view.loadUrl("file:///android_asset/error.html");
    }
}
