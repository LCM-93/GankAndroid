package com.lcm.app.ui.fragment.recent;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.lcm.android.utils.DeviceUtils;
import com.lcm.app.R;
import com.lcm.app.base.MvpFragment;
import com.lcm.app.dagger.component.AppComponent;
import com.lcm.app.dagger.component.DaggerFragmentComponent;
import com.lcm.app.data.entity.DailyContentBean;
import com.lcm.app.ui.activity.image.ImageActivity;
import com.lcm.app.ui.item.RecentItem;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import kale.adapter.CommonRcvAdapter;
import kale.adapter.RcvAdapterWrapper;
import kale.adapter.item.AdapterItem;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/11 下午1:55
 * Desc:
 * *****************************************************************
 */

public class RecentFragment extends MvpFragment<RecentPresenter> implements RecentView, View.OnClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.layout_empty)
    LinearLayout layoutEmpty;


    private List<DailyContentBean> dailyContentBeanList;
    private View headerView;
    private ImageView headerImage;
    private TextView tvTime;
    private DatePickerDialog datePickerDialog;
    private DailyContentBean headerDailyContent;

    public static RecentFragment newInstance() {
        Bundle args = new Bundle();
        RecentFragment fragment = new RecentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showEmpty() {
        layoutEmpty.setVisibility(View.VISIBLE);
        layoutEmpty.setOnClickListener(v -> {
        });
    }


    @Override
    protected int rootView() {
        return R.layout.fragment_recent;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        dailyContentBeanList = new ArrayList<>();
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#009688"), Color.parseColor("#03a9f4"), Color.parseColor("#5677fc"), Color.parseColor("#673ab7"));
        swipeRefreshLayout.setOnRefreshListener(() -> mPresenter.getHistoryDate());
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RcvAdapterWrapper rcvAdapterWrapper = new RcvAdapterWrapper(new CommonRcvAdapter<DailyContentBean>(dailyContentBeanList) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                return new RecentItem();
            }
        }, new LinearLayoutManager(getContext()));


        headerView = View.inflate(getContext(), R.layout.header_recent_list, null);
        headerImage = (ImageView) headerView.findViewById(R.id.iv_header);
        tvTime = (TextView) headerView.findViewById(R.id.tv_time);
        headerView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) DeviceUtils.dpToPixel(getContext(), 200)));
        rcvAdapterWrapper.setHeaderView(headerView);
        recyclerView.setAdapter(rcvAdapterWrapper);

        datePickerDialog = DatePickerDialog.newInstance(((view, year, monthOfYear, dayOfMonth) -> {
            LogUtils.e("lcm", "选择日期：：：" + year + "  " + (monthOfYear + 1) + "  " + dayOfMonth);
            mPresenter.getDailyData(String.valueOf(year), String.valueOf(monthOfYear + 1), String.valueOf(dayOfMonth), false);
            swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));
        }));
    }

    @Override
    protected void initData() {
        mPresenter.loadToday();
        headerImage.setOnClickListener(this);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }


    @Override
    public void refreshDailySuccess(List<DailyContentBean> dailyContentBeen) {
        layoutEmpty.setVisibility(View.GONE);
        dailyContentBeanList.clear();
        dailyContentBeanList.addAll(dailyContentBeen);
//        LogUtils.e("lcm", dailyContentBeanList.toString());
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void setHeaderView(DailyContentBean dailyContentBean, String time) {
        headerDailyContent = dailyContentBean;
        Glide.with(this)
                .load(dailyContentBean.getSrc())
                .placeholder(R.mipmap.iv_place_holder)
                .error(R.mipmap.iv_place_holder)
                .animate(R.anim.enlarge)
                .into(headerImage);

        tvTime.setVisibility(View.VISIBLE);
        tvTime.setText(time);
    }


    @Override
    public void showRefresh(boolean show) {
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(show));
    }

    @Subscriber(tag = "chooseDaily")
    public void chooseDaily(String str) {
        datePickerDialog.show(getActivity().getFragmentManager(), "date");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_header:
                Intent intent = new Intent(getActivity(), ImageActivity.class);
                intent.putExtra("image_url", headerDailyContent.getSrc());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getActivity().startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(), Pair.create(headerImage, "image")).toBundle());
                } else {
                    getActivity().startActivity(intent);
                }
                break;
        }
    }
}
