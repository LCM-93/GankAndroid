package com.lcm.app.ui.activity.image;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.lcm.android.base.BaseActivity;
import com.lcm.app.R;

import butterknife.BindView;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/7/10 下午2:39
 * Desc:
 * *****************************************************************
 */

public class ImageActivity extends BaseActivity {
    @BindView(R.id.image)
    PhotoView image;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private String imageUrl;

    @Override
    protected int rootView() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        return R.layout.activity_image;
    }

    @Override
    protected void initView() {



        toolbar.setTitle("福利");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        imageUrl = getIntent().getStringExtra("image_url");


        image.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Glide.with(this)
                .load(imageUrl)
//                .bitmapTransform(new RoundedCornersTransformation(context, (int) DeviceUtils.dpToPixel(context, 8), 0))
                .error(R.mipmap.iv_place_holder)
                .placeholder(R.mipmap.iv_place_holder)
                .into(image);


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
