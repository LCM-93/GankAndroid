package com.lcm.app.ui.activity.main;

import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lcm.app.R;
import com.lcm.app.base.MvpActivity;
import com.lcm.app.dagger.component.AppComponent;
import com.lcm.app.dagger.component.DaggerActivityComponent;
import com.lcm.app.ui.fragment.recent.RecentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends MvpActivity<MainPresenter> implements MainView, BottomNavigationBar.OnTabSelectedListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private List<Fragment> fragmentList;
    private ViewpagerAdapter mAdapter;

    @Override
    protected int rootView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        fragmentList = new ArrayList<>();
        fragmentList.add(RecentFragment.newInstance());
//        fragmentList.add(RecentFragment.newInstance());
//        fragmentList.add(RecentFragment.newInstance());
//        fragmentList.add(RecentFragment.newInstance());

        mAdapter = new ViewpagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);

        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        toolbar.inflateMenu(R.menu.menu_base_toolbar);
        setSupportActionBar(toolbar);


        bottomNavigationBar.setActiveColor(R.color.colorPrimary);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.iv_bottom_home, "首页"))
                .addItem(new BottomNavigationItem(R.mipmap.iv_bottom_type, "分类"))
                .addItem(new BottomNavigationItem(R.mipmap.iv_bottom_recomment, "精选"))
                .addItem(new BottomNavigationItem(R.mipmap.iv_bottom_about, "关于"))
                .setFirstSelectedPosition(0)
                .initialise();

        bottomNavigationBar.setTabSelectedListener(this);

    }


    @Override
    protected void initData() {

    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_base_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onTabSelected(int position) {
//        viewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


    class ViewpagerAdapter extends FragmentPagerAdapter {

        public ViewpagerAdapter(FragmentManager fm) {
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
    }
}
