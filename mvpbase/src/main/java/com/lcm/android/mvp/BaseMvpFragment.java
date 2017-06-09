package com.lcm.android.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lcm.android.base.BaseFragment;

import javax.inject.Inject;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/22 下午1:43
 * Desc:
 * *****************************************************************
 */

public abstract class BaseMvpFragment<P extends BaseMvpPresenter> extends BaseFragment {

    @Inject
    protected P mPresenter;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ComponentInject();
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 依赖注入的入口
     */
    protected abstract void ComponentInject();


    @Override
    public void onDestroy() {
        if (mPresenter != null) mPresenter.onDetachView();//释放资源
        super.onDestroy();
    }
}
