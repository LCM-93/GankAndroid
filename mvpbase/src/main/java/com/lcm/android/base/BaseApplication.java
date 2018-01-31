package com.lcm.android.base;

import android.app.Application;
import android.content.Context;

import com.lcm.android.dagger.module.AppModule;
import com.lcm.android.dagger.module.ClientModule;
import com.lcm.android.http.GlobeHttpHandler;

import java.util.LinkedList;

import okhttp3.Interceptor;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/20 下午4:21
 * Desc:
 * *****************************************************************
 */

public abstract class BaseApplication extends Application {
    private static BaseApplication mApplication;
    public LinkedList<BaseActivity> mActivityList;
    private ClientModule mClientModule;
    private AppModule mAppModule;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        mClientModule = ClientModule
                .builder()
                .baseurl(getBaseUrl())
                .globeHttpHandler(getHttpHandler())
                .interceptors(getInterceptors())
                .build();

        mAppModule = new AppModule(this);
    }


    /**
     * 返回上下文
     * @return
     */
    public static Context getContext() {
        return mApplication;
    }


    /**
     * 提供基础url给retrofit
     *
     * @return
     */
    protected abstract String getBaseUrl();

    public ClientModule getClientModule() {
        return mClientModule;
    }

    public AppModule getAppModule() {
        return mAppModule;
    }

    /**
     * 这里可以提供一个全局处理http响应结果的处理类,
     * 这里可以比客户端提前一步拿到服务器返回的结果,可以做一些操作,比如token超时,重新获取
     * 默认不实现,如果有需求可以重写此方法
     *
     * @return
     */
    protected GlobeHttpHandler getHttpHandler() {
        return null;
    }

    /**
     * 用来提供interceptor,如果要提供额外的interceptor可以让子application实现此方法
     *
     * @return
     */
    protected Interceptor[] getInterceptors() {
        return null;
    }


    /**
     * 返回一个存储所有存在的activity的列表
     *
     * @return
     */
    public LinkedList<BaseActivity> getActivityList() {
        if (mActivityList == null) {
            mActivityList = new LinkedList<>();
        }
        return mActivityList;
    }
}
