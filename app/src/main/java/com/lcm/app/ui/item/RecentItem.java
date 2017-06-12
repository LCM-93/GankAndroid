package com.lcm.app.ui.item;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lcm.app.R;
import com.lcm.app.data.entity.DailyContentBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import kale.adapter.item.AdapterItem;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/11 下午6:02
 * Desc:
 * *****************************************************************
 */

public class RecentItem implements AdapterItem<DailyContentBean> {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    private Context context;

    @Override
    public int getLayoutResId() {
        return R.layout.item_recyler_recent;
    }

    @Override
    public void bindViews(View view) {
        ButterKnife.bind(this, view);
        context = view.getContext();
    }

    @Override
    public void setViews() {

    }

    @Override
    public void handleData(DailyContentBean dailyContentBean, int i) {
        tvTitle.setText(dailyContentBean.getText());
        tvTag.setText(dailyContentBean.getType());
        if (dailyContentBean.getSrc() != null && !"null".equals(dailyContentBean.getSrc())) {
            ivImage.setVisibility(View.VISIBLE);
            Glide.with(context).load(dailyContentBean.getSrc()).into(ivImage);
        } else {
            ivImage.setVisibility(View.GONE);
        }
    }
}
