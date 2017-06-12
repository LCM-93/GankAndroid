package com.lcm.app.ui.activity.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lcm.app.R;
import com.lcm.app.base.MvpActivity;
import com.lcm.app.dagger.component.AppComponent;
import com.lcm.app.dagger.component.DaggerActivityComponent;
import com.lcm.app.data.entity.WelfareBean;
import com.lcm.app.ui.activity.main.MainActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/9 下午4:59
 * Desc:
 * *****************************************************************
 */

public class SplashActivity extends MvpActivity<SplashPresenter> implements SplashView {

    @BindView(R.id.iv_splash)
    ImageView ivSplash;
    @BindView(R.id.tv_time)
    TextView tvTime;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int rootView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mPresenter.getSplash();

        Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
                .take(5)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> tvTime.setText(String.valueOf(5 - aLong)),
                        throwable -> throwable.printStackTrace(),
                        () -> {
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                        });
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void getSplashSuccess(WelfareBean welfareBean) {
        Glide.with(this)
                .load(welfareBean.getUrl())
                .into(ivSplash);
    }


}
