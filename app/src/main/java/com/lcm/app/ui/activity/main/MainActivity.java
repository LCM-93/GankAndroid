package com.lcm.app.ui.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.blankj.utilcode.utils.LogUtils;
import com.bumptech.glide.Glide;
import com.lcm.app.R;
import com.lcm.app.base.MvpActivity;
import com.lcm.app.dagger.component.AppComponent;
import com.lcm.app.dagger.component.DaggerActivityComponent;
import com.lcm.app.ui.fragment.allgank.AllGankFragment;
import com.lcm.app.ui.fragment.recent.RecentFragment;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;


public class MainActivity extends MvpActivity<MainPresenter> implements MainView, NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.floating_button)
    FloatingActionButton floatingButton;

    private List<Fragment> fragmentList;
    private ActionBarDrawerToggle mDrawerToggle;
    private int currentIndex = 0;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private String imgUrl;
    private ImageView ivHeader;

    @Override
    protected int rootView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        imgUrl = getIntent().getStringExtra("imgUrl");

        fragmentList = new ArrayList<>();
        fragmentList.add(RecentFragment.newInstance());
        fragmentList.add(AllGankFragment.newInstance());
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.inflateMenu(R.menu.menu_base_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);

        View headerView = navigation.getHeaderView(0);
        ivHeader = (ImageView) headerView.findViewById(R.id.iv_header);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frame_layout, fragmentList.get(currentIndex)).commit();


        if (imgUrl != null) {
            Glide.with(this).load(imgUrl)
                    .bitmapTransform(new BlurTransformation(this))
                    .into(ivHeader);
        }
    }


    @Override
    protected void initData() {
        navigation.setNavigationItemSelectedListener(this);
    }


    @OnClick(R.id.floating_button)
    public void onFloatingButtonCLick() {
        EventBus.getDefault().post("chooseDaily", "chooseDaily");
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


    public void setFragment(int targetIndex) {
        if (targetIndex == currentIndex) {
            return;
        }
        Fragment targetFragment = fragmentList.get(targetIndex);
        Fragment currentFragment = fragmentList.get(currentIndex);
        fragmentTransaction = fragmentManager.beginTransaction();

        if (!targetFragment.isAdded()) {
            fragmentTransaction.add(R.id.frame_layout, targetFragment).hide(currentFragment).commitAllowingStateLoss();
        } else {
            fragmentTransaction.show(targetFragment).hide(currentFragment).commitAllowingStateLoss();
        }
        currentIndex = targetIndex;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_recent_gank:
                LogUtils.e("lcm", "点击首页");
                setFragment(0);
                drawerLayout.closeDrawers();
                break;

            case R.id.menu_all_gank:
                LogUtils.e("lcm", "点击分类");
                setFragment(1);
                drawerLayout.closeDrawers();
                break;

            case R.id.menu_recommend_gank:
                LogUtils.e("lcm", "点击推荐");
                setFragment(0);
                drawerLayout.closeDrawers();
                break;

            case R.id.menu_about:
                LogUtils.e("lcm", "点击关于我们");
                setFragment(0);
                drawerLayout.closeDrawers();
                break;
        }
        return false;
    }
}
