package com.funny.geek.ui;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.funny.geek.R;
import com.funny.geek.base.BaseActivity;
import com.funny.geek.model.net.ImageHelper;
import com.funny.geek.ui.Gank.GankMainFragment;
import com.funny.geek.ui.option.FavoriteFragment;
import com.funny.geek.ui.weChat.WeChatMainFragment;
import com.funny.geek.ui.zhihu.ZhihuMainFragment;
import com.gyf.immersionbar.ImmersionBar;

import java.util.Random;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.frame_layout)
    FrameLayout mFrameLayout;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.status_view)
    View mStatusBarView;

    private View mMenuHeadView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initStatusBar() {
        //沉浸式状态栏
        mStatusBarView = findViewById(R.id.status_view);
        ImmersionBar.with(this).statusBarView(mStatusBarView).init();
    }

    @Override
    protected void initView() {
        //去掉侧滑菜单的滑动条
        NavigationMenuView navigationMenuItemView = (NavigationMenuView) mNavView.getChildAt(0);
        if (navigationMenuItemView != null) {
            navigationMenuItemView.setVerticalScrollBarEnabled(false);
        }

        //侧滑菜单背景图片
        mMenuHeadView = mNavView.getHeaderView(0);
        ImageHelper.loadBgImage(MainActivity.this, getBgPic(), mMenuHeadView);


        switchFragment(ZhihuMainFragment.newInstance());
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initEvent() {
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.like:
                        switchFragment(FavoriteFragment.newInstance());
                        break;
                    case R.id.setting:
                        switchFragment(ZhihuMainFragment.newInstance());
                        break;
                    case R.id.about:
//                        switchFragment(ZhihuMainFragment.newInstance());
                        ScrollingActivity.start(MainActivity.this);
                        break;
                    default:
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_zhihu:
                        switchFragment(ZhihuMainFragment.newInstance());
                        break;
                    case R.id.action_wechat:
                        switchFragment(WeChatMainFragment.newInstance());
                        break;
                    case R.id.action_gank:
                        switchFragment(GankMainFragment.newInstance());
                        break;
                    case R.id.action_gold:
                        switchFragment(ZhihuMainFragment.newInstance());
                        break;
                    default:
                        break;
                }

                return true;
            }
        });

        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                ImageHelper.loadBgImage(MainActivity.this, getBgPic(), mMenuHeadView);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }

    private static String getBgPic() {
        Random random = new Random();
        return "http://106.14.135.179/ImmersionBar/" + random.nextInt(40) + ".jpg";
    }

    /**
     * 修改状态栏颜色
     *
     * @param drawable
     */
    public void setStatusBarColor(int drawable) {
        mStatusBarView.setBackgroundResource(drawable);
        ImmersionBar.with(this).statusBarView(mStatusBarView).init();
    }
}
