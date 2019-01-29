package com.funny.geek.model.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Author: Funny
 * Time: 2019/1/26
 * Description: This is RealmFavoriteBean
 */
public class RealmFavoriteBean extends RealmObject implements MultiItemEntity {

    @PrimaryKey
    public String id;

    public String image;

    public String title;

    public String url;

    public int type;

    public long time;

    @Override
    public int getItemType() {
        return type;
    }
}
