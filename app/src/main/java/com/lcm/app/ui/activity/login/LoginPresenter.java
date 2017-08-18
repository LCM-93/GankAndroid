package com.lcm.app.ui.activity.login;

import android.graphics.Color;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SnackbarUtils;
import com.lcm.android.mvp.BaseMvpPresenter;

import javax.inject.Inject;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/18 上午10:37
 * Desc:
 * *****************************************************************
 */

public class LoginPresenter extends BaseMvpPresenter<LoginView> {

    @Inject
    public LoginPresenter() {
    }


    public void login(String userName, String pwd) {
        AVUser.logInInBackground(userName, pwd, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e == null) {
                    SnackbarUtils.with(getmMvpView().getSankBarRootView())
                            .setMessage("登录成功啦 ！（*＾ワ＾*）")
                            .setBgColor(Color.parseColor("#a0000000"))
                            .show();
                    LogUtils.e("LoginPresenter", "email:::" + avUser.getEmail());
                    LogUtils.e("LoginPresenter", "userName:::" + avUser.getUsername());

                } else {
                    SnackbarUtils.with(getmMvpView().getSankBarRootView())
                            .setMessage("登录失败了 ！ヽ(‘⌒´メ)ノ")
                            .setMessageColor(Color.parseColor("#FF0006"))
                            .setBgColor(Color.parseColor("#a0000000"))
                            .show();
                }
            }
        });

    }
}
