package com.lcm.app.main;

import com.lcm.android.mvp.BaseView;
import com.lcm.app.data.entity.BannerBean;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/23 下午12:59
 * Desc:
 * *****************************************************************
 */

public interface MainView extends BaseView {

    void OnloadSuccess(BannerBean bannerBean);
}
