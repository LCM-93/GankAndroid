package com.lcm.android.mvp;

import android.support.annotation.UiThread;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/22 下午12:03
 * Desc:
 * *****************************************************************
 */

public interface BasePresenter<V extends BaseView> {

    @UiThread
    void onAttachView(V view);

    @UiThread
    void onDetachView();

}
