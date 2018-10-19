package com.funny.geek.ui.zhihu;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.funny.geek.R;
import com.funny.geek.base.RootFragment;
import com.funny.geek.contract.zhihu.DailyContract;
import com.funny.geek.presenter.zhihu.DailyPresenter;

/**
 * Author: Funny
 * Time: 2018/10/17
 * Description: This is DailyFragment
 */
public class DailyFragment extends RootFragment<DailyPresenter> implements DailyContract.View {

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

    }

    @Override
    protected void initData() {
        mPresenter.doLoadData();
    }

    @Override
    public void onShowContent() {
        Toast.makeText(mContext, "bbcc", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {

    }
}
