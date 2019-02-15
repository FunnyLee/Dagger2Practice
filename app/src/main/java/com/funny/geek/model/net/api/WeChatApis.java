package com.funny.geek.model.net.api;

import com.funny.geek.model.bean.WeChatBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author: Funny
 * Time: 2019/2/15
 * Description: This is WeChatApis
 */
public interface WeChatApis {

    String HOST = "http://api.tianapi.com/";

    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Observable<WeChatBean> getWXHot(@Query("key") String key, @Query("num") int num, @Query("page") int page);

}
