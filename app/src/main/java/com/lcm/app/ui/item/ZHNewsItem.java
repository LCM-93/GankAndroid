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
import com.lcm.app.data.entity.ZHStoriesBean;
import com.lcm.app.ui.activity.zhihuInfo.ZHInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import kale.adapter.item.AdapterItem;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/22 上午10:15
 * Desc:
 * *****************************************************************
 */

public class ZHNewsItem implements AdapterItem<ZHStoriesBean> {

    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.cardView)
    CardView cardView;

    private Context context;
    private ZHStoriesBean zhStoriesBean;
    private Activity activity;

    public ZHNewsItem(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_recycler_zhihu;
    }

    @Override
    public void bindViews(View view) {
        ButterKnife.bind(this, view);
        context = view.getContext();
    }

    @Override
    public void setViews() {
        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ZHInfoActivity.class);
            intent.putExtra("ZH_ID", zhStoriesBean.getId() + "");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity, Pair.create(ivImage, "image")).toBundle());
            } else {
                context.startActivity(intent);
            }
        });

    }

    @Override
    public void handleData(ZHStoriesBean zhStoriesBean, int i) {
        this.zhStoriesBean = zhStoriesBean;
        if (zhStoriesBean.getImages().size() > 0) {
            Glide.with(context)
                    .load(zhStoriesBean.getImages().get(0))
                    .placeholder(R.mipmap.iv_place_holder)
                    .error(R.mipmap.iv_place_holder)
                    .into(ivImage);
        }

        tvTitle.setText(zhStoriesBean.getTitle());
    }
}
