package com.funny.geek.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

/**
 * Author: Funny
 * Time: 2018/10/16
 * Description: This is 所有Activity的基类
 */
public abstract class BaseActivity extends StatusActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在这个方法里面执行inject注入操作
        //保证要在initView、initData、initEvent之前，因为这三个方法里可能会用到mPresenter对象
        initMvpDagger();
        initView();
        initData();
        initEvent();
    }

    /**
     * 自动管理Rxjava生命周期
     *
     * @return
     */
    protected LifecycleProvider<ActivityEvent> autoRxLifeCycle() {
        LifecycleProvider<ActivityEvent> activityLifecycleProvider = NaviLifecycle.createActivityLifecycleProvider(this);
        return activityLifecycleProvider;
    }

    protected void initMvpDagger() {
    }

    protected void initView() {
    }

    protected void initData() {
    }

    protected void initEvent() {
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
