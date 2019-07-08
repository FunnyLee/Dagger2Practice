package com.funny.geek.ui.zhihu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
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
import com.funny.geek.model.net.GlideHelper;
import com.funny.geek.presenter.zhihu.DailyPresenter;
import com.funny.geek.ui.MainActivity;
import com.funny.geek.ui.adpter.DailyAdapter;
import com.funny.geek.util.Constants;
import com.jakewharton.rxbinding2.view.RxView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
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
    SmartRefreshLayout mRefreshLayout;

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
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
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
        if (mRefreshLayout.getState() == RefreshState.Refreshing) {
            mRefreshLayout.finishRefresh();
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
            //轮播图切换监听
            mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    //根据轮播图，动态切换头部的颜色
                    MainActivity activity = (MainActivity) getActivity();
                    ZhihuMainFragment zhihuMainFragment = (ZhihuMainFragment) getParentFragment();
                    if(position == 0){
                        activity.setStatusBarColor(R.drawable.green_gradient_color_shape);
                        zhihuMainFragment.setTabLayoutColor(R.drawable.green_gradient_color_shape);
                    }else if(position == 1){
                        activity.setStatusBarColor(R.drawable.red_gradient_color_shape);
                        zhihuMainFragment.setTabLayoutColor(R.drawable.red_gradient_color_shape);
                    }else if(position == 2){
                        activity.setStatusBarColor(R.drawable.blue_gradient_color_shape);
                        zhihuMainFragment.setTabLayoutColor(R.drawable.blue_gradient_color_shape);
                    } else if(position == 3){
                        activity.setStatusBarColor(R.drawable.light_blue_gradient_color_shape);
                        zhihuMainFragment.setTabLayoutColor(R.drawable.light_blue_gradient_color_shape);
                    }else if(position == 4){
                        activity.setStatusBarColor(R.drawable.yellow_gradient_color_shape);
                        zhihuMainFragment.setTabLayoutColor(R.drawable.yellow_gradient_color_shape);
                    }
                }


                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            //轮播图的点击事件
            mBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    ZhihuDetailActivity.start(mContext, ids.get(position));
                }
            });
            //轮播图加载图片
            mBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    GlideHelper.loadImage(context, (String) path, imageView);
                }
            });
            mBanner.setImages(imageUrls);
            mBanner.setBannerTitles(bannerTitles);
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            //是否自动轮播
            mBanner.isAutoPlay(false);
            mBanner.start();
        } else {
            mBanner.setVisibility(View.GONE);
        }
        mDatas.clear();
        //数据太少，重复添加一些数据
        for (int i = 0; i < 10; i++) {
            mDatas.addAll(dailyListBean.stories);
        }
        mAdapter.setNewData(mDatas);
        mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void onShowErrorView() {
        onStatusNetError();
        toastErrorMsg(getString(R.string.net_error_retry));
        if (mRefreshLayout.getState() == RefreshState.Refreshing) {
            mRefreshLayout.finishRefresh();
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
