package com.lcm.app.ui.fragment.recent;

import com.blankj.utilcode.util.LogUtils;
import com.lcm.android.mvp.BaseMvpPresenter;
import com.lcm.app.data.entity.DailyContentBean;
import com.lcm.app.data.impl.RecentImpl;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/11 下午1:56
 * Desc:
 * *****************************************************************
 */

public class RecentPresenter extends BaseMvpPresenter<RecentView> {

    private RecentImpl recentImpl;


    @Inject
    public RecentPresenter(RecentImpl recentImpl) {
        this.recentImpl = recentImpl;
    }


    public void getHistoryDate() {
        recentImpl.getHistoryDateList()
                .subscribe(strings -> {
                    String s = strings.get(0);
                    String[] split = s.split("-");
                    getDailyData(split[0], split[1], split[2], false);
                }, Throwable::printStackTrace);
    }

    public void getDailyData(String year, String month, String day, boolean today) {
        recentImpl.getDailyData(year, month, day)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dailyContentBeen -> {
                    DailyContentBean dailyContentBean = dailyContentBeen.get(0);
                    if (dailyContentBean.getType().equals("福利")) {
                        getmMvpView().setHeaderView(dailyContentBean, year + "-" + month + "-" + day);
                        dailyContentBeen.remove(dailyContentBean);
                    }
                    getmMvpView().refreshDailySuccess(dailyContentBeen);
                    if (dailyContentBeen.size() == 0) {
                        if (today) {
                            getHistoryDate();
                            return;
                        }
                        getmMvpView().showEmpty();
                    }
                }, throwable -> {
                    LogUtils.e("lcm", "出错：：" + throwable.getMessage());
                    if (today) {
                        getHistoryDate();
                        return;
                    }
                    getmMvpView().showRefresh(false);
                    getmMvpView().showEmpty();
                }, () -> {
                    getmMvpView().showRefresh(false);
                    LogUtils.e("lcm", "onComplete");
                });
    }


    public void loadToday() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        LogUtils.e("lcm", "当日日期：：：" + year + "  " + (month + 1) + "  " + day);

        getDailyData(String.valueOf(year), String.valueOf(month + 1), String.valueOf(day), true);
    }
}
