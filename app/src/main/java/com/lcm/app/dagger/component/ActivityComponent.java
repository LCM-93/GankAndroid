package com.lcm.app.dagger.component;

import com.lcm.android.dagger.scope.ActivityScope;
import com.lcm.app.ui.activity.main.MainActivity;
import com.lcm.app.ui.activity.splash.SplashActivity;

import dagger.Component;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/23 下午1:59
 * Desc:
 * *****************************************************************
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(SplashActivity splashActivity);
}
