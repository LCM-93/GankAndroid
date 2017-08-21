package com.lcm.app.dagger.component;

import com.lcm.android.dagger.scope.FragmentScope;
import com.lcm.app.ui.fragment.funny.FunnyFragment;
import com.lcm.app.ui.fragment.recent.RecentFragment;
import com.lcm.app.ui.fragment.typegank.TypeGankFragment;
import com.lcm.app.ui.fragment.welfare.WelfareFragment;

import dagger.Component;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/11 下午3:00
 * Desc:
 * *****************************************************************
 */
@FragmentScope
@Component(dependencies = AppComponent.class)
public interface FragmentComponent {
    void inject(RecentFragment recentFragment);

    void inject(TypeGankFragment typeGankFragment);

    void inject(WelfareFragment welfareFragment);

    void inject(FunnyFragment funnyFragment);
}
