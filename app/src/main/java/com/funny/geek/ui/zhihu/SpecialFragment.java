package com.funny.geek.ui.zhihu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.funny.geek.R;
import com.funny.geek.base.RootFragment;
import com.funny.geek.contract.zhihu.SpecialContract;
import com.funny.geek.model.bean.SectionListBean;
import com.funny.geek.model.net.ImageHelper;
import com.funny.geek.presenter.zhihu.SpecialPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: Funny
 * Time: 2018/10/17
 * Description: This is 专栏 Fragment
 */
public class SpecialFragment extends RootFragment<SpecialPresenter> implements SpecialContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private List<SectionListBean.DataBean> mDatas = new ArrayList<>();
    private SpecialAdapter mAdapter;

    public static SpecialFragment newInstance() {
        Bundle args = new Bundle();
        SpecialFragment fragment = new SpecialFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_special;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initData() {
        mPresenter.doLoadData();
    }

    @Override
    protected void initView(View view) {
        mAdapter = new SpecialAdapter(mContext, R.layout.item_special_content_view, mDatas);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onShowContentView(SectionListBean sectionListBean) {
        mDatas.clear();
        mDatas.addAll(sectionListBean.data);
        mAdapter.setNewData(mDatas);
    }

    class SpecialAdapter extends BaseQuickAdapter<SectionListBean.DataBean, BaseViewHolder> {

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
}
