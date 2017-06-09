package com.lcm.android.mvp;


import com.blankj.utilcode.utils.ToastUtils;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/22 下午12:10
 * Desc:
 * *****************************************************************
 */

public class BaseMvpPresenter<V extends BaseView> implements BasePresenter<V>{
    private V mMvpView;

    @Override
    public void onAttachView(V view) {
        mMvpView = view;
    }

    @Override
    public void onDetachView() {
        mMvpView = null;
    }

    public V getmMvpView() {
        return mMvpView;
    }

    protected void showShortToast(String msg){
        ToastUtils.showShortToast(msg);
    }

    protected void showLongToast(String msg){
        ToastUtils.showLongToast(msg);
    }

}
