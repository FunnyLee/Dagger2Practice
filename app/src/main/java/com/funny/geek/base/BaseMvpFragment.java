package com.funny.geek.base;

import com.funny.geek.AppManager;
import com.funny.geek.di.component.DaggerFragmentComponent;
import com.funny.geek.di.component.FragmentComponent;
import com.funny.geek.di.module.FragmentModule;

import javax.inject.Inject;

/**
 * Author: Funny
 * Time: 2018/10/17
 * Description: This is MVP的BaseFragment
 */
public abstract class BaseMvpFragment<T extends IBasePresenter> extends BaseFragment {

    @Inject
    protected T mPresenter;

    protected FragmentComponent getFragmentComponent() {
        FragmentComponent fragmentComponent = DaggerFragmentComponent.builder()
                //表示dependencies
                .appComponent(AppManager.getAppComponent())
                //表示modules
                .fragmentModule(new FragmentModule(this))
                .build();
        return fragmentComponent;
    }

    @Override
    protected void initMvpDagger() {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected abstract void initInject();
}
