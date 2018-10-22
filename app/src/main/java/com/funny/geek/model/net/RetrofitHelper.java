package com.funny.geek.model.net;

import com.funny.geek.model.bean.DailyBean;
import com.funny.geek.model.bean.WelcomeBean;
import com.funny.geek.model.net.api.ZhihuApis;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Author: Funny
 * Time: 2018/10/16
 * Description: This is RetrofitHelper
 */
public class RetrofitHelper implements HttpHelper {

    //相当于retrofit.create(ZhihuApis.class)对象，通过Dagger2注入
    private ZhihuApis mZhihuService;

    @Inject
    public RetrofitHelper(ZhihuApis zhihuService) {
        mZhihuService = zhihuService;
    }

    public Observable<WelcomeBean> getWelcomeInfo(String res) {
        return mZhihuService.getWelcomeInfo(res);
    }

    @Override
    public Observable<DailyBean> fetchDailyListInfo() {
        return mZhihuService.getDailyList();
    }
}
