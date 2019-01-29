package com.funny.geek;

import android.app.Application;

import com.funny.geek.di.component.AppComponent;
import com.funny.geek.di.component.DaggerAppComponent;
import com.funny.geek.di.module.AppModule;
import com.funny.geek.di.module.HttpModule;

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
//        ProgressCallback loadingCallback = new ProgressCallback.Builder()
//                .setTitle("Loading", R.style.Hint_Title)
//                .build();
//        LoadSir.beginBuilder().addCallback()
    }

    public static AppComponent getAppComponent() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(mAppManagerContext))
                .httpModule(new HttpModule())
                .build();
        return appComponent;
    }
}
