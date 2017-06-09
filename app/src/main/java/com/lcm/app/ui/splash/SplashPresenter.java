package com.lcm.app.ui.splash;

import com.lcm.android.mvp.BaseMvpPresenter;
import com.lcm.app.data.impl.SplashImpl;

import javax.inject.Inject;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/9 下午5:00
 * Desc:
 * *****************************************************************
 */

public class SplashPresenter extends BaseMvpPresenter<SplashView> {

    private SplashImpl splashImpl;

    @Inject
    public SplashPresenter(SplashImpl splashImpl) {
        this.splashImpl = splashImpl;
    }


    public void getSplash() {
        splashImpl.getSplash()
                .subscribe(welfareBeen -> getmMvpView().getSplashSuccess(welfareBeen.get(0)));
    }
}
