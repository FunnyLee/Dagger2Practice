package com.funny.geek.ui.adpter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.funny.geek.R;
import com.funny.geek.model.bean.RealmFavoriteBean;
import com.funny.geek.model.net.ImageHelper;
import com.funny.geek.util.Constants;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/1/28
 * Description: This is FavoriteAdapter
 */
public class FavoriteAdapter extends BaseQuickAdapter<RealmFavoriteBean, BaseViewHolder> {

    private Context mContext;

    public FavoriteAdapter(Context context,@Nullable List<RealmFavoriteBean> data) {
        super(data);
        this.mContext =context;

        setMultiTypeDelegate(new MultiTypeDelegate<RealmFavoriteBean>() {
            @Override
            protected int getItemType(RealmFavoriteBean realmFavoriteBean) {
                return realmFavoriteBean.type;
            }
        });

        getMultiTypeDelegate().registerItemType(Constants.TYPE_ZHIHU, R.layout.item_favorite_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, RealmFavoriteBean item) {
        switch (item.type){
            case Constants.TYPE_ZHIHU:
                ImageView ivPic = helper.getView(R.id.pic_iv);
                TextView titleTv = helper.getView(R.id.title_tv);
                TextView fromTv = helper.getView(R.id.from_tv);
                ImageHelper.loadImage(mContext,item.image,ivPic);
                titleTv.setText(item.title);
                fromTv.setText("来自知乎");
                break;
            default:
                break;
        }
    }
}
