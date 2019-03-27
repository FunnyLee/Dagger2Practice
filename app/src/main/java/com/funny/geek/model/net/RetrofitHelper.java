package com.funny.geek.model.net;

import com.funny.geek.model.bean.DailyBean;
import com.funny.geek.model.bean.GankBean;
import com.funny.geek.model.bean.HotBean;
import com.funny.geek.model.bean.SectionListBean;
import com.funny.geek.model.bean.WeChatBean;
import com.funny.geek.model.bean.WelcomeBean;
import com.funny.geek.model.bean.ZhihuDetailBean;
import com.funny.geek.model.net.api.GankApis;
import com.funny.geek.model.net.api.WeChatApis;
import com.funny.geek.model.net.api.ZhihuApis;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Author: Funny
 * Time: 2018/10/16
 * Description: This is RetrofitHelper
 */
public class RetrofitHelper implements IHttpHelper {

    //相当于retrofit.create(ZhihuApis.class)对象，通过Dagger2注入
    private ZhihuApis mZhihuService;

    private WeChatApis mWeChatService;

    private GankApis mGankService;

    @Inject
    public RetrofitHelper(ZhihuApis zhihuService, WeChatApis weChatService, GankApis gankService) {
        mZhihuService = zhihuService;
        mWeChatService = weChatService;
        mGankService = gankService;
    }

    public Observable<WelcomeBean> getWelcomeInfo(String res) {
        return mZhihuService.getWelcomeInfo(res);
    }

    @Override
    public Observable<DailyBean> fetchDailyListInfo() {
        return mZhihuService.getDailyList();
    }

    @Override
    public Observable<DailyBean> fetchDailyBeforeList(String date) {
        return mZhihuService.getDailyBeforeList(date);
    }

    @Override
    public Observable<HotBean> fetchHotList() {
        return mZhihuService.getHotList();
    }

    @Override
    public Observable<SectionListBean> fetchSectionList() {
        return mZhihuService.getSectionList();
    }

    @Override
    public Observable<ZhihuDetailBean> fetchDetailInfo(int id) {
        return mZhihuService.getDetailInfo(id);
    }

    @Override
    public Observable<WeChatBean> fetchWeChatList(String key, int num, int page) {
        return mWeChatService.getWXHot(key, num, page);
    }

    @Override
    public Observable<GankBean> fetchTechList(String tech, int num, int page) {
        return mGankService.getTechList(tech, num, page);
    }


}
