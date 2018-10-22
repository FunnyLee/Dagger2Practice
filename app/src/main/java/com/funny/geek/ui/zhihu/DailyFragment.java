package com.funny.geek.ui.zhihu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.funny.geek.R;
import com.funny.geek.base.RootFragment;
import com.funny.geek.contract.zhihu.DailyContract;
import com.funny.geek.model.bean.DailyBean;
import com.funny.geek.presenter.zhihu.DailyPresenter;
import com.funny.geek.ui.adpter.DailyAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import io.reactivex.Observable;

/**
 * Author: Funny
 * Time: 2018/10/17
 * Description: This is DailyFragment
 */
public class DailyFragment extends RootFragment<DailyPresenter> implements DailyContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fab_btn)
    FloatingActionButton mFabBtn;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    Unbinder unbinder;

    private List<DailyBean.StoriesBean> mDatas = new ArrayList<>();
    private DailyAdapter mAdapter;

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
        mAdapter = new DailyAdapter(R.layout.item_daily_content, mDatas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.doLoadData();
    }

    @SuppressLint("CheckResult")
    @Override
    public void onShowContentView(DailyBean dailyListBean) {
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.layout_daily_header, null);
        mAdapter.addHeaderView(headerView);
        Banner banner = headerView.findViewById(R.id.banner);
        TextView tvData = headerView.findViewById(R.id.tv_date);
        tvData.setText(dailyListBean.getDate());
        List<DailyBean.TopStoriesBean> top_stories = dailyListBean.getTop_stories();
        if (top_stories != null || top_stories.size() != 0) {
            List<String> imageUrls = new ArrayList<>();
            List<String> bannerTitles = new ArrayList<>();
            Observable.fromIterable(top_stories)
                    .subscribe(topStoriesBean -> {
                        imageUrls.add(topStoriesBean.getImage());
                        bannerTitles.add(topStoriesBean.getTitle());
                    });

            //设置Banner
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(mContext)
                            .load(path)
                            .apply(new RequestOptions().centerCrop().error(R.drawable.ic_launcher))
                            .into(imageView);
                }
            });
            banner.setImages(imageUrls);
            banner.setBannerTitles(bannerTitles);
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            banner.isAutoPlay(true);
            banner.start();
        } else {
            banner.setVisibility(View.GONE);
        }
        mDatas.clear();
        mDatas.addAll(dailyListBean.getStories());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {

    }

}