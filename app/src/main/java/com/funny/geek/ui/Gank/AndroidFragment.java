package com.funny.geek.ui.Gank;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.funny.geek.R;
import com.funny.geek.base.BaseMvpFragment;
import com.funny.geek.contract.gank.AndroidContract;
import com.funny.geek.presenter.gank.AndroidPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;

import butterknife.BindView;

/**
 * Author: Funny
 * Time: 2019/3/27
 * Description: This is AndroidFragment
 */
public class AndroidFragment extends BaseMvpFragment<AndroidPresenter> implements AndroidContract.View {

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

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

}
