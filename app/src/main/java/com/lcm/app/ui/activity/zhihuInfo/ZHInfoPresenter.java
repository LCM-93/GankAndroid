package com.lcm.app.ui.activity.zhihuInfo;

import com.blankj.utilcode.util.LogUtils;
import com.lcm.android.mvp.BaseMvpPresenter;
import com.lcm.app.data.entity.ZHInfoBean;
import com.lcm.app.data.impl.ZHImpl;

import java.util.List;

import javax.inject.Inject;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/22 下午2:29
 * Desc:
 * *****************************************************************
 */

public class ZHInfoPresenter extends BaseMvpPresenter<ZHInfoView> {

    private ZHImpl zh;

    @Inject
    public ZHInfoPresenter(ZHImpl zh) {
        this.zh = zh;
    }

    public void getZHInfo(String id) {
        zh.getZHInfo(id)
                .doAfterNext(zhInfoBean -> {
                    getmMvpView().loadHtml(getHtml(zhInfoBean));
                })
                .subscribe(zhInfoBean -> {
                    getmMvpView().loadZHInfoSuccess(zhInfoBean);
//                    LogUtils.e(zhInfoBean.toString());
                });
    }

    private String addCss(String css) {
        return "<link rel=\"stylesheet\"  href=\"" + css + "\">";
    }

    private String addJs(String js) {
        return "<script src=\"" + js + "\"></script>";
    }

    private String addHeader(ZHInfoBean zhInfoBean) {
        String header = "<head><meta charset=\"utf-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"><meta name=\"apple-itunes-app\" content=\"app-id=639087967, app-argument=zhihudaily://story/9582980\"><meta name=\"viewport\" content=\"user-scalable=no, width=device-width\">";
        List<String> css = zhInfoBean.getCss();
        if (css.size() > 0) {
            for (String cs : css) {
                header = header + addCss(cs);
            }
        }

        List<String> js = zhInfoBean.getJs();
        if (js.size() > 0) {
            for (String j : js) {
                header = header + addJs(j);
            }
        }
        header = header + "</head>";
        return header;
    }

    private String getHtml(ZHInfoBean zhInfoBean) {
        String html = "<html>";

        String body = zhInfoBean.getBody().replace("<div class=\"img-place-holder\"></div>","");
        html = html + addHeader(zhInfoBean) + "<body>" + body + "</body></html>";


        return html;
    }
}
