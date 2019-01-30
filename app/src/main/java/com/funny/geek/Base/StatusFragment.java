package com.funny.geek.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.funny.geek.widget.EmptyCallback;
import com.funny.geek.widget.LoadingCallback;
import com.funny.geek.widget.NetErrorCallback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: Funny
 * Time: 2018/10/18
 * Description: This is StatusFragment，主要处理各种状态页面
 */
public abstract class StatusFragment extends EventBusFragment implements IBaseView {

    private LoadService mLoadService;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(getLayoutId(), null);
        mUnbinder = ButterKnife.bind(this, view);
        mLoadService = LoadSir.getDefault().register(view);
        mLoadService.showSuccess();
        initRootView(view);
        return mLoadService.getLoadLayout();
    }

    protected abstract int getLayoutId();

    protected abstract void initRootView(View view);

    @Override
    public void onStatusNetError() {
        mLoadService.showCallback(NetErrorCallback.class);
    }

    @Override
    public void onStatusEmpty() {
        mLoadService.showCallback(EmptyCallback.class);
    }

    @Override
    public void onStatusSuccess() {
        mLoadService.showSuccess();
    }

    @Override
    public void onStatusLoading() {
        mLoadService.showCallback(LoadingCallback.class);
    }

    @Override
    public void onShowErrorMsg(String msg) {
        Snackbar.make(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0), msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
