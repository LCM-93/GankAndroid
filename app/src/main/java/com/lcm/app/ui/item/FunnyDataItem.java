package com.lcm.app.ui.item;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.lcm.android.utils.DeviceUtils;
import com.lcm.app.R;
import com.lcm.app.data.entity.ExcerptBean;
import com.lcm.app.ui.activity.web.WebActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import kale.adapter.item.AdapterItem;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/21 下午5:47
 * Desc:
 * *****************************************************************
 */

public class FunnyDataItem implements AdapterItem<ExcerptBean> {

    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_note)
    TextView tvNote;
    @BindView(R.id.cardView)
    CardView cardView;

    private Context context;
    private ExcerptBean excerptBean;

    @Override
    public int getLayoutResId() {
        return R.layout.item_recycler_funny;
    }

    @Override
    public void bindViews(View view) {
        ButterKnife.bind(this, view);
        context = view.getContext();
    }

    @Override
    public void setViews() {
        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, WebActivity.class);
            intent.putExtra("url", excerptBean.getUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public void handleData(ExcerptBean excerptBean, int i) {
        this.excerptBean = excerptBean;
        Glide.with(context).load(excerptBean.getImg())
                .bitmapTransform(new RoundedCornersTransformation(context, (int) DeviceUtils.dpToPixel(context, 4), 0))
                .error(R.mipmap.iv_place_holder)
                .placeholder(R.mipmap.iv_place_holder)
                .into(ivImage);

        tvNote.setText(excerptBean.getNote());
        tvTime.setText(excerptBean.getUpdateTime());
        tvTitle.setText(excerptBean.getTitle());
    }
}
