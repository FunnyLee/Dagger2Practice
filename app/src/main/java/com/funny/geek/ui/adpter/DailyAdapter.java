package com.funny.geek.ui.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.funny.geek.R;
import com.funny.geek.model.bean.DailyBean;
import com.funny.geek.model.net.GlideHelper;
import com.funny.geek.ui.zhihu.ZhihuDetailActivity;
import com.funny.geek.util.Constants;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @SuppressLint("CheckResult")
    @Override
    protected void convert(BaseViewHolder helper, DailyBean.StoriesBean item) {
        ImageView ivPic = helper.getView(R.id.iv_pic);
        helper.setText(R.id.tv_title, item.title);
        GlideHelper.loadImage(mContext, item.images.get(0), ivPic);

        RxView.clicks(helper.itemView)
                .throttleFirst(Constants.CLICK_INTERVAL, TimeUnit.SECONDS)
                .subscribe(o -> {
                    ZhihuDetailActivity.start(mContext,item.id);
                });
    }
}
