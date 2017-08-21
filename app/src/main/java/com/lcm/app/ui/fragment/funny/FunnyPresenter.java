package com.lcm.app.ui.fragment.funny;

import com.blankj.utilcode.util.LogUtils;
import com.lcm.android.mvp.BaseMvpPresenter;
import com.lcm.app.data.entity.ExcerptBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/21 下午3:29
 * Desc:
 * *****************************************************************
 */

public class FunnyPresenter extends BaseMvpPresenter<FunnyView> {

    private String baseUrl = "http://www.52rkl.cn/sansanyougeng/";

    @Inject
    public FunnyPresenter() {

    }


    public void parserHtml(String parserUrl) {


        if (parserUrl == null) {
            parserUrl = baseUrl;
//            getmMvpView().showRefresh(true);
        } else {
            parserUrl = baseUrl + parserUrl;
        }
        Observable.just(parserUrl)
                .subscribeOn(Schedulers.io())
                .map(s -> Jsoup.connect(s).get())
                .concatMap(document -> Observable.fromIterable(document.getElementsByClass("excerpt")))
                .map(excerpt -> {
                    Element a = excerpt.select("a").first();
                    String url = a.attr("href");
                    String img = a.select("img").first().attr("src");
                    String title = excerpt.select("h2").first().select("a").text();
                    String time = excerpt.select("time").first().text();
                    String cat = excerpt.getElementsByClass("cat").first().text();
                    String note = excerpt.select("p").first().select("a").first().text();

                    title = title.startsWith("三三有梗：") ? title.substring(5) : title;
                    return new ExcerptBean(img, title, time, url, note, cat);
                })
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(excerptBeen -> {
                    getmMvpView().showRefresh(false);
                    getmMvpView().refreshExcerptData(excerptBeen);
                    LogUtils.e("lcm", excerptBeen.toString());
                });


    }


}
