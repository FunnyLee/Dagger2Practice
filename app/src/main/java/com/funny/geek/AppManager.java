package com.funny.geek;

import android.app.Application;

import com.funny.geek.di.component.AppComponent;
import com.funny.geek.di.component.DaggerAppComponent;
import com.funny.geek.di.module.AppModule;
import com.funny.geek.di.module.HttpModule;
import com.funny.geek.widget.LoadingCallback;
import com.funny.geek.widget.NetErrorCallback;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.core.LoadSir;

import io.realm.Realm;
import io.realm.RealmConfiguration;

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

        //初始化realm数据库
        initRealm();

        initLoadSir();
    }

    private void initRealm() {
        Realm.init(this);
        //配置数据库文件的名称和版本号
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("my.realm")
                .schemaVersion(1)
                //版本冲突时，删除原数据库
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private void initLoadSir() {
        LoadSir.beginBuilder()
                .addCallback(new LoadingCallback())
                .addCallback(new NetErrorCallback())
                .setDefaultCallback(SuccessCallback.class)
                .commit();
    }

    public static AppComponent getAppComponent() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(mAppManagerContext))
                .httpModule(new HttpModule())
                .build();
        return appComponent;
    }
}
