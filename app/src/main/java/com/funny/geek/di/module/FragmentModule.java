package com.funny.geek.di.module;

import android.app.Activity;

import com.funny.geek.base.BaseFragment;
import com.funny.geek.di.scope.FragmentScope;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

import dagger.Module;
import dagger.Provides;

/**
 * Author: Funny
 * Time: 2018/10/18
 * Description: This is FragmentModule
 */

@Module
public class FragmentModule {

    private BaseFragment mFragment;

    public FragmentModule(BaseFragment fragment) {
        mFragment = fragment;
    }

    /**
     * 提供一个Acitivty没什么用，Fragment中可以直接获取到
     * @return
     */
    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return mFragment.getActivity();
    }

    @Provides
    @FragmentScope
    public LifecycleProvider<FragmentEvent> provideLifecycle(){
        return NaviLifecycle.createFragmentLifecycleProvider(mFragment);
    }
}
