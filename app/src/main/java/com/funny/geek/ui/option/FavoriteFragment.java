package com.funny.geek.ui.option;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.funny.geek.R;
import com.funny.geek.base.RootFragment;
import com.funny.geek.contract.option.FavoriteContract;
import com.funny.geek.model.bean.RealmFavoriteBean;
import com.funny.geek.presenter.option.FavoritePresenter;
import com.funny.geek.ui.adpter.FavoriteAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: Funny
 * Time: 2019/1/28
 * Description: This is 收藏Fragment
 */
public class FavoriteFragment extends RootFragment<FavoritePresenter> implements FavoriteContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<RealmFavoriteBean> mDatas = new ArrayList<>();

    public static FavoriteFragment newInstance() {
        Bundle args = new Bundle();
        FavoriteFragment fragment = new FavoriteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initView(View view) {
        FavoriteAdapter adapter = new FavoriteAdapter(R.layout.item_favorite_view, mDatas);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        mPresenter.doLoadData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_favorite;
    }

    @Override
    public void onShowContentView() {

    }
}
