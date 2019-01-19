package com.funny.geek;

import android.app.Application;

import com.funny.geek.di.component.AppComponent;
import com.funny.geek.di.component.DaggerAppComponent;
import com.funny.geek.di.module.AppModule;
import com.funny.geek.di.module.HttpModule;

/**
 * Author: Funny
 * Time: 2018/10/18
 * Description: This is AppManager
 */
public class AppManager extends Application {

    public static AppManager mAppManagerContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppManagerContext = this;
    }

    public static AppComponent getAppComponent() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(mAppManagerContext))
                .httpModule(new HttpModule())
                .build();
        return appComponent;
    }
}
