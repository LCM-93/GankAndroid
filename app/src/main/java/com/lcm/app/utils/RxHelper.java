package com.lcm.app.utils;

import com.lcm.app.data.entity.HttpBaseResult;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/9 下午5:50
 * Desc:
 * *****************************************************************
 */

public class RxHelper {

    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static <T> ObservableTransformer<HttpBaseResult<T>, T> handleResult() {
        return upstream -> upstream.flatMap(tHttpResult -> {
            if (!tHttpResult.isError()) return createData(tHttpResult.getResults());
            else return Observable.error(new Exception("数据请求出错啦"));
        });
    }

    private static <T> Observable<T> createData(T data) {
        return Observable.create(e -> {
            try {
                e.onNext(data);
                e.onComplete();
            } catch (Exception e1) {
                e.onError(e1);
            }
        });
    }
}
