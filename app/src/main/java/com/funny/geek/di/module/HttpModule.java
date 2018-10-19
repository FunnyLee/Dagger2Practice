package com.funny.geek.di.module;

import android.util.Log;

import com.funny.geek.BuildConfig;
import com.funny.geek.di.qualifier.ZhihuUrl;
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

    @Provides
    @Singleton
    @ZhihuUrl
    Retrofit provideZhihuRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, ZhihuApis.HOST);
    }

    @Provides
    @Singleton
    ZhihuApis provideZhihuService(@ZhihuUrl Retrofit retrofit){
        return retrofit.create(ZhihuApis.class);
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder(){
        return new Retrofit.Builder();
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpBuilder(){
        return new OkHttpClient.Builder();
    }


    @Provides
    @Singleton
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
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

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        Retrofit retrofit = builder.baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
}
