package com.lcm.app.ui.fragment.typegank;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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

    public static TypeGankFragment newInstance(@GankImpl.GankType String type) {
        Bundle args = new Bundle();
        args.putString("type", type);
        TypeGankFragment fragment = new TypeGankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showEmpty() {

    }

    @Override
    protected int rootView() {
        return R.layout.fragment_type_gank;
    }

    @Override
    protected void initView() {
        type = getArguments().getString("type");
        gankBeanList = new ArrayList<>();
//        if (type.equals(GankImpl.GankType.Welfare)) {
//            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        }

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
    }

    @Override
    protected void initData() {
        mPresenter.getGankByType(type, 1);

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void getGankSuccess(List<GankBean> gankBeanList) {

        this.gankBeanList.clear();
        this.gankBeanList.addAll(gankBeanList);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
