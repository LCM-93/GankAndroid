package com.lcm.app.ui.fragment.welfare;

import com.lcm.android.mvp.BaseView;
import com.lcm.app.data.entity.GankBean;

import java.util.List;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/18 下午5:34
 * Desc:
 * *****************************************************************
 */

public interface WelfareView extends BaseView{
    void refreshGankSuccess(List<GankBean> gankBeanList);

    void loadMoreList(List<GankBean> gankBeanList);

    void showRefresh(boolean isShow);
}
