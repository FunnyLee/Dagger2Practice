package com.funny.geek.model.database;

import com.funny.geek.model.bean.RealmFavoriteBean;

import io.realm.RealmResults;

/**
 * Author: Funny
 * Time: 2019/1/28
 * Description: This is IDBHelper
 */
public interface IDBHelper {

    /**
     * 插入收藏数据
     * @param realmFavoriteBean
     */
    void insertFavorite(RealmFavoriteBean realmFavoriteBean);

    /**
     * 根据id喜爱数据
     * @param id
     */
    RealmFavoriteBean queryFavorite(String id);

    /**
     * 查询所有喜爱数据
     */
    RealmResults<RealmFavoriteBean> queryAllFavorite();


    /**
     * 删除喜爱数据
     * @param id
     */
    void deleteFavorite(String id);



}
