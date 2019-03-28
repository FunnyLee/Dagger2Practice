package com.funny.geek.model.net.api;

import com.funny.geek.model.bean.GankBean;
import com.funny.geek.model.bean.GankGirlBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author: Funny
 * Time: 2019/3/27
 * Description: This is GankApi
 */
public interface GankApis {

    String HOST = "http://gank.io/api/";

    /**
     * 技术文章列表
     */
//    @GET("data/{tech}/{num}/{page}")
//    Flowable<GankHttpResponse<List<GankItemBean>>> getTechList(@Path("tech") String tech, @Path("num") int num, @Path("page") int page);
    @GET("data/{tech}/{num}/{page}")
    Observable<GankBean> getTechList(@Path("tech") String tech, @Path("num") int num, @Path("page") int page);

    /**
     * 随机girl图
     */
    @GET("random/data/福利/{num}")
    Observable<GankGirlBean> getGankGirlList(@Path("num") int num);
}
