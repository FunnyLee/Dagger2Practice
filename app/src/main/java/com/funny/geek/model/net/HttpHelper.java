package com.funny.geek.model.net;

import com.funny.geek.model.bean.DailyBean;
import com.funny.geek.model.bean.HotBean;
import com.funny.geek.model.bean.SectionListBean;

import io.reactivex.Observable;

/**
 * Author: Funny
 * Time: 2018/10/19
 * Description: This is IHttpHelper
 */
public interface HttpHelper {

    Observable<DailyBean> fetchDailyListInfo();

    Observable<DailyBean> fetchDailyBeforeList(String date);

    Observable<HotBean> fetchHotList();

    Observable<SectionListBean> fetchSectionList();
}
