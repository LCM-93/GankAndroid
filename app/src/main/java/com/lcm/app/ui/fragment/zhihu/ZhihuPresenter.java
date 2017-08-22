package com.lcm.app.ui.fragment.zhihu;

import com.blankj.utilcode.util.LogUtils;
import com.lcm.android.mvp.BaseMvpPresenter;
import com.lcm.app.data.entity.ZHTopStoriesBean;
import com.lcm.app.data.impl.ZHImpl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/21 下午8:29
 * Desc:
 * *****************************************************************
 */

public class ZhihuPresenter extends BaseMvpPresenter<ZhihuView> {

    private ZHImpl zh;

    @Inject
    public ZhihuPresenter(ZHImpl zh) {
        this.zh = zh;
    }

    public void getZHNews() {
        zh.getZHNews()
                .doAfterNext(zhNewsBean -> {
                    List<ZHTopStoriesBean> top_stories = zhNewsBean.getTop_stories();
                    List<String> images = new ArrayList<>();
                    List<String> titles = new ArrayList<>();
                    for (ZHTopStoriesBean top_story : top_stories) {
                        images.add(top_story.getImage());
                        titles.add(top_story.getTitle());
                    }

                    LogUtils.e("lcm", images.toString() + " \n" + titles.toString());
                    getmMvpView().refreshTopNews(images, titles);
                })
                .subscribe(zhNewsBean -> {
                    getmMvpView().showRefresh(false);
                    getmMvpView().refreshZHNews(zhNewsBean.getStories());
                    getmMvpView().refreshTopNews(zhNewsBean.getTop_stories());
//                    LogUtils.e("lcm", zhNewsBean.toString());
                });
    }
}
