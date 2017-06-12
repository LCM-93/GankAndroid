package com.lcm.app.data.api;


import com.lcm.app.data.entity.DailyBean;
import com.lcm.app.data.entity.HttpBaseResult;
import com.lcm.app.data.entity.WelfareBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/9 下午1:47
 * Desc:
 * *****************************************************************
 */
public interface CommonApi {


    /**
     * @return
     */
    @GET("random/data/福利/2")
    Observable<HttpBaseResult<List<WelfareBean>>> getSplash();


    @GET("day/history")
    Observable<HttpBaseResult<List<String>>> getHistoryDateList();

    @GET("history/content/day/{YEAR}/{MONTH}/{DAY}")
    Observable<HttpBaseResult<List<DailyBean>>> getDailyData(@Path("YEAR") String year, @Path("MONTH") String month, @Path("DAY") String day);
}
