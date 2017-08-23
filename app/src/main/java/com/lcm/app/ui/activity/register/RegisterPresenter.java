package com.lcm.app.ui.activity.register;

import android.graphics.Color;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SnackbarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lcm.android.mvp.BaseMvpPresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/18 上午11:01
 * Desc:
 * *****************************************************************
 */

public class RegisterPresenter extends BaseMvpPresenter<RegisterView> {

    @Inject
    public RegisterPresenter() {
    }


    public void register(String email, String userName, String pwd) {
        getmMvpView().showLoading();
        AVUser avUser = new AVUser();
        avUser.setEmail(email);
        avUser.setUsername(userName);
        avUser.setPassword(pwd);
        avUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                getmMvpView().hideLoading();
                if (e == null) {
                    SnackbarUtils.with(getmMvpView().getSankBarRootView())
                            .setMessage("注册成功啦 ！（*＾ワ＾*）")
                            .setAction("朕知道了", v -> getmMvpView().finishView())
                            .setDuration(SnackbarUtils.LENGTH_LONG)
                            .showSuccess();

                    Observable.timer(3000, TimeUnit.MILLISECONDS)
                            .subscribe(aLong -> getmMvpView().finishView(),Throwable::printStackTrace);

                } else {
                    SnackbarUtils.with(getmMvpView().getSankBarRootView())
                            .setMessage("竟然失败了 ！ヽ(‘⌒´メ)ノ")
                            .setDuration(SnackbarUtils.LENGTH_LONG)
                            .showError();
                    getmMvpView().registerError(e.getCode());
                }
            }
        });
    }


}
