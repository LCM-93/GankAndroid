package com.lcm.app.ui.activity.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.lcm.app.R;
import com.lcm.app.base.MvpActivity;
import com.lcm.app.dagger.component.AppComponent;
import com.lcm.app.dagger.component.DaggerActivityComponent;
import com.lcm.app.data.entity.GankBean;
import com.lcm.app.data.impl.GankImpl;
import com.lcm.app.ui.item.GankTypeItem;
import com.lcm.app.ui.item.GankWelfareItem;
import com.lcm.app.weight.listener.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kale.adapter.CommonRcvAdapter;
import kale.adapter.item.AdapterItem;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/7/4 下午7:21
 * Desc:
 * *****************************************************************
 */

public class SearchActivity extends MvpActivity<SearchPresenter> implements SearchView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.layout_empty)
    LinearLayout layoutEmpty;
    @BindView(R.id.edt_query)
    EditText edtQuery;
    @BindView(R.id.iv_search)
    ImageView ivSearch;

    private String type = "all";
    private List<GankBean> gankBeanList;
    private CommonRcvAdapter<GankBean> commonRcvAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int currentPage = 1;
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;


    @Override
    protected int rootView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {

        toolbar.setTitle("搜索");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        gankBeanList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this);
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
                        return new GankWelfareItem();

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
                mPresenter.searchGank(edtQuery.getText().toString(), GankImpl.GankType.All, currentPage);
            }
        };
        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#009688"), Color.parseColor("#03a9f4"), Color.parseColor("#5677fc"), Color.parseColor("#673ab7"));
        swipeRefreshLayout.setOnRefreshListener(() -> {
            currentPage = 1;
            mPresenter.searchGank(edtQuery.getText().toString(), GankImpl.GankType.All, currentPage);
        });
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.iv_search)
    public void search() {
        if (TextUtils.isEmpty(edtQuery.getText().toString().trim())) {
            ToastUtils.showShortToast("搜索内容不能为空");
            return;
        }
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));
        currentPage = 1;
        mPresenter.searchGank(edtQuery.getText().toString(), GankImpl.GankType.All, currentPage);
    }

    @Override
    protected void initData() {
        mPresenter.searchGank(edtQuery.getText().toString(), GankImpl.GankType.All, currentPage);
    }

    @Override
    public void showEmpty() {
        layoutEmpty.setVisibility(View.VISIBLE);
    }


    @Override
    public void showRefresh(boolean show) {
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(show));
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void searchGankSuccess(List<GankBean> gankBeanList) {
        endlessRecyclerOnScrollListener.reSetPreviousTotal();
        layoutEmpty.setVisibility(View.GONE);

        this.gankBeanList.clear();
        this.gankBeanList.addAll(gankBeanList);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void searchMoreGankSuccess(List<GankBean> gankBeanList) {
        this.gankBeanList.addAll(gankBeanList);
        recyclerView.getAdapter().notifyDataSetChanged();
    }


}
