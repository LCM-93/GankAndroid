package com.lcm.app.main;

import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.lcm.app.R;
import com.lcm.app.base.MvpActivity;
import com.lcm.app.dagger.component.AppComponent;
import com.lcm.app.dagger.component.DaggerRepoComponent;
import com.lcm.app.data.entity.BannerBean;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;


public class MainActivity extends MvpActivity<MainPresenter> implements MainView {


    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.btn)
    Button btn;


    @Override
    public void showEmpty() {

    }


    @Override
    protected int rootView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        RxView.clicks(btn)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(o -> mPresenter.load());
    }


    @Override
    protected void initData() {

    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerRepoComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void OnloadSuccess(BannerBean bannerBean) {
        tv.setText(bannerBean.toString());
    }
}
