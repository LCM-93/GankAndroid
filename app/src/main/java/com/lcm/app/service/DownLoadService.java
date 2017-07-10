package com.lcm.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lcm.app.MyApplication;
import com.lcm.app.dagger.component.DaggerActivityComponent;
import com.lcm.app.data.api.ApiManager;
import com.lcm.app.utils.RxHelper;

import java.io.File;

import javax.inject.Inject;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/7/10 下午4:12
 * Desc:
 * *****************************************************************
 */

public class DownLoadService extends Service {
    private static final String TAG = "DownLoadService";

    public static final int IMAGE = 0x00;
    public static final int FILE = 0x01;

    public static final String DOWNLOAD_TYPE = "download_type";
    public static final String DOWNLOAD_URL = "download_url";


    private int downloadType;
    private String downLoadUrl;

    @Inject
    ApiManager apiManager;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        DaggerActivityComponent.builder()
                .appComponent(MyApplication.getAppComponent())
                .build()
                .inject(this);

        downloadType = intent.getIntExtra(DOWNLOAD_TYPE, IMAGE);
        downLoadUrl = intent.getStringExtra(DOWNLOAD_URL);
        if (downloadType == IMAGE) {
            downloadPic(downLoadUrl);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 下载图片
     *
     * @param downLoadUrl
     */
    public void downloadPic(String downLoadUrl) {
        if (apiManager == null) {
            LogUtils.e(TAG, "apiManager 为空");
            return;
        }
        ToastUtils.showShort("开始下载");
        apiManager.getCommonService().downloadPic(downLoadUrl)
                .compose(RxHelper.rxSchedulerHelper())
                .map(responseBody -> {
                    LogUtils.i(TAG, responseBody.contentLength());
                    return FileIOUtils.writeFileFromIS(getFile(downLoadUrl), responseBody.byteStream(), true);
                })
                .subscribe(aBoolean -> {
                    if (aBoolean) ToastUtils.showShort("图片保存成功！");
                    else ToastUtils.showShort("图片保存失败！");
                }, Throwable::printStackTrace, () -> {
                    LogUtils.i("图片保存" + getFile(downLoadUrl));
                });

    }


    private File getFile(String downLoadUrl) {

        String[] split = downLoadUrl.split("/");

        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "GankImage/" + split[split.length - 1]);


        return file;
    }
}
