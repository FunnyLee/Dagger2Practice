# Dagger2使用体会


1.从DailyPresenter的loadData方法发起网络请求为入口。Presenter对象的构造方法添加了@Inject注解。  

Dagger2的特点是，构造方法上有@Inject注解的对象可以直接获得（Dagger2没有Module时的用法）。构造方法中如果有参数对象，它就会去找这个参数对象，看它是new出来的，还是通过Dagger2生成的。    
    
在这里，DailyPresenter的构造函数需要一个DataHelper对象的参数，但是我们发现DataHelper类的构造方法并没有@Inject注解。因为在AppModule中，有一个provideDataHelper()方法返回一个DataHelper对象。（Dagger2有Module时的用法）

```java
    public class DailyPresenter implements DailyContract.Presenter{

    private DataHelper mDataHelper;

    @Inject
    public DailyPresenter(DataHelper dataHelper) {
        mDataHelper = dataHelper;
    }

    @Override
    public void doLoadData() {
        Observable<DailyListBean> dailyListBeanObservable = mDataHelper.fetchDailyListInfo();
    }

```

2.DataHelper只是一个中间类，管理所有的网络请求。  

关注三个类，DataHelper、HttpHelper、RetrofitHelper。HttpHelper是接口类，DataHelper和RetrofitHelper实现它，这样统一管理所有的网络请求方法。DataHelper是包装类（这里用到了装饰者模式的思想），而RetrofitHelper才是真正执行网络请求的角色。

```java
     @Override
    public Observable<DailyListBean> fetchDailyListInfo() {
        //这段代码是DataHelper中的。真正执行fetchDailyListInfo()方法，是在HttpHelper的子类RetrofitHelper中
        return mHttpHelper.fetchDailyListInfo();
    }
```

3.在RetrofitHelper类中，真正执行了网络请求的动作，并返回一个Observable对象给我们操作。但是使用Retrofit执行网络请求必不可少的一个东西就是ZhihuApis接口对象（retrofit.create(ZhihuApis.class).fetchDailyListInfo()），它是通过Dagger2注入的。
    
```java
    public class RetrofitHelper implements HttpHelper {
    
    //相当于retrofit.create(ZhihuApis.class)对象
    private ZhihuApis mZhihuService;

    @Inject
    public RetrofitHelper(ZhihuApis zhihuService) {
        mZhihuService = zhihuService;
    }

    //真正执行网络请求处，返回一个Observable<DailyListBean>对象
    @Override
    public Observable<DailyListBean> fetchDailyListInfo() {
        return mZhihuService.getDailyList();
    }
}
```
ZhihuApis是在哪里提供的了？顺藤摸瓜，可以发现在HttpModule中，有返回ZhihuApis类型的方法。代码中的注释已经很详细了。

```java
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
        //提供Retrofit对象，ip地址是ZhihuApis.HOST
        return createRetrofit(builder, client, ZhihuApis.HOST);
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
    Retrofit.Builder provideRetrofitBuilder() {
        //提供一个Retrofit.Builder对象，在createRetrofit方法中会用到
        return new Retrofit.Builder();
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpBuilder() {
        //提供一个OkHttpClient.Builder对象，在provideClient方法中会用到
        return new OkHttpClient.Builder();
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

```

4.看下面的这张顺藤摸瓜图（为了截图方便，我把代码都收缩起来了）。

![HttpModule顺藤摸瓜图][1]

第一个方法返回一个ZhihuApis类型的对象，这正是我们所需要的。但是这个方法需要一个Retrofit类型的参数。刚好发现第二个方法可以返回一个Retrofit对象，但是这个方法的参数列表，需要(Retrofit.Builder builder, OkHttpClient client)两个参数，顺着箭头的方向，也可以找到提供这两个参数的方法......  

我发现一个特点，***Dagger2虽然可以依赖注入某个对象，但是这个对象绝对不是凭空产生的，一定能找到提供这个对象的地方***。  

另一个特点是，如果是我们自己定义的类，比如DailyPresenter，我们可以很容易拿到它的构造方法，那么就可以在它的构造方法上加@Inject注解，也不用在Module类中提供。相对应的Activity和Fragment中可以直接拿来用，不过记得要加注解。
如果有些类不是我们自己定义的，比如OkHttpClient.Builder类，我们不好拿到它的构造方法直接操作，那么只能通过在module中provide的方式来提供。就是因为这样，所以才会有HttpModule中的那些方法一环套一环。  

这些就是我在项目中学习使用Dagger2的一些个人体会。这并不是Dagger2的基础入门使用，所以Dagger2的具体使用姿势，我没有谈到。如果你对Dagger2有基础的认识，并能简单地使用它，那我想你也会对这些内容感同身受。  

庆幸自己对Dagger2没有从入门到放弃，而是从入门到想要放弃，到咬牙坚持，然后才略懂皮毛，哈哈。不得不说，Dagger2确实是Android框架中最难理解的一个，上手很难，但还是想好好掌握它。




  [1]: https://img-blog.csdnimg.cn/20181219152034627.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0Z1bm55TGU=,size_16,color_FFFFFF,t_70
