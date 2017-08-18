package com.lcm.app.ui.activity.register;

import android.graphics.Color;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SnackbarUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lcm.android.mvp.BaseMvpPresenter;

import javax.inject.Inject;

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
        AVUser avUser = new AVUser();
        avUser.setEmail(email);
        avUser.setUsername(userName);
        avUser.setPassword(pwd);
        avUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    SnackbarUtils.with(getmMvpView().getSankBarRootView())
                            .setMessage("注册成功啦 ！（*＾ワ＾*）")
                            .setBgColor(Color.parseColor("#a0000000"))
                            .show();
                } else {
                    SnackbarUtils.with(getmMvpView().getSankBarRootView())
                            .setMessage("竟然失败了 ！ヽ(‘⌒´メ)ノ")
                            .setMessageColor(Color.parseColor("#FF0006"))
                            .setBgColor(Color.parseColor("#a0000000"))
                            .show();
                    getmMvpView().registerError(e.getCode());
                    LogUtils.e("RegisterPresenter", "code::" + e.getCode() + "    message::" + e.getMessage());
                }
            }
        });
    }


}
