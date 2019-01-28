package com.funny.geek.di.component;

import android.app.Activity;

import com.funny.geek.di.module.FragmentModule;
import com.funny.geek.di.scope.FragmentScope;
import com.funny.geek.ui.option.FavoriteFragment;
import com.funny.geek.ui.zhihu.DailyFragment;
import com.funny.geek.ui.zhihu.HotFragment;
import com.funny.geek.ui.zhihu.SpecialFragment;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import dagger.Component;

/**
 * Author: Funny
 * Time: 2018/10/18
 * Description: This is FragmentComponent
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    LifecycleProvider<FragmentEvent> getLifecycleProvider();

    //标志注入到DailyFragment位置
    void inject(DailyFragment fragment);

    void inject(HotFragment fragment);

    void inject(SpecialFragment fragment);

    void inject(FavoriteFragment fragment);

}
