package com.funny.geek.ui.zhihu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.funny.geek.R;
import com.funny.geek.base.BaseMvpFragment;
import com.funny.geek.contract.zhihu.DailyContract;
import com.funny.geek.model.bean.DailyBean;
import com.funny.geek.model.net.ImageHelper;
import com.funny.geek.presenter.zhihu.DailyPresenter;
import com.funny.geek.ui.adpter.DailyAdapter;
import com.funny.geek.util.Constants;
import com.jakewharton.rxbinding2.view.RxView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * Author: Funny
 * Time: 2018/10/17
 * Description: This is DailyFragment
 */
public class DailyFragment extends BaseMvpFragment<DailyPresenter> implements DailyContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fab_btn)
    FloatingActionButton mFabBtn;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    //当前日期
    private String mCurrentDate;

    public static final int REQUEST_CODE = 100;

    private List<DailyBean.StoriesBean> mDatas = new ArrayList<>();
    private DailyAdapter mAdapter;
    private Banner mBanner;
    private TextView mTvData;

    public static DailyFragment newInstance() {
        Bundle args = new Bundle();
        DailyFragment fragment = new DailyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initView(View view) {
        mAdapter = new DailyAdapter(mContext, R.layout.item_daily_content_view, mDatas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        //头部
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.layout_banner_header, null);
        mAdapter.addHeaderView(headerView);
        mBanner = headerView.findViewById(R.id.banner);
        mTvData = headerView.findViewById(R.id.tv_date);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        onStatusLoading();
        mPresenter.doLoadData();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initEvent() {
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.doRefresh(mCurrentDate);
            }
        });

        RxView.clicks(mFabBtn)
                .throttleFirst(Constants.CLICK_INTERVAL, TimeUnit.SECONDS)
                .subscribe(o -> {
                    Intent intent = new Intent(getContext(), SelectDateActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void onShowContentView(DailyBean dailyListBean) {
        onStatusSuccess();
        mRefreshLayout.setEnabled(true);
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
        //请求成功后，设置日期
        mCurrentDate = dailyListBean.date;
        mTvData.setText(dailyListBean.date);
        //设置Banner
        List<DailyBean.TopStoriesBean> top_stories = dailyListBean.top_stories;
        if (top_stories != null && top_stories.size() != 0) {
            List<String> imageUrls = new ArrayList<>();
            List<String> bannerTitles = new ArrayList<>();
            List<Integer> ids = new ArrayList<>();
            Observable.fromIterable(top_stories)
                    .subscribe(topStoriesBean -> {
                        imageUrls.add(topStoriesBean.image);
                        bannerTitles.add(topStoriesBean.title);
                        ids.add(topStoriesBean.id);
                    });

            //轮播图的点击事件
            mBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    ZhihuDetailActivity.start(mContext,ids.get(position));
                }
            });
            mBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    ImageHelper.loadImage(context, (String) path, imageView);
                }
            });
            mBanner.setImages(imageUrls);
            mBanner.setBannerTitles(bannerTitles);
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            mBanner.isAutoPlay(true);
            mBanner.start();
        } else {
            mBanner.setVisibility(View.GONE);
        }
        mDatas.clear();
        mDatas.addAll(dailyListBean.stories);
        mAdapter.setNewData(mDatas);
        mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void onShowErrorView() {
        onStatusNetError();
        toastErrorMsg(getString(R.string.net_error));
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
        mRefreshLayout.setEnabled(false);
    }

    @Override
    protected void doReload() {
        initData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String selectedData = data.getStringExtra("selectedData");
            mPresenter.doGetBeforeDaily(selectedData);
            mCurrentDate = selectedData;
        }
    }
}
