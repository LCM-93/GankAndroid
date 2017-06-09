package com.lcm.app.data.api;


import com.lcm.app.data.entity.BannerBean;
import com.lcm.app.data.entity.HttpBaseResult;
import com.lcm.app.data.entity.WelfareBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/9 下午1:47
 * Desc:
 * *****************************************************************
 */
public interface CommonApi {


    /**
     * 获取所有banner
     *
     * @return
     */
    @GET("bannerlist")
    Observable<HttpBaseResult<List<BannerBean>>> getBanners();

    /**
     * 获取启动页美女图
     * @return
     */
    @GET("random/data/福利/2")
    Observable<HttpBaseResult<List<WelfareBean>>> getSplash();
}
