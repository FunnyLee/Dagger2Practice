package com.funny.geek.model.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Author: Funny
 * Time: 2019/1/26
 * Description: This is RealmFavoriteBean
 */
public class RealmFavoriteBean extends RealmObject {

    @PrimaryKey
    public String id;

    public String image;

    public String title;

    public String url;

    public int type;

    public long time;

}
