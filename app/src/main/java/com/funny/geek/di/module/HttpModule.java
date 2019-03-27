package com.funny.geek.di.module;

import android.util.Log;

import com.funny.geek.BuildConfig;
import com.funny.geek.di.qualifier.GankUrl;
import com.funny.geek.di.qualifier.WeChatUrl;
import com.funny.geek.di.qualifier.ZhihuUrl;
import com.funny.geek.model.net.api.GankApis;
import com.funny.geek.model.net.api.WeChatApis;
import com.funny.geek.model.net.api.ZhihuApis;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.Logger;
import com.ihsanbal.logging.LoggingInterceptor;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: Funny
 * Time: 2018/10/16
 * Description: This is HttpModules
 */
@Module
public class HttpModule {

    @Singleton
    @Provides
    GankApis provideGankService(@GankUrl Retrofit retrofit) {
        return retrofit.create(GankApis.class);
    }

    @Provides
    @Singleton
    @GankUrl
    Retrofit provideGankRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, GankApis.HOST);
    }

    @Singleton
    @Provides
    WeChatApis provideWeChatService(@WeChatUrl Retrofit retrofit) {
        return retrofit.create(WeChatApis.class);
    }

    @Singleton
    @Provides
    @WeChatUrl
    Retrofit provideWeChatRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, WeChatApis.HOST);
    }

    @Provides
    @Singleton
    ZhihuApis provideZhihuService(@ZhihuUrl Retrofit retrofit) {
        //这个方法需要一个Retrofit类型的参数，它根据@ZhihuUrl注解来找到provideZhihuRetrofit方法，得到Retrofit对象

        //提供ZhihuApiService对象，这一个很重要，可以调用ZhihuApi里面的方法
        return retrofit.create(ZhihuApis.class);
    }


    @Provides
    @Singleton
    @ZhihuUrl
    Retrofit provideZhihuRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        //提供Retrofit对象，ip地址是ZhihuApis.HOST
        return createRetrofit(builder, client, ZhihuApis.HOST);
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder() {
        //提供一个Retrofit.Builder对象，在createRetrofit方法中会用到
        return new Retrofit.Builder();
    }

    @Provides
    @Singleton
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        //这个方法需要一个OkHttpClient.Builder参数，它就会去从带有@provide注解中的方法中去找
        OkHttpClient client = builder
                .addInterceptor(new LoggingInterceptor.Builder()
                        .loggable(BuildConfig.DEBUG)
                        .setLevel(Level.BASIC)
                        .log(Platform.INFO)
                        .request("Request")
                        .response("Response")
                        .addHeader("version", BuildConfig.VERSION_NAME)
                        .addQueryParam("query", "0")
                        .enableAndroidStudio_v3_LogsHack(true) /* enable fix for logCat logging issues with pretty format */
                        .logger(new Logger() {
                            @Override
                            public void log(int level, String tag, String msg) {
                                Log.w(tag, msg);
                            }
                        })
                        .executor(Executors.newSingleThreadExecutor())
                        .build())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        return client;
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpBuilder() {
        //提供一个OkHttpClient.Builder对象，在provideClient方法中会用到
        return new OkHttpClient.Builder();
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        //这个方法需要一个Retrofit.Builder对象和OkHttpClient对象，它就会去从带有@Provide注解的方法中去找
        Retrofit retrofit = builder.baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
}
