package com.funny.geek.model.net;

import com.funny.geek.model.bean.DailyBean;
import com.funny.geek.model.bean.HotBean;
import com.funny.geek.model.bean.RealmFavoriteBean;
import com.funny.geek.model.bean.SectionListBean;
import com.funny.geek.model.bean.ZhihuDetailBean;
import com.funny.geek.model.database.IDBHelper;

import io.reactivex.Observable;

/**
 * Author: Funny
 * Time: 2018/10/19
 * Description: This is DataManager
 */
public class DataHelper implements IHttpHelper, IDBHelper {

    private IHttpHelper mHttpHelper;
    private IDBHelper mIDBHelper;

    public DataHelper(IHttpHelper httpHelper, IDBHelper IDBHelper) {
        mHttpHelper = httpHelper;
        mIDBHelper = IDBHelper;
    }

    ///////////////////////////////////////////////////////////////////////////
    // 网络请求操作
    ///////////////////////////////////////////////////////////////////////////
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

    @Override
    public Observable<ZhihuDetailBean> fetchDetailInfo(int id) {
        return mHttpHelper.fetchDetailInfo(id);
    }

    ///////////////////////////////////////////////////////////////////////////
    // 数据库操作
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void insertFavorite(RealmFavoriteBean favoriteBean) {
        mIDBHelper.insertFavorite(favoriteBean);
    }

    @Override
    public RealmFavoriteBean queryFavorite(String id) {
        return mIDBHelper.queryFavorite(id);
    }

    @Override
    public void deleteFavorite(String id) {
        mIDBHelper.deleteFavorite(id);
    }
}
