package com.funny.geek.ui.zhihu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.funny.geek.R;
import com.funny.geek.base.BaseMvpFragment;
import com.funny.geek.contract.zhihu.DailyContract;
import com.funny.geek.model.bean.DailyBean;
import com.funny.geek.presenter.zhihu.DailyPresenter;
import com.funny.geek.ui.MainActivity;
import com.funny.geek.ui.adpter.BannerAdapter;
import com.funny.geek.ui.adpter.BannerBgAdapter;
import com.funny.geek.ui.adpter.DailyAdapter;
import com.funny.geek.util.Constants;
import com.funny.geek.util.WindowDispaly;
import com.jakewharton.rxbinding2.view.RxView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
    private List<String> mBannerList = new ArrayList<>();

    private DailyAdapter mAdapter;
    private TextView mTvData;
    private RecyclerView mBannerRecyclerView;
    private BannerAdapter mBannerAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private int mBannerPosition = 0;
    private BannerBgAdapter mBannerBgAdapter;

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
        mTvData = headerView.findViewById(R.id.tv_date);


        //Banner背景
        RecyclerView bannerBgRecyclerView = headerView.findViewById(R.id.banner_bg_recycler_view);
        bannerBgRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        bannerBgRecyclerView.setNestedScrollingEnabled(false); //背景禁止滑动
        PagerSnapHelper bgSnapHelper = new PagerSnapHelper();
        bgSnapHelper.attachToRecyclerView(bannerBgRecyclerView);
        mBannerBgAdapter = new BannerBgAdapter(R.layout.item_banner_bg_view, mBannerList);
        bannerBgRecyclerView.setAdapter(mBannerBgAdapter);

        //Banner使用RecyclerView实现
        mBannerRecyclerView = headerView.findViewById(R.id.banner_recycler_view);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBannerRecyclerView.setLayoutManager(mLinearLayoutManager);
        //对横向滚动列表的PagerSnapHelper的进行监听，每滑动一次就切换背景一次:
        PagerSnapHelper snapHelper = new PagerSnapHelper() {
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                int targetPos = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
                bannerBgRecyclerView.scrollToPosition(targetPos);

                //根据轮播图，动态切换头部的颜色
                MainActivity activity = (MainActivity) getActivity();
                ZhihuMainFragment zhihuMainFragment = (ZhihuMainFragment) getParentFragment();
                if (targetPos == 0) {
                    activity.setStatusBarColor(R.drawable.green_gradient_color_shape);
                    zhihuMainFragment.setTabLayoutColor(R.drawable.green_gradient_color_shape);
                } else if (targetPos == 1) {
                    activity.setStatusBarColor(R.drawable.red_gradient_color_shape);
                    zhihuMainFragment.setTabLayoutColor(R.drawable.red_gradient_color_shape);
                } else if (targetPos == 2) {
                    activity.setStatusBarColor(R.drawable.blue_gradient_color_shape);
                    zhihuMainFragment.setTabLayoutColor(R.drawable.blue_gradient_color_shape);
                } else if (targetPos == 3) {
                    activity.setStatusBarColor(R.drawable.light_blue_gradient_color_shape);
                    zhihuMainFragment.setTabLayoutColor(R.drawable.light_blue_gradient_color_shape);
                } else if (targetPos == 4) {
                    activity.setStatusBarColor(R.drawable.yellow_gradient_color_shape);
                    zhihuMainFragment.setTabLayoutColor(R.drawable.yellow_gradient_color_shape);
                }


                return targetPos;
            }
        };
        snapHelper.attachToRecyclerView(mBannerRecyclerView);
        //设置第一张图片和最后一张图片也居中
        mBannerRecyclerView.addItemDecoration(new GalleryItemDecoration());
        mBannerAdapter = new BannerAdapter(R.layout.item_banner_view, mBannerList);
        mBannerRecyclerView.setAdapter(mBannerAdapter);

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
            Observable.fromIterable(top_stories)
                    .subscribe(topStoriesBean -> {
                        mBannerList.add(topStoriesBean.image);
                    });
            mBannerAdapter.setNewData(mBannerList);
            mBannerBgAdapter.setNewData(mBannerList);


            //轮播图切换监听
//            mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                }
//
//                @Override
//                public void onPageSelected(int position) {
//                    //根据轮播图，动态切换头部的颜色
//                    MainActivity activity = (MainActivity) getActivity();
//                    ZhihuMainFragment zhihuMainFragment = (ZhihuMainFragment) getParentFragment();
//                    if (position == 0) {
//                        activity.setStatusBarColor(R.drawable.green_gradient_color_shape);
//                        zhihuMainFragment.setTabLayoutColor(R.drawable.green_gradient_color_shape);
//                    } else if (position == 1) {
//                        activity.setStatusBarColor(R.drawable.red_gradient_color_shape);
//                        zhihuMainFragment.setTabLayoutColor(R.drawable.red_gradient_color_shape);
//                    } else if (position == 2) {
//                        activity.setStatusBarColor(R.drawable.blue_gradient_color_shape);
//                        zhihuMainFragment.setTabLayoutColor(R.drawable.blue_gradient_color_shape);
//                    } else if (position == 3) {
//                        activity.setStatusBarColor(R.drawable.light_blue_gradient_color_shape);
//                        zhihuMainFragment.setTabLayoutColor(R.drawable.light_blue_gradient_color_shape);
//                    } else if (position == 4) {
//                        activity.setStatusBarColor(R.drawable.yellow_gradient_color_shape);
//                        zhihuMainFragment.setTabLayoutColor(R.drawable.yellow_gradient_color_shape);
//                    }
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//
//                }
//            });

            //轮播图的点击事件
        } else {
            mBannerRecyclerView.setVisibility(View.GONE);
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

    class GalleryItemDecoration extends RecyclerView.ItemDecoration {

        int mPageMargin = (int) getResources().getDimension(R.dimen.dp_5);//自定义默认item边距
        int mBorderPageVisibleWidth;//第一张图片和最后一张图片的边距

        public GalleryItemDecoration() {
            mBorderPageVisibleWidth = (WindowDispaly.getWidth() - (int) getResources().getDimension(R.dimen.dp_300) - mPageMargin) / 2;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int positon = parent.getChildAdapterPosition(view); //获得当前item的position
            int itemCount = parent.getAdapter().getItemCount(); //获得item的数量
            int leftMargin;
            if (positon == 0) {
                leftMargin = mBorderPageVisibleWidth;
            } else {
                leftMargin = mPageMargin;
            }
            int rightMargin;
            if (positon == itemCount - 1) {
                rightMargin = mBorderPageVisibleWidth;
            } else {
                rightMargin = mPageMargin;
            }
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
            lp.setMargins(leftMargin, 30, rightMargin, 30);
            view.setLayoutParams(lp);
            super.getItemOffsets(outRect, view, parent, state);
        }

    }
}
