package com.lcm.app.dagger.module;



import com.lcm.app.data.api.CommonApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/23 下午2:50
 * Desc:
 * *****************************************************************
 */
@Module
public class ServiceModule {

    @Singleton
    @Provides
    CommonApi provideCommonService(Retrofit retrofit) {
        return retrofit.create(CommonApi.class);
    }

}
