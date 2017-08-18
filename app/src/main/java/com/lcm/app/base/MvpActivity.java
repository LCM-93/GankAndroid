package com.lcm.app.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.lcm.android.mvp.BaseMvpActivity;
import com.lcm.android.mvp.BaseMvpPresenter;
import com.lcm.android.mvp.BaseView;
import com.lcm.app.MyApplication;
import com.lcm.app.dagger.component.AppComponent;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/23 下午4:34
 * Desc:
 * *****************************************************************
 */

public abstract class MvpActivity<P extends BaseMvpPresenter>  extends BaseMvpActivity<P> implements BaseView{

    protected MyApplication application;
    protected View snackBarRootView;

    @Override
    public void showMessage(String str) {
        ToastUtils.showShort(str);
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void finishView() {
        finish();
    }


    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onAttachView(this);
        snackBarRootView = findViewById(android.R.id.content);
    }
    @Override
    protected void ComponentInject() {
        application = (MyApplication) getApplication();
        setupActivityComponent(application.getAppComponent());
    }




    //提供AppComponent(提供所有的单例对象)给子类，进行Component依赖
    protected abstract void setupActivityComponent(AppComponent appComponent);



    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public View getSankBarRootView(){
        return snackBarRootView;
    }
}
