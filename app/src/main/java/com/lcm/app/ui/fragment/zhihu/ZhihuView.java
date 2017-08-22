package com.lcm.app.ui.fragment.zhihu;

import com.lcm.android.mvp.BaseView;
import com.lcm.app.data.entity.ZHStoriesBean;
import com.lcm.app.data.entity.ZHTopStoriesBean;

import java.util.List;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/21 下午8:28
 * Desc:
 * *****************************************************************
 */

public interface ZhihuView extends BaseView {

    void refreshZHNews(List<ZHStoriesBean> zhStoriesBeen);

    void refreshTopNews(List<ZHTopStoriesBean> zhTopStoriesBeen);

    void refreshTopNews(List<String> images, List<String> titles);

    void showRefresh(boolean show);
}
