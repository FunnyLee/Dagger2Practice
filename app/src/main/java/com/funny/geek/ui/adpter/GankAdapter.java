package com.funny.geek.ui.adpter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.funny.geek.model.bean.GankBean;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/3/27
 * Description: This is GankAdapter
 */
public class GankAdapter extends BaseQuickAdapter<GankBean.ResultsBean, BaseViewHolder> {

    public GankAdapter(int layoutResId, @Nullable List<GankBean.ResultsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankBean.ResultsBean item) {

    }
}
