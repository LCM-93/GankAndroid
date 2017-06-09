package com.lcm.app.dagger.component;

import com.lcm.android.dagger.scope.ActivityScope;
import com.lcm.app.main.MainActivity;
import com.lcm.app.ui.splash.SplashActivity;

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
public interface RepoComponent {

    void inject(MainActivity mainActivity);

    void inject(SplashActivity splashActivity);
}
