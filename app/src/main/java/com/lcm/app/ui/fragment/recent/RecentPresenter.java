package com.lcm.app.ui.fragment.recent;

import com.blankj.utilcode.utils.LogUtils;
import com.lcm.android.mvp.BaseMvpPresenter;
import com.lcm.app.data.entity.DailyContentBean;
import com.lcm.app.data.entity.ElementWithType;
import com.lcm.app.data.impl.RecentImpl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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
                    getDailyData(split[0], split[1], split[2]);
                }, Throwable::printStackTrace);
    }

    public void getDailyData(String year, String month, String day) {
        recentImpl.getDailyData(year, month, day)
                .subscribe(dailyContentBeen -> {
                    DailyContentBean dailyContentBean = dailyContentBeen.get(0);
                    if (dailyContentBean.getType().equals("福利")) {
                        getmMvpView().setHeaderView(dailyContentBean);
                        dailyContentBeen.remove(dailyContentBean);
                    }
                    getmMvpView().refreshDailySuccess(dailyContentBeen);
                }, Throwable::printStackTrace, () -> getmMvpView().showRefresh(false));


    }
}
