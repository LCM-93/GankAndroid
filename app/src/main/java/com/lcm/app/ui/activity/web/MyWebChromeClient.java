package com.lcm.app.ui.activity.web;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/12 下午8:14
 * Desc:
 * *****************************************************************
 */

public class MyWebChromeClient extends WebChromeClient {
    private Activity mContext;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    public static final int REQUEST_FILE_PICKER = 0x01;

    private ValueCallback<Uri> mFilePathCallback;
    private ValueCallback<Uri[]> mFilePathCallbacks;

    private View customView;
    private FrameLayout fullscreenContainer;
    private WebChromeClient.CustomViewCallback customViewCallback;
    private WebView webView;

    /**
     * 视频全屏参数
     */
    protected static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    public MyWebChromeClient(Activity mContext, Toolbar toolbar, ProgressBar progressBar) {
        this.mContext = mContext;
        this.toolbar = toolbar;
        this.progressBar = progressBar;
    }

    public ValueCallback<Uri> getmFilePathCallback() {
        return mFilePathCallback;
    }

    public ValueCallback<Uri[]> getmFilePathCallbacks() {
        return mFilePathCallbacks;
    }

    public void setmFilePathCallback(ValueCallback<Uri> mFilePathCallback) {
        this.mFilePathCallback = mFilePathCallback;
    }

    public void setmFilePathCallbacks(ValueCallback<Uri[]> mFilePathCallbacks) {
        this.mFilePathCallbacks = mFilePathCallbacks;
    }

    //=========HTML5定位==========================================================
    //需要先加入权限
    //<uses-permission android:name="android.permission.INTERNET"/>
    //<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
    //<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        super.onReceivedIcon(view, icon);
    }

    @Override
    public void onGeolocationPermissionsHidePrompt() {
        super.onGeolocationPermissionsHidePrompt();
    }

    @Override
    public void onGeolocationPermissionsShowPrompt(final String origin, final GeolocationPermissions.Callback callback) {
        callback.invoke(origin, true, false);//注意个函数，第二个参数就是是否同意定位权限，第三个是是否希望内核记住
        super.onGeolocationPermissionsShowPrompt(origin, callback);
    }
    //=========HTML5定位==========================================================


    //=========多窗口的问题==========================================================
    @Override
    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
        WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
        transport.setWebView(view);
        resultMsg.sendToTarget();
        return true;
    }
    //=========多窗口的问题==========================================================


    @Override
    public void onReceivedTitle(WebView view, String title) {
        webView = view;
        super.onReceivedTitle(view, title);
        if (toolbar != null) toolbar.setTitle(title);

    }


    /*** 视频播放相关的方法 **/

    @Override
    public View getVideoLoadingProgressView() {
        FrameLayout frameLayout = new FrameLayout(mContext);
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return frameLayout;
    }

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        showCustomView(view, callback);
    }

    @Override
    public void onHideCustomView() {
        hideCustomView();
    }

    /**
     * 视频播放全屏
     **/
    private void showCustomView(View view, CustomViewCallback callback) {
        // if a view already exists then immediately terminate the new one
        if (customView != null) {
            callback.onCustomViewHidden();
            return;
        }

        mContext.getWindow().getDecorView();

        FrameLayout decor = (FrameLayout) mContext.getWindow().getDecorView();
        fullscreenContainer = new FullscreenHolder(mContext);
        fullscreenContainer.addView(view, COVER_SCREEN_PARAMS);
        decor.addView(fullscreenContainer, COVER_SCREEN_PARAMS);
        customView = view;
        setStatusBarVisibility(false);
        customViewCallback = callback;
    }

    /**
     * 隐藏视频全屏
     */
    private void hideCustomView() {
        if (customView == null) {
            return;
        }

        setStatusBarVisibility(true);
        FrameLayout decor = (FrameLayout) mContext.getWindow().getDecorView();
        decor.removeView(fullscreenContainer);
        fullscreenContainer = null;
        customView = null;
        customViewCallback.onCustomViewHidden();
        webView.setVisibility(View.VISIBLE);
    }

    /**
     * 全屏容器界面
     */
    static class FullscreenHolder extends FrameLayout {

        public FullscreenHolder(Context ctx) {
            super(ctx);
            setBackgroundColor(ctx.getResources().getColor(android.R.color.black));
        }

        @Override
        public boolean onTouchEvent(MotionEvent evt) {
            return true;
        }
    }

    private void setStatusBarVisibility(boolean visible) {
        int flag = visible ? 0 : WindowManager.LayoutParams.FLAG_FULLSCREEN;
        mContext.getWindow().setFlags(flag, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    // 设置网页加载的进度条
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (progressBar != null) progressBar.setProgress(newProgress);
        super.onProgressChanged(view, newProgress);
    }

    // Android < 3.0 调用这个方法
    public void openFileChooser(ValueCallback<Uri> filePathCallback) {
        mFilePathCallback = filePathCallback;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        mContext.startActivityForResult(Intent.createChooser(intent, "File Chooser"),
                REQUEST_FILE_PICKER);
    }

    // 3.0 + 调用这个方法
    public void openFileChooser(ValueCallback filePathCallback,
                                String acceptType) {
        mFilePathCallback = filePathCallback;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        mContext.startActivityForResult(Intent.createChooser(intent, "File Chooser"),
                REQUEST_FILE_PICKER);
    }

    //  / js上传文件的<input type="file" name="fileField" id="fileField" />事件捕获
    // Android > 4.1.1 调用这个方法
    public void openFileChooser(ValueCallback<Uri> filePathCallback,
                                String acceptType, String capture) {
        mFilePathCallback = filePathCallback;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        mContext.startActivityForResult(Intent.createChooser(intent, "File Chooser"),
                REQUEST_FILE_PICKER);
    }

    @Override
    public boolean onShowFileChooser(WebView webView,
                                     ValueCallback<Uri[]> filePathCallback,
                                     WebChromeClient.FileChooserParams fileChooserParams) {
        mFilePathCallbacks = filePathCallback;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        mContext.startActivityForResult(Intent.createChooser(intent, "File Chooser"),
                REQUEST_FILE_PICKER);
        return true;
    }


}
