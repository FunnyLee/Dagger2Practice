package com.funny.geek.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.navi2.component.support.NaviFragment;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: Funny
 * Time: 2018/10/16
 * Description: This is AllBaseFragment
 */
public abstract class AllBaseFragment extends NaviFragment {

    protected Context mContext;
    private Unbinder mUnbinder;
    private View mView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = LayoutInflater.from(getContext()).inflate(getLayoutId(), null);
        mUnbinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
        initView(mView);
        initEvent();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    public LifecycleProvider<ActivityEvent> autoRxLifeCycle() {
        //返回Rxlifecycler的provider对象
        return NaviLifecycle.createActivityLifecycleProvider(this);
    }

    protected abstract int getLayoutId();

    protected void initData() {
    }

    protected void initView(View view) {
    }

    protected void initEvent() {
    }


}
