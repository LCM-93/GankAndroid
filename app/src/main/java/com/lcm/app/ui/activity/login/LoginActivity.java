package com.lcm.app.ui.activity.login;


import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lcm.app.R;
import com.lcm.app.base.MvpActivity;
import com.lcm.app.dagger.component.AppComponent;
import com.lcm.app.dagger.component.DaggerActivityComponent;
import com.lcm.app.ui.activity.register.RegisterActivity;

import butterknife.BindView;


public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView {


    @BindView(R.id.edt_userName)
    TextInputEditText edtUserName;
    @BindView(R.id.til_userName)
    TextInputLayout tilUserName;
    @BindView(R.id.edt_pwd)
    TextInputEditText edtPwd;
    @BindView(R.id.til_pwd)
    TextInputLayout tilPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    private KProgressHUD hud;

    @Override
    protected int rootView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {


        RxTextView.textChanges(edtUserName).subscribe(charSequence ->
                tilUserName.setErrorEnabled(false)
        );
        RxTextView.textChanges(edtPwd).subscribe(charSequence ->
                tilPwd.setErrorEnabled(false)
        );
        RxView.clicks(btnLogin)
                .map(o -> {
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
                        mPresenter.login(edtUserName.getText().toString().trim(), edtPwd.getText().toString().trim());
                });


        RxView.clicks(tvRegister)
                .subscribe(o -> startActivity(new Intent(this, RegisterActivity.class)));

        RxView.clicks(ivClose).subscribe(o -> finish());

    }


    @Override
    protected void initData() {

    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void showLoading() {
        hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
    }

    @Override
    public void hideLoading() {
        if (hud != null && hud.isShowing()) hud.dismiss();
    }
}

