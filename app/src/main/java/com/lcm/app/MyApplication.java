package com.lcm.app;

import com.avos.avoscloud.AVOSCloud;
import com.blankj.utilcode.util.Utils;
import com.lcm.android.base.BaseApplication;
import com.lcm.app.dagger.component.AppComponent;
import com.lcm.app.dagger.component.DaggerAppComponent;
import com.lcm.app.dagger.module.CacheModule;
import com.lcm.app.dagger.module.ServiceModule;


/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/23 下午12:58
 * Desc:
 * *****************************************************************
 */

public class MyApplication extends BaseApplication {
    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        AVOSCloud.initialize(this,"3e07B9fNB8M0nJ1IWHKdlygm-gzGzoHsz","0jJfWeVCD2drFqQXqvVzkFJb");
        AVOSCloud.setDebugLogEnabled(true);


        Utils.init(getApplicationContext());
        mAppComponent = DaggerAppComponent.builder()
                .appModule(getAppModule())
                .clientModule(getClientModule())
                .serviceModule(new ServiceModule())
                .cacheModule(new CacheModule())
                .build();
    }



    /**
     * 将AppComponent返回出去,供其它地方使用, AppComponent接口中声明的方法返回的实例, 在getAppComponent()拿到对象后都可以直接使用
     *
     * @return
     */
    public static AppComponent getAppComponent() {
        return mAppComponent;
    }


}
