package com.lcm.app.ui.activity.zhihuInfo;

import com.lcm.android.mvp.BaseView;
import com.lcm.app.data.entity.ZHInfoBean;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/22 下午2:28
 * Desc:
 * *****************************************************************
 */

public interface ZHInfoView extends BaseView {

    void loadZHInfoSuccess(ZHInfoBean zhInfoBean);

    void loadHtml(String html);
}
