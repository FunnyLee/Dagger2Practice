package com.funny.geek.model.net.api;

import com.funny.geek.model.bean.WelcomeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author: Funny
 * Time: 2018/10/16
 * Description: This is ZhihuApi
 */
public interface ZhihuApi {

    String HOST = "http://news-at.zhihu.com/api/4/";

    /**
     * 启动界面图片
     */
    @GET("start-image/{res}")
    Observable<WelcomeBean> getWelcomeInfo(@Path("res") String res);

}
