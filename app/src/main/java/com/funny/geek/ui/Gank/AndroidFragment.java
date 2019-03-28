package com.funny.geek.ui.Gank;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.funny.geek.R;
import com.funny.geek.base.BaseMvpFragment;
import com.funny.geek.contract.gank.AndroidContract;
import com.funny.geek.model.bean.GankBean;
import com.funny.geek.model.bean.GankGirlBean;
import com.funny.geek.model.net.ImageHelper;
import com.funny.geek.presenter.gank.AndroidPresenter;
import com.funny.geek.ui.adpter.GankAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
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

    private List<GankBean.ResultsBean> mDatas = new ArrayList<>();
    private GankAdapter mAdapter;
    private Banner mBanner;

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

        mBanner = new Banner(mContext);
    }


    @Override
    protected void initData() {
        mPresenter.doGetTechList("Android", 10, 1);
    }

    @Override
    public void onShowContentView(GankBean gankBean) {
        mAdapter.setNewData(gankBean.results);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onShowGankGirl(List<GankGirlBean.ResultsBean> girlList) {
        List<String> imageUrls = new ArrayList<>();
        Observable.fromIterable(girlList).subscribe(resultsBean -> imageUrls.add(resultsBean.url));
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                ImageHelper.loadImage(context, (String) path,imageView);
            }
        });
        mBanner.setImages(imageUrls);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBanner.isAutoPlay(true);
        mBanner.start();
    }

    @Override
    public void onShowErrorView() {

    }
}
