package com.funny.geek.ui.Gank;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.funny.geek.R;
import com.funny.geek.base.BaseMvpFragment;
import com.funny.geek.contract.gank.AndroidContract;
import com.funny.geek.model.bean.GankBean;
import com.funny.geek.presenter.gank.AndroidPresenter;
import com.scwang.smartrefresh.header.DropBoxHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
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

    @Override
    protected void initView(View view) {
        //设置下拉刷新的样式，放到这里设置的原因是：mRefreshLayout最多只支持三个子View
        mRefreshLayout.setRefreshHeader(new DropBoxHeader(mContext));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(mContext));
    }

    @Override
    protected void initData() {
        mPresenter.doGetTechList("Android", 10, 1);
    }

    @Override
    public void onShowContentView(GankBean gankBean) {

    }

    @Override
    public void onShowErrorView() {

    }
}
