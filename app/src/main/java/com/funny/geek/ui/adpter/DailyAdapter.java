package com.funny.geek.ui.adpter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.funny.geek.R;
import com.funny.geek.model.bean.DailyBean;
import com.funny.geek.model.net.ImageHelper;

import java.util.List;

/**
 * Author: Funny
 * Time: 2018/10/22
 * Description: This is DailyAdapter
 */
public class DailyAdapter extends BaseQuickAdapter<DailyBean.StoriesBean, BaseViewHolder> {

    private Context mContext;

    public DailyAdapter(Context context, int layoutResId, @Nullable List<DailyBean.StoriesBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DailyBean.StoriesBean item) {
        ImageView ivPic = helper.getView(R.id.iv_pic);
        helper.setText(R.id.tv_title, item.getTitle());
        ImageHelper.loadImage(mContext, item.getImages().get(0), ivPic);
    }
}
