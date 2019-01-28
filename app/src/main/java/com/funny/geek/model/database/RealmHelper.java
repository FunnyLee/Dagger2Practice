package com.funny.geek.model.database;

import com.funny.geek.model.bean.RealmFavoriteBean;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Author: Funny
 * Time: 2019/1/28
 * Description: This is RealmHelper
 */
public class RealmHelper implements IDBHelper {

    private Realm mRealm;

    @Inject
    public RealmHelper() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void insertFavorite(RealmFavoriteBean favoriteBean) {
        mRealm.executeTransaction(realm -> realm.copyToRealmOrUpdate(favoriteBean));
    }

    @Override
    public RealmFavoriteBean queryFavorite(String id) {
        RealmFavoriteBean realmFavoriteBean = mRealm.where(RealmFavoriteBean.class).equalTo("id", id).findFirst();
        return realmFavoriteBean;
    }

    @Override
    public void queryAllFavorite() {
        RealmResults<RealmFavoriteBean> allResult = mRealm.where(RealmFavoriteBean.class).findAll();
        // TODO: 2019/1/28 从数据库中查询数据
    }

    @Override
    public void deleteFavorite(String id) {
        //先查询到该记录，然后在事务里面删除
        RealmFavoriteBean realmFavoriteBean = mRealm.where(RealmFavoriteBean.class).equalTo("id", id).findFirst();
        mRealm.executeTransaction(realm -> realmFavoriteBean.deleteFromRealm());
    }
}
