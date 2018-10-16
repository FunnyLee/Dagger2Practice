package com.funny.geek.model.net;

import com.funny.geek.model.bean.WelcomeBean;
import com.funny.geek.model.net.api.ZhihuApi;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Author: Funny
 * Time: 2018/10/16
 * Description: This is RetrofitHelper
 */
public class RetrofitHelper {

    private ZhihuApi mZhihuApi;

    @Inject
    public RetrofitHelper(ZhihuApi zhihuApi) {
        mZhihuApi = zhihuApi;
    }

    public Observable<WelcomeBean> getWelcomeInfo(String res){
        return mZhihuApi.getWelcomeInfo(res);
    }
}
