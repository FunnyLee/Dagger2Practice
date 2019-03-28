package com.funny.geek.ui.Gank;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.funny.geek.R;
import com.funny.geek.base.BaseMvpFragment;
import com.funny.geek.contract.gank.AndroidContract;
import com.funny.geek.model.bean.GankBean;
import com.funny.geek.presenter.gank.AndroidPresenter;
import com.funny.geek.ui.adpter.GankAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: Funny
 * Time: 2019/3/27
 * Description: This is AndroidFragment
 */
public class AndroidFragment extends BaseMvpFragment<AndroidPresenter> implements AndroidContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    private List<GankBean.ResultsBean> mDatas = new ArrayList<>();
    private GankAdapter mAdapter;

    public static AndroidFragment newInstance() {
        Bundle args = new Bundle();
        AndroidFragment fragment = new AndroidFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_android;
    }

    @Override
    protected void initView(View view) {
        mAdapter = new GankAdapter(R.layout.item_gank_tech_view, mDatas);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    protected void initData() {
        mPresenter.doGetTechList("Android", 10, 1);
    }

    @Override
    public void onShowContentView(GankBean gankBean) {
        mAdapter.setNewData(gankBean.results);
    }

    @Override
    public void onShowErrorView() {

    }
}
