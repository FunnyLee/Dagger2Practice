package com.funny.geek.di.component;

import android.app.Activity;

import com.funny.geek.di.module.FragmentModule;
import com.funny.geek.di.scope.FragmentScope;
import com.funny.geek.ui.zhihu.DailyFragment;

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

    //标志注入到DailyFragment位置
    void inject(DailyFragment fragment);

}
