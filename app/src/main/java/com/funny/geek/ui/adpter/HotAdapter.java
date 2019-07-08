package com.funny.geek.ui.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.funny.geek.R;
import com.funny.geek.model.bean.HotBean;
import com.funny.geek.model.net.GlideHelper;
import com.funny.geek.ui.zhihu.ZhihuDetailActivity;
import com.funny.geek.util.Constants;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @SuppressLint("CheckResult")
    @Override
    protected void convert(BaseViewHolder helper, HotBean.RecentBean item) {
        ImageView picIv = helper.getView(R.id.pic_iv);
        TextView titleTv = helper.getView(R.id.title_tv);

        titleTv.setText(item.title);
        GlideHelper.loadImage(mContext, item.thumbnail, picIv);

        RxView.clicks(helper.itemView)
                .throttleFirst(Constants.CLICK_INTERVAL, TimeUnit.SECONDS)
                .subscribe(o -> {
                    ZhihuDetailActivity.start(mContext,item.news_id);
                });
    }
}
