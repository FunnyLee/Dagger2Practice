package com.funny.geek.ui.adpter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.funny.geek.R;
import com.funny.geek.model.net.ImageHelper;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/7/9
 * Description: This is BannerBgAdapter
 */
public class BannerBgAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public BannerBgAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView bgIv = helper.getView(R.id.bg_iv);
        ImageHelper.loadBlurryImage(mContext, item, bgIv);
    }
}
