package com.lcm.app.ui.fragment.typegank;

import com.lcm.android.mvp.BaseView;
import com.lcm.app.data.entity.GankBean;

import java.util.List;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/13 上午9:14
 * Desc:
 * *****************************************************************
 */

public interface TypeGankView extends BaseView {

    void refreshGankSuccess(List<GankBean> gankBeanList);

    void loadMoreList(List<GankBean> gankBeanList);

    void showRefresh(boolean isShow);
}
