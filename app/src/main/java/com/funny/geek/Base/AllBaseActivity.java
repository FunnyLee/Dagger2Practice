package com.funny.geek.base;

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
 * Description: This is 所有Activity的基类
 */
public abstract class AllBaseActivity extends NaviAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        onViewCreated();
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

    /**
     * 在这个方法里面执行inject注入操作
     * 保证要在initView、initData、initEvent之前，因为这三个方法里可能会用到mPresenter对象
     */
    protected abstract void onViewCreated();

    protected void initView() {
    }

    protected void initData() {
    }

    protected void initEvent() {
    }

    public abstract int getLayoutId();
}
