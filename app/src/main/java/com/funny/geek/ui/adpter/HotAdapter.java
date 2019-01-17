package com.funny.geek.ui.adpter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.funny.geek.R;
import com.funny.geek.model.bean.HotBean;
import com.funny.geek.model.net.ImageHelper;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/1/17
 * Description: This is HotAdapter
 */
public class HotAdapter extends BaseQuickAdapter<HotBean.RecentBean, BaseViewHolder> {

    private Context mContext;

    public HotAdapter(Context context, int layoutResId, @Nullable List<HotBean.RecentBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HotBean.RecentBean item) {
        ImageView picIv = helper.getView(R.id.pic_iv);
        TextView titleTv = helper.getView(R.id.title_tv);

        titleTv.setText(item.title);
        ImageHelper.loadImage(mContext, item.thumbnail, picIv);
    }
}
