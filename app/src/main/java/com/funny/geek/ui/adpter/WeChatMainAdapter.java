package com.funny.geek.ui.adpter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.funny.geek.R;
import com.funny.geek.model.bean.WeChatBean;
import com.funny.geek.model.net.GlideHelper;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/2/15
 * Description: This is WeChatMainAdapter
 */
public class WeChatMainAdapter extends BaseQuickAdapter<WeChatBean.NewslistBean, BaseViewHolder> {

    private Context mContext;

    public WeChatMainAdapter(Context context, int layoutResId, @Nullable List<WeChatBean.NewslistBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, WeChatBean.NewslistBean item) {
        ImageView picIv = helper.getView(R.id.pic_iv);
        helper.setText(R.id.title_tv, item.title).setText(R.id.desc_tv, item.description).setText(R.id.time_tv, item.ctime);
        GlideHelper.loadImage(mContext, item.picUrl, picIv);
    }
}
