package com.lcm.app.ui.activity.register;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.lcm.android.base.BaseActivity;
import com.lcm.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/17 下午8:41
 * Desc:
 * *****************************************************************
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
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

}
