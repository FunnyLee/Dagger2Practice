package com.funny.geek.di.module;

import com.funny.geek.AppManager;
import com.funny.geek.model.net.DataHelper;
import com.funny.geek.model.net.HttpHelper;
import com.funny.geek.model.net.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author: Funny
 * Time: 2018/10/18
 * Description: This is AppModule
 */

@Module
public class AppModule {

    private AppManager mAppManagerContext;

    public AppModule(AppManager appManagerContext) {
        mAppManagerContext = appManagerContext;
    }

    @Singleton
    @Provides
    AppManager provideAppManagerContext() {
        return mAppManagerContext;
    }

    @Provides
    @Singleton
    HttpHelper provideRetrofitHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DataHelper provideDataHelper(HttpHelper httpHelper) {
        return new DataHelper(httpHelper);
    }
}
