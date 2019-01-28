package com.funny.geek.ui.adpter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.funny.geek.model.bean.RealmFavoriteBean;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/1/28
 * Description: This is FavoriteAdapter
 */
public class FavoriteAdapter extends BaseQuickAdapter<RealmFavoriteBean,BaseViewHolder> {

    public FavoriteAdapter(int layoutResId, @Nullable List<RealmFavoriteBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RealmFavoriteBean item) {

    }
}
