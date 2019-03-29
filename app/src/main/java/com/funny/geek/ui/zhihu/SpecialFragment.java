package com.funny.geek.ui.zhihu;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.funny.geek.R;
import com.funny.geek.base.BaseMvpFragment;
import com.funny.geek.contract.zhihu.SpecialContract;
import com.funny.geek.model.bean.SectionListBean;
import com.funny.geek.presenter.zhihu.SpecialPresenter;
import com.funny.geek.ui.adpter.SpecialAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: Funny
 * Time: 2018/10/17
 * Description: This is 专栏 Fragment
 */
public class SpecialFragment extends BaseMvpFragment<SpecialPresenter> implements SpecialContract.View {

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
    protected void initView(View view) {
        mAdapter = new SpecialAdapter(mContext, R.layout.item_special_content_view, mDatas);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        onStatusLoading();
        mPresenter.doLoadData();
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
    public void onShowContentView(SectionListBean sectionListBean) {
        onStatusSuccess();
        if(mSwipeRefresh.isRefreshing()){
            mSwipeRefresh.setRefreshing(false);
        }
        mSwipeRefresh.setEnabled(true);
        mDatas.clear();
        mDatas.addAll(sectionListBean.data);
        mAdapter.setNewData(mDatas);
    }

    @Override
    public void onShowErrorView() {
        onStatusNetError();
        toastErrorMsg(getString(R.string.net_error_retry));
        if(mSwipeRefresh.isRefreshing()){
            mSwipeRefresh.setRefreshing(false);
        }
        mSwipeRefresh.setEnabled(false);
    }

    @Override
    protected void doReload() {
        initData();
    }
}
