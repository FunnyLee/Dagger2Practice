package com.funny.geek.base;

/**
 * Author: Funny
 * Time: 2019/1/18
 * Description: This is RootActivity，处理各种状态页面
 */
public abstract class RootActivity<P extends IBasePresenter> extends BaseActivity<P> {

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
