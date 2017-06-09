package com.lcm.android.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;


import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/22 下午1:10
 * Desc:
 * *****************************************************************
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    protected BaseApplication mApplication;
    protected Context mContext;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(rootView());
        bind = ButterKnife.bind(this);
        mApplication = (BaseApplication) getApplication();
        mContext = this;

        synchronized (BaseActivity.class) {
            mApplication.getActivityList().add(this);
        }

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initView();
        initData();
    }

    protected abstract int rootView();

    protected abstract void initView();

    protected abstract void initData();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (BaseActivity.class) {
            mApplication.getActivityList().remove(this);
        }
        bind.unbind();
    }
}
