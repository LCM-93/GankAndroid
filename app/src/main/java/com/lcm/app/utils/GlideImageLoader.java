package com.lcm.app.utils;

import android.content.Context;
import android.os.Build;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lcm.app.R;
import com.youth.banner.loader.ImageLoader;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/22 下午1:33
 * Desc:
 * *****************************************************************
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .placeholder(R.mipmap.iv_place_holder)
                .error(R.mipmap.iv_place_holder)
                .into(imageView);
    }


    @Override
    public ImageView createImageView(Context context) {
        ImageView imageView = new ImageView(context);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setTransitionName("image");
        }
        return imageView;
    }
}
