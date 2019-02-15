package com.funny.geek.ui.weChat;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.funny.geek.R;
import com.funny.geek.base.BaseMvpFragment;
import com.funny.geek.contract.weChat.WeChatMainContract;
import com.funny.geek.model.bean.WeChatBean;
import com.funny.geek.presenter.weChat.WeChatMainPresenter;
import com.funny.geek.ui.adpter.WeChatMainAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: Funny
 * Time: 2019/2/15
 * Description: This is 微信精选页面
 */
public class WeChatMainFragment extends BaseMvpFragment<WeChatMainPresenter> implements WeChatMainContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private List<WeChatBean.NewslistBean> mDatas = new ArrayList<>();
    private WeChatMainAdapter mAdapter;

    public static WeChatMainFragment newInstance() {
        Bundle args = new Bundle();
        WeChatMainFragment fragment = new WeChatMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_we_chat;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initView(View view) {
        mAdapter = new WeChatMainAdapter(mContext,R.layout.item_we_chat_main_view, mDatas);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        onStatusLoading();
        mPresenter.doLoadData();
    }

    @Override
    public void onShowContentView(List<WeChatBean.NewslistBean> newslist) {
        onStatusSuccess();
        mDatas.clear();
        mDatas.addAll(newslist);
        mAdapter.setNewData(mDatas);
    }

    @Override
    public void onShowErrorView() {
        // TODO: 2019/2/15  
    }
}
