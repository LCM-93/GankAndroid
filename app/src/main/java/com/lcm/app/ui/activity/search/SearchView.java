package com.lcm.app.ui.activity.search;

import com.lcm.android.mvp.BaseView;
import com.lcm.app.data.entity.GankBean;

import java.util.List;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/7/4 下午7:20
 * Desc:
 * *****************************************************************
 */

public interface SearchView extends BaseView {

    void showRefresh(boolean show);

    void searchGankSuccess(List<GankBean> gankBeanList);

    void searchMoreGankSuccess(List<GankBean> gankBeanList);

}
