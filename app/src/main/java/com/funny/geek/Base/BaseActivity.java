package com.funny.geek.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.navi2.component.support.NaviAppCompatActivity;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

import butterknife.ButterKnife;

/**
 * Author: Funny
 * Time: 2018/10/16
 * Description: This is BaseActivity
 */
public abstract class BaseActivity extends NaviAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
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

    protected void initView() {
    }

    protected void initData() {
    }

    protected void initEvent() {
    }

    public abstract int getLayoutId();
}
