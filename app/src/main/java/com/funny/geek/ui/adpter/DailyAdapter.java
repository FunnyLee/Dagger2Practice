package com.funny.geek.ui.adpter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.funny.geek.R;
import com.funny.geek.model.bean.DailyBean;

import java.util.List;

/**
 * Author: Funny
 * Time: 2018/10/22
 * Description: This is DailyAdapter
 */
public class DailyAdapter extends BaseQuickAdapter<DailyBean.StoriesBean,BaseViewHolder> {

    public DailyAdapter(int layoutResId, @Nullable List<DailyBean.StoriesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DailyBean.StoriesBean item) {
        helper.setText(R.id.tv_title,item.getTitle());
    }
}
