package com.lcm.app.ui.item;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lcm.android.utils.DeviceUtils;
import com.lcm.app.R;
import com.lcm.app.data.entity.GankBean;
import com.lcm.app.ui.activity.web.WebActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import kale.adapter.item.AdapterItem;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/13 上午10:08
 * Desc:
 * *****************************************************************
 */

public class GankTypeItem implements AdapterItem<GankBean> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.cardView)
    CardView cardView;
    private Context context;
    private String url;

    @Override
    public int getLayoutResId() {
        return R.layout.item_recyler_gank_type;
    }

    @Override
    public void bindViews(View view) {
        ButterKnife.bind(this, view);
        context = view.getContext();
    }

    @Override
    public void setViews() {
        cardView.setOnClickListener(v -> WebActivity.startWebActivity(context, url));
    }

    @Override
    public void handleData(GankBean gankBean, int i) {
        url = gankBean.getUrl();
        tvTitle.setText(gankBean.getDesc());
        tvTag.setText(gankBean.getType());
        if (gankBean.getImages() == null || gankBean.getImages().size() == 0) {
            ivImage.setVisibility(View.GONE);
        } else {
            ivImage.setVisibility(View.VISIBLE);
            Glide.with(context).load(gankBean.getImages().get(0))
                    .bitmapTransform(new RoundedCornersTransformation(context, (int) DeviceUtils.dpToPixel(context, 8), 0))
                    .error(R.mipmap.iv_place_holder)
                    .placeholder(R.mipmap.iv_place_holder)
                    .into(ivImage);
        }

        switch (gankBean.getType().toLowerCase()) {
            case "android":
                tvTag.setBackgroundResource(R.drawable.shape_tag_bg_android);
                break;

            case "ios":
                tvTag.setBackgroundResource(R.drawable.shape_tag_bg_ios);
                break;

            case "休息视频":
                tvTag.setBackgroundResource(R.drawable.shape_tag_bg_video);
                break;

            case "前端":
                tvTag.setBackgroundResource(R.drawable.shape_tag_bg_web);
                break;

            case "拓展资源":
                tvTag.setBackgroundResource(R.drawable.shape_tag_bg_extra);

                break;
            case "瞎推荐":
                tvTag.setBackgroundResource(R.drawable.shape_tag_bg_random_recommend);
                break;

            case "福利":
                tvTag.setBackgroundResource(R.drawable.shape_tag_bg_welfare);
                break;
        }

    }
}
