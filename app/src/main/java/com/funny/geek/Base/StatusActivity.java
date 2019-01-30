package com.funny.geek.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.ViewGroup;

import com.funny.geek.widget.EmptyCallback;
import com.funny.geek.widget.LoadingCallback;
import com.funny.geek.widget.NetErrorCallback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import butterknife.ButterKnife;

/**
 * Author: Funny
 * Time: 2019/1/18
 * Description: This is StatusActivity，处理各种状态页面
 */
public abstract class StatusActivity extends EventBusActivity implements IBaseView {

    private LoadService mLoadService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        //注册需要在setContentView之后，否则会报错
        mLoadService = LoadSir.getDefault().register(this);
        // TODO: 2019/1/30 待修改
        mLoadService.showSuccess();
    }

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
        Snackbar.make(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg, Snackbar.LENGTH_SHORT).show();
    }

    protected abstract int getLayoutId();


}
