package com.lcm.app.ui.activity.splash;

import com.lcm.android.mvp.BaseView;
import com.lcm.app.data.entity.WelfareBean;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/9 下午5:00
 * Desc:
 * *****************************************************************
 */

public interface SplashView extends BaseView {

    void getSplashSuccess(WelfareBean welfareBean);
}
