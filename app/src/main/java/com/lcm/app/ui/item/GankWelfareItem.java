package com.lcm.app.ui.item;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lcm.android.utils.DeviceUtils;
import com.lcm.app.R;
import com.lcm.app.data.entity.GankBean;
import com.lcm.app.ui.activity.image.ImageActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import kale.adapter.item.AdapterItem;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/13 上午11:15
 * Desc:
 * *****************************************************************
 */

public class GankWelfareItem implements AdapterItem<GankBean> {

    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.cardView)
    CardView cardView;
    private Context context;

    private Activity activity;
    private GankBean gankBean;

    public GankWelfareItem(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_recyler_gank_welfare;
    }

    @Override
    public void bindViews(View view) {
        ButterKnife.bind(this, view);
        context = view.getContext();
    }

    @Override
    public void setViews() {
        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ImageActivity.class);
            intent.putExtra("image_url", gankBean.getUrl());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity, Pair.create(ivImage, "image")).toBundle());
            } else {
                context.startActivity(intent);
            }
        });
    }

    @Override
    public void handleData(GankBean gankBean, int i) {
        this.gankBean = gankBean;
        tvTag.setBackgroundResource(R.drawable.shape_tag_bg_welfare);
        tvTag.setText(gankBean.getType());

        Glide.with(context)
                .load(gankBean.getUrl())
                .bitmapTransform(new RoundedCornersTransformation(context, (int) DeviceUtils.dpToPixel(context, 8), 0))
                .error(R.mipmap.iv_place_holder)
                .placeholder(R.mipmap.iv_place_holder)
                .dontAnimate()
                .into(ivImage);
    }
}
