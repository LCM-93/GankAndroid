package com.lcm.app.dagger.module;



import com.lcm.app.data.api.CommonCache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;


/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/26 下午2:34
 * Desc:
 * *****************************************************************
 */
@Module
public class CacheModule {

    @Singleton
    @Provides
    CommonCache provideCommonService(RxCache rxCache) {
        return rxCache.using(CommonCache.class);
    }

}
