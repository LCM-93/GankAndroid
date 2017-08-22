package com.lcm.app.ui.fragment.zhihu;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.lcm.android.utils.DeviceUtils;
import com.lcm.app.R;
import com.lcm.app.base.MvpFragment;
import com.lcm.app.dagger.component.AppComponent;
import com.lcm.app.dagger.component.DaggerFragmentComponent;
import com.lcm.app.data.entity.ZHStoriesBean;
import com.lcm.app.data.entity.ZHTopStoriesBean;
import com.lcm.app.ui.activity.zhihuInfo.ZHInfoActivity;
import com.lcm.app.ui.item.ZHNewsItem;
import com.lcm.app.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kale.adapter.CommonRcvAdapter;
import kale.adapter.RcvAdapterWrapper;
import kale.adapter.item.AdapterItem;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/8/21 下午8:28
 * Desc:
 * *****************************************************************
 */

public class ZhihuFragment extends MvpFragment<ZhihuPresenter> implements ZhihuView {
    private static ZhihuFragment instance;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.layout_empty)
    LinearLayout layoutEmpty;

    private List<ZHStoriesBean> zhStoriesBeanList;
    private Banner banner;
    private View headerView;
    private List<ZHTopStoriesBean> zhTopStoriesBeen;


    public static ZhihuFragment newInstance() {
        if (instance == null) {
            instance = new ZhihuFragment();
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
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void initView() {
        zhStoriesBeanList = new ArrayList<>();


        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#009688"), Color.parseColor("#03a9f4"), Color.parseColor("#5677fc"), Color.parseColor("#673ab7"));
        swipeRefreshLayout.setOnRefreshListener(() -> mPresenter.getZHNews());
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RcvAdapterWrapper rcvAdapterWrapper = new RcvAdapterWrapper(new CommonRcvAdapter<ZHStoriesBean>(zhStoriesBeanList) {
            @NonNull
            @Override
            public AdapterItem createItem(Object o) {
                return new ZHNewsItem(getActivity());
            }
        }, new LinearLayoutManager(getContext()));


        headerView = View.inflate(getContext(), R.layout.header_zhihu_list, null);
        headerView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) DeviceUtils.dpToPixel(getContext(), 200)));
        banner = (Banner) headerView.findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        banner.setImageLoader(new GlideImageLoader());
        banner.setDelayTime(5000);
        banner.isAutoPlay(true);
        rcvAdapterWrapper.setHeaderView(headerView);
        recyclerView.setAdapter(rcvAdapterWrapper);

        banner.setOnBannerListener(position -> {
            if (zhTopStoriesBeen == null) return;
            Intent intent = new Intent(getActivity(), ZHInfoActivity.class);
            intent.putExtra("ZH_ID", zhTopStoriesBeen.get(position).getId() + "");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getActivity().startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity(), Pair.create(getView(position), "image")).toBundle());
            } else {
                getActivity().startActivity(intent);
            }
        });

    }

    /**
     * 通过反射获取banner中的子View
     * @param position
     * @return
     */
    private ImageView getView(int position) {
        Class<Banner> cls = (Class<Banner>) banner.getClass();
        try {
            Field imageViews = cls.getDeclaredField("imageViews");
            imageViews.setAccessible(true);
            if (imageViews.get(banner) != null)
                return (ImageView) ((List<View>) imageViews.get(banner)).get(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void onPause() {
        banner.stopAutoPlay();
        super.onPause();
    }

    @Override
    public void onResume() {
        banner.startAutoPlay();
        super.onResume();
    }

    @Override
    protected void initData() {
        mPresenter.getZHNews();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }


    @Override
    public void refreshZHNews(List<ZHStoriesBean> zhStoriesBeen) {
        layoutEmpty.setVisibility(View.GONE);
        zhStoriesBeanList.clear();
        zhStoriesBeanList.addAll(zhStoriesBeen);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void refreshTopNews(List<ZHTopStoriesBean> zhTopStoriesBeen) {
        this.zhTopStoriesBeen = zhTopStoriesBeen;
    }

    @Override
    public void refreshTopNews(List<String> images, List<String> titles) {
        banner.update(images, titles);
        banner.start();
    }

    @Override
    public void showRefresh(boolean show) {
        swipeRefreshLayout.setRefreshing(show);
    }
}
