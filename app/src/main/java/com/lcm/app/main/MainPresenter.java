package com.lcm.app.main;

import com.blankj.utilcode.utils.LogUtils;
import com.lcm.android.mvp.BaseMvpPresenter;
import com.lcm.app.weight.ProgressObserver;
import com.lcm.app.data.entity.BannerBean;
import com.lcm.app.data.entity.HttpBaseResult;

import java.util.List;

import javax.inject.Inject;


/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/23 下午12:59
 * Desc:
 * *****************************************************************
 */

public class MainPresenter extends BaseMvpPresenter<MainView> {

    @Inject
    MainModel mainModel;

    @Inject
    public MainPresenter() {

    }

    public void load(){
      mainModel.load()
                .subscribe(new ProgressObserver<HttpBaseResult<List<BannerBean>>>(getmMvpView().getActivityContext()){
                    @Override
                    public void onNext(HttpBaseResult<List<BannerBean>> listHttpBaseResult) {
                        super.onNext(listHttpBaseResult);
                        LogUtils.e("LCM",listHttpBaseResult.getResults().get(0).toString());
                        getmMvpView().OnloadSuccess(listHttpBaseResult.getResults().get(0));
                    }
                });

    }
}
