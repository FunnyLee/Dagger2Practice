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
@Component(modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(DailyFragment fragment);

}
