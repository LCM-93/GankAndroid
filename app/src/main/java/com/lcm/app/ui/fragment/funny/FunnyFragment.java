package com.lcm.app.ui.fragment.funny;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lcm.app.R;
import com.lcm.app.base.MvpFragment;
import com.lcm.app.dagger.component.AppComponent;
import com.lcm.app.dagger.component.DaggerFragmentComponent;
import com.lcm.app.data.entity.DailyContentBean;
import com.lcm.app.data.entity.ExcerptBean;
import com.lcm.app.ui.item.FunnyDataItem;
import com.lcm.app.ui.item.RecentItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kale.adapter.CommonRcvAdapter;
import kale.adapter.RcvAdapterWrapper;
import kale.adapter.item.AdapterItem;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/21 下午3:27
 * Desc:
 * *****************************************************************
 */

public class FunnyFragment extends MvpFragment<FunnyPresenter> implements FunnyView {

    private static FunnyFragment instance;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.layout_empty)
    LinearLayout layoutEmpty;

    private List<ExcerptBean> excerptList;

    public static FunnyFragment newInstance() {
        if (instance == null) {
            instance = new FunnyFragment();
        }
        return instance;
    }

    @Override
    public void showEmpty() {
        layoutEmpty.setVisibility(View.VISIBLE);
        layoutEmpty.setOnClickListener(v -> {
        });
    }

    @Override
    protected int rootView() {
        return R.layout.fragment_funny;
    }

    @Override
    protected void initView() {
        excerptList = new ArrayList<>();

        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#009688"), Color.parseColor("#03a9f4"), Color.parseColor("#5677fc"), Color.parseColor("#673ab7"));
        swipeRefreshLayout.setOnRefreshListener(() -> mPresenter.parserHtml(null));
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RcvAdapterWrapper rcvAdapterWrapper = new RcvAdapterWrapper(new CommonRcvAdapter<ExcerptBean>(excerptList) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                return new FunnyDataItem();
            }
        }, new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(rcvAdapterWrapper);

    }

    @Override
    protected void initData() {

        mPresenter.parserHtml(null);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }


    @Override
    public void refreshExcerptData(List<ExcerptBean> excerptBeen) {
        layoutEmpty.setVisibility(View.GONE);
        excerptList.clear();
        excerptList.addAll(excerptBeen);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showRefresh(boolean show) {
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(show));
    }
}
