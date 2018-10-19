package com.funny.geek.model.net;

import com.funny.geek.model.bean.DailyListBean;

import io.reactivex.Observable;

/**
 * Author: Funny
 * Time: 2018/10/19
 * Description: This is IHttpHelper
 */
public interface HttpHelper {

    Observable<DailyListBean> fetchDailyListInfo();

}
