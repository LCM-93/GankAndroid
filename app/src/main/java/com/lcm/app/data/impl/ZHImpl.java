package com.lcm.app.data.impl;

import com.lcm.app.data.api.ApiManager;
import com.lcm.app.data.entity.ZHInfoBean;
import com.lcm.app.data.entity.ZHNewsBean;
import com.lcm.app.utils.RxHelper;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/21 下午8:43
 * Desc:
 * *****************************************************************
 */

public class ZHImpl {
    private ApiManager apiManager;

    @Inject
    public ZHImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    public Observable<ZHNewsBean> getZHNews() {
        return apiManager.getCommonService().getZHNews()
                .compose(RxHelper.rxSchedulerHelper());
    }

    public Observable<ZHInfoBean> getZHInfo(String id) {
        return apiManager.getCommonService().getZHInfo(id)
                .compose(RxHelper.rxSchedulerHelper());
    }

}
