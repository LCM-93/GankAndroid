package com.lcm.app.data.impl;

import com.lcm.app.data.api.ApiManager;
import com.lcm.app.data.entity.WelfareBean;
import com.lcm.app.utils.RxHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/9 下午6:16
 * Desc:
 * *****************************************************************
 */

public class SplashImpl {

    private ApiManager commonApi;

    @Inject
    public SplashImpl(ApiManager commonApi) {
        this.commonApi = commonApi;
    }

    public Observable<List<WelfareBean>> getSplash() {
        return commonApi.getCommonService().getSplash()
                .compose(RxHelper.rxSchedulerHelper())
                .compose(RxHelper.handleResult());

    }
}
