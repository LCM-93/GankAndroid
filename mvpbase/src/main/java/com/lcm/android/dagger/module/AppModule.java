package com.lcm.android.dagger.module;

import android.app.Application;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/20 下午4:38
 * Desc:
 * *****************************************************************
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication(){
        return application;
    }





}
