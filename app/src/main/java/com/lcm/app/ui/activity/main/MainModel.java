package com.lcm.app.ui.activity.main;

import com.lcm.android.mvp.BaseModel;
import com.lcm.app.data.api.ApiManager;
import com.lcm.app.data.api.CacheManager;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/25 下午4:16
 * Desc:
 * *****************************************************************
 */

public class MainModel extends BaseModel<ApiManager, CacheManager> {


    public MainModel(ApiManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }
}
