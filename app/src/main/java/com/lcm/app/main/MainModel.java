package com.lcm.app.main;

import com.lcm.android.mvp.BaseModel;
import com.lcm.app.data.api.ApiManager;
import com.lcm.app.data.api.CacheManager;
import com.lcm.app.data.entity.BannerBean;
import com.lcm.app.data.entity.HttpBaseResult;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/25 下午4:16
 * Desc:
 * *****************************************************************
 */

public class MainModel extends BaseModel<ApiManager, CacheManager> {

    @Inject
    public MainModel(ApiManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }

    public Observable<HttpBaseResult<List<BannerBean>>> load() {
        return baseServiceManager.getCommonService().getBanners()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
