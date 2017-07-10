package com.lcm.app.ui.activity.search;

import com.blankj.utilcode.util.LogUtils;
import com.lcm.android.mvp.BaseMvpPresenter;
import com.lcm.app.data.entity.GankBean;
import com.lcm.app.data.impl.GankImpl;
import com.lcm.app.weight.ProgressObserver;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/7/4 下午7:20
 * Desc:
 * *****************************************************************
 */

public class SearchPresenter extends BaseMvpPresenter<SearchView> {

    GankImpl gankImpl;

    @Inject
    public SearchPresenter(GankImpl gankImpl) {
        this.gankImpl = gankImpl;
    }


    public void searchGank(String quary, @GankImpl.GankType String type, int page) {

        if (page == 1) {
            gankImpl.searchGank(quary, type, page)
                    .subscribe(gankBeanList -> {
                        if (gankBeanList.size() == 0) {
                            getmMvpView().showEmpty();
                        } else {
                            getmMvpView().searchGankSuccess(gankBeanList);
                        }
                    }, throwable -> {
                        LogUtils.e("lcm", throwable.getMessage());
                        throwable.printStackTrace();
                        getmMvpView().showRefresh(false);
                        getmMvpView().showEmpty();
                    }, () -> {
                        getmMvpView().showRefresh(false);
                    });
        } else {
            gankImpl.searchGank(quary, type, page)
                    .subscribe(new ProgressObserver<List<GankBean>>(getmMvpView().getActivityContext()) {

                        @Override
                        public void onNext(List<GankBean> gankBeanList) {
                            super.onNext(gankBeanList);
                            if (gankBeanList.size() == 0) {
                                getmMvpView().showEmpty();
                            } else {
                                getmMvpView().searchMoreGankSuccess(gankBeanList);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            getmMvpView().showRefresh(false);
                            getmMvpView().showEmpty();
                        }

                        @Override
                        public void onComplete() {
                            super.onComplete();
                            getmMvpView().showRefresh(false);
                        }
                    });
        }
    }

}
