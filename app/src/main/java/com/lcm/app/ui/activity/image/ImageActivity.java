package com.lcm.app.ui.activity.image;

import android.Manifest;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.lcm.android.base.BaseActivity;
import com.lcm.app.R;
import com.lcm.app.service.DownLoadService;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/7/10 下午2:39
 * Desc:
 * *****************************************************************
 */

public class ImageActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {
    @BindView(R.id.image)
    PhotoView image;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private static final String TAG = "ImageActivity";


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
        toolbar.inflateMenu(R.menu.menu_image_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        imageUrl = getIntent().getStringExtra("image_url");


        image.setScaleType(ImageView.ScaleType.CENTER_CROP);

        new RxPermissions(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS)
                .subscribe(aBoolean -> {
                    if(aBoolean) LogUtils.i(TAG,"获取权限成功");
                });

        Glide.with(this)
                .load(imageUrl)
//                .bitmapTransform(new RoundedCornersTransformation(context, (int) DeviceUtils.dpToPixel(context, 8), 0))
                .error(R.mipmap.iv_place_holder)
                .placeholder(R.mipmap.iv_place_holder)
                .into(image);


    }

    @Override
    protected void initData() {
        toolbar.setOnMenuItemClickListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_image_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                Intent intent1=new Intent(Intent.ACTION_SEND);
                intent1.putExtra(Intent.EXTRA_TEXT,"福利哦  (｡・`ω´･)  "+imageUrl);
                intent1.setType("text/plain");
                startActivity(Intent.createChooser(intent1,"share"));

                break;

            case R.id.menu_download:

                Intent intent = new Intent(this, DownLoadService.class);
                intent.putExtra(DownLoadService.DOWNLOAD_TYPE, DownLoadService.IMAGE);
                intent.putExtra(DownLoadService.DOWNLOAD_URL, imageUrl);
                startService(intent);
                break;
        }
        return false;
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
