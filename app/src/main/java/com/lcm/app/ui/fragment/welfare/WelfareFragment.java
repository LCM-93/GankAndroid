package com.lcm.app.ui.fragment.welfare;

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
import android.widget.TextView;

import com.lcm.app.R;
import com.lcm.app.base.MvpFragment;
import com.lcm.app.dagger.component.AppComponent;
import com.lcm.app.dagger.component.DaggerFragmentComponent;
import com.lcm.app.data.entity.GankBean;
import com.lcm.app.data.impl.GankImpl;
import com.lcm.app.ui.item.GankTypeItem;
import com.lcm.app.ui.item.GankWelfareItem;
import com.lcm.app.weight.listener.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/18 下午5:33
 * Desc:
 * *****************************************************************
 */

public class WelfareFragment extends MvpFragment<WelfarePresenter> implements WelfareView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.layout_empty)
    LinearLayout layoutEmpty;

    private String type = GankImpl.GankType.Welfare;
    private List<GankBean> gankBeanList;
    private CommonRcvAdapter<GankBean> commonRcvAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int currentPage = 1;
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;

    private static WelfareFragment instance;

    public static WelfareFragment newInstance() {
        if (instance == null) {
            instance = new WelfareFragment();
        }
        return instance;
    }

    @Override
    public void showEmpty() {
        layoutEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    protected int rootView() {
        return R.layout.fragment_welfare;
    }

    @Override
    protected void initView() {

        gankBeanList = new ArrayList<>();


        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        commonRcvAdapter = new CommonRcvAdapter<GankBean>(gankBeanList) {
            @Override
            public Object getItemType(GankBean gankBean) {
                return gankBean.getType();
            }

            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                switch ((String) o) {
                    case "福利":
                        return new GankWelfareItem(getActivity());

                    default:
                        return new GankTypeItem();
                }
            }
        };
        recyclerView.setAdapter(commonRcvAdapter);

        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int curPage) {
                currentPage++;
                mPresenter.loadMoreGankList(type, currentPage);
            }
        };

        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);

        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#009688"), Color.parseColor("#03a9f4"), Color.parseColor("#5677fc"), Color.parseColor("#673ab7"));
        swipeRefreshLayout.setOnRefreshListener(() -> mPresenter.refreshGankList(type));
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));

    }

    @Override
    protected void initData() {
        mPresenter.refreshGankList(type);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void refreshGankSuccess(List<GankBean> gankBeanList) {
        endlessRecyclerOnScrollListener.reSetPreviousTotal();
        currentPage = 1;
        layoutEmpty.setVisibility(View.GONE);
        this.gankBeanList.clear();
        this.gankBeanList.addAll(gankBeanList);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void loadMoreList(List<GankBean> gankBeanList) {
        this.gankBeanList.addAll(gankBeanList);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showRefresh(boolean isShow) {
        if (swipeRefreshLayout != null) swipeRefreshLayout.setRefreshing(isShow);
    }

}
