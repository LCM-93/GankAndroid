package com.lcm.android.dagger.module;

import android.app.Application;

import com.lcm.android.BuildConfig;
import com.lcm.android.http.GlobeHttpHandler;
import com.lcm.android.http.RequestIntercept;
import com.lcm.android.utils.SPHelper;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/22 下午12:22
 * Desc:
 * *****************************************************************
 */
@Module
public class ClientModule {
    private static final int TOME_OUT = 10;
    public static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;//缓存文件最大值为10Mb
    public HttpUrl mApiUrl;
    public GlobeHttpHandler mHandler;
    public Interceptor[] mInterceptors;

    public ClientModule(Buidler buidler) {
        this.mApiUrl = buidler.apiUrl;
        this.mHandler = buidler.handler;
        this.mInterceptors = buidler.interceptors;
    }

    public static Buidler buidler() {
        return new Buidler();
    }


    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient client, HttpUrl httpUrl) {
        final Retrofit.Builder builder = new Retrofit.Builder();
        return configureRetrofit(builder, client, httpUrl);
    }

    @Singleton
    @Provides
    public OkHttpClient provideClient(Cache cache, Interceptor intercept) {
        final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        return configureClient(okHttpClient, cache, intercept);
    }


    @Singleton
    @Provides
    public HttpUrl provideBaseUrl() {
        return mApiUrl;
    }

    @Singleton
    @Provides
    public Cache provideCache(File cacheFile) {
        return new Cache(cacheFile, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);//设置缓存路径和大小
    }


    @Singleton
    @Provides
    public Interceptor provideIntercept() {
        return new RequestIntercept(mHandler);//打印请求信息的拦截器
    }


    /**
     * 提供缓存地址
     */

    @Singleton
    @Provides
    public File provideCacheFile(Application application) {
        return SPHelper.getCacheFile(application);
    }

    /**
     * 提供RXCache客户端
     */
    @Singleton
    @Provides
    public RxCache provideRxCache(File cacheDir) {
        return new RxCache
                .Builder()
                .persistence(cacheDir, new GsonSpeaker());
    }


    private Retrofit configureRetrofit(Retrofit.Builder builder, OkHttpClient client, final HttpUrl httpUrl) {
        return builder
                .baseUrl(httpUrl)//域名
                .client(client)//设置okhttp
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用rxjava
                .addConverterFactory(GsonConverterFactory.create())//使用Gson
                .build();
    }


    private OkHttpClient configureClient(OkHttpClient.Builder okHttpClient, Cache cache, Interceptor intercept) {

        OkHttpClient.Builder builder = okHttpClient
                .connectTimeout(TOME_OUT, TimeUnit.SECONDS)
                .readTimeout(TOME_OUT, TimeUnit.SECONDS)
                .cache(cache)//设置缓存
//                .addInterceptor(intercept)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG ?
                                HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE));
        if (mInterceptors != null && mInterceptors.length > 0) {//如果外部提供了interceptor的数组则遍历添加
            for (Interceptor interceptor : mInterceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        return builder.build();
    }


    public static class Buidler {
        private HttpUrl apiUrl = null;
        private GlobeHttpHandler handler;
        private Interceptor[] interceptors;

        public Buidler() {
        }

        public Buidler baseurl(String baseurl) {//基础url
            this.apiUrl = HttpUrl.parse(baseurl);
            return this;
        }

        public Buidler globeHttpHandler(GlobeHttpHandler handler) {//用来处理http响应结果
            this.handler = handler;
            return this;
        }

        public Buidler interceptors(Interceptor[] interceptors) {//动态添加任意个interceptor
            this.interceptors = interceptors;
            return this;
        }


        public ClientModule build() {
            if (apiUrl == null) {
                throw new IllegalStateException("baseurl is required");
            }
            return new ClientModule(this);
        }


    }

}
