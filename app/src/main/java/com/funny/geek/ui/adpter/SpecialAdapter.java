package com.funny.geek.ui.adpter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.funny.geek.R;
import com.funny.geek.model.bean.SectionListBean;
import com.funny.geek.model.net.ImageHelper;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/1/18
 * Description: This is SpecialAdapter
 */
public class SpecialAdapter extends BaseQuickAdapter<SectionListBean.DataBean, BaseViewHolder> {

    private Context mContext;

    public SpecialAdapter(Context context, int layoutResId, @Nullable List<SectionListBean.DataBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionListBean.DataBean item) {
        ImageView bgIv = helper.getView(R.id.section_bg);
        TextView kindTv = helper.getView(R.id.kind_tv);
        TextView descTv = helper.getView(R.id.desc_tv);

        ImageHelper.loadImage(mContext,item.thumbnail,bgIv);
        kindTv.setText(item.name);
        descTv.setText(item.description);
    }
}
