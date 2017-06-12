package com.lcm.app.ui.fragment.recent;

import com.lcm.android.mvp.BaseView;
import com.lcm.app.data.entity.DailyContentBean;

import java.util.List;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/11 下午1:56
 * Desc:
 * *****************************************************************
 */

public interface  RecentView extends BaseView {

    void refreshDailySuccess(List<DailyContentBean> dailyContentBeen);

    void setHeaderView(DailyContentBean dailyContentBean);

    void showRefresh(boolean show);

}
