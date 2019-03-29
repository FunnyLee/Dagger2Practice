package com.funny.geek.ui.Gank;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.funny.geek.R;
import com.funny.geek.base.BaseMvpFragment;
import com.funny.geek.contract.gank.AndroidContract;
import com.funny.geek.model.bean.GankBean;
import com.funny.geek.model.bean.GankGirlBean;
import com.funny.geek.model.net.ImageHelper;
import com.funny.geek.presenter.gank.AndroidPresenter;
import com.funny.geek.ui.adpter.GankAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;

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
    private TextView mTvData;

    private List<GankBean.ResultsBean> mDatas = new ArrayList<>();
    private GankAdapter mAdapter;
    private Banner mBanner;
    private int mPageNo = 1;

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
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.layout_banner_header, null);
        mAdapter.addHeaderView(headerView);
        mBanner = headerView.findViewById(R.id.banner);
        mTvData = headerView.findViewById(R.id.tv_date);
        mTvData.setVisibility(View.GONE);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    protected void initData() {
        onStatusLoading();
        mPresenter.doGetTechList("Android", 10, mPageNo);
        mPresenter.doGetGankGirlList(5);
    }

    @Override
    protected void initEvent() {
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.doGetTechList("Android", 10, mPageNo);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPageNo = 1;
                mDatas.clear();
                mPresenter.doGetTechList("Android", 10, mPageNo);
            }
        });
    }

    @Override
    public void onShowContentView(GankBean gankBean) {
        onStatusSuccess();
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
        mPageNo++;
        mDatas.addAll(gankBean.results);
        mAdapter.setNewData(mDatas);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onShowGankGirl(List<GankGirlBean.ResultsBean> girlList) {
        List<String> imageUrls = new ArrayList<>();
        Observable.fromIterable(girlList).subscribe(resultsBean -> {
            imageUrls.add(resultsBean.url);
        });

        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                ImageHelper.loadImage(context, (String) path, imageView);
            }
        });
        mBanner.setImages(imageUrls);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        mBanner.isAutoPlay(true);
        mBanner.start();
    }

    @Override
    public void onShowErrorView() {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
        if (mPageNo == 1) {
            onStatusNetError();
            toastErrorMsg(getString(R.string.net_error_retry));
        } else {
            Toast.makeText(mContext, R.string.net_error, Toast.LENGTH_SHORT).show();
        }
    }
}
