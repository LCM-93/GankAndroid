package com.lcm.app.data.impl;

import com.lcm.app.data.api.ApiManager;
import com.lcm.app.data.entity.DailyBean;
import com.lcm.app.data.entity.DailyContentBean;
import com.lcm.app.utils.RxHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/11 下午2:04
 * Desc:
 * *****************************************************************
 */

public class RecentImpl {

    private ApiManager apiManager;

    @Inject
    public RecentImpl(ApiManager apiManager) {
        this.apiManager = apiManager;
    }

    public Observable<List<String>> getHistoryDateList() {
        return apiManager.getCommonService().getHistoryDateList()
                .compose(RxHelper.rxSchedulerHelper())
                .compose(RxHelper.handleResult());
    }

    public Observable<List<DailyContentBean>> getDailyData(String year, String month, String day) {
        return apiManager.getCommonService().getDailyData(year, month, day)
                .compose(RxHelper.rxSchedulerHelper())
                .compose(RxHelper.handleResult())
                .map(dailyBeen -> {
                    if (dailyBeen == null || dailyBeen.size() == 0) {
                        throw new RuntimeException(new Throwable("暂时没有数据哦"));
                    } else {
                        return dailyBeen.get(0);
                    }
                })
                .map(dailyBean -> {
                    String content = dailyBean.getContent();
                    return Jsoup.parse(content);
                })
                .map(document -> {
                    List<DailyContentBean> list = new ArrayList<>();
                    Elements h3 = document.getElementsByTag("h3");
                    String attr = document.select("p").select("img").attr("src");
                    DailyContentBean dailyContentBean1 = new DailyContentBean();
                    dailyContentBean1.setSrc(attr);
                    dailyContentBean1.setType("福利");
                    list.add(dailyContentBean1);

                    for (int i = 0; i < h3.size(); i++) {
                        Element element = h3.get(i);
                        Element ulElement = element.nextElementSibling();
                        Elements li = ulElement.children();
                        for (int j = 0; j < li.size(); j++) {
                            DailyContentBean dailyContentBean = new DailyContentBean();
                            dailyContentBean.setType(element.text());

                            Element liElement = li.get(j);
                            dailyContentBean.setUrl(liElement.select("a").attr("href"));
                            dailyContentBean.setText(liElement.select("a").text());

                            Elements ul = liElement.select("ul");
                            Elements children = ul.get(0).children();
                            if (children.size() != 0) {
                                Element element1 = children.get(0);
                                dailyContentBean.setSrc(element1.select("img").attr("src"));
                            }

                            list.add(dailyContentBean);
                        }
                    }
                    return list;
                });


//        return apiManager.getCommonService().getDailyData(year, month, day)
//                .compose(RxHelper.rxSchedulerHelper())
//                .compose(RxHelper.handleResult())
//                .map(dailyBeen -> {
//                    if (dailyBeen == null || dailyBeen.size() == 0) {
//                        throw new RuntimeException(new Throwable("暂时没有数据哦"));
//                    } else {
//                        return dailyBeen.get(0);
//                    }
//                })
//                .map(dailyBean -> {
//                    String content = dailyBean.getContent();
//                    return Jsoup.parse(content);
//                })
//                .flatMap(document -> {
//                    Elements h3 = document.getElementsByTag("h3");
//                    List<Element> elementList = new ArrayList<Element>();
//                    for (int i = 0; i < h3.size(); i++) {
//                        elementList.add(h3.get(i));
//                    }
//                    return Observable.fromIterable(elementList);
//                })
//                .flatMap(element -> {
//                    Element element1 = element.nextElementSibling();
//                    Elements children = element1.children();
//
//                    List<ElementWithType> elementList = new ArrayList<>();
//                    for (int i = 0; i < children.size(); i++) {
//                        elementList.add(new ElementWithType(children.get(i), element1.text()));
//                    }
//                    return Observable.fromIterable(elementList);
//                })
//                .map(elementWithType -> {
//                    DailyContentBean dailyContentBean = new DailyContentBean();
//                    dailyContentBean.setType(elementWithType.getType());
//                    Element element = elementWithType.getElement();
//
//                    dailyContentBean.setUrl(element.select("a").attr("href"));
//                    dailyContentBean.setText(element.select("a").text());
//
//                    Elements ul = element.select("ul");
//                    Elements children = ul.get(0).children();
//                    if (children.size() != 0) {
//                        Element element1 = children.get(0);
//                        dailyContentBean.setSrc(element1.select("img").attr("src"));
//                    }
//                    return dailyContentBean;
//                })
    }
}
