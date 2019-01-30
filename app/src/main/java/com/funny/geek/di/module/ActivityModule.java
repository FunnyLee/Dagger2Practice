package com.funny.geek.di.module;

import android.app.Activity;

import com.funny.geek.base.BaseActivity;
import com.funny.geek.di.scope.ActivityScope;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

import dagger.Module;
import dagger.Provides;

/**
 * Author: Funny
 * Time: 2019/1/18
 * Description: This is ActivityModule
 */
@Module
public class ActivityModule {

    private BaseActivity mActivity;

    public ActivityModule(BaseActivity activity) {
        this.mActivity = activity;
    }

    /**
     * 提供一个mActivity没什么用
     * @return
     */
    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityScope
    public LifecycleProvider<ActivityEvent> provideLifecycle() {
        return NaviLifecycle.createActivityLifecycleProvider(mActivity);
    }
}
