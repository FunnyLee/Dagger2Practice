package com.funny.geek.di.component;

import com.funny.geek.AppManager;
import com.funny.geek.di.module.AppModule;
import com.funny.geek.di.module.HttpModule;
import com.funny.geek.model.net.DataHelper;
import com.funny.geek.model.net.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author: Funny
 * Time: 2018/10/18
 * Description: This is AppComponent
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    AppManager getAppManagerContext();

    RetrofitHelper getRetrofitHelper();

    DataHelper getDataHelper();
}
