package com.funny.geek.base;

/**
 * Author: Funny
 * Time: 2018/10/18
 * Description: This is RootFragment，主要处理各种状态页面
 */
public abstract class RootFragment<P extends IBasePresenter> extends BaseFragment<P> {

    @Override
    public void onStateError() {

    }

    @Override
    public void onStateEmpty() {

    }

    @Override
    public void onStateSuccess() {

    }

    @Override
    public void onStateLoading() {

    }
}
