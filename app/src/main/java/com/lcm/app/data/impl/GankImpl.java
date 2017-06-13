package com.lcm.app.data.impl;

import android.support.annotation.StringDef;

import com.lcm.app.data.api.ApiManager;
import com.lcm.app.data.entity.GankBean;
import com.lcm.app.utils.RxHelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/13 上午9:29
 * Desc:
 * *****************************************************************
 */

public class GankImpl {

    private ApiManager apiManager;

    @Inject
    public GankImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    public Observable<List<GankBean>> getGankByType(@GankType String type, int page) {
        return apiManager.getCommonService().getGankByType(type, page)
                .compose(RxHelper.rxSchedulerHelper())
                .compose(RxHelper.handleResult());
    }


    @Retention(RetentionPolicy.SOURCE)
    @StringDef({GankType.All, GankType.Android, GankType.IOS, GankType.Video, GankType.Web, GankType.Extra, GankType.Welfare, GankType.Random_Recommend})
    public @interface GankType {
        String All = "all", Android = "Android", IOS = "iOS", Video = "休息视频", Web = "前端", Welfare = "福利", Extra = "拓展资源", Random_Recommend = "瞎推荐";
    }
}
