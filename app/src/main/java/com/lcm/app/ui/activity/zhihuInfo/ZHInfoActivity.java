package com.lcm.app.ui.activity.zhihuInfo;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lcm.app.R;
import com.lcm.app.base.MvpActivity;
import com.lcm.app.dagger.component.AppComponent;
import com.lcm.app.dagger.component.DaggerActivityComponent;
import com.lcm.app.data.entity.ZHInfoBean;
import com.lcm.app.ui.activity.web.MyWebChromeClient;
import com.lcm.app.ui.activity.web.MyWebViewClient;
import com.lcm.app.ui.activity.web.WebViewDownLoanListener;

import butterknife.BindView;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/22 下午2:12
 * Desc:
 * *****************************************************************
 */

public class ZHInfoActivity extends MvpActivity<ZHInfoPresenter> implements ZHInfoView {
    @BindView(R.id.toolbar_image)
    ImageView toolbarImage;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    //    @BindView(R.id.progressBar)
//    ProgressBar mProgressBar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.fab_share)
    FloatingActionButton fabShare;

    private MyWebViewClient myWebViewClient;
    private MyWebChromeClient myWebChromeClient;
    private WebViewDownLoanListener webViewDownLoanListener;

    private String id;
    private String shareUrl;

    @Override
    protected int rootView() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        return R.layout.activity_zh_info;
    }

    @Override
    protected void initView() {
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        toolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        Intent intent = getIntent();
        id = intent.getStringExtra("ZH_ID");
        initWebView();

        mPresenter.getZHInfo(id);

    }

    @Override
    protected void initData() {

        fabShare.setOnClickListener(v -> {
            if (shareUrl != null) {
                Intent intent1 = new Intent(Intent.ACTION_SEND);
                intent1.putExtra(Intent.EXTRA_TEXT, "知乎日报 (｡・`ω´･)  " + shareUrl);
                intent1.setType("text/plain");
                startActivity(Intent.createChooser(intent1, "share"));
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 初始化WebView
     */
    private void initWebView() {
        myWebViewClient = new MyWebViewClient(this, null);
        myWebChromeClient = new MyWebChromeClient(this, toolbar, null);
        webViewDownLoanListener = new WebViewDownLoanListener(this);


        WebSettings mWebSettings = mWebView.getSettings();
        mWebSettings.setSupportZoom(true);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setDefaultTextEncodingName("utf-8");
        mWebSettings.setLoadsImagesAutomatically(true);
        mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        //调用JS方法.安卓版本大于17,加上注解 @JavascriptInterface
        mWebSettings.setJavaScriptEnabled(true);

        saveData(mWebSettings);
        newWin(mWebSettings);

        mWebView.setWebChromeClient(myWebChromeClient);
        mWebView.setWebViewClient(myWebViewClient);
        mWebView.setDownloadListener(webViewDownLoanListener);
    }


    /**
     * 多窗口的问题
     */
    private void newWin(WebSettings mWebSettings) {
        //html中的_bank标签就是新建窗口打开，有时会打不开，需要加以下
        //然后 复写 WebChromeClient的onCreateWindow方法
        mWebSettings.setSupportMultipleWindows(false);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    }

    /**
     * HTML5数据存储
     */
    private void saveData(WebSettings mWebSettings) {
        //有时候网页需要自己保存一些关键数据,Android WebView 需要自己设置
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setAppCacheEnabled(true);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        mWebSettings.setAppCachePath(appCachePath);
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void loadZHInfoSuccess(ZHInfoBean zhInfoBean) {
        Glide.with(this)
                .load(zhInfoBean.getImage())
                .error(R.mipmap.iv_place_holder)
                .placeholder(R.mipmap.iv_place_holder)
                .into(toolbarImage);

        toolbar.setTitle(zhInfoBean.getTitle());
        toolbarLayout.setTitle(zhInfoBean.getTitle());
        shareUrl = zhInfoBean.getShare_url();
    }

    @Override
    public void loadHtml(String html) {
        mWebView.loadData(html, "text/html; charset=UTF-8", null);
    }
}
