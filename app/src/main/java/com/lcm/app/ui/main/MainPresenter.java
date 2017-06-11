package com.lcm.app.ui.main;

import com.lcm.android.mvp.BaseMvpPresenter;

import javax.inject.Inject;


/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/23 下午12:59
 * Desc:
 * *****************************************************************
 */

public class MainPresenter extends BaseMvpPresenter<MainView> {

    @Inject
    MainModel mainModel;

    @Inject
    public MainPresenter() {

    }

    public void load(){


    }
}
