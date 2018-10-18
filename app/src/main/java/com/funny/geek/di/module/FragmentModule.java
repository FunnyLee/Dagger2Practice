package com.funny.geek.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.funny.geek.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Author: Funny
 * Time: 2018/10/18
 * Description: This is FragmentModule
 */

@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return mFragment.getActivity();
    }
}
