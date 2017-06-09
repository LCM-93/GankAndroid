package com.lcm.android.mvp;

import com.lcm.android.http.BaseCacheManager;
import com.lcm.android.http.BaseServiceManager;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/22 下午12:05
 * Desc:
 * *****************************************************************
 */

public class BaseModel<S extends BaseServiceManager,C extends BaseCacheManager> {
    protected S baseServiceManager;
    protected C baseCacheManager;

    public BaseModel(S serviceManager,C cacheManager){
        this.baseCacheManager = cacheManager;
        this.baseServiceManager = serviceManager;
    }

    public BaseModel(S serviceManger){
        this.baseServiceManager = serviceManger;
    }


    public void onDestory(){
        this.baseServiceManager = null;
        this.baseCacheManager = null;
    }

}
