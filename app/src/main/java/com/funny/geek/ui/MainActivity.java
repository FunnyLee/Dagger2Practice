package com.funny.geek.ui;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.funny.geek.R;
import com.funny.geek.base.AllBaseActivity;
import com.funny.geek.ui.zhihu.ZhihuMainFragment;

import butterknife.BindView;

public class MainActivity extends AllBaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.frame_layout)
    FrameLayout mFrameLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        switchFragment(ZhihuMainFragment.newInstance());
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initEvent() {
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.zhihu:
                        switchFragment(ZhihuMainFragment.newInstance());
                        break;
                    case R.id.wechat:
                        switchFragment(ZhihuMainFragment.newInstance());
                        break;
                    case R.id.gank:
                        switchFragment(ZhihuMainFragment.newInstance());
                        break;
                    case R.id.gold:
                        switchFragment(ZhihuMainFragment.newInstance());
                        break;
                    case R.id.v2ex:
                        switchFragment(ZhihuMainFragment.newInstance());
                        break;
                    case R.id.like:
                        switchFragment(ZhihuMainFragment.newInstance());
                        break;
                    case R.id.setting:
                        switchFragment(ZhihuMainFragment.newInstance());
                        break;
                    case R.id.about:
                        switchFragment(ZhihuMainFragment.newInstance());
                        break;
                    default:
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                mToolbar.setTitle(item.getTitle());
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
