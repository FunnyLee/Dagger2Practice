package com.funny.geek.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.funny.geek.R;
import com.funny.geek.util.Constants;
import com.funny.geek.widget.EmptyCallback;
import com.funny.geek.widget.LoadingCallback;
import com.funny.geek.widget.NetErrorCallback;
import com.jakewharton.rxbinding2.view.RxView;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.loadsir.core.Transport;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: Funny
 * Time: 2018/10/18
 * Description: This is StatusFragment，主要处理各种状态页面
 */
public abstract class StatusFragment extends EventBusFragment implements IBaseView {

    protected LoadService mLoadService;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(getLayoutId(), null);
        mUnbinder = ButterKnife.bind(this, view);
        mLoadService = LoadSir.getDefault().register(view);
        //设置重试的点击事件
        mLoadService.setCallBack(NetErrorCallback.class, new Transport() {
            @SuppressLint("CheckResult")
            @Override
            public void order(Context context, View view) {
                LinearLayout errorLl = view.findViewById(R.id.error_ll);
                RxView.clicks(errorLl)
                        .throttleFirst(Constants.CLICK_INTERVAL, TimeUnit.SECONDS)
                        .subscribe(o -> doReload());
            }
        });
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
    public void toastErrorMsg(String msg) {
        Snackbar.make(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0), msg, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 重新加载的方法
     * 如果使用了多状态页面，必须重写此方法，实现重新加载逻辑
     */
    protected void doReload() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
