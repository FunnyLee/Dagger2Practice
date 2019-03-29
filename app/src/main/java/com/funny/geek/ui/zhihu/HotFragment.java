package com.funny.geek.ui.zhihu;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.funny.geek.R;
import com.funny.geek.base.BaseMvpFragment;
import com.funny.geek.contract.zhihu.HotContract;
import com.funny.geek.model.bean.HotBean;
import com.funny.geek.presenter.zhihu.HotPresenter;
import com.funny.geek.ui.adpter.HotAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: Funny
 * Time: 2018/10/17
 * Description: This is HotFragment
 */
public class HotFragment extends BaseMvpFragment<HotPresenter> implements HotContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private List<HotBean.RecentBean> mDatas = new ArrayList<>();
    private HotAdapter mAdapter;

    public static HotFragment newInstance() {
        Bundle args = new Bundle();
        HotFragment fragment = new HotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initData() {
        onStatusLoading();
        mPresenter.doLoadData();
    }

    @Override
    protected void initView(View view) {
        mAdapter = new HotAdapter(getContext(), R.layout.item_hot_content_view, mDatas);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initEvent() {
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.doLoadData();
            }
        });
    }

    @Override
    public void onShowContentView(HotBean hotBean) {
        onStatusSuccess();
        mSwipeRefresh.setEnabled(true);
        if (mSwipeRefresh.isRefreshing()) {
            mSwipeRefresh.setRefreshing(false);
        }
        mDatas.clear();
        mDatas.addAll(hotBean.recent);
        mAdapter.setNewData(mDatas);
    }

    @Override
    public void onShowErrorView() {
        onStatusNetError();
        toastErrorMsg(getString(R.string.net_error_retry));
        mSwipeRefresh.setEnabled(false);
        if (mSwipeRefresh.isRefreshing()) {
            mSwipeRefresh.setRefreshing(false);
        }
    }

    @Override
    protected void doReload() {
        initData();
    }
}
