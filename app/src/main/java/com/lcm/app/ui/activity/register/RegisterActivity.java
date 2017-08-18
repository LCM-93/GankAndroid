package com.lcm.app.ui.activity.register;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.RegexUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lcm.android.base.BaseActivity;
import com.lcm.app.R;
import com.lcm.app.base.MvpActivity;
import com.lcm.app.dagger.component.AppComponent;
import com.lcm.app.dagger.component.DaggerActivityComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/17 下午8:41
 * Desc:
 * *****************************************************************
 */

public class RegisterActivity extends MvpActivity<RegisterPresenter> implements RegisterView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edt_email)
    TextInputEditText edtEmail;
    @BindView(R.id.til_email)
    TextInputLayout tilEmail;
    @BindView(R.id.edt_userName)
    TextInputEditText edtUserName;
    @BindView(R.id.til_userName)
    TextInputLayout tilUserName;
    @BindView(R.id.edt_pwd)
    TextInputEditText edtPwd;
    @BindView(R.id.til_pwd)
    TextInputLayout tilPwd;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private KProgressHUD hud;


    @Override
    protected int rootView() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("注册");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //改变ToolBar返回键颜色
        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        snackBarRootView = findViewById(android.R.id.content);


        RxTextView.textChanges(edtEmail).subscribe(charSequence -> tilEmail.setErrorEnabled(false));
        RxTextView.textChanges(edtUserName).subscribe(charSequence -> tilUserName.setErrorEnabled(false));
        RxTextView.textChanges(edtPwd).subscribe(charSequence -> tilPwd.setErrorEnabled(false));

        RxView.clicks(btnRegister)
                .map(o -> {
                    if (TextUtils.isEmpty(edtEmail.getText().toString().trim())) {
                        tilEmail.setError("邮箱不能为空！");
                        return false;
                    } else {
                        return true;
                    }
                })
                .map(aBoolean -> {
                    if (!aBoolean) return aBoolean;
                    if (!RegexUtils.isEmail(edtEmail.getText().toString().trim())) {
                        tilEmail.setError("请输入正确的邮箱！");
                        return false;
                    } else {
                        return true;
                    }
                })
                .map(aBoolean -> {
                    if (!aBoolean) return aBoolean;
                    if (TextUtils.isEmpty(edtUserName.getText().toString().trim())) {
                        tilUserName.setError("用户名不能为空！");
                        return false;
                    } else {
                        return true;
                    }
                })
                .map(aBoolean -> {
                    if (!aBoolean) return aBoolean;
                    if (TextUtils.isEmpty(edtPwd.getText().toString().trim())) {
                        tilPwd.setError("密码不能为空！");
                        return false;
                    } else {
                        return true;
                    }
                })
                .subscribe(aBoolean -> {
                    if (aBoolean)
                        mPresenter.register(edtEmail.getText().toString().trim(), edtUserName.getText().toString().trim(), edtPwd.getText().toString().trim());
                });


    }

    @Override
    protected void initData() {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }


    @Override
    public void registerError(int code) {
        if (code == 202) {
            tilUserName.setError("用户名已存在！");
        } else if (code == 203) {
            tilEmail.setError("邮箱已存在！");
        }
    }

    @Override
    public void showLoading() {
        hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).show();
    }

    @Override
    public void hideLoading() {
        if (hud != null && hud.isShowing()) hud.dismiss();
    }
}
