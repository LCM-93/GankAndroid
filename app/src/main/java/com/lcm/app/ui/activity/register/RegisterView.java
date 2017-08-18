package com.lcm.app.ui.activity.register;

import android.view.View;

import com.lcm.android.mvp.BaseView;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/18 上午11:01
 * Desc:
 * *****************************************************************
 */

public interface RegisterView extends BaseView {


    void registerError(int code);

}
