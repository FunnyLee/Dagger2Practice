package com.funny.geek.di.component;

import android.app.Activity;

import com.funny.geek.di.module.ActivityModule;
import com.funny.geek.di.scope.ActivityScope;
import com.funny.geek.ui.zhihu.ZhihuDetailActivity;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import dagger.Component;

/**
 * Author: Funny
 * Time: 2019/1/18
 * Description: This is ActivityComponent
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
//一定要添加dependencies = AppComponent.class，这样才能获取到AppModule里面DataHelper对象进行网络请求
public interface ActivityComponent {

    Activity getActivity();

    LifecycleProvider<ActivityEvent> getLifecycleProvider();

    void inject(ZhihuDetailActivity activity);

}
