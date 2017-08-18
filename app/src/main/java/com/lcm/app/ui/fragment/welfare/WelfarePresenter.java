package com.lcm.app.ui.fragment.welfare;

import com.lcm.android.mvp.BaseMvpPresenter;
import com.lcm.app.data.entity.GankBean;
import com.lcm.app.data.impl.GankImpl;
import com.lcm.app.weight.ProgressObserver;

import java.util.List;

import javax.inject.Inject;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/18 下午5:34
 * Desc:
 * *****************************************************************
 */

public class WelfarePresenter extends BaseMvpPresenter<WelfareView> {

    GankImpl gankImpl;

    @Inject
    public WelfarePresenter(GankImpl gankImpl) {
        this.gankImpl = gankImpl;
    }

    public void refreshGankList(@GankImpl.GankType String type) {
        gankImpl.getGankByType(type, 1)
                .subscribe(gankBeanList -> {
                    if (gankBeanList.size() == 0) {
                        getmMvpView().showEmpty();
                    } else {
                        getmMvpView().refreshGankSuccess(gankBeanList);
                    }
                }, throwable -> {
                    getmMvpView().showRefresh(false);
                    getmMvpView().showEmpty();
                }, () -> getmMvpView().showRefresh(false));
    }

    public void loadMoreGankList(@GankImpl.GankType String type, int page) {
        gankImpl.getGankByType(type, page)
                .subscribe(new ProgressObserver<List<GankBean>>(getmMvpView().getActivityContext()) {
                    @Override
                    public void onNext(List<GankBean> gankBeanList) {
                        getmMvpView().loadMoreList(gankBeanList);
                    }
                });
    }
}
