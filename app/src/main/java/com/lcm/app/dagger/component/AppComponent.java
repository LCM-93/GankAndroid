package com.lcm.app.dagger.component;

import android.app.Application;

import com.lcm.android.dagger.module.AppModule;
import com.lcm.android.dagger.module.ClientModule;
import com.lcm.app.dagger.module.CacheModule;
import com.lcm.app.dagger.module.ServiceModule;
import com.lcm.app.data.api.ApiManager;
import com.lcm.app.data.api.CacheManager;
import com.lcm.app.data.api.CommonApi;

import javax.inject.Singleton;

import dagger.Component;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/23 下午1:53
 * Desc:
 * *****************************************************************
 */
@Singleton
@Component(modules = {AppModule.class, ClientModule.class, ServiceModule.class, CacheModule.class})
public interface AppComponent {


    Application Application();

    //服务管理器,retrofitApi
    ApiManager serviceManager();

    CacheManager cacheManager();


    CommonApi commonApi();

}
