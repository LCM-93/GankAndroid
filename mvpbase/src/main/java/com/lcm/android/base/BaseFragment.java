package com.lcm.android.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/22 下午1:36
 * Desc:
 * *****************************************************************
 */

public abstract class BaseFragment extends RxFragment {

    private View mRootView;
    protected BaseActivity mActivity;
    protected Context mContext;
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(rootView(), null);
        bind = ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    protected abstract int rootView();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        mContext = getContext();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
       bind.unbind();
    }
}
