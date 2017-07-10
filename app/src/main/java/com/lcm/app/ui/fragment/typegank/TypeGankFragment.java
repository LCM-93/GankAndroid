package com.lcm.app.ui.fragment.typegank;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.LogUtils;
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
import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/13 上午9:14
 * Desc:
 * *****************************************************************
 */

public class TypeGankFragment extends MvpFragment<TypeGankPresenter> implements TypeGankView {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.layout_empty)
    LinearLayout layoutEmpty;
    @BindView(R.id.tv)
    TextView tv;

    private String type;
    private List<GankBean> gankBeanList;
    private CommonRcvAdapter<GankBean> commonRcvAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int currentPage = 1;
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;

    public static TypeGankFragment newInstance(@GankImpl.GankType String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        TypeGankFragment fragment = new TypeGankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showEmpty() {
        layoutEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    protected int rootView() {
        return R.layout.fragment_type_gank;
    }

    @Override
    protected void initView() {
        type = getArguments().getString("type");
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
