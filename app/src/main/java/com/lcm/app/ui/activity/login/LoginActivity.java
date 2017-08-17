package com.lcm.app.ui.activity.login;


import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;
import com.lcm.android.base.BaseActivity;
import com.lcm.app.R;
import com.lcm.app.ui.activity.register.RegisterActivity;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class LoginActivity extends BaseActivity {


    @BindView(R.id.edt_email)
    TextInputEditText edtEmail;
    @BindView(R.id.til_email)
    TextInputLayout tilEmail;
    @BindView(R.id.edt_pwd)
    TextInputEditText edtPwd;
    @BindView(R.id.til_pwd)
    TextInputLayout tilPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    @Override
    protected int rootView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {


        RxView.focusChanges(edtEmail)
                .map(aBoolean -> {
                    if (!aBoolean) {
                        return RegexUtils.isEmail(edtEmail.getText().toString().trim());
                    }
                    return aBoolean;
                })
                .subscribe(aBoolean -> {
                    if (!aBoolean) {
                        tilEmail.setError("请输入正确的邮箱");
                    } else {
                        tilEmail.setErrorEnabled(false);
                    }
                });

        RxTextView.textChanges(edtPwd)
                .subscribe(charSequence -> tilPwd.setErrorEnabled(false));

        RxView.clicks(btnLogin)
                .map(o -> {
                    if (TextUtils.isEmpty(edtPwd.getText().toString().trim())) {
                        tilPwd.setError("密码不能为空");
                        return false;
                    } else {
                        return true;
                    }
                })
                .subscribe(aBoolean -> {
                    if (aBoolean) login();
                });

        RxView.clicks(tvRegister)
                .subscribe(o -> startActivity(new Intent(this, RegisterActivity.class)));

    }


    private void login() {

    }

    @Override
    protected void initData() {

    }


}

