package com.lcm.app.ui.fragment.typegank;

import com.blankj.utilcode.utils.LogUtils;
import com.lcm.android.mvp.BaseMvpPresenter;
import com.lcm.app.data.entity.GankBean;
import com.lcm.app.data.impl.GankImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

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


    public void getGankByType(@GankImpl.GankType String type, int page) {
        gankImpl.getGankByType(type, page)
                .subscribe(gankBeanList -> {
                    getmMvpView().getGankSuccess(gankBeanList);
                }, throwable -> {
                }, () -> {
                });
    }
}
