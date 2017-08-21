package com.lcm.app.ui.fragment.funny;

import com.lcm.android.mvp.BaseView;
import com.lcm.app.data.entity.ExcerptBean;

import java.util.List;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/21 下午3:29
 * Desc:
 * *****************************************************************
 */

public interface FunnyView extends BaseView {

    void refreshExcerptData(List<ExcerptBean> excerptBeen);

    void showRefresh(boolean show);
}
