package com.funny.geek.model.net;

import com.funny.geek.model.bean.DailyBean;

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
}