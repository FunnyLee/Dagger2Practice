package com.funny.geek.ui.option;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.funny.geek.R;
import com.funny.geek.base.BindEventBus;
import com.funny.geek.base.RootFragment;
import com.funny.geek.contract.option.FavoriteContract;
import com.funny.geek.model.bean.RealmFavoriteBean;
import com.funny.geek.model.event.DeleteFavoriteEvent;
import com.funny.geek.presenter.option.FavoritePresenter;
import com.funny.geek.ui.adpter.FavoriteAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.realm.RealmResults;

/**
 * Author: Funny
 * Time: 2019/1/28
 * Description: This is 收藏Fragment
 */
@BindEventBus
public class FavoriteFragment extends RootFragment<FavoritePresenter> implements FavoriteContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<RealmFavoriteBean> mDatas = new ArrayList<>();
    private FavoriteAdapter mAdapter;

    public static FavoriteFragment newInstance() {
        Bundle args = new Bundle();
        FavoriteFragment fragment = new FavoriteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_favorite;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initView(View view) {
        mAdapter = new FavoriteAdapter(getActivity(), mDatas);
        mAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return 1;
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.doLoadData();
    }

    @Override
    public void onShowContentView(RealmResults<RealmFavoriteBean> results) {
        mDatas.clear();
        mDatas.addAll(results);
        mAdapter.setNewData(mDatas);
    }

    @SuppressLint("CheckResult")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeleteFavorite(DeleteFavoriteEvent event) {
        initData();
    }
}
