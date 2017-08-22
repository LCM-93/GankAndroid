package com.lcm.app.dagger.component;

import com.lcm.android.dagger.scope.ActivityScope;
import com.lcm.app.service.DownLoadService;
import com.lcm.app.ui.activity.login.LoginActivity;
import com.lcm.app.ui.activity.main.MainActivity;
import com.lcm.app.ui.activity.register.RegisterActivity;
import com.lcm.app.ui.activity.search.SearchActivity;
import com.lcm.app.ui.activity.splash.SplashActivity;
import com.lcm.app.ui.activity.zhihuInfo.ZHInfoActivity;

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

    void inject(SearchActivity searchActivity);

    void inject(DownLoadService downLoadService);

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);

    void inject(ZHInfoActivity zhInfoActivity);
}
