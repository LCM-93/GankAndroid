package com.lcm.android.mvp;

import android.content.Context;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/5/22 下午12:01
 * Desc:
 * *****************************************************************
 */

public interface BaseView<T> {
    Context getActivityContext();

    void showMessage(String str);

    void showEmpty();

    void finishView();
}
