package com.funny.geek.ui.zhihu;

import android.os.Bundle;

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

    }

    @Override
    public void onShowContent() {

    }

    @Override
    public void onRefresh() {

    }
}
