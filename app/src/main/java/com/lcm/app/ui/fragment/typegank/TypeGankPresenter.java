package com.lcm.app.ui.fragment.typegank;

import com.lcm.android.mvp.BaseMvpPresenter;
import com.lcm.app.data.entity.GankBean;
import com.lcm.app.data.impl.GankImpl;
import com.lcm.app.weight.ProgressObserver;

import java.util.List;

import javax.inject.Inject;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/13 上午9:14
 * Desc:
 * *****************************************************************
 */

public class TypeGankPresenter extends BaseMvpPresenter<TypeGankView> {

    GankImpl gankImpl;

    @Inject
    public TypeGankPresenter(GankImpl gankImpl) {
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
