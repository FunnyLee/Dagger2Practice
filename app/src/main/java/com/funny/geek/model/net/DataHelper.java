package com.funny.geek.model.net;

import com.funny.geek.model.bean.DailyBean;
import com.funny.geek.model.bean.HotBean;
import com.funny.geek.model.bean.SectionListBean;

import io.reactivex.Observable;

/**
 * Author: Funny
 * Time: 2018/10/19
 * Description: This is DataManager
 */
public class DataHelper implements HttpHelper {

    private HttpHelper mHttpHelper;

    public DataHelper(HttpHelper httpHelper) {
        mHttpHelper = httpHelper;
    }

    @Override
    public Observable<DailyBean> fetchDailyListInfo() {
        return mHttpHelper.fetchDailyListInfo();
    }

    @Override
    public Observable<DailyBean> fetchDailyBeforeList(String date) {
        return mHttpHelper.fetchDailyBeforeList(date);
    }

    @Override
    public Observable<HotBean> fetchHotList() {
        return mHttpHelper.fetchHotList();
    }

    @Override
    public Observable<SectionListBean> fetchSectionList() {
        return mHttpHelper.fetchSectionList();
    }


}
