package com.lcm.app.data.api;

import com.lcm.android.http.BaseServiceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/23 下午1:47
 * Desc:
 * *****************************************************************
 */

@Singleton
public class  ApiManager extends BaseServiceManager {
    private CommonApi mCommonService;

    /**
     * 如果需要添加service只需在构造方法中添加对应的service,在提供get方法返回出去,只要在ServiceModule提供了该service
     * Dagger2会自行注入
     * @param commonService
     */
    @Inject
    public ApiManager(CommonApi commonService){
        this.mCommonService = commonService;
    }

    public CommonApi getCommonService() {
        return mCommonService;
    }
}
