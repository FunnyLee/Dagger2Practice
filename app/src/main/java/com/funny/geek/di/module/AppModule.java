package com.funny.geek.di.module;

import com.funny.geek.AppManager;
import com.funny.geek.model.database.IDBHelper;
import com.funny.geek.model.database.RealmHelper;
import com.funny.geek.model.net.DataHelper;
import com.funny.geek.model.net.IHttpHelper;
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
    IHttpHelper provideRetrofitHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    IDBHelper provideRealmHelper(RealmHelper realmHelper) {
        return realmHelper;
    }

    @Provides
    @Singleton
    DataHelper provideDataHelper(IHttpHelper httpHelper, IDBHelper dbHelper) {
        return new DataHelper(httpHelper, dbHelper);
    }

}
