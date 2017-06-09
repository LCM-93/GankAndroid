package com.lcm.app.weight;

import android.content.Context;
import android.util.Log;


import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.NetworkUtils;
import com.blankj.utilcode.utils.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/23 下午5:57
 * Desc:
 * *****************************************************************
 */

public class ProgressObserver<T> implements Observer<T>, ProgressCancelListener {

    private ProgressDialogHandler mProgressDialogHandler;

    private Context context;

    public ProgressObserver(Context context) {
        this.context = context;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true, true);
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        showProgressDialog();
    }

    @Override
    public void onNext(T t) {
        Log.e("lcm","onNext");
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        dismissProgressDialog();
//        if (e.getMessage().equals("login_error"))
//            RouterHelper.getLoginActivityHelper().start(App.getContext());
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShortToast("请检查网络连接");
        }
    }

    @Override
    public void onComplete() {
        Log.e("lcm","onComplete");
        dismissProgressDialog();
    }

    @Override
    public void onCancelProgress() {
        Log.e("lcm","onCancelProgress");
    }
}
