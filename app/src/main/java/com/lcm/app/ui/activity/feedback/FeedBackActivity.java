package com.lcm.app.ui.activity.feedback;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.lcm.android.base.BaseActivity;
import com.lcm.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/18 下午3:00
 * Desc:
 * *****************************************************************
 */

public class FeedBackActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.iv_add_image)
    ImageView ivAddImage;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.edt_feedback)
    EditText edtFeedBack;

    @Override
    protected int rootView() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("意见反馈");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.inflateMenu(R.menu.menu_image_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

    }

    @Override
    protected void initData() {
        RxTextView.textChanges(edtFeedBack)
                .subscribe(charSequence -> {
                    tvCount.setText(charSequence.length() + "/300");
                    if (charSequence.length() > 300) {
                        edtFeedBack.setBackground(getResources().getDrawable(R.drawable.shape_bg_input_error));
                        tvCount.setTextColor(Color.parseColor("#FF0007"));
                        edtFeedBack.setTextColor(Color.parseColor("#FF0007"));
                    } else {
                        edtFeedBack.setBackground(getResources().getDrawable(R.drawable.shape_bg_input_focus));
                        tvCount.setTextColor(Color.parseColor("#666666"));
                        edtFeedBack.setTextColor(Color.parseColor("#333333"));
                    }
                });
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
