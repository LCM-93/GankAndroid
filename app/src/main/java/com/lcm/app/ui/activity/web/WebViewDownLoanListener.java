package com.lcm.app.ui.activity.web;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/12 下午8:12
 * Desc:
 * *****************************************************************
 */

public class WebViewDownLoanListener implements DownloadListener {

    private Activity mContext;

    public WebViewDownLoanListener(Activity mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        mContext.startActivity(intent);
    }
}
