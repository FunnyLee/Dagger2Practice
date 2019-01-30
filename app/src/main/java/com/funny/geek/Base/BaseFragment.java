package com.funny.geek.base;

import android.content.Context;
import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

/**
 * Author: Funny
 * Time: 2018/10/16
 * Description: This is 所有Fragment的基类
 */
public abstract class BaseFragment extends StatusFragment {

    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    protected void initRootView(View view) {
        initMvpDagger();
        //因为这三个方法中，可能会用到presenter对象
        //所以需要控制一定要在initInject方法后执行
        initView(view);
        initData();
        initEvent();
    }

    public LifecycleProvider<FragmentEvent> autoRxLifeCycle() {
        //返回Rxlifecycler的provider对象
        return NaviLifecycle.createFragmentLifecycleProvider(this);
    }

    protected abstract int getLayoutId();

    protected void initMvpDagger() {
    }

    protected void initData() {
    }

    protected void initView(View view) {
    }

    protected void initEvent() {
    }


}
