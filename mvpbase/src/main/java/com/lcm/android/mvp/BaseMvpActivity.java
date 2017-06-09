package com.lcm.android.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lcm.android.base.BaseActivity;

import javax.inject.Inject;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/22 下午1:40
 * Desc:
 * *****************************************************************
 */

public abstract class BaseMvpActivity<P extends BaseMvpPresenter> extends BaseActivity {

    @Inject
    protected P mPresenter;


    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ComponentInject();//依赖注入
        super.onCreate(savedInstanceState);
    }

    /**
     * 依赖注入的入口
     */
    protected abstract void ComponentInject();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDetachView();//释放资源
    }


}
