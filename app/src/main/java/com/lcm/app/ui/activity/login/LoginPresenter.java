package com.lcm.app.ui.activity.login;

import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SnackbarUtils;
import com.lcm.android.mvp.BaseMvpPresenter;
import com.lcm.app.data.Contract;

import org.simple.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;

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
        getmMvpView().showLoading();
        AVUser.logInInBackground(userName, pwd, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {

                getmMvpView().hideLoading();

                if (e == null) {

                    LogUtils.e("LoginPresenter", "email:::" + avUser.getEmail());
                    LogUtils.e("LoginPresenter", "userName:::" + avUser.getUsername());

                    SPUtils.getInstance(Contract.SPNAME).put(Contract.ISLOGIN, true);
                    SPUtils.getInstance(Contract.SPNAME).put(Contract.USERNAME, avUser.getUsername());
                    SPUtils.getInstance(Contract.SPNAME).put(Contract.USEREMAIL, avUser.getEmail());

                    EventBus.getDefault().post("Login", "Login");

                    SnackbarUtils.with(getmMvpView().getSankBarRootView())
                            .setMessage("登录成功啦 ！（*＾ワ＾*）")
                            .setAction("朕知道了", v -> getmMvpView().finishView())
                            .setDuration(SnackbarUtils.LENGTH_LONG)
                            .showSuccess();

                    Observable.timer(3000,TimeUnit.MILLISECONDS)
                            .subscribe(aLong -> getmMvpView().finishView());


                } else {
                    SnackbarUtils.with(getmMvpView().getSankBarRootView())
                            .setMessage("登录失败了 ！ヽ(‘⌒´メ)ノ")
                            .setDuration(SnackbarUtils.LENGTH_LONG)
                            .showError();
                }

            }
        });

    }
}
