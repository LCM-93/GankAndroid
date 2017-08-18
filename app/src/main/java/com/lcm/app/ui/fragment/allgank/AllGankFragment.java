package com.lcm.app.ui.fragment.allgank;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.lcm.android.base.BaseFragment;
import com.lcm.app.R;
import com.lcm.app.data.impl.GankImpl;
import com.lcm.app.ui.fragment.typegank.TypeGankFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * ****************************************************************
 * Author: LCM
 * Date: 2017/6/13 上午9:05
 * Desc:
 * *****************************************************************
 */

public class AllGankFragment extends BaseFragment {


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private List<Fragment> fragmentList;
    private MyFragmentAdapter mAdapter;

    private String[] typeList = new String[]{GankImpl.GankType.All, GankImpl.GankType.Android, GankImpl.GankType.IOS, GankImpl.GankType.Web, GankImpl.GankType.Video,
            GankImpl.GankType.Extra, GankImpl.GankType.Random_Recommend};

    public static AllGankFragment newInstance() {

        Bundle args = new Bundle();

        AllGankFragment fragment = new AllGankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int rootView() {
        return R.layout.fragment_all_gank;
    }

    @Override
    protected void initView() {
        fragmentList = new ArrayList<>();
        for (String s : typeList) {
            fragmentList.add(TypeGankFragment.newInstance(s));
        }
        mAdapter = new MyFragmentAdapter(getFragmentManager());
        viewPager.setAdapter(mAdapter);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initData() {

    }


    class MyFragmentAdapter extends FragmentPagerAdapter {

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return typeList[position];
        }
    }

}
